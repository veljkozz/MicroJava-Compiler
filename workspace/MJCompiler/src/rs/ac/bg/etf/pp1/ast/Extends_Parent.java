// generated with ast extension for cup
// version 0.8
// 16/4/2022 17:58:8


package rs.ac.bg.etf.pp1.ast;

public class Extends_Parent extends Extends {

    private String parentName;

    public Extends_Parent (String parentName) {
        this.parentName=parentName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName=parentName;
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
        buffer.append("Extends_Parent(\n");

        buffer.append(" "+tab+parentName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Extends_Parent]");
        return buffer.toString();
    }
}
