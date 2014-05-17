package worms.model.programs;

import worms.model.Object;

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
	
	public void createPartExpressionSelf(Object self) {
		partExpression = new Self(self);
		
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
	
	public class DoubleLiteral extends PartExpression {
		
		public DoubleLiteral(double d/*, Expression reference*/) {
			this.value = d;
			//this.reference = reference;
		}
		
		public double value;
		
		public Double getValue() {
			return this.value;
		}
	}
	
	public class BooleanLiteral extends PartExpression {
		
		public BooleanLiteral(boolean b/*, Expression reference*/) {
			this.value = b;
		//	this.reference = reference;
		}
		
		public boolean value;
		
		public Boolean getValue() {
			return this.value;
		}
	}
	
	public class LogicAnd extends PartExpressionLogic {
		
		public LogicAnd(Expression e1, Expression e2) {
			this.left = e1;
			this.right = e2;
		}
		
		public Boolean getValue() {
			return getLeftValue() && getRightValue();
		}
		
		
	}
	
	public class LogicOr extends PartExpressionLogic {
		
		public LogicOr(Expression e1, Expression e2) {
			this.left = e1;
			this.right = e2;
		}
		
		public Boolean getValue() {
			return getLeftValue() || getRightValue();
		}
	}
	
	public class LogicNot extends PartExpressionLogic {
		
		public LogicNot(Expression e) {
			this.left = e;
		}
		
		public Boolean getValue() {
			return ! getLeftValue();
		}
	}
	
	public class LogicNull extends PartExpressionLogic {
		
		public LogicNull() {
		}
		
		public Boolean getValue() {
			return null;
		}
	}

	public class Self extends PartExpressionObject {
		
		public Self(Object self) {
			this.object = self;
		}
		
		public Object getValue() {
			return (Object) this.getObjectValue();
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