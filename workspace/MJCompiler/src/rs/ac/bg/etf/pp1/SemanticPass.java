package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {

	boolean errorDetected = false;
	int globalVarCnt = 0;
	Struct currVarType = null;
	Obj currMethod = null;
	Obj currClassObj = null;
	Struct currClassStruct = null;
	
	boolean isEqCond = false;
	Obj currDesignator = null;
	
	Logger log = Logger.getLogger(getClass());
	
	// Moji dodati tipovi
	public static final Struct boolType = new Struct(Struct.Bool);
	public SemanticPass()
	{
		Tab.insert(Obj.Type, "bool",  boolType);
	}
	
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
    	StringBuilder msg = new StringBuilder(message); 
    	int line = (info == null) ? 0 : info.getLine();
    	if (line != 0)
            msg.append (" on line ").append(line);
        log.error(msg.toString());
    }
	
	public void report_info(String message, SyntaxNode info) {
    	StringBuilder msg = new StringBuilder(message); 
    	int line = (info == null) ? 0 : info.getLine();
    	if (line != 0)
            msg.append (" on line ").append(line);
        log.info(msg.toString());
    }
	
	public void visit(ProgName progName)
	{
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}
	
	public void visit(Program program)
	{
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
		System.out.println("IME PROGRAMA: " + program.getProgName().getProgName());
	}
	
	// Var Declaration
	public void visit(Type type)
	{
		Obj typeNode = Tab.find(type.getTypeName());
		if(typeNode == Tab.noObj)
		{
			report_error("Error: undefined type " + type.getTypeName(), type);
			type.struct = Tab.noType;
		}
		else
		{
			if(typeNode.getKind() == Obj.Type)
			{
				type.struct = typeNode.getType();
				currVarType = type.struct;
			}
			else
			{
				report_error("Error: name " + type.getTypeName() + " does not represent a type", type);
				type.struct = Tab.noType;
			}
		}
	}
	
	public void visit(Var_ var)
	{
		globalVarCnt++;
		Tab.insert(Obj.Var, var.getVarName(), currVarType);
	}	
	public void visit(Var_Array var_arr) 
	{ 
		globalVarCnt++;
		Struct s = new Struct(Struct.Array);
		s.setElementType(currVarType);
		Tab.insert(Obj.Var, var_arr.getVarName(), s);
	}
	
	// Const Declaration
	public void visit(ConstDecl_Single decl)
	{
		globalVarCnt++;
		Val val = decl.getVal();
		// Since all consts are of a simple type(int,char,bool)
		// there is no need to check for polimorphism
		if(val.simpletype.s.equals(currVarType))
		{
			Obj obj = Tab.insert(Obj.Con, decl.getVarName(), currVarType);
			switch(val.simpletype.s.getKind())
			{
				case Struct.Int: obj.setAdr(val.simpletype.num); break;
				case Struct.Char: obj.setAdr(val.simpletype.character); break;
				case Struct.Bool: obj.setAdr(val.simpletype.num); break;
			}
		}
		else
		{
			report_error("Error: incompatible data types"  , decl);
		}
	}
	
	public void visit(ConstDecl_List decl)
	{
		globalVarCnt++;
		Tab.insert(Obj.Var, decl.getVarName(), currVarType);
	}
	
	public void visit(Val_Num num)
	{
		num.simpletype = new MJParser.SimpleType(Tab.intType); 
		num.simpletype.num = num.getN1();
	}
	public void visit(Val_Char vchar)
	{
		vchar.simpletype = new MJParser.SimpleType(Tab.charType); 
		vchar.simpletype.character = vchar.getC1();
	}
	public void visit(Val_Bool bool)
	{
		bool.simpletype = new MJParser.SimpleType(boolType); 
		bool.simpletype.num = bool.getB1() ? 1 : 0;
	}
	
	// Class Declaration
	public void visit(ClassName className)
	{
		currClassStruct = new Struct(Struct.Class);
		currClassObj = Tab.insert(Obj.Type, className.getName(), currClassStruct);
		className.obj = currClassObj;
		Tab.openScope();
	}
	
	public void visit(Extends_Parent parent)
	{
		// COLLECT PARENT HERE
		if((parent.obj = Tab.find(parent.getParentName())) == Tab.noObj) report_error("Error: parent class does not exist", parent);
	}
	
	public void visit(Class_NoParent_Declaration classNoParent)
	{
		Tab.chainLocalSymbols(currClassStruct);
		Tab.closeScope();
		
		currClassObj = null;
		currClassStruct = null;
	}
	
	public void visit(Class_Parent_Declaration classParent)
	{
		// ADD SUPER AND COPYING MEMBERS FROM PARENT
		Obj parent = classParent.getExtends().obj;
		if(parent != Tab.noObj) 
			System.out.println("Class " + classParent.getClassName().getName() + " has parent class " + parent.getName());
		Tab.chainLocalSymbols(currClassStruct);
		Tab.closeScope();
		
		currClassObj = null;
		currClassStruct = null;
	}
	
	public void visit(Class_Var var)
	{
		Tab.insert(Obj.Var, var.getVarName(), currVarType);
	}
	
	public void visit(Class_Var_Arr var)
	{
		Struct s = new Struct(Struct.Array);
		s.setElementType(currVarType);
		Tab.insert(Obj.Var, var.getVarName(), s);
	}
	
	public void visit(Method_Type type)
	{
		type.struct = type.getType().struct;
	}
	
	public void visit(VoidType type)
	{
		type.struct = Tab.noType;
	}
	
	public void visit(MethodTypeName methodTypeName)
	{
		currMethod = Tab.insert(Obj.Meth, methodTypeName.getMethodName(), methodTypeName.getMethodType().struct);
		methodTypeName.obj = currMethod;
		Tab.openScope();
	}
	
	public void visit(MethodDecl method)
	{
		Tab.chainLocalSymbols(currMethod);
		Tab.closeScope();
		
		currMethod = null;
	}
	
	public void visit(ReturnStmt ret)
	{
		if(currMethod.getType().equals(Tab.noType))
			return;
		else 
			report_error("Error: function " + currMethod.getName() + " must return type " + currMethod.getType().getKind(), ret);
	}
	
	public void visit(ReturnExprStmt ret)
	{
	}
	
	// Condfact
	public void visit(CondFact_Relop fact)
	{
		//MJParser.
		if(!fact.getExpr().struct.compatibleWith(fact.getExpr1().struct))
		{
			report_error("Error: expressions not compatible", fact);
		}
		else
			fact.struct = fact.getExpr().struct;
		//System.out.println("RelOp: " + fact.childrenAccept(null));
	}
	
	public void visit(RelOp_EQ relop)
	{
		isEqCond = true;
	}
	
	public void visit(RelOp_NEQ relop)
	{
		isEqCond = true;
	}
	
	// Expr
	public void visit(Expr_Pos expr)
	{
		expr.struct = expr.getTermList().struct;
	}
	
	public void visit(Expr_Neg expr)
	{
		expr.struct = expr.getTermList().struct;
	}
	
	// Term
	
	public void visit(TermList_Term tlist)
	{
		tlist.struct = tlist.getTerm().struct;
	}
	
	public void visit(TermList_AddOp tlist)
	{
		tlist.struct = tlist.getTerm().struct;
	}
	
	public void visit(Term_MulOp tmul)
	{
		tmul.struct = tmul.getFactor().struct;
		if(!tmul.getFactor().struct.equals(Tab.intType) || tmul.getTerm().struct.equals(Tab.intType))
			report_error("Error: expression is not of integer type", tmul);
	}
	
	public void visit(Term_Fact tfact)
	{
		tfact.struct = tfact.getFactor().struct;
	}
	
	// Factor
	
	public void visit(Factor_Designator fdes)
	{
		fdes.struct = fdes.getDesignator().obj.getType();
	}
	
	public void visit(Factor_FunCall fcall)
	{
		Obj obj = fcall.getDesignator().obj;
		if(!(obj.getKind() == Obj.Meth))
			report_error("Error: " + obj.getName() + " is not a method", fcall);
		fcall.struct = obj.getType();
	}
	
	public void visit(Factor_ConstVal fval)
	{
		fval.struct = fval.getVal().simpletype.s;
	}
	
	public void visit(Factor_New fnew)
	{
		fnew.struct = fnew.getType().struct;
		if(fnew.struct.getKind() != Struct.None && fnew.struct.getKind() != Struct.Class)
			report_error("Error: type " + fnew.getType().getTypeName() + " not a class", fnew);
	}
	
	public void visit(Factor_New_Arr fnew)
	{
		fnew.struct = fnew.getType().struct;
		if(fnew.struct.getKind() != Struct.None && fnew.struct.getKind() != Struct.Class) {
			report_error("Error: type " + fnew.getType().getTypeName() + " not a class", fnew);
			return;
		}
		if(!fnew.getExpr().struct.equals(Tab.intType))
		{
			report_error("Error: expression must have int type", fnew);
		}
	}
	
	public void visit(Factor_ParenExpr fexpr)
	{
		// idk sta da radim ovde yet
	}
	
	public void visit(Designator designator)
	{
		designator.obj = currDesignator;
		currDesignator = null;
	}
	public void visit(DesignatorName designatorName)
	{
		currDesignator = Tab.find(designatorName.getName());
		designatorName.obj = currDesignator;
		if(currDesignator == Tab.noObj)
		{
			report_error("Error: no type with name " + designatorName.getName() + " defined", designatorName);
		}
	}
	
	public void visit(DesignatorList_Arr designatorList)
	{
		if(!designatorList.getExpr().struct.equals(Tab.intType))
			report_error("Error: expression must have int type", designatorList);
		if(!(currDesignator.getType().getKind() == Struct.Array))
			report_error("Error: variable not an array", designatorList);
	}
	
	public void visit(DesignatorList_Field designatorList)
	{
		String name = designatorList.getName();
		Struct currS = currDesignator.getType();
		if(currS.getKind() == Struct.Class && currS.getMembersTable().searchKey(name) != null)
			currDesignator = currS.getMembersTable().searchKey(name);
		else
			report_error("Error: cannot find field " + designatorList.getName(), designatorList);
	}
}
