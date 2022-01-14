// generated with ast extension for cup
// version 0.8
// 13/0/2022 22:18:15


package rs.ac.bg.etf.pp1.ast;

public class RelOp_NEQ extends RelOp {

    private String t;

    public RelOp_NEQ (String t) {
        this.t=t;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t=t;
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
        buffer.append("RelOp_NEQ(\n");

        buffer.append(" "+tab+t);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RelOp_NEQ]");
        return buffer.toString();
    }
}
