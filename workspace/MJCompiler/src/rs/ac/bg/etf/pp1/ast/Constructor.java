// generated with ast extension for cup
// version 0.8
// 16/4/2022 17:58:8


package rs.ac.bg.etf.pp1.ast;

public class Constructor implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ConstructorName ConstructorName;
    private OptionalVarDeclarations OptionalVarDeclarations;
    private Statements Statements;

    public Constructor (ConstructorName ConstructorName, OptionalVarDeclarations OptionalVarDeclarations, Statements Statements) {
        this.ConstructorName=ConstructorName;
        if(ConstructorName!=null) ConstructorName.setParent(this);
        this.OptionalVarDeclarations=OptionalVarDeclarations;
        if(OptionalVarDeclarations!=null) OptionalVarDeclarations.setParent(this);
        this.Statements=Statements;
        if(Statements!=null) Statements.setParent(this);
    }

    public ConstructorName getConstructorName() {
        return ConstructorName;
    }

    public void setConstructorName(ConstructorName ConstructorName) {
        this.ConstructorName=ConstructorName;
    }

    public OptionalVarDeclarations getOptionalVarDeclarations() {
        return OptionalVarDeclarations;
    }

    public void setOptionalVarDeclarations(OptionalVarDeclarations OptionalVarDeclarations) {
        this.OptionalVarDeclarations=OptionalVarDeclarations;
    }

    public Statements getStatements() {
        return Statements;
    }

    public void setStatements(Statements Statements) {
        this.Statements=Statements;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstructorName!=null) ConstructorName.accept(visitor);
        if(OptionalVarDeclarations!=null) OptionalVarDeclarations.accept(visitor);
        if(Statements!=null) Statements.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorName!=null) ConstructorName.traverseTopDown(visitor);
        if(OptionalVarDeclarations!=null) OptionalVarDeclarations.traverseTopDown(visitor);
        if(Statements!=null) Statements.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorName!=null) ConstructorName.traverseBottomUp(visitor);
        if(OptionalVarDeclarations!=null) OptionalVarDeclarations.traverseBottomUp(visitor);
        if(Statements!=null) Statements.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Constructor(\n");

        if(ConstructorName!=null)
            buffer.append(ConstructorName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalVarDeclarations!=null)
            buffer.append(OptionalVarDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statements!=null)
            buffer.append(Statements.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Constructor]");
        return buffer.toString();
    }
}
