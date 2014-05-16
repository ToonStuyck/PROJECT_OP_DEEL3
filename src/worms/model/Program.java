package worms.model;

public class Program {

	public Program(Worm worm, ProgramFactoryImpl pfi) {
		this.worm = worm;
		pfi.setProgram(this);
		
	}

	protected Worm worm;
	
	public Worm getWorm() {
		return this.worm;
	}
}
