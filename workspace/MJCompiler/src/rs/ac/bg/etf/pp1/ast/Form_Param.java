// generated with ast extension for cup
// version 0.8
// 12/0/2022 13:35:55


package rs.ac.bg.etf.pp1.ast;

public class Form_Param extends FormParam {

    private Type Type;
    private String varname;

    public Form_Param (Type Type, String varname) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.varname=varname;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getVarname() {
        return varname;
    }

    public void setVarname(String varname) {
        this.varname=varname;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Form_Param(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varname);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Form_Param]");
        return buffer.toString();
    }
}
