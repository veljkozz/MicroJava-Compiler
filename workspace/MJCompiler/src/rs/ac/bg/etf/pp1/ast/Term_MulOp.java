// generated with ast extension for cup
// version 0.8
// 12/0/2022 13:35:55


package rs.ac.bg.etf.pp1.ast;

public class Term_MulOp extends Term {

    private Factor Factor;
    private MulOp MulOp;
    private Term Term;

    public Term_MulOp (Factor Factor, MulOp MulOp, Term Term) {
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
        this.MulOp=MulOp;
        if(MulOp!=null) MulOp.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public MulOp getMulOp() {
        return MulOp;
    }

    public void setMulOp(MulOp MulOp) {
        this.MulOp=MulOp;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Factor!=null) Factor.accept(visitor);
        if(MulOp!=null) MulOp.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
        if(MulOp!=null) MulOp.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        if(MulOp!=null) MulOp.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Term_MulOp(\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MulOp!=null)
            buffer.append(MulOp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Term_MulOp]");
        return buffer.toString();
    }
}
