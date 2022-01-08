// generated with ast extension for cup
// version 0.8
// 8/0/2022 2:52:6


package rs.ac.bg.etf.pp1.ast;

public class AssignExprDerived1 extends AssignExpr {

    public AssignExprDerived1 () {
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
        buffer.append("AssignExprDerived1(\n");

        buffer.append(tab);
        buffer.append(") [AssignExprDerived1]");
        return buffer.toString();
    }
}
