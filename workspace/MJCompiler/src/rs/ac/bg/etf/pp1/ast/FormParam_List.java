// generated with ast extension for cup
// version 0.8
// 11/0/2022 1:29:58


package rs.ac.bg.etf.pp1.ast;

public class FormParam_List extends FormParamList {

    private FormParamList FormParamList;
    private FormParam FormParam;

    public FormParam_List (FormParamList FormParamList, FormParam FormParam) {
        this.FormParamList=FormParamList;
        if(FormParamList!=null) FormParamList.setParent(this);
        this.FormParam=FormParam;
        if(FormParam!=null) FormParam.setParent(this);
    }

    public FormParamList getFormParamList() {
        return FormParamList;
    }

    public void setFormParamList(FormParamList FormParamList) {
        this.FormParamList=FormParamList;
    }

    public FormParam getFormParam() {
        return FormParam;
    }

    public void setFormParam(FormParam FormParam) {
        this.FormParam=FormParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParamList!=null) FormParamList.accept(visitor);
        if(FormParam!=null) FormParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParamList!=null) FormParamList.traverseTopDown(visitor);
        if(FormParam!=null) FormParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParamList!=null) FormParamList.traverseBottomUp(visitor);
        if(FormParam!=null) FormParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParam_List(\n");

        if(FormParamList!=null)
            buffer.append(FormParamList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParam!=null)
            buffer.append(FormParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParam_List]");
        return buffer.toString();
    }
}
