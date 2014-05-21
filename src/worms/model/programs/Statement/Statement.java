package worms.model.programs.Statement;

import java.util.ArrayList;
import java.util.List;

import worms.gui.game.IActionHandler;
import worms.model.Food;
import worms.model.Program;
import worms.model.Worm;
import worms.model.programs.ProgramFactory.ForeachType;
import worms.model.programs.Expression.DoubleExpression;
import worms.model.programs.Expression.Expression;
import worms.model.programs.Expression.Expression.Self;
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

	
	//ACTION
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
		partStatement = new ActionToggleWeap() ;
	}
	
	public void createPartStatementFire(Expression yield) {
		partStatement = new ActionFire(yield);
	}
	
	public void createPartStatementSkip() {
		partStatement = new ActionSkip();
	}

	
	public class ActionTurn extends PartStatementAction {
		
		public ActionTurn(Expression angle) {
			System.out.println("is dit het probleem?");
			this.angle = (DoubleExpression) angle.getPartExpression();
			
		}
		
		public Program program;
		public DoubleExpression angle;
		
		public void execute() {
			if ((Self.getWorm().getProgram().getNbStatements()<1000) 
					&& (Self.getWorm().isValidTurn(this.angle.getValue().getValue())) 
					&& (this.isNotExecuted())){
				Worm worm = Self.getWorm();
				IActionHandler handler = worm.getProgram().getHandler();
				handler.turn(worm, this.angle.getValue().getValue());
				worm.getProgram().increaseNbStatements();
				this.executedTrue();
			}
		}
	}
	
	public class ActionMove extends PartStatementAction {
		
		public ActionMove() {
		}
		
		public void execute() {
			if ((Self.getWorm().getProgram().getNbStatements()<1000) && (Self.getWorm().isValidStep()) 
					&& (this.isNotExecuted())){
				Worm worm = Self.getWorm();
				IActionHandler handler = worm.getProgram().getHandler();
				handler.move(worm);
				worm.getProgram().increaseNbStatements();
				this.executedTrue();
			}
		}
	}

	public class ActionJump extends PartStatementAction {
		
		public ActionJump() {
		}
		
		public void execute() {
			if ((Self.getWorm().getProgram().getNbStatements()<1000) && (Self.getWorm().canJump()) 
					&& (this.isNotExecuted())){
				Worm worm = Self.getWorm();
				IActionHandler handler = worm.getProgram().getHandler();
				handler.jump(worm);
				worm.getProgram().increaseNbStatements();
				this.executedTrue();
			}
		}
	}
	
	public class ActionToggleWeap extends PartStatementAction {
		
		public ActionToggleWeap() {
		}
		
		public void execute() {
			if ((Self.getWorm().getProgram().getNbStatements()<1000)
				&& (this.isNotExecuted())){
				Worm worm = Self.getWorm();
				IActionHandler handler = worm.getProgram().getHandler();
				handler.toggleWeapon(worm);
				worm.getProgram().increaseNbStatements();
				this.executedTrue();
			}
		}
	}
	
	public class ActionFire extends PartStatementAction {
		
		public ActionFire(Expression yield) {
			this.yield = (DoubleExpression) yield.getPartExpression();
		}
	
		public DoubleExpression yield;
		
		public void execute() {
			if ((Self.getWorm().getProgram().getNbStatements()<1000) && (Self.getWorm().canShoot()) 
					&& (this.isNotExecuted())){
				Worm worm = Self.getWorm();
				IActionHandler handler = worm.getProgram().getHandler();
				handler.fire(worm, (int) Math.round(yield.getValue().getValue()));
				worm.getProgram().increaseNbStatements();
				this.executedTrue();
			}
		}
	}

	public class ActionSkip extends PartStatementAction {
	
		public ActionSkip() {
		}
		
		public void execute() {
			if ((Self.getWorm().getProgram().getNbStatements()<1000) 
				&& (this.isNotExecuted())){
				Worm worm = Self.getWorm();
				worm.getWorld().startNextTurn();
				worm.getProgram().increaseNbStatements();
				this.executedTrue();
			}
		}
	}

	
	//OTHER STATEMENTS
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
	
	public void createPartStatementForEach(
			worms.model.programs.ProgramFactory.ForeachType type,
			String variableName, Statement body) {
		this.partStatement = new ForEach(type, variableName, body);
	}
	
	public void createPartStatementSequence(List<Statement> statements) {
		this.partStatement = new Sequence(statements);
	}
	
	public void createPartStatementPrint(Expression e) {
		this.partStatement = new Print(e);
	}
	
	
	public class Assignment extends PartStatement{
		public Assignment(String variableName, Expression rhs){
			this.name = variableName;
			this.value = rhs;
		}
		
		public String name;
		public Expression value;
		
		@Override
		public void execute() {
			if (Self.getWorm().getProgram().getNbStatements()<1000){
				Worm worm = Self.getWorm();
	        	worm.getProgram().getGlobals().put(name, value.getPartExpression().getValue());
	        	worm.getProgram().increaseNbStatements();
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
			if (Self.getWorm().getProgram().getNbStatements()<1000){
				if((boolean) condition.getPartExpression().getValue().getValue()){
					Self.getWorm().getProgram().increaseNbStatements();
					then.getPartStatement().execute();
				}
				else {
					Self.getWorm().getProgram().increaseNbStatements();
					other.getPartStatement().execute();
				}
			}
		}

		public List<Statement> getStatements() {
			List<Statement> list = new ArrayList<Statement>();
			list.add(then); list.add(other);
			return list;
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
			if (Self.getWorm().getProgram().getNbStatements()<1000){
				while((boolean) condition.getPartExpression().getValue().getValue()){
					Self.getWorm().getProgram().increaseNbStatements();
					body.getPartStatement().execute();
				}
			}
		}
	}

	public class ForEach extends PartStatement{
		public ForEach(ForeachType type, String variableName, Statement body){
			this.type = type;
			this.name= variableName;
			this.body = body;
		}
		
		public Statement body;
		public String name;
		public worms.model.programs.ProgramFactory.ForeachType type;
		
		public void execute() {
			if (Self.getWorm().getProgram().getNbStatements()<1000){
				if (this.type == ForeachType.WORM) {
					for (Worm w:Self.getWorm().getWorld().getWorms()) {
						EntityType<Worm> worm =new EntityType<Worm>(w);
						Self.getWorm().getProgram().getGlobals().put(name,worm);
						body.getPartStatement().execute();
					}
				} else if (this.type == ForeachType.FOOD) {
					for (Food f:Self.getWorm().getWorld().getFood()) {
						EntityType<Food> food =new EntityType<Food>(f);
						Self.getWorm().getProgram().getGlobals().put(name,food);
						body.getPartStatement().execute();
					}
				} else {
					for (Worm w:Self.getWorm().getWorld().getWorms()) {
						EntityType<Worm> worm =new EntityType<Worm>(w);
						Self.getWorm().getProgram().getGlobals().put(name,worm);
						body.getPartStatement().execute();
					}
					for (Food f:Self.getWorm().getWorld().getFood()) {
						EntityType<Food> food =new EntityType<Food>(f);
						Self.getWorm().getProgram().getGlobals().put(name,food);
						body.getPartStatement().execute();
					}
				}	
			}
			Self.getWorm().getProgram().increaseNbStatements();
		}
	}
	
	public class Sequence extends PartStatement {

		public Sequence(List<Statement> statements) {
			this.statements = statements;
		}
		
		public List<Statement> statements;
		
		@Override
		public void execute() {
			if (Self.getWorm().getProgram().getNbStatements()<1000){
				for (int i=0; i<this.statements.size(); i++) {
					statements.get(i).getPartStatement().execute();
				}
				Self.getWorm().getProgram().increaseNbStatements();
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
			if (Self.getWorm().getProgram().getNbStatements()<1000){
				System.out.println(this.value.getPartExpression().getValue().getValue());
				Self.getWorm().getProgram().increaseNbStatements();
			}
		}
	}
}


	


