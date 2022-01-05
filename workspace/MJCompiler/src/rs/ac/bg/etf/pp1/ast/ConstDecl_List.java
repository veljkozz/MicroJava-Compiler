// generated with ast extension for cup
// version 0.8
// 5/0/2022 1:35:55


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl_List extends ConstVarList {

    private String varName;
    private Val Val;
    private ConstVarList ConstVarList;

    public ConstDecl_List (String varName, Val Val, ConstVarList ConstVarList) {
        this.varName=varName;
        this.Val=Val;
        if(Val!=null) Val.setParent(this);
        this.ConstVarList=ConstVarList;
        if(ConstVarList!=null) ConstVarList.setParent(this);
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

    public ConstVarList getConstVarList() {
        return ConstVarList;
    }

    public void setConstVarList(ConstVarList ConstVarList) {
        this.ConstVarList=ConstVarList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Val!=null) Val.accept(visitor);
        if(ConstVarList!=null) ConstVarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Val!=null) Val.traverseTopDown(visitor);
        if(ConstVarList!=null) ConstVarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Val!=null) Val.traverseBottomUp(visitor);
        if(ConstVarList!=null) ConstVarList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl_List(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(Val!=null)
            buffer.append(Val.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstVarList!=null)
            buffer.append(ConstVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl_List]");
        return buffer.toString();
    }
}
