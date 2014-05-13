package worms.model.programs;

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
		partExpression = new DoubleLiteral(d);
	}
	
	public void createPartExpressionBooleanLiteral(boolean b) {
		partExpression = new BooleanLiteral(b);	
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
	
	
	public class DoubleLiteral extends PartExpressionDouble {
		
		public DoubleLiteral(double d) {
			this.value = d;
		}
		
		public Double getValue() {
			return getDoubleValue();
		}
	}
	
	public class BooleanLiteral extends PartExpressionBoolean {
		
		public BooleanLiteral(boolean b) {
			this.value = b;
		}
		
		public Boolean getValue() {
			return getBooleanValue();
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
			this.expression = e;
		}
		
		public Boolean getValue() {
			return ! getLogicValue();
		}
	}
	
	public class LogicNull extends PartExpressionLogic {
		
		public LogicNull() {
		}
		
		public Boolean getValue() {
			return null;
		}
	}

	

	

	
	


	





	

}