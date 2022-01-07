// generated with ast extension for cup
// version 0.8
// 7/0/2022 18:9:42


package rs.ac.bg.etf.pp1.ast;

public class ClassVarDeclarationsListDerived1 extends ClassVarDeclarationsList {

    private ClassVarDeclarations ClassVarDeclarations;
    private ClassVarDeclarationsList ClassVarDeclarationsList;

    public ClassVarDeclarationsListDerived1 (ClassVarDeclarations ClassVarDeclarations, ClassVarDeclarationsList ClassVarDeclarationsList) {
        this.ClassVarDeclarations=ClassVarDeclarations;
        if(ClassVarDeclarations!=null) ClassVarDeclarations.setParent(this);
        this.ClassVarDeclarationsList=ClassVarDeclarationsList;
        if(ClassVarDeclarationsList!=null) ClassVarDeclarationsList.setParent(this);
    }

    public ClassVarDeclarations getClassVarDeclarations() {
        return ClassVarDeclarations;
    }

    public void setClassVarDeclarations(ClassVarDeclarations ClassVarDeclarations) {
        this.ClassVarDeclarations=ClassVarDeclarations;
    }

    public ClassVarDeclarationsList getClassVarDeclarationsList() {
        return ClassVarDeclarationsList;
    }

    public void setClassVarDeclarationsList(ClassVarDeclarationsList ClassVarDeclarationsList) {
        this.ClassVarDeclarationsList=ClassVarDeclarationsList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVarDeclarations!=null) ClassVarDeclarations.accept(visitor);
        if(ClassVarDeclarationsList!=null) ClassVarDeclarationsList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarDeclarations!=null) ClassVarDeclarations.traverseTopDown(visitor);
        if(ClassVarDeclarationsList!=null) ClassVarDeclarationsList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarDeclarations!=null) ClassVarDeclarations.traverseBottomUp(visitor);
        if(ClassVarDeclarationsList!=null) ClassVarDeclarationsList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassVarDeclarationsListDerived1(\n");

        if(ClassVarDeclarations!=null)
            buffer.append(ClassVarDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarDeclarationsList!=null)
            buffer.append(ClassVarDeclarationsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassVarDeclarationsListDerived1]");
        return buffer.toString();
    }
}
