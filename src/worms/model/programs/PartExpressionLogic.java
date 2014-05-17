package worms.model.programs;

import worms.model.programs.Expression.BooleanLiteral;

public abstract class PartExpressionLogic extends PartExpressionOperator{
	
	protected Expression expression;
	
	public boolean getLeftValue() {
		return ((BooleanLiteral) getLeftExpression().getPartExpression()).getValue();
	}

	public boolean getRightValue() {
		return ((BooleanLiteral) getRightExpression().getPartExpression()).getValue();
	}
	
}
