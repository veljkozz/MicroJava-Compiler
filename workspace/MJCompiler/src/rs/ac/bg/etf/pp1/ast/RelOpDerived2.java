// generated with ast extension for cup
// version 0.8
// 13/0/2022 22:18:15


package rs.ac.bg.etf.pp1.ast;

public class RelOpDerived2 extends RelOp {

    private String t;

    public RelOpDerived2 (String t) {
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
        buffer.append("RelOpDerived2(\n");

        buffer.append(" "+tab+t);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RelOpDerived2]");
        return buffer.toString();
    }
}
