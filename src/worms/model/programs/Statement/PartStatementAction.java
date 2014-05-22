package worms.model.programs.Statement;

public abstract class PartStatementAction extends PartStatement{
	
	@Override
	public abstract void execute();
	
	public boolean executed=false;
	
	public boolean isNotExecuted() {
		return ! executed;
	}
	
	public void executedTrue() {
		this.executed = true;
	}
	

}
