package worms.model.programs;

import worms.model.Expression;

public class Expression {
	
	public Expression(int line, int column) {
		this.line = line;
		this.column = column;
	}

	private int line;
	private int column;
	private PartExpression partExpression;
	
	public void createLogicAnd(Expression e1, Expression e2) {
		this.partExpression = new LogicAnd(e1,e2);
	}
	

	
}
