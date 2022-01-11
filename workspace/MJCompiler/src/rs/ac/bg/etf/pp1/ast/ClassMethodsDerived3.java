// generated with ast extension for cup
// version 0.8
// 11/0/2022 1:29:58


package rs.ac.bg.etf.pp1.ast;

public class ClassMethodsDerived3 extends ClassMethods {

    private Constructor Constructor;

    public ClassMethodsDerived3 (Constructor Constructor) {
        this.Constructor=Constructor;
        if(Constructor!=null) Constructor.setParent(this);
    }

    public Constructor getConstructor() {
        return Constructor;
    }

    public void setConstructor(Constructor Constructor) {
        this.Constructor=Constructor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Constructor!=null) Constructor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Constructor!=null) Constructor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Constructor!=null) Constructor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassMethodsDerived3(\n");

        if(Constructor!=null)
            buffer.append(Constructor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassMethodsDerived3]");
        return buffer.toString();
    }
}
