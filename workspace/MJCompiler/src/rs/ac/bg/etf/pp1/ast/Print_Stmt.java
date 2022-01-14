// generated with ast extension for cup
// version 0.8
// 13/0/2022 22:18:15


package rs.ac.bg.etf.pp1.ast;

public class Print_Stmt extends SingleStatement {

    private PrintStmt PrintStmt;

    public Print_Stmt (PrintStmt PrintStmt) {
        this.PrintStmt=PrintStmt;
        if(PrintStmt!=null) PrintStmt.setParent(this);
    }

    public PrintStmt getPrintStmt() {
        return PrintStmt;
    }

    public void setPrintStmt(PrintStmt PrintStmt) {
        this.PrintStmt=PrintStmt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(PrintStmt!=null) PrintStmt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(PrintStmt!=null) PrintStmt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(PrintStmt!=null) PrintStmt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Print_Stmt(\n");

        if(PrintStmt!=null)
            buffer.append(PrintStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Print_Stmt]");
        return buffer.toString();
    }
}
