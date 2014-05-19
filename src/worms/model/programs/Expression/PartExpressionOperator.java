package worms.model.programs.Expression;

public abstract class PartExpressionOperator extends PartExpression{
	
	protected Expression left;
	protected Expression right;
	
	public Expression getLeftExpression() {
		return this.left;
	}
	
	public Expression getRightExpression() {
		return this.right;
	}

}
