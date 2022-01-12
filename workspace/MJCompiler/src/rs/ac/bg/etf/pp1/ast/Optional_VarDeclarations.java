// generated with ast extension for cup
// version 0.8
// 12/0/2022 13:35:55


package rs.ac.bg.etf.pp1.ast;

public class Optional_VarDeclarations extends OptionalVarDeclarations {

    private VarDeclarations VarDeclarations;
    private OptionalVarDeclarations OptionalVarDeclarations;

    public Optional_VarDeclarations (VarDeclarations VarDeclarations, OptionalVarDeclarations OptionalVarDeclarations) {
        this.VarDeclarations=VarDeclarations;
        if(VarDeclarations!=null) VarDeclarations.setParent(this);
        this.OptionalVarDeclarations=OptionalVarDeclarations;
        if(OptionalVarDeclarations!=null) OptionalVarDeclarations.setParent(this);
    }

    public VarDeclarations getVarDeclarations() {
        return VarDeclarations;
    }

    public void setVarDeclarations(VarDeclarations VarDeclarations) {
        this.VarDeclarations=VarDeclarations;
    }

    public OptionalVarDeclarations getOptionalVarDeclarations() {
        return OptionalVarDeclarations;
    }

    public void setOptionalVarDeclarations(OptionalVarDeclarations OptionalVarDeclarations) {
        this.OptionalVarDeclarations=OptionalVarDeclarations;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclarations!=null) VarDeclarations.accept(visitor);
        if(OptionalVarDeclarations!=null) OptionalVarDeclarations.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclarations!=null) VarDeclarations.traverseTopDown(visitor);
        if(OptionalVarDeclarations!=null) OptionalVarDeclarations.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclarations!=null) VarDeclarations.traverseBottomUp(visitor);
        if(OptionalVarDeclarations!=null) OptionalVarDeclarations.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Optional_VarDeclarations(\n");

        if(VarDeclarations!=null)
            buffer.append(VarDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalVarDeclarations!=null)
            buffer.append(OptionalVarDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Optional_VarDeclarations]");
        return buffer.toString();
    }
}
