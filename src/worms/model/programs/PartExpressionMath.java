package worms.model.programs;

public class PartExpressionMath extends PartExpressionOperator{
	
	protected Expression expression;
	
	public Expression getExpression() {
		return this.expression;
	}
	
	public Double getLeftValue() {
		return (Double) getLeftExpression().getPartExpression().getValue();
	}

	public Double getRightValue() {
		return (Double) getRightExpression().getPartExpression().getValue();
	}

}