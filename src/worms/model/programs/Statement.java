package worms.model.programs;

import java.util.List;

import worms.model.Food;
import worms.model.Program;
import worms.model.Worm;
import worms.model.programs.ProgramFactory.ForeachType;
import worms.model.programs.Expression.Expression;
import worms.model.programs.Expression.Expression.BooleanLiteral;
import worms.model.programs.Expression.Expression.DoubleLiteral;
import worms.model.programs.Type.BooleanType;
import worms.model.programs.Type.DoubleType;
import worms.model.programs.Type.EntityType;

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

	
	
	public void createPartStatementTurn(Expression angle, Program program) {
		partStatement = new ActionTurn(angle, program);
	}
	
	public void createPartStatementMove(Program program) {
		partStatement = new ActionMove(program);
	}
	
	public void createPartStatementJump(Program program) {
		partStatement = new ActionJump(program);
	}
	
	public void createPartStatementToggleWeap(Program program) {
		partStatement = new ActionToggleWeap(program);
	}
	
	public void createPartStatementFire(Expression yield, Program program) {
		partStatement = new ActionFire(yield, program);
	}
	
	public void createPartStatementSkip(Program program) {
		partStatement = new ActionSkip(program);
	}

	
	public class ActionTurn extends PartStatementAction {
		
		public ActionTurn(Expression angle, Program program) {
			this.angle = (DoubleLiteral) angle.getPartExpression();
			this.program = program;
		}
		
		public Program program;
		public DoubleLiteral angle;
		
		@Override
		public void execute() {
			this.program.getActionhandler().turn(program.getWorm(), this.angle.getValue().getValue());
		}
	}
	
	public class ActionMove extends PartStatementAction {
		
		public ActionMove(Program program) {
			this.program = program;
		}
		
		public Program program;
		
		@Override
		public void execute() {
			this.program.getActionhandler().move(program.getWorm());
		}
	}

	public class ActionJump extends PartStatementAction {
		
		public ActionJump(Program program) {
			this.program = program;
		}
		
		public Program program;
		
		@Override
		public void execute() {
			this.program.getActionhandler().jump(program.getWorm());
		}
	}
	
	public class ActionToggleWeap extends PartStatementAction {
		
		public ActionToggleWeap(Program program) {
			this.program = program;
		}
		
		public Program program;
		
		@Override
		public void execute() {
			this.program.getActionhandler().toggleWeapon(program.getWorm());
		}
	}
	
	public class ActionFire extends PartStatementAction {
		
		public ActionFire(Expression yield, Program program) {
			this.program = program;
			this.yield = (DoubleLiteral) yield.getPartExpression();
		}
		
		public Program program;
		public DoubleLiteral yield;
		
		@Override
		public void execute() {
			this.program.getActionhandler().fire(this.program.getWorm(), (int) Math.round(this.yield.getValue().getValue()));
		}
	}

	public class ActionSkip extends PartStatementAction {
	
		public ActionSkip(Program program) {
			this.program = program;
		}
		
		public Program program;
		
		@Override
		public void execute() {
			this.program.getWorm().getWorld().startNextTurn();
		}
	}

	
	
	public void createPartStatementAssignment(String variableName,
			Expression rhs, Program program) {
		this.partStatement = new Assignment(variableName, rhs, program);
	}
	
	public void createPartStatementIf(Expression condition, Statement then,
			Statement otherwise) {
		this.partStatement = new If(condition, then, otherwise);
	}
	
	public void createPartStatementWhile(Expression condition, Statement body) {
		this.partStatement = new While(condition, body);
	}
	
	public void createPartStatementForEach(
			worms.model.programs.ProgramFactory.ForeachType type,
			String variableName, Statement body, Program program) {
		this.partStatement = new ForEach(type, variableName, body, program);
	}
	
	public void createPartStatementSequence(List<Statement> statements) {
		this.partStatement = new Sequence(statements);
	}
	
	public void createPartStatementPrint(Expression e) {
		this.partStatement = new Print(e);
	}
	
	
	public class Assignment extends PartStatement{
		public Assignment(String variableName, Expression rhs, Program program){
			this.name = variableName;
			this.value = rhs;
			this.program = program;
		}
		
		public Program program;
		public String name;
		public Expression value;
		
		@Override
		public void execute() {
			if (this.value.getPartExpression().getValue() instanceof DoubleLiteral) {
				this.program.getGlobals().put(this.name, new DoubleType((double) this.value.getPartExpression().getValue()));
			} else if (this.value.getPartExpression().getValue() instanceof BooleanLiteral){
				this.program.getGlobals().put(this.name, new BooleanType((boolean) this.value.getPartExpression().getValue()));
			} else {
				this.program.getGlobals().put(this.name, new EntityType<Object>((Object) this.value.getPartExpression().getValue()));
			}
		}
	}
	
	public class If extends PartStatement{
		public If(Expression condition, Statement then, Statement otherwise){
			this.condition = condition;
			this.then = then;
			this.other = otherwise;
		}
		
		public Expression condition;
		public Statement then;
		public Statement other;
		
		public void execute() {
			if ((boolean) condition.getPartExpression().getValue()) {
				this.then.getPartStatement().execute();
			} else {
				this.other.getPartStatement().execute();
			}
		}
	}
	
	public class While extends PartStatement{
		public While(Expression condition, Statement body){
			this.condition = condition;
			this.body = body;
		}
		
		public Statement body;
		public Expression condition;
		
		public void execute() {
			while ((boolean) condition.getPartExpression().getValue()) {
				this.body.getPartStatement().execute();
			} 
		}
	}

	public class ForEach extends PartStatement{
		public ForEach( ForeachType type,
				String variableName, Statement body, Program program){
			this.type = type;
			this.name= variableName;
			this.body = body;
			this.program= program;
		}
		
		public Statement body;
		public String name;
		public Program program;
		public worms.model.programs.ProgramFactory.ForeachType type;
		
		public void execute() {
			if (this.type == ForeachType.WORM) {
				for ( Worm worm: program.getWorm().getWorld().getWorms()) {
					this.program.getGlobals().put(this.name, new EntityType<Worm>(worm));
					this.body.getPartStatement().execute();
				}
			} else if (this.type == ForeachType.FOOD) {
				for ( Food food: program.getWorm().getWorld().getFood()) {
					this.program.getGlobals().put(this.name, new EntityType<Food>(food));
					this.body.getPartStatement().execute();
				}	
			} else {
				for ( Object object: program.getWorm().getWorld().getAllObjects()) {
					this.program.getGlobals().put(this.name, new EntityType<Object>(object));
					this.body.getPartStatement().execute();
				}
			}
		}
	}
	
	public class Sequence extends PartStatement {

		public Sequence(List<Statement> statements) {
			this.statements = statements;
		}
		
		public List<Statement> statements;
		
		@Override
		public void execute() {
			for (int i=0; i<this.statements.size(); i++) {
				statements.get(i).getPartStatement().execute();
			}
		}
	}
	
	public class Print extends PartStatement {

		public Print(Expression e) {
			this.value = e;
		}
		
		public Expression value;
		
		@Override
		public void execute() {
			System.out.println(this.value.getPartExpression().getValue());
		}
	}
}


	


