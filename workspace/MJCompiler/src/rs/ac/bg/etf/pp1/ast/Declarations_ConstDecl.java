// generated with ast extension for cup
// version 0.8
// 14/0/2022 16:46:17


package rs.ac.bg.etf.pp1.ast;

public class Declarations_ConstDecl extends Declaration {

    private ConstDeclarations ConstDeclarations;

    public Declarations_ConstDecl (ConstDeclarations ConstDeclarations) {
        this.ConstDeclarations=ConstDeclarations;
        if(ConstDeclarations!=null) ConstDeclarations.setParent(this);
    }

    public ConstDeclarations getConstDeclarations() {
        return ConstDeclarations;
    }

    public void setConstDeclarations(ConstDeclarations ConstDeclarations) {
        this.ConstDeclarations=ConstDeclarations;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclarations!=null) ConstDeclarations.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclarations!=null) ConstDeclarations.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclarations!=null) ConstDeclarations.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Declarations_ConstDecl(\n");

        if(ConstDeclarations!=null)
            buffer.append(ConstDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Declarations_ConstDecl]");
        return buffer.toString();
    }
}
