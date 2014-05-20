package worms.model.programs.Type;

public class BooleanType extends Type {
	
	public BooleanType(Boolean b) {
		this.value = b;
	}

	public boolean value;
	
	@Override
	public Boolean getValue() {
		return this.value;
	}

}
