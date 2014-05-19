package worms.model.programs.Type;

public class EntityType<T> extends Type {
	
	public EntityType(T o) {
		this.value =o;
	}
	
	public T value;

	@Override
	public T getValue() {
		return this.value;
	}

}
