package worms.model.programs.Expression;

import java.util.HashMap;
import java.util.Map;

import worms.model.Food;
import worms.model.Object;
import worms.model.Worm;
import worms.model.programs.Type.BooleanType;
import worms.model.programs.Type.DoubleType;
import worms.model.programs.Type.EntityType;
import worms.model.programs.Type.Type;

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
	
	public void createPartExpressionVariableAcces(String name,
			Map<String, Type> globals) {
		this.partExpression = new VariableAcces(name,globals);
		
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
	
	public class VariableAcces extends PartExpression {
		
		public VariableAcces(String name, Map<String,Type> globals) {
			this.name = name;
			this.globals =  new HashMap<String, Type>(globals);
		}
	
		public String name;
		public Map<String,Type> globals;
		
		public Type getValue(){
			return this.globals.get(this.name);
		}
	}
	
	public class CompareLessThan extends BooleanExpression {
		
		public CompareLessThan(Expression e1, Expression e2) {
			this.left = (DoubleLiteral) e1.getPartExpression();
			this.right = (DoubleLiteral) e2.getPartExpression();
		}
		
		public DoubleLiteral left;
		public DoubleLiteral right;
		
		public BooleanType getValue() {
			return new BooleanType(this.left.getValue().getValue() < this.right.getValue().getValue());
		}
	}

	public class CompareGreaterThan extends BooleanExpression {
		
		public CompareGreaterThan(Expression e1, Expression e2) {
			this.left = (DoubleLiteral) e1.getPartExpression();
			this.right = (DoubleLiteral) e2.getPartExpression();
		}
		
		public DoubleLiteral left;
		public DoubleLiteral right;
		
		public BooleanType getValue() {
			return new BooleanType(this.left.getValue().getValue() > this.right.getValue().getValue());
		}
	}

	
	public class CompareLessThanOrEqual extends BooleanExpression {
		
		public CompareLessThanOrEqual(Expression e1, Expression e2) {
			this.left = (DoubleLiteral) e1.getPartExpression();
			this.right = (DoubleLiteral) e2.getPartExpression();
		}
		
		public DoubleLiteral left;
		public DoubleLiteral right;
		
		public BooleanType getValue() {
			return new BooleanType(this.left.getValue().getValue() <= this.right.getValue().getValue());
		}
	}
	
	public class CompareGreaterThanOrEqual extends BooleanExpression {
		
		public CompareGreaterThanOrEqual(Expression e1, Expression e2) {
			this.left = (DoubleLiteral) e1.getPartExpression();
			this.right = (DoubleLiteral) e2.getPartExpression();
		}
		
		public DoubleLiteral left;
		public DoubleLiteral right;
		
		public BooleanType getValue() {
			return new BooleanType(this.left.getValue().getValue() >= this.right.getValue().getValue());
		}
	}

	public class CompareEquality extends BooleanExpression {
		
		public CompareEquality(Expression e1, Expression e2) {
			this.left = (DoubleLiteral) e1.getPartExpression();
			this.right = (DoubleLiteral) e2.getPartExpression();
		}
		
		public DoubleLiteral left;
		public DoubleLiteral right;
		
		public BooleanType getValue() {
			return new BooleanType(this.left.getValue().getValue() == this.right.getValue().getValue());
		}
	}
	
	public class CompareInequality extends BooleanExpression {
		
		public CompareInequality(Expression e1, Expression e2) {
			this.left = (DoubleLiteral) e1.getPartExpression();
			this.right = (DoubleLiteral) e2.getPartExpression();
		}
		
		public DoubleLiteral left;
		public DoubleLiteral right;
		
		public BooleanType getValue() {
			return new BooleanType(this.left.getValue().getValue() != this.right.getValue().getValue());
		}
	}

	public class MathAdd extends DoubleBinaryExpression {
		
		public MathAdd(Expression e1, Expression e2) {
			this.left = (DoubleLiteral) e1.getPartExpression();
			this.right = (DoubleLiteral) e2.getPartExpression();
		}
		
		public DoubleLiteral left;
		public DoubleLiteral right;
		
		public DoubleType getValue() {
			return new DoubleType(this.left.getValue().getValue() + this.right.getValue().getValue());
		}
	}

	public class MathSubtraction extends DoubleBinaryExpression {
		
		public MathSubtraction(Expression e1, Expression e2) {
			this.left = (DoubleLiteral) e1.getPartExpression();
			this.right = (DoubleLiteral) e2.getPartExpression();
		}
		
		public DoubleLiteral left;
		public DoubleLiteral right;
		
		public DoubleType getValue() {
			return new DoubleType(this.left.getValue().getValue() - this.right.getValue().getValue());
		}
	}

	public class MathMul extends DoubleBinaryExpression {
	
		public MathMul(Expression e1, Expression e2) {
			this.left = (DoubleLiteral) e1.getPartExpression();
			this.right = (DoubleLiteral) e2.getPartExpression();
		}
		
		public DoubleLiteral left;
		public DoubleLiteral right;
		
		public DoubleType getValue() {
			return new DoubleType(this.left.getValue().getValue() * this.right.getValue().getValue());
		}
	}

	public class MathDivision extends DoubleBinaryExpression {
	
		public MathDivision(Expression e1, Expression e2) {
			this.left = (DoubleLiteral) e1.getPartExpression();
			this.right = (DoubleLiteral) e2.getPartExpression();
		}
		
		public DoubleLiteral left;
		public DoubleLiteral right;
		
		public DoubleType getValue() {
			return new DoubleType(this.left.getValue().getValue() / this.right.getValue().getValue());
		}
	}
	
	public class MathSqrt extends DoubleUnaryExpression {
		
		public MathSqrt(Expression e) {
			this.subject = (DoubleLiteral) e.getPartExpression();
		}
		
		public DoubleLiteral subject;
		
		public DoubleType getValue() {
			return new DoubleType(Math.sqrt(this.subject.getValue().getValue()));
		}
	}
	
	public class MathSin extends DoubleUnaryExpression {
		
		public MathSin(Expression e) {
			this.subject = (DoubleLiteral) e.getPartExpression();
		}
		
		public DoubleLiteral subject;
		
		public DoubleType getValue() {
			return new DoubleType(Math.sin(this.subject.getValue().getValue()));
		}
	}
	
	public class MathCos extends DoubleUnaryExpression {
		
		public MathCos(Expression e) {
			this.subject = (DoubleLiteral) e.getPartExpression();
		}
		
		public DoubleLiteral subject;
		
		public DoubleType getValue() {
			return new DoubleType(Math.cos(this.subject.getValue().getValue()));
		}
	}
	
	
		
	}
	

	





	

