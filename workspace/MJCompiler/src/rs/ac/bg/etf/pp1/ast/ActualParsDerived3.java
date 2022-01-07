// generated with ast extension for cup
// version 0.8
// 7/0/2022 16:15:17


package rs.ac.bg.etf.pp1.ast;

public class ActualParsDerived3 extends ActualPars {

    public ActualParsDerived3 () {
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
        buffer.append("ActualParsDerived3(\n");

        buffer.append(tab);
        buffer.append(") [ActualParsDerived3]");
        return buffer.toString();
    }
}
