// generated with ast extension for cup
// version 0.8
// 14/0/2022 16:46:17


package rs.ac.bg.etf.pp1.ast;

public class ConstVal_Num_Neg extends ConstVal {

    private Integer N1;

    public ConstVal_Num_Neg (Integer N1) {
        this.N1=N1;
    }

    public Integer getN1() {
        return N1;
    }

    public void setN1(Integer N1) {
        this.N1=N1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstVal_Num_Neg(\n");

        buffer.append(" "+tab+N1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstVal_Num_Neg]");
        return buffer.toString();
    }
}
