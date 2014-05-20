package worms.model;

import java.util.List;
import java.util.Map;

import worms.gui.game.IActionHandler;
import worms.model.programs.ActionHandler;
import worms.model.programs.ParseOutcome;
import worms.model.programs.ProgramParser;
import worms.model.programs.Expression.Expression;
import worms.model.programs.Statement.Statement;
import worms.model.programs.Type.Type;

public class Program {

	public Program(Map<String, Type> globals, Statement statements, ProgramFactoryImpl pfi) {
		this.globals = globals;
		this.statement = statements;
		this.pfi = pfi;
	}
	protected Map<String, Type> globals;
	protected Statement statement;
	protected List<String> errors;
	protected Worm worm;
	protected ProgramFactoryImpl pfi;
	protected IActionHandler actionHandler;
	
	public void setWorm(Worm worm) {
		this.worm = worm;
	}
	
	public ProgramFactoryImpl getPfi() {
		return this.pfi;
	}

	public Worm getWorm() {
		return this.worm;
	}
	
	public Map<String, Type> getGlobals() {
		return this.globals;
	}
	
	public List<String> getErrors() {
		return this.errors;
	}
	
	public IActionHandler getActionhandler() {
		return this.actionHandler;
	}
}
