package worms.model;

import java.util.Map;

import worms.model.programs.Statement;
import worms.model.programs.Type;

public class Program {

	public Program(Map<String, Type> globals, Statement statement) {
		this.globals = globals;
		this.statement = statement;
		
	}

	protected Map<String, Type> globals;
	protected Statement statement;
	protected Worm worm;
	
	public void setWorm(Worm worm) {
		this.worm = worm;
	}

	public Worm getWorm() {
		return this.worm;
	}
}
