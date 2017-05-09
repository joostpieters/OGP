package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of bullets with a given position, velocity and radius in two dimensions.
 * @ see implementation
 * @version 3.1
 * @author  Sander Leyssens & Sarah Joseph
 */

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
	 *         | result == this.remainingBounces
	 */
	public int getRemainingBounces(){
		return this.remainingBounces;
	}
	
	/**
	 * Decrement the remaining bounces by one. 
	 * @post   The new value of the remaining bounces is the old value minus one
	 *         | new.getRemainingBounces() == getRemainingBounces() - 1
	 */
	private void removeABounce(){
		remainingBounces--;
	}
	
	private int remainingBounces = 2;

	/**
	 * Set the ship of this bullet to the given ship.
	 * @param ship
	 * 	      The ship this bullet belongs to
	 * @post  If the given ship contains the bullet or the given ship is null, the ship is set to the given ship.
	 *        | if (ship == null || ship.getBullets().contains(this)) new.getShip == ship
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
	 * @post The new source of this bullet is the given ship
	 *       | new.getSource() == ship
	 */
	public void setSource(Ship ship) {
		this.source = ship;
	}
	
	private Ship source;

	/**
	 * Return the density of this bullet.
	 * @return Returns the density of this bullet.
	 *         | result == density
	 */
	public double getDensity(){
		return density;
	}
	
	/**
	 * Return the minimum radius of this bullet.
	 * @return Returns the minimum radius of this bullet.
	 *         | result == minRadius
	 */
	public double getMinRadius(){
		return minRadius;
	}
	
	private static final double density = 7.8E12;
	
	private static final double minRadius = 1;

	/**
	 * Resolve collisions between this bullet and another entity
	 * @param  entity
	 * 	       The given entity
	 * @effect If this bullet collides with its source, it is reloaded onto its source
	 *         | if (this.getSource() == entity) entity.loadBullet(this);
	 * @effect If this bullet collides with another entity both will be terminated
	 * 	       | else entity.terminate() && this.terminate()
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
	 * Resolves collisions between this bullet and one of the boundaries of its world
	 * @effect The bullet is terminated if remainingBounces is lower than zero
	 *        | if(this.getRemainingBounces() < 0) this.terminate()
	 */
	public void collideBoundary() {
		super.collideBoundary();
		this.removeABounce();
		if (this.getRemainingBounces() < 0) this.terminate();
	}
	
	/**
	 * Terminates the entity that contains this bullet
	 * @post The entity containing this bullet is terminated
	 *       | super.terminate()
	 */
	public void terminate(){
		super.terminate();
		if (this.getShip() != null) ship.removeBullet(this);
	}
	
}
