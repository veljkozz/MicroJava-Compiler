// generated with ast extension for cup
// version 0.8
// 7/0/2022 18:9:42


package rs.ac.bg.etf.pp1.ast;

public class RecordContentDerived1 extends RecordContent {

    private RecordContent RecordContent;
    private VarDeclarations VarDeclarations;

    public RecordContentDerived1 (RecordContent RecordContent, VarDeclarations VarDeclarations) {
        this.RecordContent=RecordContent;
        if(RecordContent!=null) RecordContent.setParent(this);
        this.VarDeclarations=VarDeclarations;
        if(VarDeclarations!=null) VarDeclarations.setParent(this);
    }

    public RecordContent getRecordContent() {
        return RecordContent;
    }

    public void setRecordContent(RecordContent RecordContent) {
        this.RecordContent=RecordContent;
    }

    public VarDeclarations getVarDeclarations() {
        return VarDeclarations;
    }

    public void setVarDeclarations(VarDeclarations VarDeclarations) {
        this.VarDeclarations=VarDeclarations;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RecordContent!=null) RecordContent.accept(visitor);
        if(VarDeclarations!=null) VarDeclarations.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RecordContent!=null) RecordContent.traverseTopDown(visitor);
        if(VarDeclarations!=null) VarDeclarations.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RecordContent!=null) RecordContent.traverseBottomUp(visitor);
        if(VarDeclarations!=null) VarDeclarations.traverseBottomUp(visitor);
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

        if(VarDeclarations!=null)
            buffer.append(VarDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RecordContentDerived1]");
        return buffer.toString();
    }
}
