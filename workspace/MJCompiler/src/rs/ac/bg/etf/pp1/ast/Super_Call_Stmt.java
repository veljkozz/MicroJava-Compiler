// generated with ast extension for cup
// version 0.8
// 11/0/2022 1:29:58


package rs.ac.bg.etf.pp1.ast;

public class Super_Call_Stmt extends DesignatorStatement {

    private SuperCall SuperCall;
    private ActualPars ActualPars;

    public Super_Call_Stmt (SuperCall SuperCall, ActualPars ActualPars) {
        this.SuperCall=SuperCall;
        if(SuperCall!=null) SuperCall.setParent(this);
        this.ActualPars=ActualPars;
        if(ActualPars!=null) ActualPars.setParent(this);
    }

    public SuperCall getSuperCall() {
        return SuperCall;
    }

    public void setSuperCall(SuperCall SuperCall) {
        this.SuperCall=SuperCall;
    }

    public ActualPars getActualPars() {
        return ActualPars;
    }

    public void setActualPars(ActualPars ActualPars) {
        this.ActualPars=ActualPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SuperCall!=null) SuperCall.accept(visitor);
        if(ActualPars!=null) ActualPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SuperCall!=null) SuperCall.traverseTopDown(visitor);
        if(ActualPars!=null) ActualPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SuperCall!=null) SuperCall.traverseBottomUp(visitor);
        if(ActualPars!=null) ActualPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Super_Call_Stmt(\n");

        if(SuperCall!=null)
            buffer.append(SuperCall.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualPars!=null)
            buffer.append(ActualPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Super_Call_Stmt]");
        return buffer.toString();
    }
}
