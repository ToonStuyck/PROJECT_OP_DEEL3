package worms.model;

import worms.gui.game.IActionHandler;

public class ActionHandler implements IActionHandler{


	@Override
	public boolean turn(Worm worm, double angle) {
		return worm.isValidTurn(angle);
	}

	@Override
	public boolean move(Worm worm) {
		return (worm.isValidStep() && worm.canMove());
	}

	@Override
	public boolean jump(Worm worm) {
		return worm.canJump();
	}

	@Override
	public boolean fire(Worm worm, int propulsion) {
		return (worm.isValidPropulsion(propulsion) && worm.canShoot());
	}

	@Override
	public boolean toggleWeapon(Worm worm) {
		return worm.canToggleWeapon();
	}

	@Override
	public void print(String message) {
		// TODO Auto-generated method stub
		
	}

}
