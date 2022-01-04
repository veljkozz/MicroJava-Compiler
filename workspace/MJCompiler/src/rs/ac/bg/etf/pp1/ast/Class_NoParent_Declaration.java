// generated with ast extension for cup
// version 0.8
// 5/0/2022 0:27:14


package rs.ac.bg.etf.pp1.ast;

public class Class_NoParent_Declaration extends ClassDeclaration {

    private String ClassName;
    private ClassContent ClassContent;

    public Class_NoParent_Declaration (String ClassName, ClassContent ClassContent) {
        this.ClassName=ClassName;
        this.ClassContent=ClassContent;
        if(ClassContent!=null) ClassContent.setParent(this);
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
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
        if(ClassContent!=null) ClassContent.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassContent!=null) ClassContent.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassContent!=null) ClassContent.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Class_NoParent_Declaration(\n");

        buffer.append(" "+tab+ClassName);
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