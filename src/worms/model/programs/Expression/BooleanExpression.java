package worms.model.programs.Expression;

import worms.model.programs.Type.BooleanType;

public abstract class BooleanExpression extends PartExpression{
	
	@Override
	public abstract BooleanType getValue();

}
