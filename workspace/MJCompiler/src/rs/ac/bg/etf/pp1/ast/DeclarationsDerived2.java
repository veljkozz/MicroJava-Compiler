// generated with ast extension for cup
// version 0.8
// 7/0/2022 18:9:42


package rs.ac.bg.etf.pp1.ast;

public class DeclarationsDerived2 extends Declarations {

    public DeclarationsDerived2 () {
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
        buffer.append("DeclarationsDerived2(\n");

        buffer.append(tab);
        buffer.append(") [DeclarationsDerived2]");
        return buffer.toString();
    }
}
