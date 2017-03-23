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
<<<<<<< HEAD
		super(x,y,xVelocity,yVelocity,radius,orientation);
		this.setMassShip(mass);
		Bullet[] bullets = new Bullet[15];
		for(int i = 0; i < 15; i++){
//			Bullet bullet = new Bullet();//TODO
//			bullets[i] = bullet;
=======
		super(x,y,xVelocity,yVelocity,radius);
		this.setOrientation(orientation);
		this.setMass(mass);
		Bullet[] bullets = new Bullet[15];
		for(int i = 0; i < 15; i++){
			Bullet bullet = new Bullet(x, y, xVelocity, yVelocity, radius*(1+Math.random()*3)/5);
			bullets[i] = bullet;
>>>>>>> refs/remotes/origin/master
		}
		this.loadBullet(bullets);
	}
	
	@Override
	public boolean isValidRadius(double radius){
		return (radius > minRadius);
	}

<<<<<<< HEAD
	private void setMassShip(double mass) {
		double minMass = 4/3*Math.PI*Math.pow(this.getRadius(),3)*minDensity;
		if (mass >= minMass) this.mass = mass;
		else this.mass = minMass;
	}
	
	private double mass;

	private static final double minDensity = 1.42*Math.pow(10, 12);
	
	public double getMassShip(){
		return this.mass;
=======
	/**
	 * Return the validity of the given orientation for any entity. The orientation is a
	 * type double between 0 and 2*pi.
	 * @param  orientation
	 * 	       The given orientation.
	 * @return Returns the validity of the given orientation.
	 *         | result == (orientation >=0 && orientation < 2*Math.PI)
	 */
	@Raw
	public static boolean isValidOrientation(double orientation) {
		return (orientation >=0 && orientation < 2*Math.PI);
	}

	/**
	 * Return the orientation of this entity as type double between 0 and 2*pi.
	 * @return Returns the orientation of this entity.
	 *         | result == this.orientation
	 */
	@Basic
	@Raw
	public double getOrientation() {
		return orientation;
	}

	/**
	 * Set the orientation of this entity to the given position.
	 * @param orientation
	 * 	      The orientation of this entity
	 * @Pre   The given orientation is valid.
	 * 	      | isValidOrientation(orientation)
	 * @post  The new orientation of this entity is equal to the given orientation.
	 *        | new.getOrientation() == orientation
	 */
	@Raw
	protected void setOrientation(double orientation) {
		assert(isValidOrientation(orientation));
		this.orientation = orientation;
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
		double totalMass = this.mass;
		for (Bullet bullet : this.getBullets()) totalMass += bullet.getMass();
		return totalMass;
>>>>>>> refs/remotes/origin/master
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


<<<<<<< HEAD
	public void thrustOn() {
		// TODO Auto-generated method stub
		
	}

	public void thrustOff() {
		// TODO Auto-generated method stub
		
	}

	public double getAcceleration() {
		// TODO Auto-generated method stub
		return 0;
=======
	public boolean isThrusterActive() {
		return thrustEnabled;
	}

	public void thrustOn() {
		thrustEnabled = true;
	}

	public void thrustOff() {
		thrustEnabled = false;
		
	}
	
	private boolean thrustEnabled;

	public double getAcceleration() {
		if (!thrustEnabled) return 0;
		double force = 1.1*Math.pow(10, 21);
		return getMass()/force;
>>>>>>> refs/remotes/origin/master
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

<<<<<<< HEAD
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
		bullet.fire();
		this.removeBullet(bullet);
=======
	private double orientation;

	public int getNbBullets() {
		return getBullets().size();
	}

	public void loadBullet(Bullet bullet) {
		if(!canHaveAsBullet(bullet)) throw new IllegalArgumentException("The given bullet is invalid.");
		bullets.add(bullet);
		bullet.setShip(this);
	}

	private boolean canHaveAsBullet(Bullet bullet) {
		if (bullet == null) return false;
		if (bullet.getShip() != null && bullet.getShip() != this) return false;
		if (bullet.getSource() != null && bullet.getSource() != this) return false;
		return (this.getDistanceBetweenCenters(bullet) < 0.99*(this.getRadius() - bullet.getRadius()));
	}

	public void loadBullet(Bullet... bullets) {
		for(Bullet bullet : bullets) this.loadBullet(bullet);
	}

	public void removeBullet(Bullet bullet) {
		if (bullet.getShip() != this) throw new IllegalArgumentException();
		bullets.remove(bullet);
		bullet.setShip(null);
	}
	
	public void move(double dt){
		super.move(dt);
		thrust(getAcceleration()*dt);
	}

	
	public void fireBullet(){
		Bullet bullet = getBullets().iterator().next();
		bullet.fire();
	}

	@Override
	public void collide(Entity entity) {
		if(entity instanceof Bullet) {
			Bullet bullet = (Bullet)entity;
			if (bullet.getSource() == this) this.loadBullet(bullet);
			else{
				bullet.terminate();
				this.terminate();
			}
		}
		if(entity instanceof Ship) {
			double mi = this.getMass();double mj = entity.getMass();
			double sigma = this.getRadius() + entity.getRadius();
			double[] deltaR = this.getPositionDifference(entity);
			double[] deltaV = this.getVelocityDifference(entity);
			double j = 2*mi*mj*(dotProduct(deltaV,deltaR))/(sigma*(mi+mj));
			double jx = j*deltaR[0]/sigma;double jy = j*deltaR[1]/sigma;
			double[] oldVelocityi = this.getVelocity();
			double[] oldVelocityj = entity.getVelocity();
			double[] newVelocityi = new double[]{oldVelocityi[0]+jx/mi,oldVelocityi[1]+jy/mi};
			double[] newVelocityj = new double[]{oldVelocityj[0]+jx/mj,oldVelocityj[1]+jy/mj};
			this.setVelocity(newVelocityi);entity.setVelocity(newVelocityj);
		}
		
>>>>>>> refs/remotes/origin/master
	}



}
