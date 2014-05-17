package worms.model.programs;

public abstract class PartExpressionCompare extends PartExpressionOperator {

	public Double getLeftValue() {
		return (Double) getLeftExpression().getPartExpression().getValue();
	}

	public Double getRightValue() {
		return (Double) getRightExpression().getPartExpression().getValue();
	}
	
}
