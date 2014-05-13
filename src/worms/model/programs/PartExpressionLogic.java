package worms.model.programs;

public class PartExpressionLogic extends PartExpressionOperator{
	
	protected Expression expression;
	
	public Boolean getLeftValue() {
		return (Boolean) getLeftExpression().getPartExpression().getValue();
	}

	public Boolean getRightValue() {
		return (Boolean) getRightExpression().getPartExpression().getValue();
	}
	
	public Expression getExpression() {
		return this.expression;
	}
	
	public Boolean getLogicValue() {
		return (Boolean) getExpression().getPartExpression().getValue();
	}
}
