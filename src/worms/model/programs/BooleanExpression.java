package worms.model.programs;

public abstract class BooleanExpression implements Expression<Boolean>{
	

	@Override
	public abstract Boolean getExpression(Expression<?> e);
	
}
