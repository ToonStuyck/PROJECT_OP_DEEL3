package worms.model.programs;


public class BooleanExpression implements Expression<Boolean>{
	
	public BooleanExpression(boolean b) {
		booleanexpr = b;
	}

	private boolean booleanexpr;

	@Override
	public Expression<Boolean> getExpression() {
		return this.booleanexpr;
	}

	

}
