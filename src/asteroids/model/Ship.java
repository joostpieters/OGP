package asteroids.model;

import java.util.*;

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
			double yVelocity, double radius, double orientation, double mass) throws IllegalArgumentException {
		super(x,y,xVelocity,yVelocity,radius,orientation);
		this.setMass(mass);
		Bullet[] bullets = new Bullet[15];
		for(int i = 0; i < 15; i++){
//			Bullet bullet = new Bullet();//TODO
//			bullets[i] = bullet;
		}
		this.loadBullet(bullets);
	}
	
	@Override
	public boolean isValidRadius(double radius){
		return (radius > minRadius);
	}

	private void setMass(double mass) {
		double minMass = 4/3*Math.PI*Math.pow(this.getRadius(),3)*minDensity;
		if (mass >= minMass) this.mass = mass;
		else this.mass = minMass;
	}
	
	private double mass;

	private static final double minDensity = 1.42*Math.pow(10, 12);
	
	@Override
	public double getMass(){
		return this.mass;
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


	public void thrustOn() {
		// TODO Auto-generated method stub
		
	}

	public void thrustOff() {
		// TODO Auto-generated method stub
		
	}

	public double getAcceleration() {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public void terminate() {
		super.terminate();
		getWorld().removeShip(this);
	}

	
	public Set<Bullet> getBullets() {
		return this.bullets;
	}

	private Set<Bullet> bullets = new HashSet<Bullet>();

	public int getNbBullets() {
		return getBullets().size();
	}

	public void loadBullet(Bullet bullet) {
		if(!canHaveAsBullet(bullet)) throw new IllegalArgumentException("The given bullet is invalid.");
		bullets.add(bullet);
		bullet.setShip(this);
	}

	private boolean canHaveAsBullet(Bullet bullet) {
		if (bullet.getShip() != null && bullet.getShip() != this) return false;
		if (bullet.getSource() != null && bullet.getSource() != this) return false;
		return (this.getDistanceBetweenCenters(bullet) < this.getRadius() - bullet.getRadius());
	}

	public void loadBullet(Bullet... bullets) {
		for(Bullet bullet : bullets) this.loadBullet(bullet);
	}

	public void removeBullet(Bullet bullet) {
		if (bullet.getShip() != this) throw new IllegalArgumentException();
		bullets.remove(bullet);
		bullet.setShip(null);
	}

	
	public void fireBullet(){
		Bullet bullet = bullets.iterator().next();
		double orientation = this.getOrientation();
		double distance = (2.5*this.getRadius()+2.5*bullet.getRadius())/2;//TODO
		double[] startPosition = new double[]{getPosition()[0]+distance*Math.cos(orientation),getPosition()[1]+distance*Math.sin(orientation)};
		this.removeBullet(bullet);
		bullet.fire(startPosition,orientation);
	}



}
