package worms.model;

public class Program {

	public Program(Worm worm) {
		this.worm = worm;
	}

	protected Worm worm;
	
	public Worm getWorm() {
		return this.worm;
	}
}
