// generated with ast extension for cup
// version 0.8
// 13/0/2022 22:18:15


package rs.ac.bg.etf.pp1.ast;

public class ActualPars_List extends ActualPars {

    private ActualPars ActualPars;
    private Expr Expr;

    public ActualPars_List (ActualPars ActualPars, Expr Expr) {
        this.ActualPars=ActualPars;
        if(ActualPars!=null) ActualPars.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public ActualPars getActualPars() {
        return ActualPars;
    }

    public void setActualPars(ActualPars ActualPars) {
        this.ActualPars=ActualPars;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActualPars!=null) ActualPars.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualPars!=null) ActualPars.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualPars!=null) ActualPars.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualPars_List(\n");

        if(ActualPars!=null)
            buffer.append(ActualPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualPars_List]");
        return buffer.toString();
    }
}
