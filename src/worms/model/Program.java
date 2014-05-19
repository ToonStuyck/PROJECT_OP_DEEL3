package worms.model;

import java.util.Map;

import worms.model.programs.Statement;
import worms.model.programs.Type.Type;

public class Program {

	public Program(Map<String, Type> globals, Statement statement, ProgramFactoryImpl pfi) {
		this.globals = globals;
		this.statement = statement;
		setPfi(pfi);
	}
	
	public void setPfi(ProgramFactoryImpl pfi) {
		this.pfi = pfi;
		pfi.setProgram(this);
	}

	protected Map<String, Type> globals;
	protected Statement statement;
	protected Worm worm;
	protected ProgramFactoryImpl pfi;
	
	public void setWorm(Worm worm) {
		this.worm = worm;
	}

	public Worm getWorm() {
		return this.worm;
	}
}
