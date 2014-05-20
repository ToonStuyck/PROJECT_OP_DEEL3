package worms.model;

import java.util.Map;

import worms.gui.game.IActionHandler;
import worms.model.programs.ActionHandler;
import worms.model.programs.Statement.Statement;
import worms.model.programs.Type.Type;

public class Program {

	public Program(Map<String, Type> globals, Statement statement, ProgramFactoryImpl pfi, IActionHandler handler) {
		this.globals = globals;
		this.statement = statement;
		this.actionHandler = handler;
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
	protected IActionHandler actionHandler;
	
	public void setWorm(Worm worm) {
		this.worm = worm;
	}

	public Worm getWorm() {
		return this.worm;
	}
	
	public Map<String, Type> getGlobals() {
		return this.globals;
	}
	
	public IActionHandler getActionhandler() {
		return this.actionHandler;
	}
}
