package worms.model.programs;

import worms.model.programs.Expression.Expression;

public class PartStatementAction extends PartStatement{
	
	protected Expression expression;
	
	public Object getActionValue() {
		return this.expression.getPartExpression().getValue();
	}

}
