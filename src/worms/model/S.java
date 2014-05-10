package worms.model;

public class S<T> {
	
	
	public void turn(T element) throws IllegalStateException,
	IllegalArgumentException {
if (this.size == elements.length)
	throw new IllegalStateException();
elements[this.size++] = element;
}

}
