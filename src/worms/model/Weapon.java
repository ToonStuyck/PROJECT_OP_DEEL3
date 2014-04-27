package worms.model;

import be.kuleuven.cs.som.annotate.*;


/**
 * A class of weapons involving a name.
 * Complemented with methodes that interact with the weapon and changes certain values.
 * 
 * @invar	The mass of the Rifle must be 10g.
 * 		  	| if worm.getSelectedWeapon() =="Rifle"
 * 			|	then this.getMass() == 0.01
 * @invar	The mass of the Bazooka must be 300g.
 * 			| if worm.getSelectedWeapon() =="Bazooka"
 * 			|	then this.getMass() == 0.3
 * @invar	The force that is exerted by the Rifle is 1.5
 * 			| if worm.getSelectedWeapon() =="Rifle"
 * 			|	then this.getForce() == 1.5
 * @invar	The force that is exerted by the Bazooka is in range of 2.5 and 9.5
 * 			| if worm.getSelectedWeapon() =="Bazooka"
 * 			|	then this.getForce() == 2.5+(this.getYield()*0.07)
 * @invar	The damage done by the Rifle is 20.
 * 			| if worm.getSelectedWeapon() =="Rifle"
 * 			|	then this.getDamage() == 20
 * @invar	The damage done by the Bazooka is 80.
 * 			| if worm.getSelectedWeapon() =="Bazooka"
 * 			|	then this.getDamage() == 80
 * @invar	The cost to shoot with the Rifle is 10 actionpoints.
 * 			| if worm.getSelectedWeapon() =="Rifle"
 * 			|	then this.getCost() == 10
 * @invar	The cost to shoot with the Bazooka is 50 actionpoints.
 * 			| if worm.getSelectedWeapon() =="Bazooka"
 * 			|	then this.getCost() == 50
 * 	
 * @author 	Toon Stuyck
 * 			Toon Van Deun
 * 			Burgerlijk Ingenieur
 * 			https://github.com/ToonVanDeun/PROJECT_OP_DEEL2
 * @version 2.0
 */
@Value
public class Weapon {
	/**
	 * Initialize a weapon with the name Rifle.
	 *  @post	The weapon is given the name.
	 * 			| 	new.getName() == Rifle
	 */
	@Raw
	public Weapon(){
		this.setName("Rifle");
		this.setCost();
	}
	/**
	 * Sets the name of the weapon.
	 * @param name
	 * 			The name of the weapon
	 */
	@Raw
	private void setName(String name){
		this.name = name;
	}
	/**
	 * Returns the name of the weapon.
	 */
	@Basic @Raw
	public String getName(){
		return this.name;
	}
	/**
	 * The method sets the cost of action points to use the weapon.
	 */
	@Raw
	private void setCost(){
		if (this.name == "Rifle"){
			this.cost = 10;
		} else {
			this.cost = 50;
		}
	}
	/**
	 * The method returns the cost of action points to use the weapon.
	 */
	@Basic @Raw
	public int getCost(){
		return this.cost;
	}
	/**
	 * The method makes you change between the two weapons.
	 * @post	If the current weapon is the Rifle, the weapon will change to the Bazooka
	 * 				and if the current weapon is the Bazooka it will change in to the RIfle.
	 * 			| if (old.getName() == "Rifle")
	 * 			|	then (new.getName() == "Bazooka")
	 * 			| if (old.getName() == "Bazooka")
	 * 			|	then (new.getName() == "Rifle")
	 */
	@Raw
	public void changeWeapon(){
		if (this.name == "Rifle"){
			this.setName("Bazooka");
			this.setCost();
		}  else {
			this.setName("Rifle");
			this.setCost();
		}
	}
	
	// Variables
	private String name;
	private int cost;
}
