// generated with ast extension for cup
// version 0.8
// 7/0/2022 16:15:17


package rs.ac.bg.etf.pp1.ast;

public class Record_Declaration extends RecordDeclaration {

    private String I1;
    private RecordContent RecordContent;

    public Record_Declaration (String I1, RecordContent RecordContent) {
        this.I1=I1;
        this.RecordContent=RecordContent;
        if(RecordContent!=null) RecordContent.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public RecordContent getRecordContent() {
        return RecordContent;
    }

    public void setRecordContent(RecordContent RecordContent) {
        this.RecordContent=RecordContent;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RecordContent!=null) RecordContent.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RecordContent!=null) RecordContent.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RecordContent!=null) RecordContent.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Record_Declaration(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(RecordContent!=null)
            buffer.append(RecordContent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Record_Declaration]");
        return buffer.toString();
    }
}
