// generated with ast extension for cup
// version 0.8
// 5/0/2022 0:27:14


package rs.ac.bg.etf.pp1.ast;

public class DesignatorListDerived3 extends DesignatorList {

    public DesignatorListDerived3 () {
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
        buffer.append("DesignatorListDerived3(\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListDerived3]");
        return buffer.toString();
    }
}