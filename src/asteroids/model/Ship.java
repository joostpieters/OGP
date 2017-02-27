package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class of ships involving a position, velocity, radius and orientation.
 * 
 * @version 1.4
 * @author  Sander Leyssens & Sarah Joseph
 *
 */
public class Ship {
	
	/**
	 * Create a new ship with the given position, velocity, radius and
	 * orientation (in radians).
	 * @param x
	 *        The x-coordinate of the position of this new ship.
	 * @param y
	 *        The y-coordinate of the position of this new ship.
	 * @param xVelocity
	 *        The x-coordinate of the velocity of this new ship.
	 * @param yVelocity
	 *        The y-coordinate of the velocity of this new ship.
	 * @param radius
	 *        The radius of this new ship.
	 * @param orientation
	 *        The orientation of this new ship.
	 * @post  The new position of this ship is equal to the given position.
	 *        | new.getPosition() == {x,y}
	 * @post  The new velocity of this ship is equal to the given velocity.
	 *        | new.getVelocity() == {xVelocity,yVelocity}
	 * @post  The new radius of this ship is equal to the given radius.
	 *        | new.getRadius() == radius
	 * @post  The new orientation of this ship is equal to the given orientation.
	 *        | new.getOrientation() == orientation
	 */
	public Ship(double x, double y, double xVelocity,
			double yVelocity, double radius, double orientation){
		this.setPosition(new double[] {x,y});
		this.setVelocity(new double[] {xVelocity,yVelocity});
		this.setRadius(radius);
		this.setOrientation(orientation);
	}
	
	/**
	 * Create a new ship with a default position, velocity, radius and
	 * direction.
	 * 
	 * Result is a unit circle centered on <code>(0, 0)</code> facing right. Its
	 * speed is zero.
	 * @post  The new position of this ship is equal to (0,0).
	 *        | new.getPosition() == {0,0}
	 * @post  The new velocity of this ship is equal to (0,0).
	 *        | new.getVelocity() == {0,0}
	 * @post  The new radius of this ship is equal to 1.
	 *        | new.getRadius() == 1
	 * @post  The new orientation of this ship is equal to 0 (faces to the right).
	 *        | new.getOrientation() == 0
     */
	public Ship() {
		this(0,0,0,0,1,0);
	}
	
	/**
	 * Return the position of this ship as an array of length 2, with the
	 * x-coordinate at index 0 and the y-coordinate at index 1.
	 * @return Returns the position of this ship.
	 *         | result == this.position
	 */
	@Basic @Raw
	public double[] getPosition() {
		return position;
	}
	
	/**
	 * Set the position to the given position.
	 * @param  position
	 * @throws IllegalArgumentException
	 *         The given position is not an array of two values.
	 *         | position.length != 2
	 */
	private void setPosition(double[] position) throws IllegalArgumentException {
		if (position.length != 2) throw new IllegalArgumentException();
		this.position = position;
	}
	
	private double[] position = new double[2];
	
	/**
	 * Return the velocity of this ship as an array of length 2, with the velocity
	 * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
	 * @return Returns the velocity of this ship.
	 *         | result == this.velocity
	 */
	@Basic @Raw
	public double[] getVelocity() {
		return velocity;
	}
	
	private void setVelocity(double[] velocity) {
		if (velocity.length == 1) this.setVelocity(new double[] {velocity[0],0});
		double speed = this.getSpeed();
		if (speed < SPEED_OF_LIGHT) this.velocity = velocity;
		else this.velocity = new double[] {velocity[0]/speed,velocity[1]/speed};
	}
	
	@Raw
	public double getSpeed() {
		return Math.sqrt(dotProduct(this.getVelocity(), this.getVelocity()));
	}
	
	private double[] velocity = new double[2];
	
	public static double SPEED_OF_LIGHT = 300000;
	
	@Raw
	public boolean isValidRadius(double radius) {
		return (radius > MIN_RADIUS);
	}
	
	/**
	 * Return the radius of this ship.
	 */
	@Basic @Raw
	public double getRadius() {
		return radius;
	}
	
	private void setRadius(double radius) throws IllegalArgumentException {
		if (!isValidRadius(radius)) throw new IllegalArgumentException();
		this.radius = radius;
	}

	private double radius;
	
	private static final double MIN_RADIUS = 10;
	
	@Raw
	public boolean isValidOrientation(double orientation) {
		return (orientation >=0 && orientation < 2*Math.PI);
	}
	
	/**
	 * Return the orientation of this ship (in radians).
	 */
	@Basic @Raw
	public double getOrientation() {
		return orientation;
	}
	
	private void setOrientation(double orientation) {
		assert(isValidOrientation(orientation));
		this.orientation = orientation;
	}

	private double orientation;
	


	/**
	 * Update this ship's position, assuming it moves <code>dt</code>
	 * seconds at its current velocity.
	 */
	public void move(double dt) throws IllegalArgumentException {
		//TODO Defensive implementation
		if (dt < 0) throw new IllegalArgumentException();
		setPosition(getPositionAfterMovingForAPeriodOf(dt));
		
	}
	
	private double[] getPositionAfterMovingForAPeriodOf(double dt) {
		double[] position = getPosition();
		double[] velocity = getVelocity();
		return new double[] {position[0]+velocity[0]*dt,position[1]+velocity[1]*dt};
	}


	/**
	 * Update this ship's velocity based on its current velocity, its
	 * direction and the given <code>amount</code>.
	 */
	public void thrust(double amount) {
		//TODO Total implementation
		if (amount < 0) amount = 0;
		double[] velocity = getVelocity();
		double orientation = getOrientation();
		setVelocity(new double[] {velocity[0]+amount*Math.cos(orientation),velocity[1]+amount*Math.sin(orientation)});
	}


	/**
	 * Update the direction of this ship by adding <code>angle</code>
	 * (in radians) to its current direction. <code>angle</code> may be
	 * negative.
	 */
	public void turn(double angle) {
		assert(isValidOrientation(getOrientation()+angle));
		setOrientation(getOrientation()+angle);
	}


	/**
	 * Return the distance between this ship and <code>ship2</code>.
	 * 
	 * The absolute value of the result of this method is the minimum distance
	 * either ship should move such that both ships are adjacent. Note that the
	 * result must be negative if the ships overlap. The distance between a ship
	 * and itself is 0.
	 */
	public double getDistanceBetween(Ship ship2) {
		if (ship2 == this) return 0;
		else return (this.getDistanceBetweenCenters(ship2) - this.getRadius() - ship2.getRadius());
	}
	
	public double getDistanceBetweenCenters(Ship ship2) {
		double[] positionDifference = getPositionDifference(ship2);
		double distance = Math.sqrt(dotProduct(positionDifference, positionDifference));
		return distance;
	}
	
	public double[] getPositionDifference(Ship ship2) {
		return new double[] {ship2.getPosition()[0]-this.getPosition()[0],ship2.getPosition()[1]-this.getPosition()[1]};
	}
	
	public double[] getVelocityDifference(Ship ship2) {
		return new double[] {ship2.getVelocity()[0]-this.getVelocity()[0],ship2.getVelocity()[1]-this.getVelocity()[1]};
	}
	

	/**
	 * Check whether this ship and <code>ship2</code> overlap. A ship
	 * always overlaps with itself.
	 */
	public boolean overlap(Ship ship2) {
		if (this == ship2) return true;
		else return (this.getDistanceBetween(ship2) < 0);
	}


	/**
	 * Return the number of seconds until the first collision between
	 * this ship and <code>ship2</code>, or Double.POSITIVE_INFINITY if
	 * they never collide. A ship never collides with itself.
	 */
	public double getTimeToCollision(Ship ship2) {
		double[] deltaR = getPositionDifference(ship2);
		double[] deltaV = getVelocityDifference(ship2);
		if (dotProduct(deltaR,deltaV) >= 0) return Double.POSITIVE_INFINITY;
		if (this.overlap(ship2)) return Double.POSITIVE_INFINITY;
		else {
			double d = Math.pow(dotProduct(deltaV,deltaR), 2) - (dotProduct(deltaV,deltaV))*(dotProduct(deltaR,deltaR)-Math.pow(this.getRadius()+ship2.getRadius(), 2));
			return -(dotProduct(deltaR,deltaV)+Math.sqrt(d))/dotProduct(deltaV,deltaV);
		}
	}

	/**
	 * Calculates the dot product of the given vectors of length 2
	 * @param vector1
	 * @param vector2
	 * @return
	 */
	@Raw
	private double dotProduct(double[] vector1, double[] vector2) {
		return vector1[0]*vector2[0]+vector1[1]*vector2[1];
	}

	/**
	 * Return the first position where this ship and <code>ship2</code>
	 * collide, or <code>null</code> if they never collide. A ship never
	 * collides with itself.
	 * 
	 * The result of this method is either null or an array of length 2, where
	 * the element at index 0 represents the x-coordinate and the element at
	 * index 1 represents the y-coordinate.
	 */
	public double[] getCollisionPosition(Ship ship2) {
		double time = getTimeToCollision(ship2);
		if (time == Double.POSITIVE_INFINITY) return null;
		return getPositionAfterMovingForAPeriodOf(time);
	}


}
