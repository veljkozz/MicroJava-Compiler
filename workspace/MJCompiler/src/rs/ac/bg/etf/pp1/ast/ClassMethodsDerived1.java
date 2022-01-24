// generated with ast extension for cup
// version 0.8
// 19/0/2022 1:30:41


package rs.ac.bg.etf.pp1.ast;

public class ClassMethodsDerived1 extends ClassMethods {

    private Constructor Constructor;
    private MethodList MethodList;

    public ClassMethodsDerived1 (Constructor Constructor, MethodList MethodList) {
        this.Constructor=Constructor;
        if(Constructor!=null) Constructor.setParent(this);
        this.MethodList=MethodList;
        if(MethodList!=null) MethodList.setParent(this);
    }

    public Constructor getConstructor() {
        return Constructor;
    }

    public void setConstructor(Constructor Constructor) {
        this.Constructor=Constructor;
    }

    public MethodList getMethodList() {
        return MethodList;
    }

    public void setMethodList(MethodList MethodList) {
        this.MethodList=MethodList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Constructor!=null) Constructor.accept(visitor);
        if(MethodList!=null) MethodList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Constructor!=null) Constructor.traverseTopDown(visitor);
        if(MethodList!=null) MethodList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Constructor!=null) Constructor.traverseBottomUp(visitor);
        if(MethodList!=null) MethodList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassMethodsDerived1(\n");

        if(Constructor!=null)
            buffer.append(Constructor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodList!=null)
            buffer.append(MethodList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassMethodsDerived1]");
        return buffer.toString();
    }
}
