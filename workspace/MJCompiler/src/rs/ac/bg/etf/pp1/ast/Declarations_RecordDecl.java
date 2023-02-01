// generated with ast extension for cup
// version 0.8
// 16/4/2022 17:58:8


package rs.ac.bg.etf.pp1.ast;

public class Declarations_RecordDecl extends Declaration {

    private RecordDeclaration RecordDeclaration;

    public Declarations_RecordDecl (RecordDeclaration RecordDeclaration) {
        this.RecordDeclaration=RecordDeclaration;
        if(RecordDeclaration!=null) RecordDeclaration.setParent(this);
    }

    public RecordDeclaration getRecordDeclaration() {
        return RecordDeclaration;
    }

    public void setRecordDeclaration(RecordDeclaration RecordDeclaration) {
        this.RecordDeclaration=RecordDeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RecordDeclaration!=null) RecordDeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RecordDeclaration!=null) RecordDeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RecordDeclaration!=null) RecordDeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Declarations_RecordDecl(\n");

        if(RecordDeclaration!=null)
            buffer.append(RecordDeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Declarations_RecordDecl]");
        return buffer.toString();
    }
}
