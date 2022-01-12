package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	public int mainPc;
	
	Obj currDesignatorName = null;
	
	// Class - vfptr table creation
	public void visit(ClassName className)
	{
		// Create a virtual function table
		
	}
	
	public void visit(Factor_ConstVal fconst)
	{
		Obj con = new Obj(Obj.Con, "$", fconst.struct);
		con.setLevel(0);
		con.setAdr(fconst.getVal().simpletype.num);
		
		Code.load(con);
	}
	
	public void visit(MethodTypeName mtypeName)
	{
		mtypeName.obj.setAdr(Code.pc);
		if(mtypeName.getMethodName().equalsIgnoreCase("main"))
		{
			mainPc = Code.pc;
		}
		
		// Collect arguments and local variables
		SyntaxNode methNode = mtypeName.getParent();
		
		VarCounter varCounter = new VarCounter();
		methNode.traverseTopDown(varCounter);
		FormParamCounter fpcounter = new FormParamCounter();
		methNode.traverseTopDown(fpcounter);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpcounter.count);
		Code.put(fpcounter.count + varCounter.count);
	}
	public void visit(Method_Decl methDecl)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(PrintStmt_ stmt)
	{
		if(stmt.getExpr().struct.equals(Tab.intType))
		{
			Code.loadConst(4);
			Code.put(Code.print);
		}
		else if(stmt.getExpr().struct.equals(Tab.charType))
		{
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
		else if(stmt.getExpr().struct.equals(SemanticPass.boolType))
		{
			Code.loadConst(1);
			Code.put(Code.print);
		}
		else
		{
			assert(false);
		}
	}
	
	public void visit(Assignment assignment)
	{
		Obj obj = assignment.getDesignator().obj;
		//System.out.println("ASSIGNMENT TO " + obj.getName() + " of kind " + obj.getKind() + " and addr " + obj.getAdr());
		Code.store(obj);
	}
	
	
	// Designator 
	public void visit(Designator designator)
	{
		SyntaxNode parentNode = designator.getParent();
		Class<?> parentClass = parentNode.getClass();
		// If should be loaded to expr stack
		if(parentClass.equals(Factor_Designator.class))
			Code.load(designator.obj);	
		currDesignatorName = null;
	}
	
	public void visit(Designator_Name designator) { currDesignatorName = designator.obj; }
	public void visit(DesignatorName_Arr designator) 
	{ 
		currDesignatorName = designator.obj; 
		Code.load(designator.obj); 
		// HACKY WITH DUP_X1 
		// Copy this below the stakc and pop it
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
	}
	
	public void visit(DesignatorList_Field desField)
	{
		if(!desField.getDesignatorList().getClass().equals(EmptyDesignatorList.class))
		{
			Code.load(desField.getDesignatorList().obj);
		}
		else {
			Code.load(currDesignatorName);
		}
	}
	
	public void visit(DesignatorList_Arr desField)
	{
		if(!desField.getDesignatorList().getClass().equals(EmptyDesignatorList.class))
		{
			Code.load(desField.getDesignatorList().obj);
		}
		else {
			Code.load(currDesignatorName);
		}
	}
	
	void call_fun(Obj fobj)
	{
		int offs = fobj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offs);
	}
	
	public void visit(FuncCall fcall) {
		Obj fobj = fcall.getDesignator().obj;
		call_fun(fobj);
	}
	
	// Factor
	public void visit(Factor_FunCall fcall)
	{
		Obj fobj = fcall.getDesignator().obj;
		call_fun(fobj);
		if(!fobj.getType().equals(Tab.noType))
		{
			Code.put(Code.pop);
		}
	}
	
	public void visit(Factor_New fnew)
	{
		Code.put(Code.new_);
		Code.put2(fnew.getType().struct.getNumberOfFields()*4);
	}
	public void visit(Factor_New_Arr fnewArr)
	{
		Code.put(Code.newarray);
		Struct s = fnewArr.getType().struct;
		if(s.getKind() == Struct.Char || s.getKind() == Struct.Bool)
		{
			Code.put(0);
		}
		else Code.put(1);
		//Code.put(fnewArr.get)
	}
	
	public void visit(ReturnExprStmt returnExor)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	public void visit(ReturnStmt returnStmt)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(Add op)
	{
		op.obj = new Obj(Obj.NO_VALUE, "+", Tab.noType, Code.add, 0);
	}
	public void visit(Sub op)
	{
		op.obj = new Obj(Obj.NO_VALUE, "-", Tab.noType, Code.sub, 0);
	}	
	public void visit(Mul op)
	{
		op.obj = new Obj(Obj.NO_VALUE, "*", Tab.noType, Code.mul, 0);
	}
	public void visit(Div op)
	{
		op.obj = new Obj(Obj.NO_VALUE, "/", Tab.noType, Code.div, 0);
	}
	public void visit(Mod op)
	{
		op.obj = new Obj(Obj.NO_VALUE, "%", Tab.noType, Code.rem, 0);
	}
	public void visit(TermList_AddOp addOp) { Code.put(addOp.getAddOp().obj.getAdr()); }
	public void visit(Term_MulOp mulOp){ Code.put(mulOp.getMulOp().obj.getAdr());}
}
