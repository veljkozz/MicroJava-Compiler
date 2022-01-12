// generated with ast extension for cup
// version 0.8
// 12/0/2022 13:35:54


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl_Single extends ConstVarList {

    private String varName;
    private ConstVal ConstVal;

    public ConstDecl_Single (String varName, ConstVal ConstVal) {
        this.varName=varName;
        this.ConstVal=ConstVal;
        if(ConstVal!=null) ConstVal.setParent(this);
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstVal!=null) ConstVal.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVal!=null) ConstVal.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVal!=null) ConstVal.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl_Single(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(ConstVal!=null)
            buffer.append(ConstVal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl_Single]");
        return buffer.toString();
    }
}
