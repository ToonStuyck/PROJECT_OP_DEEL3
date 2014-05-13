package worms.model.programs;

public abstract class DoubleExpression implements Expression<Double> {
	
	@Override
	public abstract Double getExpression();
	
	@Override
    public final Class<Double> getType() {
        return Double.class;
    }

}
