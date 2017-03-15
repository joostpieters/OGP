package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class of ships involving a position, velocity, radius and orientation.
 * @version 1.4
 * @author  Sander Leyssens & Sarah Joseph
 * 
 *
 */

// Name: Sarah Joseph
// Course: Informatica
// Repository: https://github.com/sjoseph22/OGP.git

public class Ship extends Entity {
	
	/**
	 * Create a new ship with the given position, velocity, radius and
	 * orientation (in radians).
	 * @param  x
	 *         The x-coordinate of the position of this new ship.
	 * @param  y
	 *         The y-coordinate of the position of this new ship.
	 * @param  xVelocity
	 *         The x-coordinate of the velocity of this new ship.
	 * @param  yVelocity
	 *         The y-coordinate of the velocity of this new ship.
	 * @param  radius
	 *         The radius of this new ship.
	 * @param  orientation
	 *         The orientation of this new ship.
	 * @post   The new position of the ship is equal to the given position.
	 *         | new.getPosition().equals({x,y})
	 * @post   The new velocity of the ship is equal to the given velocity.
	 *         | new.getVelocity().equals({xVelocity,yVelocity})
	 * @post   The new radius of the ship is equal to the given radius.
	 *         | new.getRadius() == radius
	 * @post   The new orientation of the ship is equal to the given orientation.
	 *         | new.getOrientation() == orientation
	 * @throws IllegalArgumentException
	 *         The given initial position or radius is not valid.
	 *         | !isValidPosition({x,y})||!isValidRadius(radius)
	 * 
	 */
	public Ship(double x, double y, double xVelocity,
			double yVelocity, double radius, double orientation) throws IllegalArgumentException {
		this.setPosition(new double[] {x,y});
		this.setVelocity(new double[] {xVelocity,yVelocity});
		if (!isValidRadius(radius)) throw new IllegalArgumentException();
		setRadius(radius);
		this.setOrientation(orientation);
	}
	
	/**
	 * Create a new ship with a default position, velocity, radius and
	 * direction.
	 * 
	 * Result is a unit circle centered on <code>(0, 0)</code> facing right. Its
	 * speed is zero.//TODO: @effect?
	 * @post  The new position of the ship is equal to (0,0).
	 *        | new.getPosition().equals({0,0})
	 * @post  The new velocity of the ship is equal to (0,0).
	 *        | new.getVelocity().equals({0,0})
	 * @post  The new radius of the ship is equal to 1.
	 *        | new.getRadius() == 1
	 * @post  The new orientation of the ship is equal to 0 (faces to the right).
	 *        | new.getOrientation() == 0
	 * @throws IllegalArgumentException
	 *         | true
     */
	public Ship() throws IllegalArgumentException {
		this(0,0,0,0,1,0);
	}
	
	public static boolean isValidRadius(double radius){
		return (radius > minRadius);
	}

	private static final double minRadius = 10;

	
	/**
	 * Update the ship's velocity based on its current velocity, its
	 * direction and the given <code>amount</code>.
	 * @param  amount
	 * 	       The thrust of the ship.
	 * @post   The thrust is larger than 0. TODO: may need editing
	 * 	       | if (amount < 0 || Double.isNaN(amount)) amount = 0
	 * @effect The new velocity of the ship is set to the calculated velocity.
	 *         | setVelocity({this.getVelocity()[0]+amount*Math.cos(this.getOrientation()),this.getVelocity()[1]+amount*Math.sin(this.getOrientation())});
	 */
	public void thrust(double amount) {
		if (amount < 0 || Double.isNaN(amount)) amount = 0;
		double[] velocity = getVelocity();
		double orientation = getOrientation();
		setVelocity(new double[] {velocity[0]+amount*Math.cos(orientation),velocity[1]+amount*Math.sin(orientation)});
	}


	/**
	 * Update the direction of the ship by adding <code>angle</code>
	 * (in radians) to its current direction. <code>angle</code> may be
	 * negative.
	 * @Pre   The given angle is valid.
	 * 	      | isValidOrientation(getOrientation()+angle)
	 * @param angle
	 * 	      The ship's angle of deviation from its original orientation.
	 * @post  The new orientation of the ship is equal to the angle added 
	 * 	      to the initial orientation.
	 *        | new.getOrientation() = this.getOrientation()+angle
	 */
	public void turn(double angle) {
		assert(isValidOrientation(getOrientation()+angle));
		setOrientation(getOrientation()+angle);
	}




}
