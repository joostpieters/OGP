package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

public class Bullet extends Entity {

	/**
	 * Create a new bullet with the given position, velocity and radius.
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
	 * @post   The new position of the ship is equal to the given position.
	 *         | new.getPosition().equals({x,y})
	 * @post   The new velocity of the ship is equal to the given velocity.
	 *         | new.getVelocity().equals({xVelocity,yVelocity})
	 * @post   The new radius of the ship is equal to the given radius.
	 *         | new.getRadius() == radius
	 */
	public Bullet(double x, double y, double xVelocity, double yVelocity,
			double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
	}
	
	/**
	 * Return the remaining bounces the bullet can have with a boundary
	 * @return Returns the remaining bounces
	 *         | result = this.remainingBounces
	 */
	public int getRemainingBounces(){
		return this.remainingBounces;
	}
	
	/**
	 * Decrement the remaining bounces by one. 
	 * @post   The new value of the remaining bounces is the old value minus one
	 *         | remainingBounces--
	 */
	private void removeABounce(){
		remainingBounces--;
	}
	
	private int remainingBounces = 2;

	/**
	 * Set the ship of this bullet to the given ship.
	 * @param ship
	 * 	      The ship this bullet belongs to
	 * @Pre   The given orientation is valid.
	 * 	      | isValidOrientation(orientation)
	 * @post  The given ship contains this bullet
	 *        | this.ship = ship
	 */
	@Raw
	public void setShip(@Raw Ship ship) {
		if (ship == null || ship.getBullets().contains(this)){
			this.ship = ship;
		}
	}
	
	private Ship ship;
	
	/**
	 * Return the ship that this bullet belongs to 
	 * @return returns this bullet's ship
	 *         | result == this.ship
	 */

	public Ship getShip() {
		return this.ship;
	}

	/**
	 * Return the source of this bullet
	 * @return returns the source of this bullet
	 *         | result == this.source
	 */
	public Ship getSource() {
		return this.source;
	}
	
	/**
	 * Set the source for the given ship
	 * @post sets the source of this bullet to the given ship
	 *       | this.source = ship
	 */
	public void setSource(Ship ship) {
		this.source = ship;
	}
	
	private Ship source;

	/**
	 * Return the mass of this bullet.
	 * @return Returns the mass of this 
	 *         | result == mass
	 */
	@Override
	public double getMass() {
		double radius = getRadius();
		double mass = 3/4 * Math.PI * Math.pow(radius, 3) * getDensity();
		return mass;
		
	}

	/**
	 * Return the density of this bullet.
	 * @return Returns the density of this bullet.
	 *         | result == minDensity
	 */
	public double getDensity(){
		return density;
	}
	
	
	public double getMinRadius(){
		return minRadius;
	}
	
	private static final double density = 7.8*Math.pow(10, 12);
	
	private static final double minRadius = 1;

	/**
	 * Resolve collisions between this bullet and another entity
	 * @param  entity
	 * 	       The given entity
	 * @post   If this bullet collides with another entity both will be terminated
	 * 	       | entity.terminate() && this.terminate()
	 */
	@Override
	public void collide(Entity entity) {
		if (this.getSource() == entity) {
			this.setPosition(entity.getPosition());
			((Ship)entity).loadBullet(this);
		}
		else {
			entity.terminate();
			this.terminate();
		}
	}
	
	/**
	 * Resolves collisions between this bullet and one of it's world's boundaries
	 * @post  The boundary is terminated if remainingBounces is lower than zero
	 *        | if(this.getRemainingBounces() < 0) this.terminate()
	 */
	public void collideBoundary() {
		super.collideBoundary();
		this.removeABounce();
		if (this.getRemainingBounces() < 0) this.terminate();
	}

	/**
	 * Terminate this ship from the world it is located in.
	 * @post   The world which the ship was set to does not contain the ship anymore
	 *         | getWorld().removeShip(this)
	 */
	@Override
	public void terminate() {
		super.terminate();
		if(this.getWorld() != null) getWorld().removeBullet(this);
	}
	
}
