package worms.model;

import java.util.List;




import worms.model.programs.ActionHandler;
import worms.model.programs.Expression;
import worms.model.programs.Statement;
import worms.model.programs.Type;
import worms.model.programs.ProgramFactory;



public class ProgramFactoryImpl implements
		ProgramFactory<Expression, Statement, Type> {

	public Program program;
	
	public Program getProgram() {
		return this.program;
	}
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	@Override
	public Expression createDoubleLiteral(int line, int column, double d) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionDoubleLiteral(d);
		return expr;
	}

	@Override
	public Expression createBooleanLiteral(int line, int column, boolean b) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionBooleanLiteral(b);
		return expr;
	}

	@Override
	public Expression createAnd(int line, int column, Expression e1,
			Expression e2) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionLogicAnd(e1, e2);
		return expr;
	}

	@Override
	public Expression createOr(int line, int column, Expression e1,
			Expression e2) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionLogicOr(e1,e2);
		return expr;
	}

	@Override
	public Expression createNot(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionLogicNot(e);
		return expr;
	}

	@Override
	public Expression createNull(int line, int column) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionLogicNull();
		return expr;
	}

	@Override
	public Expression createSelf(int line, int column) {
		Expression expr = new Expression(line, column);
		Object self = getProgram().getWorm();
		expr.createPartExpressionSelf(self);
		
		return expr;
	}

	@Override
	public Expression createGetX(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		Object obj = (Object) e.getPartExpression().getValue();
		expr.createPartExpressionDoubleLiteral(obj.getXpos());
		return expr;
	}

	@Override
	public Expression createGetY(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		Object obj = (Object) e.getPartExpression().getValue();
		expr.createPartExpressionDoubleLiteral(obj.getYpos());
		return expr;
	}

	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		Object obj = (Object) e.getPartExpression().getValue();
		expr.createPartExpressionDoubleLiteral(obj.getRadius());
		return expr;
	}

	@Override
	public Expression createGetDir(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		Worm obj = (Worm) e.getPartExpression().getValue();
		expr.createPartExpressionDoubleLiteral(obj.getDirection());
		return expr;
	}

	@Override
	public Expression createGetAP(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		Worm obj = (Worm) e.getPartExpression().getValue();
		expr.createPartExpressionDoubleLiteral(obj.getActionPoints());
		return expr;
	}

	@Override
	public Expression createGetMaxAP(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		Worm obj = (Worm) e.getPartExpression().getValue();
		expr.createPartExpressionDoubleLiteral(obj.getMaxActionPoints());
		return expr;
	}

	@Override
	public Expression createGetHP(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		Worm obj = (Worm) e.getPartExpression().getValue();
		expr.createPartExpressionDoubleLiteral(obj.getHitPoints());
		return expr;
	}

	@Override
	public Expression createGetMaxHP(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		Worm obj = (Worm) e.getPartExpression().getValue();
		expr.createPartExpressionDoubleLiteral(obj.getMaxHitPoints());
		return expr;
	}

	@Override
	public Expression createSameTeam(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSearchObj(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsWorm(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		Object obj = (Object) e.getPartExpression().getValue();
		expr.createPartExpressionBooleanLiteral(obj instanceof Worm);
		return expr;
	}

	@Override
	public Expression createIsFood(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		Object obj = (Object) e.getPartExpression().getValue();
		expr.createPartExpressionBooleanLiteral(obj instanceof Food);
		return expr;
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name,
			Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createLessThan(int line, int column, Expression e1,
			Expression e2) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionCompareLessThan(e1,e2);
		return expr;
	}

	@Override
	public Expression createGreaterThan(int line, int column, Expression e1,
			Expression e2) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionCompareGreaterThan(e1,e2);
		return expr;
	}

	@Override
	public Expression createLessThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionCompareLessThanOrEqual(e1,e2);
		return expr;
	}

	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionCompareGreaterThanOrEqual(e1,e2);
		return expr;
	}

	@Override
	public Expression createEquality(int line, int column, Expression e1,
			Expression e2) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionCompareEquality(e1,e2);
		return expr;
	}

	@Override
	public Expression createInequality(int line, int column, Expression e1,
			Expression e2) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionCompareInequality(e1,e2);
		return expr;
	}

	@Override
	public Expression createAdd(int line, int column, Expression e1,
			Expression e2) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionMathAdd(e1,e2);
		return expr;
	}

	@Override
	public Expression createSubtraction(int line, int column, Expression e1,
			Expression e2) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionMathSubtraction(e1,e2);
		return expr;
	}

	@Override
	public Expression createMul(int line, int column, Expression e1,
			Expression e2) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionMathMul(e1,e2);
		return expr;
	}

	@Override
	public Expression createDivision(int line, int column, Expression e1,
			Expression e2) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionMathDivision(e1,e2);
		return expr;
	}

	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionMathSqrt(e);
		return expr;
	}

	@Override
	public Expression createSin(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionMathSin(e);
		return expr;
	}

	@Override
	public Expression createCos(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionMathCos(e);
		return expr;
	}

	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		Statement stmnt = new Statement(line, column);
		stmnt.createPartStatementTurn(angle);
		return stmnt;
	}

	@Override
	public Statement createMove(int line, int column) {
		Statement stmnt = new Statement(line, column);
		stmnt.createPartStatementMove();
		return stmnt;
	}

	@Override
	public Statement createJump(int line, int column) {
		Statement stmnt = new Statement(line, column);
		stmnt.createPartStatementJump();
		return stmnt;
	}

	@Override
	public Statement createToggleWeap(int line, int column) {
		Statement stmnt = new Statement(line, column);
		stmnt.createPartStatementToggleWeap();
		return stmnt;
	}

	@Override
	public Statement createFire(int line, int column, Expression yield) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSkip(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAssignment(int line, int column,
			String variableName, Expression rhs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createIf(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createWhile(int line, int column, Expression condition,
			Statement body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createForeach(int line, int column,
			worms.model.programs.ProgramFactory.ForeachType type,
			String variableName, Statement body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSequence(int line, int column,
			List<Statement> statements) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createPrint(int line, int column, Expression e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type createDoubleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type createBooleanType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type createEntityType() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
