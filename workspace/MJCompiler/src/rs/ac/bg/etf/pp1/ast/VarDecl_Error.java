// generated with ast extension for cup
// version 0.8
// 16/4/2022 17:58:8


package rs.ac.bg.etf.pp1.ast;

public class VarDecl_Error extends VarDeclarations {

    public VarDecl_Error () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl_Error(\n");

        buffer.append(tab);
        buffer.append(") [VarDecl_Error]");
        return buffer.toString();
    }
}
