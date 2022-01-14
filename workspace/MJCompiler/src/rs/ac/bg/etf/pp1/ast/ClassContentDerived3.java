// generated with ast extension for cup
// version 0.8
// 14/0/2022 16:46:17


package rs.ac.bg.etf.pp1.ast;

public class ClassContentDerived3 extends ClassContent {

    private ClassVarDeclarationsList ClassVarDeclarationsList;

    public ClassContentDerived3 (ClassVarDeclarationsList ClassVarDeclarationsList) {
        this.ClassVarDeclarationsList=ClassVarDeclarationsList;
        if(ClassVarDeclarationsList!=null) ClassVarDeclarationsList.setParent(this);
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
        if(ClassVarDeclarationsList!=null) ClassVarDeclarationsList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarDeclarationsList!=null) ClassVarDeclarationsList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarDeclarationsList!=null) ClassVarDeclarationsList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassContentDerived3(\n");

        if(ClassVarDeclarationsList!=null)
            buffer.append(ClassVarDeclarationsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassContentDerived3]");
        return buffer.toString();
    }
}
