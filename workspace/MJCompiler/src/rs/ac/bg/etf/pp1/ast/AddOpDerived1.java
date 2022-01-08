// generated with ast extension for cup
// version 0.8
// 8/0/2022 2:52:6


package rs.ac.bg.etf.pp1.ast;

public class AddOpDerived1 extends AddOp {

    public AddOpDerived1 () {
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
        buffer.append("AddOpDerived1(\n");

        buffer.append(tab);
        buffer.append(") [AddOpDerived1]");
        return buffer.toString();
    }
}
