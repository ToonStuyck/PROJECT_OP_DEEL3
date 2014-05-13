package worms.model.programs;

public class PartExpressionLogic extends PartExpressionOperator{
	
	public Boolean getLeftValue() {
		return (Boolean) getLeftExpression().getPartExpression().getValue();
	}

	public Boolean getRightValue() {
		return (Boolean) getRightExpression().getPartExpression().getValue();
	}
}
