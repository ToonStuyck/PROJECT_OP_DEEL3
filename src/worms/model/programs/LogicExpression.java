package worms.model.programs;

public class LogicExpression extends PartExpression {
	
	public LogicExpression(
	
	public boolean getLeftValue() {
		return this.getLeftExpression().getValue();
	}
	
	public boolean getRightValue() {
		return this.getRightExpression().getValue();
	}
	
	public boolean getValue() {
		return false;
	}
	
	public class LogicAnd extends LogicExpression {
		
		public LogicAnd(Expression e1, Expression e2) {
			this.left = e1;
			this.right = e2;
		}
		
		private Expression left;
		private Expression right;
		
		public boolean getValue() {
			return this.getLeftValue() && this.getRightValue();
		}
		
	}
}


