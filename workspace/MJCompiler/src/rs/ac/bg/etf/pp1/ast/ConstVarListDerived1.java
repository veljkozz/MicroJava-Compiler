// generated with ast extension for cup
// version 0.8
// 15/0/2022 2:5:44


package rs.ac.bg.etf.pp1.ast;

public class ConstVarListDerived1 extends ConstVarList {

    public ConstVarListDerived1 () {
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
        buffer.append("ConstVarListDerived1(\n");

        buffer.append(tab);
        buffer.append(") [ConstVarListDerived1]");
        return buffer.toString();
    }
}
