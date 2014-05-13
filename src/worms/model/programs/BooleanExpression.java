package worms.model.programs;

public abstract class BooleanExpression implements Expression<Boolean>{
	

	@Override
	public abstract Boolean getExpression();
	
	@Override
    public final Class<Boolean> getType() {
        return Boolean.class;
    }
}
