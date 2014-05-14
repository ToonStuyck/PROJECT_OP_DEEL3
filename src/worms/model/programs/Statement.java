package worms.model.programs;

public class Statement {
	
	public Statement(int line, int column) {
		this.line = line;
		this.column = column;
	}

	private int line;
	private int column;
	private PartStatement partStatement;
	
	public PartStatement getPartStatement() {
		return this.partStatement;
	}

	
	
	public void createPartStatementTurn(Expression angle) {
		partStatement = new ActionTurn(angle);
	}
	
	public void createPartStatementMove() {
		partStatement = new ActionMove();
	}
	
	public void createPartStatementJump() {
		partStatement = new ActionJump();
	}
	
	public void createPartStatementToggleWeap() {
		partStatement = new ActionToggleWeap();
	}

	
	public class ActionTurn extends PartStatementAction {
		
		public ActionTurn(Expression angle) {
			this.expression = angle;
		}
		
		public Object GetValue() {
			return this.getActionValue();
		}
	}
	
	public class ActionMove extends PartStatementAction {
		
		public ActionMove() {
		}
	}

	public class ActionJump extends PartStatementAction {
		
		public ActionJump() {
		}
	}
	
	public class ActionToggleWeap extends PartStatementAction {
		
		public ActionToggleWeap() {
		}
	}
	

}
