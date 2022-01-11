// generated with ast extension for cup
// version 0.8
// 11/0/2022 1:29:58


package rs.ac.bg.etf.pp1.ast;

public class Class_Parent_Declaration extends ClassDeclaration {

    private ClassName ClassName;
    private Extends Extends;
    private ClassContent ClassContent;

    public Class_Parent_Declaration (ClassName ClassName, Extends Extends, ClassContent ClassContent) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.Extends=Extends;
        if(Extends!=null) Extends.setParent(this);
        this.ClassContent=ClassContent;
        if(ClassContent!=null) ClassContent.setParent(this);
    }

    public ClassName getClassName() {
        return ClassName;
    }

    public void setClassName(ClassName ClassName) {
        this.ClassName=ClassName;
    }

    public Extends getExtends() {
        return Extends;
    }

    public void setExtends(Extends Extends) {
        this.Extends=Extends;
    }

    public ClassContent getClassContent() {
        return ClassContent;
    }

    public void setClassContent(ClassContent ClassContent) {
        this.ClassContent=ClassContent;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassName!=null) ClassName.accept(visitor);
        if(Extends!=null) Extends.accept(visitor);
        if(ClassContent!=null) ClassContent.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(Extends!=null) Extends.traverseTopDown(visitor);
        if(ClassContent!=null) ClassContent.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
        if(Extends!=null) Extends.traverseBottomUp(visitor);
        if(ClassContent!=null) ClassContent.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Class_Parent_Declaration(\n");

        if(ClassName!=null)
            buffer.append(ClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Extends!=null)
            buffer.append(Extends.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassContent!=null)
            buffer.append(ClassContent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Class_Parent_Declaration]");
        return buffer.toString();
    }
}
