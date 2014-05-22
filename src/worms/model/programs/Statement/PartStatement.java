package worms.model.programs.Statement;

public abstract class PartStatement{

	public abstract void execute();

	
	public void setExecuted(boolean state) {
		executed = state;
	}
	
	public static boolean executed;
	
	public static void setAllExecuted(boolean state) {
		executed = state;
	}
	
	public boolean isExecuted() {
		return executed;
	}

}
