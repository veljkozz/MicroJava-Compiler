package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	public int mainPc;
	
	Obj currDesignatorName = null;
	Obj currClass = null;
	Obj currMeth = null;
	int ClassMethCnt = 0;
	
	Obj currDes = null;
	Obj currArr = null;
	Obj currElem = null;
	
	boolean inClass = false;
	static class VMT
	{
		Obj classObj;
		int vmt_addr = -1;
		ArrayList<Obj> Methods = new ArrayList<Obj>();
		public VMT(Obj classObj)
		{
			this.classObj = classObj;
		}
	}
	// Class - vfptr table creation
	
	// Virtual pointer table 
	HashMap<String, VMT> vmtMap = new HashMap<String, VMT>();
	public void visit(ClassName className)
	{
		inClass = true;
		currClass = className.obj;
		// Create a virtual function table for this class
		vmtMap.put(className.getName(), new VMT(className.obj));
		
		Struct parent = currClass.getType().getElemType();
		if(parent != null && parent != Tab.noType)
		{
			// Copy VMT from parent
			vmtMap.put(currClass.getName(), new VMT(currClass));
			VMT vmt = vmtMap.get(currClass.getName());
			for(Obj obj: parent.getMembers())
			{
				if(obj.getKind() == Obj.Meth)
				{
					vmt.Methods.add(obj);
				}
				
			}
		}
	}
	
	int nextVmt = Code.dataSize; 
	
	void createVMT()
	{
		// Create VMTs in static memory
		int mstatic = Code.dataSize;
		for(VMT vmt : vmtMap.values())
		{
			//vmt.vmt_addr = mstatic;
			mstatic = vmt.vmt_addr;
			for(Obj meth: vmt.Methods)
			{
				for(int i=0 ;i < meth.getName().length(); i++)
				{
					Code.loadConst(meth.getName().charAt(i));
					Code.put(Code.putstatic); Code.put2(mstatic);
					mstatic++;
				}
				Code.loadConst(-1);
				Code.put(Code.putstatic); Code.put2(mstatic); mstatic++;
				Code.loadConst(meth.getAdr());
				Code.put(Code.putstatic); Code.put2(mstatic); mstatic++;
			}
			Code.loadConst(-2); Code.put(Code.putstatic); Code.put2(mstatic); mstatic++;
		}
		Code.dataSize = nextVmt+1;
	}
	
	void fillVMTMap()
	{
		if(vmtMap.isEmpty()) nextVmt = Code.dataSize;
		
		if(vmtMap.get(currClass.getName()) != null)
			vmtMap.put(currClass.getName(), new VMT(currClass));
		Struct currS = currClass.getType();
		VMT vmt = vmtMap.get(currClass.getName());
		vmt.vmt_addr = nextVmt;
		
		for(Obj obj: currS.getMembers())
		{
			if(obj.getKind() == Obj.Meth)
			{
				// find if method already exists
				boolean found = false;
				for(int i=0; i<vmt.Methods.size(); i++)
				{
					if(vmt.Methods.get(i).getName().equals(obj.getName()))
					{
						vmt.Methods.set(i, obj);
						found = true;
						break;
					}
				}
				if(!found) vmt.Methods.add(obj);
				
				nextVmt += obj.getName().length() + 1;
			}
		}
		// pointers to methods + -2 terminator
		nextVmt += vmt.Methods.size() + 1;
	}
	
	public void visit(Class_NoParent_Declaration classDecl)
	{
		currClass = classDecl.getClassName().obj;
		fillVMTMap();
		inClass = false;
		currClass = null;
	}
	public void visit(Class_Parent_Declaration classDecl)
	{
		currClass = classDecl.getClassName().obj;
		fillVMTMap();
		currClass = null;
		inClass = false;
	}
	
	public void visit(ConstructorName constr)
	{
		Obj obj = currClass.getType().getMembersTable().searchKey(constr.getName());
		obj.setAdr(Code.pc);
		currMeth = obj;
		
		// local variables
		SyntaxNode methNode = constr.getParent();
		
		VarCounter varCounter = new VarCounter();
		methNode.traverseTopDown(varCounter);
		// Generate the entry
		Code.put(Code.enter);
		Code.put(1);
		Code.put(varCounter.count + 1);
	}
	public void visit(Constructor constr)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public Obj findClassByStruct(Struct s)
	{
		for(VMT vmt: vmtMap.values())
		{
			if(vmt.classObj.getType() == s)
			{
				return vmt.classObj;
			}
		}
		return null;
	}
	
	public void visit(Super_Call_Stmt supercall)
	{
		Struct baseClass = currClass.getType().getElemType();
		Obj baseClassObj = findClassByStruct(baseClass);
		Obj vfptr;
		int fpcounter;
		String name;
		if(currMeth.getName().equals(currClass.getName()))
		{
			// super constructor call
			fpcounter = 0;
			name = baseClassObj.getName();
		}
		else {
			name = currMeth.getName();
		}
		
		Code.put(Code.dup);
		Code.put(Code.dup);
		vfptr = currClass.getType().getMembersTable().searchKey("_vfptr");
		Code.load(vfptr);
		Code.put(Code.invokevirtual);
		for(int i=0; i<name.length(); i++)
		{
			int val = name.charAt(i);
			Code.put4(val);
		}
		Code.put4(-1);
		
	}
	
	public void visit(MethodTypeName mtypeName)
	{
		Obj methObj = mtypeName.obj;
		currMeth = methObj;
		String name = methObj.getName();
		if(currClass == null)
		{
			// global method
			mtypeName.obj.setAdr(Code.pc);
			if(mtypeName.getMethodName().equalsIgnoreCase("main")) {
				mainPc = Code.pc;
				createVMT();
				
			}
		}
		else {
			// Class method
			methObj.setAdr(Code.pc);
		}
		
		// Collect arguments and local variables
		SyntaxNode methNode = mtypeName.getParent();
		
		VarCounter varCounter = new VarCounter();
		methNode.traverseTopDown(varCounter);
		FormParamCounter fpcounter = new FormParamCounter();
		methNode.traverseTopDown(fpcounter);
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpcounter.count + (currClass == null ? 0 : 1));
		Code.put(fpcounter.count + varCounter.count + (currClass == null ? 0 : 1));
	}
	public void visit(Method_Decl methDecl)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(PrintStmt_Num stmt)
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
		Code.loadConst(stmt.getN2());
		currDes = null;
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
		currDes = null;
	}
	public void visit(ReadStmt readstmt) { 
		// NOT IMPLEMENTED YET
		if(currDes.getType().equals(Tab.charType)) {
			Code.put(Code.bread);
		}
		else Code.put(Code.read);
		Code.store(currDes);
		currDes = null; 
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
		if(designator.obj.getKind() == Obj.Meth) currMeth = designator.obj;
	}
	
	public void visit(Designator_Name designator) { 
		currDesignatorName = designator.obj;
		if(designator.obj.getKind() != Obj.Meth) currDes = designator.obj;
		if(currDesignatorName.getKind() == Obj.Fld && currClass != null)
		{
			// this kada se pristupa sopstvenom polju unutar klase
			Code.load(currMeth.getLocalSymbols().iterator().next());
		}
		else if(designator.obj.getType().getKind() == Struct.Array)
			currArr = designator.obj;
	}
	
	public void visit(DesignatorList_Field desField)
	{
		if(!desField.getDesignatorList().getClass().equals(EmptyDesignatorList.class))
		{
			Code.load(desField.getDesignatorList().obj);
			currDesignatorName = desField.getDesignatorList().obj;
		}
		else {
			Code.load(currDesignatorName);
		}
		
		
		if(desField.obj.getKind() == Obj.Meth) 
		{
				ClassMethCnt++;
				// Dup for putting "this"
				//Code.put(Code.dup);
		}
		else {
			currDes = desField.obj;
			if(currDes.getType().getKind() == Struct.Array)
				currArr = currDes;
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

		// HACKY WITH DUP_X1 
		// Reverse order of index and adr of arr
		Code.put(Code.dup_x1);
		Code.put(Code.pop);		
		// Get current elem and its index
		currElem = desField.obj;
		currDes = desField.obj;
	}
	
	void call_fun(Obj fobj)
	{
		int offs = fobj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offs);
		
		
	}
	
	public void visit(FuncCall fcall) {
		Obj fobj = fcall.getDesignator().obj;
		String name = fobj.getName();
		if(currDes != null) 
		{
			Obj vfptr = currDes.getType().getMembersTable().searchKey("_vfptr");
			
			Code.load(vfptr);
			Code.put(Code.invokevirtual);
			for(int i=0; i<name.length(); i++)
			{
				int val = name.charAt(i);
				Code.put4(val);
			}
			Code.put4(-1);
			
			currMeth = null;
			currDes = null;
		}
		else if(currClass != null)
		{
			Obj vfptr = currClass.getType().getMembersTable().searchKey("_vfptr");
			
			Obj fakethis = new Obj(Obj.Var, "this", currClass.getType(), 0, 1);
			Code.load(fakethis); 
			Code.put(Code.dup_x1);
			Code.load(vfptr);
			Code.put(Code.invokevirtual);
			for(int i=0; i<name.length(); i++)
			{
				int val = name.charAt(i);
				Code.put4(val);
			}
			Code.put4(-1);
			
			currMeth = null;
			currDes = null;
		}
		else {
			call_fun(fobj);
		}
		
		if(!fobj.getType().equals(Tab.noType))
		{
			Code.put(Code.pop);
		}
	}
	
	// Factor
	public void visit(Factor_FunCall fcall)
	{
		Obj fobj = fcall.getDesignator().obj;
		
		if(currDes != null) 
		{
			String name = fobj.getName();
			Obj vfptr = currDes.getType().getMembersTable().searchKey("_vfptr");
			
			Code.load(vfptr);
			Code.put(Code.invokevirtual);
			for(int i=0; i<name.length(); i++)
			{
				int val = name.charAt(i);
				Code.put4(val);
			}
			Code.put4(-1);
			
			currMeth = null;
		}
		else call_fun(fobj);
		
	}
	
	public void visit(Expr_Neg negExpr)
	{
		Code.loadConst(-1);
		Code.put(Code.mul);
	}
	
	public void visit(Factor_ConstVal fconst)
	{
		Obj con = new Obj(Obj.Con, "$", fconst.struct);
		con.setLevel(0);
		con.setAdr(fconst.getVal().simpletype.num);
		
		Code.load(con);
	}
	
	public void visit(Factor_New fnew)
	{
		Struct classType = fnew.struct;
		String className = fnew.getType().getTypeName();
		
		Code.put(Code.new_);
		Code.put2(classType.getNumberOfFields()*4);
		// load vfptr val
		Code.put(Code.dup);
		Code.loadConst(vmtMap.get(className).vmt_addr);
		Code.put(Code.putfield); 
		Code.put2(0);
		
		Obj constructor;
		if((constructor = classType.getMembersTable().searchKey(className)) != null)
		{
			// Class has constructor
			Obj vfptr = classType.getMembersTable().searchKey("_vfptr");
			
			//Code.load(assign.getDesignator().obj);
			Code.put(Code.dup); Code.put(Code.dup);
			Code.put(Code.getfield); 
			Code.put2(0);
			Code.put(Code.invokevirtual);
			for(int i=0; i<className.length(); i++)
			{
				int val = className.charAt(i);
				Code.put4(val);
			}
			Code.put4(-1);
		}
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
		currDes = null;
	}
	public void visit(ReturnStmt returnStmt)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
		currDes = null;
	}
	
	public void visit(Statement_Designator designStmt) { currDes = null; }
	
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
	
	// ADDING "THIS" ARG
	public void visit(NoPars nopars)
	{
		if(currDes != null && currMeth != null)
		{
			// add "this" as first parameter
			//Code.load(currClass);
			Code.put(Code.dup);
		}
	}
	
	public void visit(ActualPars_Expr parsExpr)
	{
		if(currDes != null && currMeth != null)
		{
			// add "this" as first parameter
			
			//Code.load(currClass);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);	
			Code.put(Code.dup_x1);
		}
	}
	public void visit(ActualPars_List actPars)
	{
		if(currDes != null)
		{
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
		}
		
	}
}
