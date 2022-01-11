// generated with ast extension for cup
// version 0.8
// 11/0/2022 1:29:58


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(MethodDecl MethodDecl);
    public void visit(TermList TermList);
    public void visit(GlobalMethods GlobalMethods);
    public void visit(MethodType MethodType);
    public void visit(PrintStmt PrintStmt);
    public void visit(Var Var);
    public void visit(StatementList StatementList);
    public void visit(Extends Extends);
    public void visit(Val Val);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(VarList VarList);
    public void visit(ClassVarDeclarations ClassVarDeclarations);
    public void visit(OptionalVarDeclarations OptionalVarDeclarations);
    public void visit(Term Term);
    public void visit(Condition Condition);
    public void visit(Statements Statements);
    public void visit(MulOp MulOp);
    public void visit(AssignExpr AssignExpr);
    public void visit(ClassVar ClassVar);
    public void visit(ClassVarDeclarationsList ClassVarDeclarationsList);
    public void visit(FormParams FormParams);
    public void visit(RelOp RelOp);
    public void visit(ClassContent ClassContent);
    public void visit(AssignOp AssignOp);
    public void visit(MethodList MethodList);
    public void visit(DesignatorName DesignatorName);
    public void visit(ConstDeclarations ConstDeclarations);
    public void visit(SuperCall SuperCall);
    public void visit(Declarations Declarations);
    public void visit(Expr Expr);
    public void visit(ClassDeclaration ClassDeclaration);
    public void visit(VarDeclarationsList VarDeclarationsList);
    public void visit(AddOp AddOp);
    public void visit(DesignatorList DesignatorList);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(RecordContent RecordContent);
    public void visit(ActualPars ActualPars);
    public void visit(FormParamList FormParamList);
    public void visit(ClassVarList ClassVarList);
    public void visit(Statement Statement);
    public void visit(ConstVarList ConstVarList);
    public void visit(CondFact CondFact);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(Declaration Declaration);
    public void visit(SingleStatement SingleStatement);
    public void visit(ClassMethods ClassMethods);
    public void visit(FormParam FormParam);
    public void visit(RelOpDerived4 RelOpDerived4);
    public void visit(RelOpDerived3 RelOpDerived3);
    public void visit(RelOpDerived2 RelOpDerived2);
    public void visit(RelOpDerived1 RelOpDerived1);
    public void visit(RelOp_NEQ RelOp_NEQ);
    public void visit(RelOp_EQ RelOp_EQ);
    public void visit(MulOpDerived3 MulOpDerived3);
    public void visit(MulOpDerived2 MulOpDerived2);
    public void visit(MulOpDerived1 MulOpDerived1);
    public void visit(AddOpDerived2 AddOpDerived2);
    public void visit(AddOpDerived1 AddOpDerived1);
    public void visit(AssignOpDerived1 AssignOpDerived1);
    public void visit(ActualParsDerived1 ActualParsDerived1);
    public void visit(ActualPars_List ActualPars_List);
    public void visit(ActualPars_Expr ActualPars_Expr);
    public void visit(DesignatorListDerived1 DesignatorListDerived1);
    public void visit(DesignatorList_Field DesignatorList_Field);
    public void visit(DesignatorList_Arr DesignatorList_Arr);
    public void visit(DesignatorName_Arr DesignatorName_Arr);
    public void visit(Designator_Name Designator_Name);
    public void visit(Designator Designator);
    public void visit(Factor_ParenExpr Factor_ParenExpr);
    public void visit(Factor_New_Arr Factor_New_Arr);
    public void visit(Factor_New Factor_New);
    public void visit(Factor_ConstVal Factor_ConstVal);
    public void visit(Factor_Super Factor_Super);
    public void visit(Factor_FunCall Factor_FunCall);
    public void visit(Factor_Designator Factor_Designator);
    public void visit(Term_Fact Term_Fact);
    public void visit(Term_MulOp Term_MulOp);
    public void visit(TermList_AddOp TermList_AddOp);
    public void visit(TermList_Term TermList_Term);
    public void visit(Expr_Neg Expr_Neg);
    public void visit(Expr_Pos Expr_Pos);
    public void visit(AssignExprDerived1 AssignExprDerived1);
    public void visit(Assign_Expr Assign_Expr);
    public void visit(CondFact_Relop CondFact_Relop);
    public void visit(CondFact_Expr CondFact_Expr);
    public void visit(CondTerm_List CondTerm_List);
    public void visit(CondTerm_CondFact CondTerm_CondFact);
    public void visit(ConditionDerived1 ConditionDerived1);
    public void visit(Condition_List Condition_List);
    public void visit(Condition_CondTerm Condition_CondTerm);
    public void visit(Super_Call Super_Call);
    public void visit(PostDecrement PostDecrement);
    public void visit(PostIncrement PostIncrement);
    public void visit(Super_Call_Stmt Super_Call_Stmt);
    public void visit(FuncCall FuncCall);
    public void visit(Assignment Assignment);
    public void visit(PrintStmt_Num PrintStmt_Num);
    public void visit(PrintStmt_ PrintStmt_);
    public void visit(DO_NT DO_NT);
    public void visit(Print_Stmt Print_Stmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(ReturnExprStmt ReturnExprStmt);
    public void visit(ReturnStmt ReturnStmt);
    public void visit(ContinueStmt ContinueStmt);
    public void visit(BreakStmt BreakStmt);
    public void visit(DoWhileStmt DoWhileStmt);
    public void visit(IfElseStmt IfElseStmt);
    public void visit(IfStmt IfStmt);
    public void visit(Statement_Designator Statement_Designator);
    public void visit(StatementsDerived1 StatementsDerived1);
    public void visit(StatementDerived2 StatementDerived2);
    public void visit(StatementDerived1 StatementDerived1);
    public void visit(NoStmt NoStmt);
    public void visit(Statement_List Statement_List);
    public void visit(FormParamDerived1 FormParamDerived1);
    public void visit(Form_Param_Arr Form_Param_Arr);
    public void visit(Form_Param Form_Param);
    public void visit(SingleFormalParam SingleFormalParam);
    public void visit(FormParam_List FormParam_List);
    public void visit(NoFormParam NoFormParam);
    public void visit(FormParams_List FormParams_List);
    public void visit(Optional_NoVarDeclarations Optional_NoVarDeclarations);
    public void visit(Optional_VarDeclarations Optional_VarDeclarations);
    public void visit(VoidType VoidType);
    public void visit(Method_Type Method_Type);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(Method_Decl Method_Decl);
    public void visit(MethodListDerived2 MethodListDerived2);
    public void visit(MethodListDerived1 MethodListDerived1);
    public void visit(GlobalMethodsDerived1 GlobalMethodsDerived1);
    public void visit(Global_Methods Global_Methods);
    public void visit(RecordContentDerived2 RecordContentDerived2);
    public void visit(RecordContentDerived1 RecordContentDerived1);
    public void visit(RecordName RecordName);
    public void visit(RecordDeclaration RecordDeclaration);
    public void visit(ConstructorName ConstructorName);
    public void visit(Constructor Constructor);
    public void visit(ClassMethodsDerived4 ClassMethodsDerived4);
    public void visit(ClassMethodsDerived3 ClassMethodsDerived3);
    public void visit(ClassMethodsDerived2 ClassMethodsDerived2);
    public void visit(ClassMethodsDerived1 ClassMethodsDerived1);
    public void visit(Class_Var_Arr Class_Var_Arr);
    public void visit(Class_Var Class_Var);
    public void visit(ClassVarListDerived3 ClassVarListDerived3);
    public void visit(ClassVarListDerived2 ClassVarListDerived2);
    public void visit(ClassVarListDerived1 ClassVarListDerived1);
    public void visit(ClassVarDecl ClassVarDecl);
    public void visit(ClassVarDeclarationsListDerived3 ClassVarDeclarationsListDerived3);
    public void visit(ClassVarDeclarationsListDerived2 ClassVarDeclarationsListDerived2);
    public void visit(ClassVarDeclarationsListDerived1 ClassVarDeclarationsListDerived1);
    public void visit(ClassContentDerived4 ClassContentDerived4);
    public void visit(ClassContentDerived3 ClassContentDerived3);
    public void visit(ClassContentDerived2 ClassContentDerived2);
    public void visit(ClassContentDerived1 ClassContentDerived1);
    public void visit(ExtendsDerived1 ExtendsDerived1);
    public void visit(Extends_Parent Extends_Parent);
    public void visit(Class_Parent_Declaration Class_Parent_Declaration);
    public void visit(Class_NoParent_Declaration Class_NoParent_Declaration);
    public void visit(ClassName ClassName);
    public void visit(OneVar OneVar);
    public void visit(Var_List Var_List);
    public void visit(Type Type);
    public void visit(VarDerived1 VarDerived1);
    public void visit(Var_Array Var_Array);
    public void visit(Var_ Var_);
    public void visit(Val_Bool Val_Bool);
    public void visit(Val_Char Val_Char);
    public void visit(Val_Num Val_Num);
    public void visit(VarDecl_Error VarDecl_Error);
    public void visit(Var_Declarations Var_Declarations);
    public void visit(ConstVarListDerived1 ConstVarListDerived1);
    public void visit(ConstDecl_List ConstDecl_List);
    public void visit(ConstDecl_Single ConstDecl_Single);
    public void visit(Const_Declarations Const_Declarations);
    public void visit(Declarations_RecordDecl Declarations_RecordDecl);
    public void visit(Declarations_ClassDecl Declarations_ClassDecl);
    public void visit(Declarations_ConstDecl Declarations_ConstDecl);
    public void visit(Declarations_VarDecl Declarations_VarDecl);
    public void visit(DeclarationsDerived2 DeclarationsDerived2);
    public void visit(DeclarationsDerived1 DeclarationsDerived1);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
