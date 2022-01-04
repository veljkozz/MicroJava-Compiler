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
            msg.append (" na liniji ").append(line);
        log.error(msg.toString());
    }
	
	public void report_info(String message, SyntaxNode info) {
    	StringBuilder msg = new StringBuilder(message); 
    	int line = (info == null) ? 0 : info.getLine();
    	if (line != 0)
            msg.append (" na liniji ").append(line);
        log.info(msg.toString());
    }
	
	public void visit(ProgName progName)
	{
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
		System.out.println("\nBuraz jel radi ovo?!\n");
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
			report_error("Error: Type " + type.getTypeName() + " not found", null);
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
		report_info("Deklarisana promenljiva " + var.getVarName() + " sa tipom " + currVarType.getKind(), var);
		Tab.insert(Obj.Var, var.getVarName(), currVarType);
	}	
	public void visit(Var_Array var_arr) 
	{ 
		globalVarCnt++;
		Struct s = new Struct(Struct.Array);
		report_info("Deklarisana promenljiva " + var_arr.getVarName() + " sa tipom " + s.getKind(), var_arr);
		s.setElementType(currVarType);
		Tab.insert(Obj.Var, var_arr.getVarName(), s);
	}
	
	// Const Declaration
	public void visit(ConstDecl_Single decl)
	{
		globalVarCnt++;
		report_info("Deklarisana konstanta " + decl.getVarName() + " sa tipom " + currVarType.getKind(), decl);
		Obj obj = Tab.insert(Obj.Con, decl.getVarName(), currVarType);
		Val val = decl.getVal();
		// Since all consts are of a simple type(int,char,bool)
		// there is no need to check for polimorphism
		if(val.simpletype.s == currVarType)
		{
			switch(val.simpletype.s.getKind())
			{
			case Struct.Int: System.out.println("Int\n"); obj.setAdr(val.simpletype.num); break;
			case Struct.Char: System.out.println("Char\n"); obj.setAdr(val.simpletype.character); break;
			case Struct.Bool: obj.setAdr(val.simpletype.num); break;
			default:
				report_error("Error: invalid type of constant(allowed types are int, char, bool)"  , decl);
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
		report_info("Deklarisana konstanta " + decl.getVarName() + " sa tipom " + currVarType.getKind(), decl);
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
	//public void visit(ClassName className)
	{
		
	}
	
	public void visit(Class_NoParent_Declaration classNoParent)
	{
		
	}
	
	public void visit(Class_Parent_Declaration classParent)
	{
		
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
		if(currMethod.getType().getKind() == Struct.None)
			return;
		else 
			report_error("Error: function " + currMethod.getName() + " must return type " + currMethod.getType().getKind(), ret);
	}
	
}
