// generated with ast extension for cup
// version 0.8
// 14/0/2022 16:46:17


package rs.ac.bg.etf.pp1.ast;

public class ClassVarListDerived1 extends ClassVarList {

    private ClassVar ClassVar;
    private ClassVarList ClassVarList;

    public ClassVarListDerived1 (ClassVar ClassVar, ClassVarList ClassVarList) {
        this.ClassVar=ClassVar;
        if(ClassVar!=null) ClassVar.setParent(this);
        this.ClassVarList=ClassVarList;
        if(ClassVarList!=null) ClassVarList.setParent(this);
    }

    public ClassVar getClassVar() {
        return ClassVar;
    }

    public void setClassVar(ClassVar ClassVar) {
        this.ClassVar=ClassVar;
    }

    public ClassVarList getClassVarList() {
        return ClassVarList;
    }

    public void setClassVarList(ClassVarList ClassVarList) {
        this.ClassVarList=ClassVarList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVar!=null) ClassVar.accept(visitor);
        if(ClassVarList!=null) ClassVarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVar!=null) ClassVar.traverseTopDown(visitor);
        if(ClassVarList!=null) ClassVarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVar!=null) ClassVar.traverseBottomUp(visitor);
        if(ClassVarList!=null) ClassVarList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassVarListDerived1(\n");

        if(ClassVar!=null)
            buffer.append(ClassVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarList!=null)
            buffer.append(ClassVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassVarListDerived1]");
        return buffer.toString();
    }
}
