package worms.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import worms.gui.game.IActionHandler;
import worms.model.programs.Expression.Expression.Self;
import worms.model.programs.Statement.IllegalActionPointsException;
import worms.model.programs.Statement.PartStatement;
import worms.model.programs.Statement.Statement;
import worms.model.programs.Statement.Statement.ForEach;
import worms.model.programs.Statement.Statement.If;
import worms.model.programs.Statement.Statement.Sequence;
import worms.model.programs.Statement.Statement.While;
import worms.model.programs.Statement.StopProgramException;
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
	
	public void deleteWorm(Worm worm) {
		worms.remove(worm);
	}

	public Map<String, Type> getGlobals() {
		return this.globals;
	}
	
	public IActionHandler getHandler(){
		return this.actionHandler;
	}	

	public void runProgram(){
		nbStatements=0;
		try {
			statement.getPartStatement().execute();
		} catch (IllegalActionPointsException | StopProgramException e) {
			World world = Self.getWorm().getWorld();
			world.startNextTurn();
			return;
		}
		World world = Self.getWorm().getWorld();
		world.startNextTurn();
		ResetProgram();
	}

	
	public void ResetProgram() {
		PartStatement.setAllExecuted(false);
	}
	
	public void increaseNbStatements(){
		nbStatements+=1;
	}
	
	public int getNbStatements(){
		return nbStatements;
	}
	
	private boolean forEach;
	
	public boolean isWellFormed() {
		Statement stm = statement;
		if (stm.getPartStatement() instanceof If) {
			if(!isWellFormedIf(((If)stm.getPartStatement()).getStatements())) {
				return false;
			}
		} else if (stm.getPartStatement() instanceof While) {
			if(!isWellFormedWhile(((While)stm.getPartStatement()).body)) {
				return false;
			}
		} else if (stm.getPartStatement() instanceof ForEach) {
			forEach = true;
			if(!isWellFormedForEach(((ForEach)stm.getPartStatement()).body)) {
				return false;
			}
		} else if (stm.getPartStatement() instanceof Sequence) {
			if(!isWellFormedSequence(((Sequence)stm.getPartStatement()).statements)) {
				return false;
			}
		}  
		return true;
	}

	private boolean isWellFormedIf(List<Statement> statements) {
		for (Statement stm: statements) {
			if (stm.getPartStatement() instanceof If) {
				if(!isWellFormedIf(((If)stm.getPartStatement()).getStatements())) {
					return false;
				}
			} else if (stm.getPartStatement() instanceof While) {
				if(!isWellFormedWhile(((While)stm.getPartStatement()).body)) {
					return false;
				}
			} else if (stm.getPartStatement() instanceof ForEach) {
				forEach = true;
				if(!isWellFormedForEach(((ForEach)stm.getPartStatement()).body)) {
					return false;
				}
			} else if (stm.getPartStatement() instanceof Sequence) {
				if(!isWellFormedSequence(((Sequence)stm.getPartStatement()).statements)) {
					return false;
				}
			}  
		}
		if (forEach == false) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isWellFormedWhile(Statement stm) {
		if (stm.getPartStatement() instanceof If) {
			if(!isWellFormedIf(((If)stm.getPartStatement()).getStatements())) {
				return false;
			}
		} else if (stm.getPartStatement() instanceof While) {
			if(!isWellFormedWhile(((While)stm.getPartStatement()).body)) {
				return false;
			}
		} else if (stm.getPartStatement() instanceof ForEach) {
			forEach = true;
			if(!isWellFormedForEach(((ForEach)stm.getPartStatement()).body)) {
				return false;
			}
		} else if (stm.getPartStatement() instanceof Sequence) {
			if(!isWellFormedSequence(((Sequence)stm.getPartStatement()).statements)) {
				return false;
			}
		} 
		if (forEach == false) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isWellFormedForEach(Statement stm) {
		if (stm.getPartStatement() instanceof If) {
			if(!isWellFormedIf(((If)stm.getPartStatement()).getStatements())) {
				return false;
			}
		} else if (stm.getPartStatement() instanceof While) {
			if(!isWellFormedWhile(((While)stm.getPartStatement()).body)) {
				return false;
			}
		} else if (stm.getPartStatement() instanceof ForEach) {
			if(!isWellFormedForEach(((ForEach)stm.getPartStatement()).body)) {
				return false;
			}
		} else if (stm.getPartStatement() instanceof Sequence) {
			if(!isWellFormedSequence(((Sequence)stm.getPartStatement()).statements)) {
				return false;
			}
		}
		return false;		
	}
	
	private boolean isWellFormedSequence(List<Statement> statements) {
		for (Statement stm: statements) {
			if (stm.getPartStatement() instanceof If) {
				if(!isWellFormedIf(((If)stm.getPartStatement()).getStatements())) {
					return false;
				}
			} else if (stm.getPartStatement() instanceof While) {
				if(!isWellFormedWhile(((While)stm.getPartStatement()).body)) {
					return false;
				}
			} else if (stm.getPartStatement() instanceof ForEach) {
				forEach = true;
				if(!isWellFormedForEach(((ForEach)stm.getPartStatement()).body)) {
					return false;
				}
			} else if (stm.getPartStatement() instanceof Sequence) {
				if(!isWellFormedSequence(((Sequence)stm.getPartStatement()).statements)) {
					return false;
				}
			}
		}
		if (forEach == false) {
			return true;
		} else {
			return false;
		}
	}
	
}
