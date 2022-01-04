// generated with ast extension for cup
// version 0.8
// 5/0/2022 0:27:14


package rs.ac.bg.etf.pp1.ast;

public class ConstructorDerived1 extends Constructor {

    private String I1;
    private OptionalVarDeclarations OptionalVarDeclarations;
    private Statements Statements;

    public ConstructorDerived1 (String I1, OptionalVarDeclarations OptionalVarDeclarations, Statements Statements) {
        this.I1=I1;
        this.OptionalVarDeclarations=OptionalVarDeclarations;
        if(OptionalVarDeclarations!=null) OptionalVarDeclarations.setParent(this);
        this.Statements=Statements;
        if(Statements!=null) Statements.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalVarDeclarations!=null) OptionalVarDeclarations.accept(visitor);
        if(Statements!=null) Statements.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalVarDeclarations!=null) OptionalVarDeclarations.traverseTopDown(visitor);
        if(Statements!=null) Statements.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalVarDeclarations!=null) OptionalVarDeclarations.traverseBottomUp(visitor);
        if(Statements!=null) Statements.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstructorDerived1(\n");

        buffer.append(" "+tab+I1);
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
        buffer.append(") [ConstructorDerived1]");
        return buffer.toString();
    }
}
