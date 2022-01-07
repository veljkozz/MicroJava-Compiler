// generated with ast extension for cup
// version 0.8
// 7/0/2022 18:9:42


package rs.ac.bg.etf.pp1.ast;

public class ClassVarDeclarationsListDerived2 extends ClassVarDeclarationsList {

    private ClassVarDeclarations ClassVarDeclarations;

    public ClassVarDeclarationsListDerived2 (ClassVarDeclarations ClassVarDeclarations) {
        this.ClassVarDeclarations=ClassVarDeclarations;
        if(ClassVarDeclarations!=null) ClassVarDeclarations.setParent(this);
    }

    public ClassVarDeclarations getClassVarDeclarations() {
        return ClassVarDeclarations;
    }

    public void setClassVarDeclarations(ClassVarDeclarations ClassVarDeclarations) {
        this.ClassVarDeclarations=ClassVarDeclarations;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVarDeclarations!=null) ClassVarDeclarations.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarDeclarations!=null) ClassVarDeclarations.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarDeclarations!=null) ClassVarDeclarations.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassVarDeclarationsListDerived2(\n");

        if(ClassVarDeclarations!=null)
            buffer.append(ClassVarDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassVarDeclarationsListDerived2]");
        return buffer.toString();
    }
}
