// generated with ast extension for cup
// version 0.8
// 11/0/2022 1:29:58


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl_Single extends ConstVarList {

    private String varName;
    private Val Val;

    public ConstDecl_Single (String varName, Val Val) {
        this.varName=varName;
        this.Val=Val;
        if(Val!=null) Val.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
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
        buffer.append("ConstDecl_Single(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(Val!=null)
            buffer.append(Val.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl_Single]");
        return buffer.toString();
    }
}
