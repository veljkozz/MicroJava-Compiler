// generated with ast extension for cup
// version 0.8
// 15/0/2022 2:5:44


package rs.ac.bg.etf.pp1.ast;

public class If_ extends If {

    public If_ () {
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
        buffer.append("If_(\n");

        buffer.append(tab);
        buffer.append(") [If_]");
        return buffer.toString();
    }
}
