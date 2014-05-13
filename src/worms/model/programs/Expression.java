package worms.model.programs;

public interface Expression<T> {
	
//	public Expression(int line, int column) {
//		this.line = line;
//		this.column = column;
//	}
//
//	private int line;
//	private int column;
//	private PartExpression partExpression;
//	
//	public void createLogicAnd(Expression e1, Expression e2) {
//		this.partExpression = new LogicAnd(e1,e2);
//	}
//	
	
	public T getExpression();
	
	public Class<T> getType();

	
}
