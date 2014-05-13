package worms.model;

public interface Entity {
	
	public double getXpos() throws IllegalStateException;
	
	public double getYpos() throws IllegalStateException;
	
	public double getRadius() throws IllegalStateException;
	
	public double getDirection() throws IllegalStateException;
	
	public double getActionPoints() throws IllegalStateException;
	
	public double getMaxActionPoints() throws IllegalStateException;
	
	public double getHitPoints() throws IllegalStateException;
	
	public double getMaxHitPoints() throws IllegalStateException;

}
