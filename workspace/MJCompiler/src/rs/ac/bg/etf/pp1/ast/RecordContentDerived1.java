// generated with ast extension for cup
// version 0.8
// 19/0/2022 1:30:41


package rs.ac.bg.etf.pp1.ast;

public class RecordContentDerived1 extends RecordContent {

    private RecordContent RecordContent;
    private ClassVarDeclarations ClassVarDeclarations;

    public RecordContentDerived1 (RecordContent RecordContent, ClassVarDeclarations ClassVarDeclarations) {
        this.RecordContent=RecordContent;
        if(RecordContent!=null) RecordContent.setParent(this);
        this.ClassVarDeclarations=ClassVarDeclarations;
        if(ClassVarDeclarations!=null) ClassVarDeclarations.setParent(this);
    }

    public RecordContent getRecordContent() {
        return RecordContent;
    }

    public void setRecordContent(RecordContent RecordContent) {
        this.RecordContent=RecordContent;
    }

    public ClassVarDeclarations getClassVarDeclarations() {
        return ClassVarDeclarations;
    }

    public void setClassVarDeclarations(ClassVarDeclarations ClassVarDeclarations) {
        this.ClassVarDeclarations=ClassVarDeclarations;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RecordContent!=null) RecordContent.accept(visitor);
        if(ClassVarDeclarations!=null) ClassVarDeclarations.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RecordContent!=null) RecordContent.traverseTopDown(visitor);
        if(ClassVarDeclarations!=null) ClassVarDeclarations.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RecordContent!=null) RecordContent.traverseBottomUp(visitor);
        if(ClassVarDeclarations!=null) ClassVarDeclarations.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RecordContentDerived1(\n");

        if(RecordContent!=null)
            buffer.append(RecordContent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarDeclarations!=null)
            buffer.append(ClassVarDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RecordContentDerived1]");
        return buffer.toString();
    }
}
