package worms.model.programs.Type;

public class DoubleType extends Type {
	
	public DoubleType(double d) {
		this.value = d;
	}
	
	public double value;

	@Override
	public Double getValue() {
		return this.value;
	}
}
