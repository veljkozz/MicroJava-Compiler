// generated with ast extension for cup
// version 0.8
// 5/0/2022 1:35:55


package rs.ac.bg.etf.pp1.ast;

public class Class_NoParent_Declaration extends ClassDeclaration {

    private ClassName ClassName;
    private ClassContent ClassContent;

    public Class_NoParent_Declaration (ClassName ClassName, ClassContent ClassContent) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.ClassContent=ClassContent;
        if(ClassContent!=null) ClassContent.setParent(this);
    }

    public ClassName getClassName() {
        return ClassName;
    }

    public void setClassName(ClassName ClassName) {
        this.ClassName=ClassName;
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
        if(ClassContent!=null) ClassContent.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(ClassContent!=null) ClassContent.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
        if(ClassContent!=null) ClassContent.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Class_NoParent_Declaration(\n");

        if(ClassName!=null)
            buffer.append(ClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassContent!=null)
            buffer.append(ClassContent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Class_NoParent_Declaration]");
        return buffer.toString();
    }
}
