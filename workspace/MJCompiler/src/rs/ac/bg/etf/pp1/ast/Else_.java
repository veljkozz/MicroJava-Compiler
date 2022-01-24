// generated with ast extension for cup
// version 0.8
// 19/0/2022 1:30:41


package rs.ac.bg.etf.pp1.ast;

public class Else_ extends Else {

    public Else_ () {
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
        buffer.append("Else_(\n");

        buffer.append(tab);
        buffer.append(") [Else_]");
        return buffer.toString();
    }
}
