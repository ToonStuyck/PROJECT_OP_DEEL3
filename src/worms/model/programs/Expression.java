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
	
	
	public void createPartExpressionLogicAnd(Expression e1, Expression e2) {
		partExpression = new LogicAnd(e1,e2);
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

}