package worms.model;

import worms.gui.game.IActionHandler;

public class ActionHandler implements IActionHandler{


	@Override
	public boolean turn(Worm worm, double angle) {
		
		return false;
	}

	@Override
	public boolean move(Worm worm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean jump(Worm worm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fire(Worm worm, int propulsion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean toggleWeapon(Worm worm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void print(String message) {
		// TODO Auto-generated method stub
		
	}

}
