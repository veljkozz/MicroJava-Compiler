// generated with ast extension for cup
// version 0.8
// 5/0/2022 0:27:14


package rs.ac.bg.etf.pp1.ast;

public class ClassMethodsDerived4 extends ClassMethods {

    public ClassMethodsDerived4 () {
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
        buffer.append("ClassMethodsDerived4(\n");

        buffer.append(tab);
        buffer.append(") [ClassMethodsDerived4]");
        return buffer.toString();
    }
}
