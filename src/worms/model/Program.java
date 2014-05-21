package worms.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import worms.gui.game.IActionHandler;
import worms.model.programs.Expression.Expression.Self;
import worms.model.programs.Statement.Statement;
import worms.model.programs.Type.Type;

public class Program {

	public Program(Map<String, Type> globals, Statement statements, String programText, IActionHandler handler) {
		this.globals = globals;
		this.statement = statements;
		this.programText = programText;
		this.actionHandler = handler;
	}
	protected Map<String, Type> globals;
	protected Statement statement;
	protected String programText;
	protected IActionHandler actionHandler;
	
	public final List<Worm> worms = new ArrayList<Worm>();
	public int nbStatements;
	
	public void addWorm(Worm worm) {
		worms.add(worm);
	}

	public Map<String, Type> getGlobals() {
		return this.globals;
	}
	
	public IActionHandler getHandler(){
		return this.actionHandler;
	}

	public void runProgram(){
		nbStatements=0;
		statement.getPartStatement().execute();
		World world = Self.getWorm().getWorld();
		if (world.isGameFinished()){
			world.getWinner();
		}
		world.startNextTurn();
	}

	public void increaseNbStatements(){
		nbStatements+=1;
	}
	
	public int getNbStatements(){
		return nbStatements;
	}

}
