package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {
	
	int count;
	
	public static class FormParamCounter extends CounterVisitor
	{
		public void visit(Form_Param formParam)
		{
			count++;
		}
		public void visit(Form_Param_Arr formParamArr)
		{
			count++;
		}
	}
	
	public static class VarCounter extends CounterVisitor
	{
		public void visit(Var_ formParam)
		{
			count++;
		}
		public void visit(Var_Arr formParamArr)
		{
			count++;
		}
	}
}
