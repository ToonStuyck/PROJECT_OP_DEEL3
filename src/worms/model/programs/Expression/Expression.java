package worms.model.programs.Expression;

import worms.model.Food;
import worms.model.Object;
import worms.model.Worm;
import worms.model.programs.Type.BooleanType;
import worms.model.programs.Type.DoubleType;
import worms.model.programs.Type.EntityType;

public class Expression {
	
	public Expression(int line, int column) {
		this.line = line;
		this.column = column;
	}

	private int line;
	private int column;
	private PartExpression partExpression;
	
	public PartExpression getPartExpression() {
		return this.partExpression;
	}
	
	
	public void createPartExpressionDoubleLiteral(double d) {
		partExpression = new DoubleLiteral(d/*, this*/);
	}
	
	public void createPartExpressionBooleanLiteral(boolean b) {
		partExpression = new BooleanLiteral(b/*, this*/);	
	}	
	
	public void createPartExpressionLogicAnd(Expression e1, Expression e2) {
		partExpression = new LogicAnd(e1,e2);
	}
	
	public void createPartExpressionLogicOr(Expression e1, Expression e2) {
		partExpression = new LogicOr(e1,e2);
	}
	
	public void createPartExpressionLogicNot(Expression e) {
		partExpression = new LogicNot(e);
	}
	
	public void createPartExpressionLogicNull() {
		partExpression = new LogicNull();
	}
	
	public void createPartExpressionSelf(Worm self) {
		partExpression = new Self(self);
	}
	
	public void createpartExpressionSearchObject(Expression e, Worm programExecutingWorm) {
		partExpression = new SearchObject(e, programExecutingWorm);
		
	}
	
	
	
	
	public void createPartExpressionCompareLessThan(Expression e1, Expression e2) {
		partExpression = new CompareLessThan(e1, e2);
	}
	
	public void createPartExpressionCompareGreaterThan(Expression e1, Expression e2) {
		partExpression = new CompareGreaterThan(e1, e2);
	}
	
	public void createPartExpressionCompareLessThanOrEqual(Expression e1, Expression e2) {
		partExpression = new CompareLessThanOrEqual(e1, e2);
	}
	
	public void createPartExpressionCompareGreaterThanOrEqual(Expression e1, Expression e2) {
		partExpression = new CompareGreaterThanOrEqual(e1, e2);
	}
	
	public void createPartExpressionCompareEquality(Expression e1, Expression e2) {
		partExpression = new CompareEquality(e1, e2);
	}
	
	public void createPartExpressionCompareInequality(Expression e1, Expression e2) {
		partExpression = new CompareInequality(e1, e2);
	}
	
	public void createPartExpressionMathAdd(Expression e1, Expression e2) {
		partExpression = new MathAdd(e1,e2);
	}
	
	public void createPartExpressionMathSubtraction(Expression e1, Expression e2) {
		partExpression = new MathSubtraction(e1,e2);
	}
	
	public void createPartExpressionMathMul(Expression e1, Expression e2) {
		partExpression = new MathMul(e1,e2);
	}
	
	public void createPartExpressionMathDivision(Expression e1, Expression e2) {
		partExpression = new MathDivision(e1,e2);
	}
	
	public void createPartExpressionMathSqrt(Expression e) {
		partExpression = new MathSqrt(e);
	}
	
	public void createPartExpressionMathSin(Expression e) {
		partExpression = new MathSin(e);
	}
	
	public void createPartExpressionMathCos(Expression e) {
		partExpression = new MathCos(e);
	}
	
	public class DoubleLiteral extends DoubleBasicExpression {
		
		public DoubleLiteral(double d) {
			this.value = new DoubleType(d);
		}
		
		public DoubleType value;
		
		public DoubleType getValue() {
			return this.value;
		}
	}
	
	public class BooleanLiteral extends BooleanBasicExpression {
		
		public BooleanLiteral(boolean b) {
			this.value = new BooleanType(b);
		}
		
		public BooleanType value;
		
		public BooleanType getValue() {
			return this.value;
		}
	}
	
	public class LogicAnd extends BooleanExpression {
		
		public LogicAnd(Expression e1, Expression e2) {
			this.left = (BooleanLiteral) e1.getPartExpression();
			this.right = (BooleanLiteral) e2.getPartExpression();
		}
		
		public BooleanLiteral left;
		public BooleanLiteral right;
		
		public BooleanType getValue() {
			return new BooleanType(this.left.getValue().getValue() && this.right.getValue().getValue());
		}
	}
	
	public class LogicOr extends BooleanExpression {
		
		public LogicOr(Expression e1, Expression e2) {
			this.left = (BooleanLiteral) e1.getPartExpression();
			this.right = (BooleanLiteral) e2.getPartExpression();
		}
		
		public BooleanLiteral left;
		public BooleanLiteral right;
		
		public BooleanType getValue() {
			return new BooleanType(this.left.getValue().getValue() || this.right.getValue().getValue());
		}
	}
	
	public class LogicNot extends BooleanExpression {
		
		public LogicNot(Expression e) {
			this.subject = (BooleanLiteral) e.getPartExpression();
		}
		
		public BooleanLiteral subject;
		
		public BooleanType getValue() {
			return new BooleanType(!this.subject.getValue().getValue());
		}
	}
	
	public class LogicNull extends PartExpression{

		@Override
		public EntityType<Double> getValue() {
			return new EntityType<Double>(null);
		}
	}

	public class Self extends PartExpression {
		
		public Self(Worm worm){
			this.worm = worm;
		}
	   
		public Worm getWorm() {
			return this.worm;
		}

		public Worm worm;

		@Override
		public EntityType<Worm> getValue() {
			EntityType<Worm> worm =new EntityType<Worm>(this.getWorm());
			return worm;
		}
	}
	
	public class SearchObject extends PartExpression {
		public SearchObject(Expression e, Worm programExecutingWorm) {
			this.angle = programExecutingWorm.getDirection() + ((DoubleLiteral) e.getPartExpression()).getValue().getValue();
			this.programExecutingWorm = programExecutingWorm;
		}
		
		public Worm programExecutingWorm;		
		public double angle;

		@Override
		public EntityType<?> getValue() {
			Object closestObject = null;
			for (Object obj: programExecutingWorm.getWorld().getAllObjects()) {
				if (Math.tan(this.angle)==((obj.getYpos()-programExecutingWorm.getYpos())/(obj.getXpos()-programExecutingWorm.getXpos()))) {
					if (closestObject == null){
						closestObject = obj;
					}
					else {
						double distanceNew = Math.sqrt(Math.pow(obj.getYpos()-programExecutingWorm.getYpos(), 2)+
								Math.pow(obj.getXpos()-programExecutingWorm.getXpos(), 2));
						double distanceOld = Math.sqrt(Math.pow(closestObject.getYpos()-programExecutingWorm.getYpos(), 2)+
								Math.pow(closestObject.getXpos()-programExecutingWorm.getXpos(), 2));
						if (distanceNew<distanceOld) {
							closestObject = obj;
						}
						
					}
				}
			}
			if (closestObject instanceof Worm) {
				return new EntityType<Worm>((Worm) closestObject);
			} else {
				return new EntityType<Food>((Food) closestObject);
			}
		}
	}
	
	public class CompareLessThan extends PartExpressionCompare {
		
		public CompareLessThan(Expression e1, Expression e2) {
			this.left = e1;
			this.right = e2;
		}
		
		public Boolean getValue() {
			return getLeftValue() < getRightValue();
		}
	}

	public class CompareGreaterThan extends PartExpressionCompare {
		
		public CompareGreaterThan(Expression e1, Expression e2) {
			this.left = e1;
			this.right = e2;
		}
		
		public Boolean getValue() {
			return getLeftValue() > getRightValue();
		}
	}

	
	public class CompareLessThanOrEqual extends PartExpressionCompare {
		
		public CompareLessThanOrEqual(Expression e1, Expression e2) {
			this.left = e1;
			this.right = e2;
		}
		
		public Boolean getValue() {
			return getLeftValue() <= getRightValue();
		}
	}
	
	public class CompareGreaterThanOrEqual extends PartExpressionCompare {
		
		public CompareGreaterThanOrEqual(Expression e1, Expression e2) {
			this.left = e1;
			this.right = e2;
		}
		
		public Boolean getValue() {
			return getLeftValue() >= getRightValue();
		}
	}

	public class CompareEquality extends PartExpressionCompare {
		
		public CompareEquality(Expression e1, Expression e2) {
			this.left = e1;
			this.right = e2;
		}
		
		public Boolean getValue() {
			return getLeftValue() == getRightValue();
		}
	}
	
	public class CompareInequality extends PartExpressionCompare {
		
		public CompareInequality(Expression e1, Expression e2) {
			this.left = e1;
			this.right = e2;
		}
		
		public Boolean getValue() {
			return getLeftValue() != getRightValue();
		}
	}

	public class MathAdd extends PartExpressionMath {
		
		public MathAdd(Expression e1, Expression e2) {
			this.left = e1;
			this.right = e2;
		}
		
		public Double getValue() {
			return getLeftValue() + getRightValue();
		}
	}

	public class MathSubtraction extends PartExpressionMath {
		
		public MathSubtraction(Expression e1, Expression e2) {
			this.left = e1;
			this.right = e2;
		}
		
		public Double getValue() {
			return getLeftValue() - getRightValue();
		}
	}

	public class MathMul extends PartExpressionMath {
	
		public MathMul(Expression e1, Expression e2) {
			this.left = e1;
			this.right = e2;
		}
		
		public Double getValue() {
			return getLeftValue() * getRightValue();
			}
	}

	public class MathDivision extends PartExpressionMath {
	
		public MathDivision(Expression e1, Expression e2) {
			this.left = e1;
			this.right = e2;
		}
		
		public Double getValue() {
			return getLeftValue() / getRightValue();
		}
	}
	
	public class MathSqrt extends PartExpressionMath {
		
		public MathSqrt(Expression e) {
			this.expression = e;
		}
		
		public Double getValue() {
			return Math.sqrt((Double) getExpression().getPartExpression().getValue());
		}
	}
	
	public class MathSin extends PartExpressionMath {
		
		public MathSin(Expression e) {
			this.expression = e;
		}
		
		public Double getValue() {
			return Math.sin((Double) getExpression().getPartExpression().getValue());
		}
	}
	
	public class MathCos extends PartExpressionMath {
		
		public MathCos(Expression e) {
			this.expression = e;
		}
		
		public Double getValue() {
			return Math.cos((Double) getExpression().getPartExpression().getValue());
		}
	}

	





	

}