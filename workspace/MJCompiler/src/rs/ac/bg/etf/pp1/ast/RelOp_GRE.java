// generated with ast extension for cup
// version 0.8
// 16/4/2022 17:58:8


package rs.ac.bg.etf.pp1.ast;

public class RelOp_GRE extends RelOp {

    private String t;

    public RelOp_GRE (String t) {
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
        buffer.append("RelOp_GRE(\n");

        buffer.append(" "+tab+t);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RelOp_GRE]");
        return buffer.toString();
    }
}
