package worms.model.programs;

public class PartStatementAction extends PartStatement{
	
	protected Expression expression;
	
	public Object getActionValue() {
		return this.expression.getPartExpression().getValue();
	}

}
