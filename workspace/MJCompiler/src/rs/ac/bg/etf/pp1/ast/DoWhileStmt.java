// generated with ast extension for cup
// version 0.8
// 12/0/2022 13:35:55


package rs.ac.bg.etf.pp1.ast;

public class DoWhileStmt extends SingleStatement {

    private DO_NT DO_NT;
    private Statements Statements;
    private Condition Condition;

    public DoWhileStmt (DO_NT DO_NT, Statements Statements, Condition Condition) {
        this.DO_NT=DO_NT;
        if(DO_NT!=null) DO_NT.setParent(this);
        this.Statements=Statements;
        if(Statements!=null) Statements.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
    }

    public DO_NT getDO_NT() {
        return DO_NT;
    }

    public void setDO_NT(DO_NT DO_NT) {
        this.DO_NT=DO_NT;
    }

    public Statements getStatements() {
        return Statements;
    }

    public void setStatements(Statements Statements) {
        this.Statements=Statements;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DO_NT!=null) DO_NT.accept(visitor);
        if(Statements!=null) Statements.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DO_NT!=null) DO_NT.traverseTopDown(visitor);
        if(Statements!=null) Statements.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DO_NT!=null) DO_NT.traverseBottomUp(visitor);
        if(Statements!=null) Statements.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DoWhileStmt(\n");

        if(DO_NT!=null)
            buffer.append(DO_NT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statements!=null)
            buffer.append(Statements.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DoWhileStmt]");
        return buffer.toString();
    }
}
