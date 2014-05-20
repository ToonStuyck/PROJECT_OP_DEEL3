package worms.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import worms.gui.game.IActionHandler;
import worms.model.programs.ActionHandler;
import worms.model.programs.ProgramFactory;
import worms.model.programs.ProgramParser;
import worms.model.programs.Expression.EntityExpression;
import worms.model.programs.Expression.Expression;
import worms.model.programs.Expression.Expression.Self;
import worms.model.programs.Expression.Expression.VariableAcces;
import worms.model.programs.Expression.PartExpression;
import worms.model.programs.Statement.Statement;
import worms.model.programs.Type.BooleanType;
import worms.model.programs.Type.DoubleType;
import worms.model.programs.Type.EntityType;
import worms.model.programs.Type.Type;


public class ProgramFactoryImpl implements
		ProgramFactory<Expression, Statement, Type> {

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
		expr.createPartExpressionSelf();
		return expr;
	}

	@Override
	public Expression createGetX(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		PartExpression expression = e.getPartExpression();
		if (expression instanceof Self){
			expression= new EntityExpression<Worm>((new Self()).getValue());}
		if ((expression.getValue().getValue()) instanceof Worm) {
			expr.createPartExpressionDoubleLiteral(((Worm)((EntityType<?>) expression.getValue()).getValue()).getXpos());
		} else {
			expr.createPartExpressionDoubleLiteral(((Food)((EntityType<?>) expression.getValue()).getValue()).getXpos());
		}
		return expr;

	}

	@Override
	public Expression createGetY(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		PartExpression expression = e.getPartExpression();
		if (expression instanceof Self){
			expression= new EntityExpression<Worm>((new Self()).getValue());}
		if ((expression.getValue().getValue()) instanceof Worm) {
			expr.createPartExpressionDoubleLiteral(((Worm)((EntityType<?>) expression.getValue()).getValue()).getYpos());
		} else {
		expr.createPartExpressionDoubleLiteral(((Food)((EntityType<?>) expression.getValue()).getValue()).getYpos());
		}
		return expr;
	}

	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		PartExpression expression = e.getPartExpression();
		if (expression instanceof Self){
			expression= new EntityExpression<Worm>((new Self()).getValue());}
		if ((expression.getValue().getValue()) instanceof Worm) {
			expr.createPartExpressionDoubleLiteral(((Worm)((EntityType<?>) expression.getValue()).getValue()).getRadius());
		} else {
		expr.createPartExpressionDoubleLiteral(((Food)((EntityType<?>) expression.getValue()).getValue()).getRadius());
		}
		return expr;
	}

	@Override
	public Expression createGetDir(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		PartExpression expression = e.getPartExpression();
		if (expression instanceof Self){
			expression= new EntityExpression<Worm>((new Self()).getValue());}
		
		expr.createPartExpressionDoubleLiteral(((Worm)((EntityType<?>) expression.getValue()).getValue()).getDirection());
		return expr;
	}

	@Override
	public Expression createGetAP(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		PartExpression expression = e.getPartExpression();
		if (expression instanceof Self){
			expression= new EntityExpression<Worm>((new Self()).getValue());}
		
		expr.createPartExpressionDoubleLiteral(((Worm)((EntityType<?>) expression.getValue()).getValue()).getActionPoints());
		return expr;
	}

	@Override
	public Expression createGetMaxAP(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		PartExpression expression = e.getPartExpression();
		if (expression instanceof Self){
			expression= new EntityExpression<Worm>((new Self()).getValue());}
		
		expr.createPartExpressionDoubleLiteral(((Worm)((EntityType<?>) expression.getValue()).getValue()).getMaxActionPoints());
		return expr;
	}

	@Override
	public Expression createGetHP(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		PartExpression expression = e.getPartExpression();
		if (expression instanceof Self){
			expression= new EntityExpression<Worm>((new Self()).getValue());}
		
		expr.createPartExpressionDoubleLiteral(((Worm)((EntityType<?>) expression.getValue()).getValue()).getHitPoints());
		return expr;
	}

	@Override
	public Expression createGetMaxHP(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		PartExpression expression = e.getPartExpression();
		if (expression instanceof Self){
			expression= new EntityExpression<Worm>((new Self()).getValue());}
		
		expr.createPartExpressionDoubleLiteral(((Worm)((EntityType<?>) expression.getValue()).getValue()).getMaxHitPoints());
		return expr;
	}

	@Override
	public Expression createSameTeam(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		boolean outcome;
		if (Self.getWorm().getTeam() == null)
				outcome = false;
		else {
			outcome = (Self.getWorm().getTeam() == ((EntityType<Worm>) e.getPartExpression().getValue()).getValue().getTeam());
		}
		expr.createPartExpressionBooleanLiteral(outcome);
		return expr;
	}

	@Override
	public Expression createSearchObj(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		expr.createpartExpressionSearchObject(e);
		return expr;
	}

	@Override
	public Expression createIsWorm(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		Object obj = (Object) e.getPartExpression().getValue().getValue();
		expr.createPartExpressionBooleanLiteral(obj instanceof Worm);
		return expr;
	}

	@Override
	public Expression createIsFood(int line, int column, Expression e) {
		Expression expr = new Expression(line, column);
		Object obj = (Object) e.getPartExpression().getValue().getValue();
		expr.createPartExpressionBooleanLiteral(obj instanceof Food);
		return expr;
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name) {
		Expression expr = new Expression(line, column);
		expr.createPartExpressionVariableAcces(name);
		return expr;
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
//		Statement stmnt = new Statement(line, column);
//		stmnt.createPartStatementTurn(angle, getProgram());
//		return stmnt;
		return null;
	}

	@Override
	public Statement createMove(int line, int column) {
//		Statement stmnt = new Statement(line, column);
//		stmnt.createPartStatementMove(getProgram());
//		return stmnt;
		return null;
	}

	@Override
	public Statement createJump(int line, int column) {
//		Statement stmnt = new Statement(line, column);
//		stmnt.createPartStatementJump(getProgram());
//		return stmnt;
		return null;
	}

	@Override
	public Statement createToggleWeap(int line, int column) {
//		Statement stmnt = new Statement(line, column);
//		stmnt.createPartStatementToggleWeap(getProgram());
//		return stmnt;
		return null;
	}

	@Override
	public Statement createFire(int line, int column, Expression yield) {
//		Statement stmnt = new Statement(line, column);
//		stmnt.createPartStatementFire(yield, getProgram());
//		return stmnt;
		return null;
	}

	@Override
	public Statement createSkip(int line, int column) {
//		Statement stmnt = new Statement(line, column);
//		stmnt.createPartStatementSkip(getProgram());
//		return stmnt;
		return null;
	}

	@Override
	public Statement createAssignment(int line, int column,
			String variableName, Expression rhs) {
//		Statement stmnt = new Statement(line, column);
//		stmnt.createPartStatementAssignment(variableName, rhs, getProgram());
//		return stmnt;
		return null;
	}

	@Override
	public Statement createIf(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
//		Statement stmnt = new Statement(line, column);
//		stmnt.createPartStatementIf(condition, then, otherwise);
//		return stmnt;
		return null;
	}

	@Override
	public Statement createWhile(int line, int column, Expression condition,
			Statement body) {
//		Statement stmnt = new Statement(line, column);
//		stmnt.createPartStatementWhile(condition, body);
//		return stmnt;
		return null;
	}

	@Override
	public Statement createForeach(int line, int column,
			worms.model.programs.ProgramFactory.ForeachType type,
			String variableName, Statement body) {
//		Statement stmnt = new Statement(line, column);
//		stmnt.createPartStatementForEach(type, variableName, body, getProgram());
//		return stmnt;
		return null;
	}

	@Override
	public Statement createSequence(int line, int column,
			List<Statement> statements) {
//		Statement stmnt = new Statement(line, column);
//		stmnt.createPartStatementSequence(statements);
//		return stmnt;
		return null;
	}

	@Override
	public Statement createPrint(int line, int column, Expression e) {
//		Statement stmnt = new Statement(line, column);
//		stmnt.createPartStatementPrint(e);
//		return stmnt;
		return null;
	}

	@Override
	public Type createDoubleType() {
		return new DoubleType(0);
	}

	@Override
	public Type createBooleanType() {
		return new BooleanType(false);
	}

	@Override
	public Type createEntityType() {
		return new EntityType<>(null);
	}

}
