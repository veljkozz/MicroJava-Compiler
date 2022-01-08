package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class SemanticPass extends VisitorAdaptor {

	boolean errorDetected = false;
	int globalVarCnt = 0;
	Struct currVarType = null;
	
	Obj currMethod = null;
	Obj currClassObj = null;
	Struct currClassStruct = null;
	
	Set<String> classNames = new HashSet<>();
	
	boolean inWhileLoop = false;
	boolean isEqCond = false;
	boolean isClassMeth = false;
	boolean hasReturn = false;
	Obj currDesignator = null;
	
	ArrayList<Struct> currActualParams = new ArrayList<Struct>();
	
	Logger log = Logger.getLogger(getClass());
	
	// Static types
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
	void addConst(Val val, String name) {
		// Since all consts are of a simple type(int,char,bool)
		// there is no need to check for polimorphism
		if(val.simpletype.s.equals(currVarType))
		{
			Obj obj = Tab.insert(Obj.Con, name, currVarType);
			switch(val.simpletype.s.getKind())
			{
				case Struct.Int: obj.setAdr(val.simpletype.num); break;
				case Struct.Char: obj.setAdr(val.simpletype.character); break;
				case Struct.Bool: obj.setAdr(val.simpletype.num); break;
			}
		}
		else
		{
			report_error("Error: incompatible data types"  , val);
		}
	}
	public void visit(ConstDecl_Single decl)
	{
		globalVarCnt++;
		addConst(decl.getVal(), decl.getVarName());
	}
	
	public void visit(ConstDecl_List decl)
	{
		globalVarCnt++;
		Val val = decl.getVal();
		addConst(val, decl.getVarName());
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
		classNames.add(className.getName());
		Tab.openScope();
	}
	
	public void visit(Extends_Parent parent)
	{
		// COLLECT PARENT HERE
		if((parent.obj = Tab.find(parent.getParentName())) == Tab.noObj) 
			report_error("Error: undefined parent class", parent);
		else if(!classNames.contains(parent.obj.getName()))
			report_error("Error: parent is not class type", parent);
		else if(parent.obj.getName().equals(currClassObj.getName()))
			report_error("Error: class can't extend itself...you think I'm dumb? >:(", parent);
		currClassStruct.setElementType(parent.obj.getType());
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
	
	public void visit(ConstructorName constructorName)
	{
		if(!constructorName.getName().equals(currClassObj.getName()))
				report_error("Error: constructor name different than class name", constructorName);
		else {
			currMethod = Tab.insert(Obj.Meth, constructorName.getName(), Tab.noType);
			Tab.openScope();
		}
	}
	
	public void visit(Constructor constructor)
	{
		Tab.chainLocalSymbols(currMethod);
		Tab.closeScope();
		currMethod = null;
	}
	
	// Record Declaration
	public void visit(RecordDeclaration record)
	{
		Tab.chainLocalSymbols(currClassStruct);
		Tab.closeScope();
	}
	public void visit(RecordName recordName)
	{
		currClassStruct = new Struct(Struct.Class);
		currClassObj = Tab.insert(Obj.Type, recordName.getName(), currClassStruct);
		//recordName.obj = currClassObj;
		Tab.openScope();
	}
	
	// Methods
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
		hasReturn = false;
		Tab.openScope();
		
		// Add "this" if its in a class
		if(currClassObj != null)
		{
			Obj obj = Tab.insert(Obj.Var, "this", currClassStruct);
			obj.setFpPos(currMethod.getLevel());
		}
		
	}
	 
	public void visit(Form_Param formParam)
	{
		Struct type = formParam.getType().struct;
		String paramName = formParam.getVarname();
		currMethod.setLevel(currMethod.getLevel() + 1);
		Obj obj = Tab.insert(Obj.Var, paramName, type);
		obj.setFpPos(currMethod.getLevel());
	}
	
	public void visit(Form_Param_Arr formParam)
	{
		Struct type = new Struct(Struct.Array);
		type.setElementType(formParam.getType().struct);
		currMethod.setLevel(currMethod.getLevel() + 1);
		Obj obj = Tab.insert(Obj.Var, formParam.getVarname(), type);
		obj.setFpPos(currMethod.getLevel());
	}
	
	// ADD PARAMS
	public void visit(Method_Decl method)
	{
		if(!currMethod.getType().equals(Tab.noType) && !hasReturn)
			report_error("Error: function without return statement", method);
		Tab.chainLocalSymbols(currMethod);
		Tab.closeScope();
		hasReturn = false;
		currMethod = null;
	}
	
	// SingleStatement
	public void visit(ReturnStmt ret)
	{
		if(currMethod == null)
			report_error("Error: return statement not inside function", ret);
		else if(currMethod.getType().equals(Tab.noType))
			return;
		else 
			report_error("Error: invalid return type", ret);
	}
	
	public void visit(ReturnExprStmt ret)
	{
		hasReturn = true;
		if(currMethod == null)
			report_error("Error: return statement not inside function", ret);
		else if(!ret.getExpr().struct.compatibleWith(currMethod.getType()))
				report_error("Error: wrong type in return statement", ret);
	}
	
	public void visit(DoWhileStmt doWhile)
	{
		inWhileLoop = false;
		//if(!doWhile.getCondition().struct.equals(boolType))
		//	report_error("Error: condition in while not bool type", doWhile);
	}
	public void visit(DO_NT startWhileLoop)
	{
		inWhileLoop = true;
	}
	
	public void visit(BreakStmt breakStmt)
	{
		if(!inWhileLoop) report_error("Error: break statement not in while loop", breakStmt);
	}
	
	public void visit(ContinueStmt continueStmt)
	{
		if(!inWhileLoop) report_error("Error: break statement not in while loop", continueStmt);
	}
	
	boolean isSimpleType(Struct type) {
		return type.equals(Tab.intType) 
				|| type.equals(Tab.charType) 
				|| type.equals(boolType);
	}
	
	public void visit(ReadStmt stmt)
	{
		Obj obj = stmt.getDesignator().obj;
		int kind = stmt.getDesignator().obj.getKind() ;
		if(!(kind == Obj.Elem || kind== Obj.Var || kind == Obj.Fld) || !isSimpleType(obj.getType()))
			report_error("Error: invalid argument for read function", stmt);
	}
	
	public void visit(PrintStmt_ stmt)
	{
		Struct type = stmt.getExpr().struct;
		if(!isSimpleType(type))
			report_error("Error: invalid argument for read function", stmt);
	}
	
	public void visit(IfStmt stmt)
	{
		//if(!stmt.getCondition().struct.equals(boolType))
		//	report_error("Error: condition in if statement not bool type", stmt);
	}
	
	public void visit(IfElseStmt stmt)
	{
		//if(!stmt.getCondition().struct.equals(boolType))
		//	report_error("Error: condition in if statement not bool type", stmt);
	}
	// DesignatorStatement
	
	public void visit(Assignment stmt)
	{
		Struct desType = stmt.getDesignator().obj.getType();
		Struct srcType = stmt.getAssignExpr().struct;
		if(!isCompatible(desType, srcType))
			report_error("Error: non compatible types", stmt);
	}
	
	public void visit(Assign_Expr expr)
	{
		expr.struct = expr.getExpr().struct;
	}
	
	public void visit(FuncCall stmt)
	{
		Obj method = stmt.getDesignator().obj;
		callMeth(method, stmt);
	}
	
	public void visit(SuperCall stmt)
	{
		if(currClassObj == null)
			report_error("Error: calling super from outside a class", stmt);
		if(currClassStruct.getElemType() == null || currClassStruct.getElemType().equals(Tab.noType))
		{
			report_error("Error: class calls super but has no parent", stmt);
		}
		else {
			if(currMethod.getName().equals(currClassObj.getName()))
			{
				// poziv super unutar konstruktora
				//currClassStruct.getElemType()
			}
			else
			{
				// poziv super unutar metode
			}
			
		}
	}
	
	public void visit(PostIncrement stmt)
	{
		
		if(!stmt.getDesignator().obj.getType().equals(Tab.intType))
		{
			report_error("Error: variable not an integer", stmt);
		}else {
			int kind = stmt.getDesignator().obj.getKind();
			if(!(kind == Obj.Var || kind == Obj.Elem || kind == Obj.Fld))
			{
				report_error("Error: illegal simbol for expression", stmt);
			}
		}
	}
	
	public void visit(PostDecrement stmt)
	{
		if(!stmt.getDesignator().obj.getType().equals(Tab.intType))
		{
			report_error("Error: variable not an integer", stmt);
		}else {
			int kind = stmt.getDesignator().obj.getKind();
			if(!(kind == Obj.Var || kind == Obj.Elem || kind == Obj.Fld))
			{
				report_error("Error: illegal simbol for expression", stmt);
			}
		}
	}
	
	// ActualParams
	public void visit(ActualPars_Expr pars)
	{
		currActualParams.add(pars.getExpr().struct);
	}
	
	public void visit(ActualPars_List pars)
	{
		currActualParams.add(pars.getExpr().struct);
	}
	
	// Condition, CondFact
	
	
	public void visit(CondFact_Expr cfact)
	{
		if(!cfact.getExpr().struct.equals(boolType))
			report_error("Error: expression is not a boolean type", cfact);	
		cfact.struct = boolType;
	}
	
	public void visit(CondFact_Relop fact)
	{
		Struct expr1 = fact.getExpr().struct;
		Struct expr2 = fact.getExpr1().struct;
		if(!isCompatible(expr1, expr2) && !isCompatible(expr2, expr1))
		{
			report_error("Error: expressions not compatible", fact);
		}
		else
		{
			fact.struct = fact.getExpr().struct;
			if(fact.struct.isRefType() && !isEqCond)
			{
				report_error("Error: illegal operation with ref type", fact);
			}
		}
		fact.struct = boolType;
		isEqCond = false;
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
		if(!(tmul.getFactor().struct.getKind() == Struct.Int) || !(tmul.getTerm().struct.getKind() == Struct.Int))
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
		Obj method = fcall.getDesignator().obj;
		callMeth(method, fcall);
		fcall.struct = method.getType();
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
		// idk sta da radim ovde
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
		if(currS.getKind() == Struct.Class && currS.getMembersTable().searchKey(name) != null) {
			currDesignator = currS.getMembersTable().searchKey(name);
			if(currDesignator.getKind() == Obj.Meth) isClassMeth = true;
			else isClassMeth = false;
		}
		else
			report_error("Error: cannot find field " + designatorList.getName(), designatorList);
	}
	
	
	void callMeth(Obj method, SyntaxNode node)
	{
		if(!(method.getKind() == Obj.Meth))
			report_error("Error: " + method.getName() + " is not a method", node);
		// Check parameters
		Iterator<Obj> paramIt = method.getLocalSymbols().iterator();
		int numParams = method.getLevel() - (isClassMeth ? 1 : 0);
		if(currActualParams.size() != numParams)
			report_error("Error: number of parameters", node);
		else {
			while(paramIt.hasNext())
			{
				Obj param = paramIt.next();
				if(isClassMeth && param.getName().equals("this"));
				int fpos = param.getFpPos() - 1 - (isClassMeth ? 1 : 0);
				if(fpos >= 0)
				{
					if(!isCompatible(param.getType(), currActualParams.get(fpos)))
					{
						report_error("Error: parameter types do not match", node);
						break;
					}
				}
			}
		}
		currActualParams.clear();
		isClassMeth = false;
	}
	
	boolean isCompatible(Struct s1, Struct s2)
	{
		if(s1.compatibleWith(s2)) return true;
		else if(s1.getKind() == Struct.Class && s2.getKind() == Struct.Class)
			return isBase(s1, s2);
		return false;
	}
	
	boolean isBase(Struct base, Struct derived)
	{
		Struct t = derived.getElemType();
		while(t != null && t.getKind() != Struct.None)
		{
			if(t.equals(base))
			{
				return true;
			}
			else
			{
				t = t.getElemType();
			}
		}
		return false;
	}
}
