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
	
	ArrayList<Obj> currDesignatorName = new ArrayList<Obj>();
	Obj currClass = null;
	Obj currMeth = null;
	Obj currMethCall = null;
	int ClassMethCnt = 0;
	
	Obj currDes = null;
	Obj currArr = null;
	Obj currElem = null;
	
	boolean inClass = false;
	boolean superCall = false;
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
		
		vmtMap.get(className.getName()).vmt_addr = nextVmt;
		
		if(vmtMap.isEmpty()) nextVmt = Code.dataSize;
		
		Struct parent = currClass.getType().getElemType();
		if(parent != null && parent != Tab.noType)
		{
			// Copy VMT from parent
			//vmtMap.put(currClass.getName(), new VMT(currClass));
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
	
	// This is called in main
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
	
	// This is called after class is declared
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
			
			// for vfptr + "this"
			//Code.put(Code.dup);
			//Code.put(Code.dup);
			vfptr = currClass.getType().getMembersTable().searchKey("_vfptr");
			Code.load(vfptr);
		}
		else {
			name = currMeth.getName();
			//Code.put(Code.load_n + 0);
			VMT baseClassVmt = vmtMap.get(baseClassObj.getName());
			Code.loadConst(baseClassVmt.vmt_addr);
		}
		
		
		
		Code.put(Code.invokevirtual);
		for(int i=0; i<name.length(); i++)
		{
			int val = name.charAt(i);
			Code.put4(val);
		}
		Code.put4(-1);
		
	}
	public void visi(Super_Call supercall)
	{
		superCall = true;
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
		else if(stmt.getExpr().struct.equals(SemanticAnalyzer.boolType))
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
		else if(stmt.getExpr().struct.equals(SemanticAnalyzer.boolType))
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
		if(readstmt.getDesignator().obj.getType().equals(Tab.charType)) {
			Code.put(Code.bread);
		}
		else Code.put(Code.read);
		Code.store(readstmt.getDesignator().obj);
		currDes = null; 
	}
	
	public void visit(Assignment assignment)
	{
		Obj obj = assignment.getDesignator().obj;
		//System.out.println("ASSIGNMENT TO " + obj.getName() + " of kind " + obj.getKind() + " and addr " + obj.getAdr());
		Code.store(obj);
		
		currDes = null;
	}
	
	
	// Designator 
	public void visit(Designator designator)
	{
		SyntaxNode parentNode = designator.getParent();
		Class<?> parentClass = parentNode.getClass();
		// If should be loaded to expr stack
		if(parentClass.equals(Factor_Designator.class))
			Code.load(designator.obj);	
		currDesignatorName.remove(0);
		if(designator.obj.getKind() == Obj.Meth) currMethCall = designator.obj;
		
		//currDes = null;
	}
	
	public void visit(Designator_Name designator) { 
		currDesignatorName.add(0, designator.obj);
		//if(designator.obj.getKind() currDes = designator.obj;
		if(currDesignatorName.get(0).getKind() == Obj.Fld && currClass != null)
		{
			// load this kada se pristupa sopstvenom polju unutar klase
			Code.load(currMeth.getLocalSymbols().iterator().next());
		}
		
		if(designator.obj.getType().getKind() == Struct.Array)
			currArr = designator.obj;
		
		if(designator.obj.getKind() == Obj.Var && designator.obj.getType().isRefType())
			if(!(((Designator)designator.getParent()).getDesignatorList().getClass() == EmptyDesignatorList.class))
			{
				Code.load(designator.obj);
			}
				
	}
	
	public void visit(DesignatorList_Field desField)
	{
		if(!desField.getDesignatorList().getClass().equals(EmptyDesignatorList.class))
		{
			//Code.load(desField.getDesignatorList().obj);
			currDesignatorName.set(0, desField.getDesignatorList().obj);
		}
		else {
			//Code.load(currDesignatorName.get(0));
		}
		
		
		if(desField.obj.getKind() == Obj.Meth) 
		{
				ClassMethCnt++;
				currDes = currDesignatorName.get(0);
				// Dup for putting "this"
				//Code.put(Code.dup);
		}
		else {
			if(desField.getParent().getClass() != Designator.class)
			{
				Code.load(desField.obj);
			}
				
			
			currDes = desField.obj;
			if(currDes.getType().getKind() == Struct.Array)
				currArr = currDes;
		}
			
		
	}
	
	public void visit(DesignatorList_Arr desField)
	{
		if(!desField.getDesignatorList().getClass().equals(EmptyDesignatorList.class))
		{
			//Code.load(desField.getDesignatorList().obj);
		}
		else {
			//Code.load(currDesignatorName.get(0));
		}
		
		if(desField.getParent().getClass() != Designator.class)
		{
			Code.load(desField.obj);
		}
		
		currElem = desField.obj;
		currDes = desField.obj;
	}
	
	void call_fun(Obj fobj)
	{
		int offs = fobj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offs);
		
		
	}
	
	boolean isClassMeth(Obj meth, Obj classObj)
	{
		if(classObj == null) return false;
		if(classObj.getType().getMembersTable().searchKey(meth.getName()) != null)
			return true;
		else return false;
	}
	
	boolean currClassMethod(Obj meth)
	{
		if(currClass == null) return false;
		if(currClass.getType().getMembersTable().searchKey(meth.getName()) != null)
			return true;
		else return false;
	}
	
	public void visit(FuncCall fcall) {
		Obj fobj = fcall.getDesignator().obj;
		String name = fobj.getName();
		if(isClassMeth(fobj, currDes)) 
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
			
			currMethCall = null;
			currDes = null;
		}
		else if(currClassMethod(fobj))
		{
			Obj vfptr = currClass.getType().getMembersTable().searchKey("_vfptr");
			
			Code.load(vfptr);
			Code.put(Code.invokevirtual);
			for(int i=0; i<name.length(); i++)
			{
				int val = name.charAt(i);
				Code.put4(val);
			}
			Code.put4(-1);
			
			currMethCall = null;
			currDes = null;
		}
		else {
			call_fun(fobj);
		}
		
		if(!fobj.getType().equals(Tab.noType))
		{
			Code.put(Code.pop);
		}
		currDes = null;
	}
	
	
	
	// Factor
	public void visit(Factor_FunCall fcall)
	{
		Obj fobj = fcall.getDesignator().obj;
		String name = fobj.getName();
		
		if(currDes != null) 
		{
			Obj vfptr = currDes.getType().getMembersTable().searchKey("_vfptr");
			
			fcall.getDesignator();
			
			Code.load(vfptr);
			Code.put(Code.invokevirtual);
			for(int i=0; i<name.length(); i++)
			{
				int val = name.charAt(i);
				Code.put4(val);
			}
			Code.put4(-1);
			
			
		}
		else if(currClassMethod(fobj))
		{
			Obj vfptr = currClass.getType().getMembersTable().searchKey("_vfptr");
			
			Code.load(vfptr);
			Code.put(Code.invokevirtual);
			for(int i=0; i<name.length(); i++)
			{
				int val = name.charAt(i);
				Code.put4(val);
			}
			Code.put4(-1);
			
			
		}
		else call_fun(fobj);
		currMethCall = null;
		currDes = null;
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
		
		if(classType.getElemType() != null && classType.getElemType().getKind() == Struct.Enum) 
		{
			//RECORD
			Code.put(Code.new_);
			Code.put2(classType.getNumberOfFields()*4);
		}
		else {
			// CLASS
			Code.put(Code.new_);
			Code.put2(classType.getNumberOfFields()*4);
			// load vfptr val
			Code.put(Code.dup);
			Code.loadConst(vmtMap.get(className).vmt_addr);
			Code.put(Code.putfield); 
			Code.put2(0);
			//Code.put(Code.pop);
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
	public void visit(PostIncrement postInc)
	{
		Obj obj = postInc.getDesignator().obj;
		
		if(obj.getType().isRefType() || obj.getKind() == Obj.Elem|| obj.getKind() == Obj.Fld)
			Code.put(Code.dup);
		Code.load(obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(obj);
	}
	public void visit(PostDecrement postDec)
	{
		Obj obj = postDec.getDesignator().obj;
		
		if(obj.getType().isRefType() || obj.getKind() == Obj.Elem|| obj.getKind() == Obj.Fld)
			Code.put(Code.dup);
		Code.load(obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(obj);
	}
	
	// ADDING "THIS" ARG
	void addThis(SyntaxNode parent, SyntaxNode thisNode) {
		if(currClassMethod(currMeth))
		{
			// add "this" as first parameter
			Code.put(Code.load_n + 0);	
			if(thisNode.getClass() == ActualPars_Expr.class)
			{
				Code.put(Code.dup_x1);
			}
			else
				Code.put(Code.dup);
		}
		else
		{
			// Ok ovde je poziv funkcije
			if(thisNode.getClass() == ActualPars_Expr.class)
			{
				Code.put(Code.dup_x1);
				Code.put(Code.pop);
				Code.put(Code.dup_x1);
			} else  // ovo je poziv bez arg
				Code.put(Code.dup);
				
		}
		
	}
	
	void getCurrDes(SyntaxNode parent)
	{
		Designator d;
		while(parent.getClass() != Factor_FunCall.class && parent.getClass() != FuncCall.class)
			parent = parent.getParent();
		if(parent.getClass() == Factor_FunCall.class) {
			Factor_FunCall fcall = (Factor_FunCall)(parent);
			d = fcall.getDesignator();
		}
		else {
			FuncCall fcall = (FuncCall)parent;
			d = fcall.getDesignator();
		}
		
		if(d.getDesignatorList().getClass() == EmptyDesignatorList.class)
		{
			//nista currDes = null?
		}
		else {
			// This is function obj
			DesignatorList_Field desList = (DesignatorList_Field) d.getDesignatorList();
			if(desList.getDesignatorList().getClass() == EmptyDesignatorList.class)
				currDes = d.getDesignatorName().obj;
			else currDes = desList.getDesignatorList().obj;
		}
	}
	
	public void visit(NoPars noPars)
	{
		SyntaxNode parent = noPars.getParent();
		if(currClassMethod(currMeth)|| isClassMeth(currMethCall, currDes))
				addThis(parent, noPars);
	}
	
	public void visit(ActualPars_Expr parsExpr)
	{
		SyntaxNode parent =  parsExpr.getParent();
		
		getCurrDes(parent);
		
		if(currClassMethod(currMeth)|| isClassMeth(currMethCall, currDes))
			addThis(parent, parsExpr);
	}
	public void visit(ActualPars_List actPars)
	{
		if(currClassMethod(currMeth)|| isClassMeth(currMethCall, currDes))
		{
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
		}
		
	}
	
	
	// SKOKOVI I USLOVNE STRUKTURE
	ArrayList<Boolean> inWhile = new ArrayList<Boolean>();
	ArrayList<Integer> whileStart = new ArrayList<Integer>();
	ArrayList<ArrayList<Integer>> ifPatches = new ArrayList<ArrayList<Integer>>();
	
	ArrayList<Integer> ifElsePatches = new ArrayList<Integer>();
	
	ArrayList<ArrayList<Integer>> breakPatches = new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> continuePatches = new ArrayList<ArrayList<Integer>>();
	
	ArrayList<Integer> enterPatches = new ArrayList<Integer>();
	ArrayList<ArrayList<Integer>> trueJumps = new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> falseJumps = new ArrayList<ArrayList<Integer>>();
	
	ArrayList<Boolean> isOrCond = new ArrayList<Boolean>();
	
	public void visit(DO_NT startWhile)
	{
		if(whileStart.isEmpty()) whileStart.add(Code.pc);
		else whileStart.add(0, Code.pc);
			
		if(trueJumps.isEmpty())
			trueJumps.add(new ArrayList<Integer>());
		else
			trueJumps.add(0, new ArrayList<Integer>());
		
		if(falseJumps.isEmpty())		
			falseJumps.add(new ArrayList<Integer>());
		else 
			falseJumps.add(0, new ArrayList<Integer>());
		
		if(inWhile.isEmpty()) inWhile.add(true);
		else inWhile.add(0, true);
		
		//if(ifElsePatches.isEmpty()) ifElsePatches.add(new ArrayList<Integer>());
		//else ifElsePatches.add(0, new ArrayList<Integer>());
		
		if(breakPatches.isEmpty()) breakPatches.add(new ArrayList<Integer>());
		else breakPatches.add(0, new ArrayList<Integer>());
		
		if(continuePatches.isEmpty()) continuePatches.add(new ArrayList<Integer>());
		else continuePatches.add(0, new ArrayList<Integer>());
	}
	public void visit(If_ startIf)
	{
		if(trueJumps.isEmpty())
			trueJumps.add(new ArrayList<Integer>());
		else
			trueJumps.add(0, new ArrayList<Integer>());
		
		if(falseJumps.isEmpty())		
			falseJumps.add(new ArrayList<Integer>());
		else 
			falseJumps.add(0, new ArrayList<Integer>());
		//ifPatches.get(0).add(Code.pc, 0);

		if(inWhile.isEmpty()) inWhile.add(false);
		else inWhile.add(0, false);
	}
	public void visit(DoWhileStmt loop)
	{
		Code.putJump(whileStart.remove(0));
		backPatch();
		// backpatch break stmts
		ArrayList<Integer> breakPatch = breakPatches.remove(0);
		for(Integer addr: breakPatch)
		{
			Code.fixup(addr);
		}
	}
	
	void backPatch()
	{
		if(!trueJumps.isEmpty())
		{
			ArrayList<Integer> trueJmps = trueJumps.remove(0);
			for(Integer addr: trueJmps)
			{
				Code.fixup(addr);
				Code.put(Code.pop);
				Code.put(Code.pop);
				
			}
		}
		if(!falseJumps.isEmpty())
		{
			ArrayList<Integer> falseJmps = falseJumps.remove(0);
			for(Integer addr: falseJmps)
			{
				Code.fixup(addr);
			}
		}
	
		if(!ifPatches.isEmpty())
		{
			ArrayList<Integer> addrs = ifPatches.remove(0);
			for(Integer addr: addrs)
			{
				//Code.fixup(addr);
			}
		}
		
		inWhile.remove(0);
	}
	public void visit(IfStmt statements)
	{
		backPatch();
	}
	
	public void visit(IfElseStmt statements)
	{
		Code.fixup(ifElsePatches.remove(0));
	}
	
	public void visit(Else_ Else)
	{
		Code.putJump(0);
		if(ifElsePatches.isEmpty()) ifElsePatches.add(Code.pc-2);
		else ifElsePatches.add(0, Code.pc-2);
		//backpatch only falseJumps
		backPatch();
		
	}
	
	ArrayList<Integer> toRemove = new ArrayList<Integer>();
	int Op = -1;
	
	// fixing previous true jumps
	public void visit(Condition_CondTerm condition)
	{
		for(int i=0;i< trueJumps.get(0).size(); i++)
		{
			
			Code.fixup(trueJumps.get(0).get(i));
			Code.put(Code.pop); Code.put(Code.pop);
			
			if(condition.getParent().getClass() != Condition_List.class)
				toRemove.add(trueJumps.get(0).get(i));
		}
		for(Integer i: toRemove)
		{
			trueJumps.get(0).remove(i);
		}
		toRemove.clear();
	}
	
	
	
	// Fixup true jumps in AND and remove them
	public void visit(And andCondition)
	{
		for(int i=0;i<trueJumps.get(0).size(); i++)
		{
			Code.fixup(trueJumps.get(0).get(i));
			Code.put(Code.pop); Code.put(Code.pop);
			
			toRemove.add(trueJumps.get(0).get(i));
		}
		for(Integer i: toRemove)
		{
			trueJumps.get(0).remove(i);
		}
		toRemove.clear();
	}
	
	
	// Fixup and remove patches for previous false jumps
	public void visit(Or orCondition)
	{
		for(int i=0;i<falseJumps.get(0).size(); i++)
		{
			Code.fixup(falseJumps.get(0).get(i));
			toRemove.add(falseJumps.get(0).get(i));
		}
		for(Integer i: toRemove)
		{
			falseJumps.get(0).remove(i);
		}
		toRemove.clear();
		
	}
	// Fix and remove patches for previous true jumps
	// Condition OR CondTerm
	public void visit(Condition_List condition)
	{
		for(int i=0;i<trueJumps.get(0).size(); i++)
		{
			Code.fixup(trueJumps.get(0).get(i));
			Code.put(Code.pop); Code.put(Code.pop);
			
			toRemove.add(trueJumps.get(0).get(i));
		}
		
		for(Integer i: toRemove)
		{
			trueJumps.get(0).remove(i);
		}
		toRemove.clear();
	}
	
	// a AND b
	// fixup false jump
	public void visit(CondTerm_List cndTerm)
	{
		int op = Op; //opobj.getKind();
		{
			// true jump samo nastavlja
			Code.putFalseJump(Code.inverse[op], 0);
			trueJumps.get(0).add(Code.pc-2);
			Code.putFalseJump(op, 0);
			falseJumps.get(0).add(Code.pc-2);
		}
	}
	public void visit(CondTerm_CondFact cndTerm)
	{
		int op = Op; //opobj.getKind();
		{
			Code.putFalseJump(Code.inverse[op], 0);
			trueJumps.get(0).add(Code.pc-2);
			Code.putFalseJump(op, 0);
			falseJumps.get(0).add(Code.pc-2);
		}
	}
	int names = 0;
	public void visit(CondFact_Relop relOp)
	{
		Code.put(Code.dup2);
		Op = relOp.getRelOp().obj.getKind();
	}
	
	public void visit(CondFact_Expr boolExpr)
	{
		Code.loadConst(1);
		if(!inWhile.get(0)) Code.put(Code.dup2);
		Op = Code.eq;
	}
	
	public void visit(RelOp_EQ op) { op.obj = new Obj(Code.eq, "eq", Tab.noType); }
	public void visit(RelOp_NEQ op) { op.obj = new Obj(Code.ne, "eq", Tab.noType); }
	public void visit(RelOp_GRT op) { op.obj = new Obj(Code.gt, "eq", Tab.noType); }
	public void visit(RelOp_GRE op) { op.obj = new Obj(Code.ge, "eq", Tab.noType); }
	public void visit(RelOp_LSS op) { op.obj = new Obj(Code.lt, "eq", Tab.noType); }
	public void visit(RelOp_LSSE op) { op.obj = new Obj(Code.le, "eq", Tab.noType); }
	
	public void visit(BreakStmt breakstmt)
	{
		Code.putJump(0);
		breakPatches.get(0).add(Code.pc-2);
	}
	
	public void visit(ContinueStmt continuestmt)
	{
		Code.putJump(0);
		continuePatches.get(0).add(Code.pc-2);
	}
	
	
	public void visit(While_ whileCondBegin)
	{
		// backpatch continue statements
		ArrayList<Integer> continuePatch = continuePatches.remove(0);
		for(Integer addr: continuePatch)
			Code.fixup(addr);
	}
	
	void addOrdAndChr()
	{
		Tab.find("ord").setAdr(Code.pc);
		Tab.find("chr").setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n+0);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	// Add definitions for ord and chr
	public void visit(ProgName progName)
	{
		addOrdAndChr();
	}
}
