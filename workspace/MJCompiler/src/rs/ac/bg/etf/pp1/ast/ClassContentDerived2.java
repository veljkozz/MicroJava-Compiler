// generated with ast extension for cup
// version 0.8
// 11/0/2022 1:29:58


package rs.ac.bg.etf.pp1.ast;

public class ClassContentDerived2 extends ClassContent {

    private ClassVarDeclarationsList ClassVarDeclarationsList;
    private ClassMethods ClassMethods;

    public ClassContentDerived2 (ClassVarDeclarationsList ClassVarDeclarationsList, ClassMethods ClassMethods) {
        this.ClassVarDeclarationsList=ClassVarDeclarationsList;
        if(ClassVarDeclarationsList!=null) ClassVarDeclarationsList.setParent(this);
        this.ClassMethods=ClassMethods;
        if(ClassMethods!=null) ClassMethods.setParent(this);
    }

    public ClassVarDeclarationsList getClassVarDeclarationsList() {
        return ClassVarDeclarationsList;
    }

    public void setClassVarDeclarationsList(ClassVarDeclarationsList ClassVarDeclarationsList) {
        this.ClassVarDeclarationsList=ClassVarDeclarationsList;
    }

    public ClassMethods getClassMethods() {
        return ClassMethods;
    }

    public void setClassMethods(ClassMethods ClassMethods) {
        this.ClassMethods=ClassMethods;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVarDeclarationsList!=null) ClassVarDeclarationsList.accept(visitor);
        if(ClassMethods!=null) ClassMethods.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarDeclarationsList!=null) ClassVarDeclarationsList.traverseTopDown(visitor);
        if(ClassMethods!=null) ClassMethods.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarDeclarationsList!=null) ClassVarDeclarationsList.traverseBottomUp(visitor);
        if(ClassMethods!=null) ClassMethods.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassContentDerived2(\n");

        if(ClassVarDeclarationsList!=null)
            buffer.append(ClassVarDeclarationsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassMethods!=null)
            buffer.append(ClassMethods.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassContentDerived2]");
        return buffer.toString();
    }
}
