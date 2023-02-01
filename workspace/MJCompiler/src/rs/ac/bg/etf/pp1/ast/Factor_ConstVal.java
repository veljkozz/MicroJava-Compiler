// generated with ast extension for cup
// version 0.8
// 16/4/2022 17:58:8


package rs.ac.bg.etf.pp1.ast;

public class Factor_ConstVal extends Factor {

    private Val Val;

    public Factor_ConstVal (Val Val) {
        this.Val=Val;
        if(Val!=null) Val.setParent(this);
    }

    public Val getVal() {
        return Val;
    }

    public void setVal(Val Val) {
        this.Val=Val;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Val!=null) Val.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Val!=null) Val.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Val!=null) Val.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Factor_ConstVal(\n");

        if(Val!=null)
            buffer.append(Val.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor_ConstVal]");
        return buffer.toString();
    }
}
