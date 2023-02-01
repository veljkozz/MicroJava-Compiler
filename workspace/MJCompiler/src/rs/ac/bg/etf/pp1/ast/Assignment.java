// generated with ast extension for cup
// version 0.8
// 16/4/2022 17:58:8


package rs.ac.bg.etf.pp1.ast;

public class Assignment extends DesignatorStatement {

    private Designator Designator;
    private AssignOp AssignOp;
    private AssignExpr AssignExpr;

    public Assignment (Designator Designator, AssignOp AssignOp, AssignExpr AssignExpr) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.AssignOp=AssignOp;
        if(AssignOp!=null) AssignOp.setParent(this);
        this.AssignExpr=AssignExpr;
        if(AssignExpr!=null) AssignExpr.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public AssignOp getAssignOp() {
        return AssignOp;
    }

    public void setAssignOp(AssignOp AssignOp) {
        this.AssignOp=AssignOp;
    }

    public AssignExpr getAssignExpr() {
        return AssignExpr;
    }

    public void setAssignExpr(AssignExpr AssignExpr) {
        this.AssignExpr=AssignExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(AssignOp!=null) AssignOp.accept(visitor);
        if(AssignExpr!=null) AssignExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(AssignOp!=null) AssignOp.traverseTopDown(visitor);
        if(AssignExpr!=null) AssignExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(AssignOp!=null) AssignOp.traverseBottomUp(visitor);
        if(AssignExpr!=null) AssignExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Assignment(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AssignOp!=null)
            buffer.append(AssignOp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AssignExpr!=null)
            buffer.append(AssignExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Assignment]");
        return buffer.toString();
    }
}
