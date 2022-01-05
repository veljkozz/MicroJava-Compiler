// generated with ast extension for cup
// version 0.8
// 5/0/2022 1:35:55


package rs.ac.bg.etf.pp1.ast;

public class RecordContentDerived2 extends RecordContent {

    public RecordContentDerived2 () {
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
        buffer.append("RecordContentDerived2(\n");

        buffer.append(tab);
        buffer.append(") [RecordContentDerived2]");
        return buffer.toString();
    }
}
