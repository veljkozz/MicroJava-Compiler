// generated with ast extension for cup
// version 0.8
// 12/0/2022 13:35:54


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl_List extends ConstVarList {

    private String varName;
    private ConstVal ConstVal;
    private ConstVarList ConstVarList;

    public ConstDecl_List (String varName, ConstVal ConstVal, ConstVarList ConstVarList) {
        this.varName=varName;
        this.ConstVal=ConstVal;
        if(ConstVal!=null) ConstVal.setParent(this);
        this.ConstVarList=ConstVarList;
        if(ConstVarList!=null) ConstVarList.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public ConstVal getConstVal() {
        return ConstVal;
    }

    public void setConstVal(ConstVal ConstVal) {
        this.ConstVal=ConstVal;
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
        if(ConstVal!=null) ConstVal.accept(visitor);
        if(ConstVarList!=null) ConstVarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVal!=null) ConstVal.traverseTopDown(visitor);
        if(ConstVarList!=null) ConstVarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVal!=null) ConstVal.traverseBottomUp(visitor);
        if(ConstVarList!=null) ConstVarList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl_List(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(ConstVal!=null)
            buffer.append(ConstVal.toString("  "+tab));
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
