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
	
	public void createPartStatementFire() {
		partStatement = new ActionFire();
	}
	
	public void createPartStatementSkip() {
		partStatement = new ActionSkip();
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
	
	public class ActionFire extends PartStatementAction {
		
		public ActionFire() {
		}
	}

	public class ActionSkip extends PartStatementAction {
	
		public ActionSkip() {
		}
	}

	
	
	public void createPartStatementAssignment(String variableName,
			Expression rhs) {
		this.partStatement = new Assignment(variableName, rhs);
	}
	
	public void createPartStatementIf(Expression condition, Statement then,
			Statement otherwise) {
		this.partStatement = new If(condition, then, otherwise);
	}
	
	public void createPartStatementWhile(Expression condition, Statement body) {
		this.partStatement = new While(condition, body);
	}
	
	public class Assignment extends PartStatement{
		public Assignment(String variableName, Expression rhs){
			this.name = variableName;
			this.value = rhs;
		}
		
		public String name;
		public Expression value;
	}
	
	public class If extends PartStatement{
		public If(Expression condition, Statement then, Statement otherwise){
			this.condition = condition;
			this.then = then;
			this.otherwise = otherwise;
		}
		
		public Expression condition;
		public Statement then;
		public Statement otherwise;
	}
	
	public class While extends PartStatement{
		public While(Expression condition, Statement body){
			this.body = body;
		}
		
		public Statement body;
	}

}
