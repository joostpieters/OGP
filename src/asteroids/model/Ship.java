package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class of ships involving a position, velocity, radius and orientation.
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
	 * @throws TODO
	 */
	public Ship(double x, double y, double xVelocity,
			double yVelocity, double radius, double orientation) throws IllegalArgumentException {
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
	 * 		   The x-and y-coordinate of the ship 
	 * @Pre    The given position is valid.
	 *         |(Double.isNaN(position[0]) && (Double.isNaN(position[1])
	 * @post   The new value of the position of the ship equals position.
	 *         | new.getPosition() == position
	 * @throws IllegalArgumentException
	 *         The given position is not an array of two values.
	 *         | position.length != 2
	 *
	 */
	private void setPosition(double[] position) throws IllegalArgumentException {
		if (position.length != 2) throw new IllegalArgumentException();
		if(Double.isNaN(position[0])) throw new IllegalArgumentException("NaN is not allowed as a coordinate of position.");
		if(Double.isNaN(position[1])) throw new IllegalArgumentException("NaN is not allowed as a coordinate of position.");
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
	
	/**
	 * @param  velocity
	 * 		   The x-and y-velocity of the ship
	 * @Pre    The given value for velocity is valid.
	 * 		   | (Double.isNaN(velocity[0])) && (Double.isNaN(velocity[1]))
	 * @post   the new velocity is slower than the speed of light and set to the given velocity
	 * 		   |speed < SPEED_OF_LIGHT && this.velocity = velocity TODO: edit
	 */
	
	private void setVelocity(double[] velocity) {
		if (Double.isNaN(velocity[0]))
			velocity[0] = 0;
		if (Double.isNaN(velocity[1]))
			velocity[1] = 0;
		if (velocity.length == 1) this.setVelocity(new double[] {velocity[0],0});
		double speed = this.getSpeed();
		if (speed < SPEED_OF_LIGHT) this.velocity = velocity;
		else this.velocity = new double[] {velocity[0]*SPEED_OF_LIGHT/speed,velocity[1]*SPEED_OF_LIGHT/speed};
	}
	
	/**
	 * Return the speed of this ship as type double.
	 * @return Returns the speed of this ship.
	 *         | result == Math.sqrt(dotProduct(this.getVelocity(), this.getVelocity()))
	 */
	@Raw
	public double getSpeed() {
		return Math.sqrt(dotProduct(this.getVelocity(), this.getVelocity()));
	}
	
	private double[] velocity = new double[2];
	
	public static double SPEED_OF_LIGHT = 300000;
	
	
	/**
	 * Return the validity of a potential radius for this ship as type boolean.
	 * @param radius
	 * 		  | A potential radius for this ship
	 * @return Returns the validity of the potential radius for this ship.
	 *        | result == (radius > MIN_RADIUS)
	 */
	@Raw
	public boolean isValidRadius(double radius) {
		return (radius > MIN_RADIUS);
	}
	
	/**
	 * Return the radius of this ship.
	 * @return Returns the radius of this ship.
	 *         | result == this.radius
	 */
	@Basic @Raw
	public double getRadius() {
		return radius;
	}
	
	/**
	 * Set the radius to a given value.
	 * @param  radius
	 * 		   The radius of the ship.
	 * @post   the radius is valid.
	 * 		   | (!isValidRadius(radius)) throw new IllegalArgumentException()
	 * @post   The new radius of this ship is equal to the given radius.
	 *         | new.getRadius() = radius
	 * @throws IllegalArgumentException
	 *         The given radius is not valid
	 *         | !isValidRadius(radius)
	 */
	
	private void setRadius(double radius) throws IllegalArgumentException {
		if (!isValidRadius(radius)) throw new IllegalArgumentException();
		this.radius = radius;
	}

	private double radius;
	
	private static final double MIN_RADIUS = 10;
	
	/**
	 * Return the validity of the orientation of this ship. The orientation is a
	 * type double between 0 and 2*pi.
	 * @param orientation
	 * 		  the orientation of this ship
	 * @return Returns the validity of the orientation of this ship.
	 *         | result == (orientation >=0 && orientation < 2*Math.PI)
	 */
	@Raw
	public boolean isValidOrientation(double orientation) {
		return (orientation >=0 && orientation < 2*Math.PI);
	}
	
	/**
	 * Return the orientation of this ship as type double between 0 and 2*pi.
	 * @Pre    Ship is created.
	 * @return Returns the orientation of this ship.
	 *         | result == this.orientation
	 */
	@Basic @Raw
	public double getOrientation() {
		return orientation;
	}
	
	/**
	 * Set the orientation of this ship to the given position.
	 * @param  orientation
	 * 		   The orientation of the ship
	 * @Pre    The given orientation is valid.
	 * 		   | isValidOrientation(orientation)
	 * @post   The new orientation of this ship is equal to the given orientation.
	 *         | new.getOrientation() == orientation
	 */
	
	private void setOrientation(double orientation) {
		assert(isValidOrientation(orientation));
		this.orientation = orientation;
	}

	private double orientation;
	


	/**
	 * Update this ship's position, assuming it moves <code>dt</code>
	 * seconds at its current velocity.
	 * @param dt
	 * 		  The time of movement of this ship.
	 * @Pre   The given time is valid.
	 * 		  | 
	 * @post  The position is set to the new position after movement for a period of dt.
	 * 		  |setPosition(getPositionAfterMovingForAPeriodOf(dt));
	 * @throws IllegalArgumentException
	 *         The given time is not valid
	 *         | Double.isNaN(dt) || dt<0
	 *         
	 */
	public void move(double dt) throws IllegalArgumentException {
		//TODO Defensive implementation
		if (Double.isNaN(dt)) throw new IllegalArgumentException("NaN is not allowed as a value of time");
		if (dt < 0) throw new IllegalArgumentException();
		setPosition(getPositionAfterMovingForAPeriodOf(dt));
		
	}
	
	/**
	 * Return the position of this ship as an array of length 2 x-coordinate at 
	 * index 0 and the y-coordinate at index 1, after moving for the given time dt.
	 * @param dt
	 * @return Returns the position of this ship.
	 *         | result == getPosition()[0]+getVelocity[0]*dt,getPosition[1]+getVelocity[1]*dt
	 */
	
	private double[] getPositionAfterMovingForAPeriodOf(double dt) {
		double[] position = getPosition();
		double[] velocity = getVelocity();
		return new double[] {position[0]+velocity[0]*dt,position[1]+velocity[1]*dt};
	}

	/**
	 * Update this ship's velocity based on its current velocity, its
	 * direction and the given <code>amount</code>.
	 * @param amount
	 * 		  The thrust of this ship.
	 * @post The thrust is larger than 0. TODO: may need editing
	 * 		 |if (amount < 0) amount = 0
	 * @post the new velocity is slower than the speed of light
	 * 		 |speed < SPEED_OF_LIGHT
	 * @post The new velocity of this ship is equal the calculated velocity.
	 *       | setVelocity(new double[] {velocity[0]+amount*Math.cos(orientation),velocity[1]+amount*Math.sin(orientation)});
	 */
	
	public void thrust(double amount) {
		//TODO Total implementation
		if (amount < 0 || Double.isNaN(amount)) amount = 0;
		double[] velocity = getVelocity();
		double orientation = getOrientation();
		setVelocity(new double[] {velocity[0]+amount*Math.cos(orientation),velocity[1]+amount*Math.sin(orientation)});
	}


	/**
	 * Update the direction of this ship by adding <code>angle</code>
	 * (in radians) to its current direction. <code>angle</code> may be
	 * negative.
	 * @Pre   The given angle is valid.
	 * 		  | isValidOrientation(getOrientation()+angle)
	 * @param angle
	 * 		  The ship's angle of deviation from it's original orientation.
	 * @post  The new orientation of this ship is equal to the angle added 
	 * 	      to the initial orientation.
	 *        | new.getOrientation() = this.getOrientation()+angle
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
	 * @param  ship2
	 * 		   The ship named ship2.
	 * @return Return 0 if this ship and ship2 are identical.
	 * 		   | if (ship2 == this) result == 0
	 * @return Return the distance between ship and ship2.
	 *         | result == this.getDistanceBetweenCenters(ship2) - this.getRadius() - ship2.getRadius())
	 * @throws NullPointerException
	 *         Ship2 is not created
	 *         | ship2 == null
	 */
	
	public double getDistanceBetween(Ship ship2) throws NullPointerException {
		if (ship2 == null) throw new NullPointerException("The second ship does not exist.");
		if (ship2 == this) return 0;
		else return (this.getDistanceBetweenCenters(ship2) - this.getRadius() - ship2.getRadius());
	}
	
	
	/**
	 * Return the distance between the center of this ship and <code>ship2</code>.
	 * 
	 * The absolute value of the result of this method is the minimum distance
	 * either ship should move such that both ships are adjacent. Note that the
	 * result must be negative if the ships overlap. The distance between a ship
	 * and itself is 0.
	 * @param  ship2
	 * 		   The ship named ship2.
	 * @return Return the distance between the centers of ship and ship2.
	 *         | result == Math.sqrt(dotProduct(this.getPositionDifference(ship2), this.getPositionDifference(ship2))
	 * @throws NullPointerException
	 *         Ship2 is not created
	 *         | ship2 == null
	 */
	
	public double getDistanceBetweenCenters(Ship ship2) throws NullPointerException {
		if (ship2 == null) throw new NullPointerException("The second ship does not exist.");
		double[] positionDifference = getPositionDifference(ship2);
		double distance = Math.sqrt(dotProduct(positionDifference, positionDifference));
		return distance;
	}
	
	/**
	 * Return the difference in position of this ship and ship2
	 * @param  ship2
	 * 		   The ship named ship2.
	 * @return Return the difference in position between ship and ship2.
	 *         | ship2.getPosition()[0]-this.getPosition()[0],ship2.getPosition()[1]-this.getPosition()[1]}
	 * @throws NullPointerException
	 *         Ship2 is not created
	 *         | ship2 == null
	 */
	
	public double[] getPositionDifference(Ship ship2) throws NullPointerException {
		if (ship2 == null) throw new NullPointerException("The second ship does not exist.");
		return new double[] {ship2.getPosition()[0]-this.getPosition()[0],ship2.getPosition()[1]-this.getPosition()[1]};
	}
	
	/**
	 * Return the difference in velocity between this ship and ship2
	 * @param  ship2
	 * 		   The ship named ship2.
	 * @return Return the difference in velocity between between ship and ship2.
	 *         | result == {ship2.getVelocity()[0]-this.getVelocity()[0],ship2.getVelocity()[1]-this.getVelocity()[1]}
	 * @throws NullPointerException
	 *         Ship2 is not created
	 *         | ship2 == null
	 *         
	 */
	
	public double[] getVelocityDifference(Ship ship2) throws NullPointerException {
		if (ship2 == null) throw new NullPointerException("The second ship does not exist.");
		return new double[] {ship2.getVelocity()[0]-this.getVelocity()[0],ship2.getVelocity()[1]-this.getVelocity()[1]};
	}
	
	/**
	 * Check whether this ship and <code>ship2</code> overlap. A ship
	 * always overlaps with itself.
	 * @param  ship2
	 * 		   The ship named ship2.
	 * @return Return whether ship and ship2 overlap
	 *         | result == (this.getDistanceBetween(ship2) < 0)
	 * @throws NullPointerException
	 *         Ship2 is not created
	 *         | ship2 == null
	 */
	
	public boolean overlap(Ship ship2) throws NullPointerException {
		if (ship2 == null) throw new NullPointerException("The second ship does not exist.");
		if (this == ship2) return true;
		else return (this.getDistanceBetween(ship2) < 0);
	}


	/**
	 * Return the number of seconds until the first collision between
	 * this ship and <code>ship2</code>, or Double.POSITIVE_INFINITY if
	 * they never collide. A ship never collides with itself.
	 * @param ship2
	 * 		  The ship named ship2
	 * @post   The time to collision is a positive number
	 * 		   | dotProduct(deltaR,deltaV) < 0
	 * @return Return  the time to collision between ship and ship2 TODO
	 * 		   | result == -(dotProduct(deltaR,deltaV)+Math.sqrt(d))/dotProduct(deltaV,deltaV)
	 * @throws NullPointerException
	 *         Ship2 is not created
	 *         | ship2 == null
	 */
	public double getTimeToCollision(Ship ship2) throws NullPointerException, IllegalArgumentException {
		if (ship2 == null) throw new NullPointerException("The second ship does not exist.");
		if (this.overlap(ship2)) throw new IllegalArgumentException("The ships overlap.");
		double[] deltaR = getPositionDifference(ship2);
		double[] deltaV = getVelocityDifference(ship2);
		if (dotProduct(deltaR,deltaV) >= 0) return Double.POSITIVE_INFINITY;
		double d = Math.pow(dotProduct(deltaV,deltaR), 2) - (dotProduct(deltaV,deltaV))*(dotProduct(deltaR,deltaR)-Math.pow(this.getRadius()+ship2.getRadius(), 2));
		if (d <= 0) return Double.POSITIVE_INFINITY;
		else {
			return -(dotProduct(deltaR,deltaV)+Math.sqrt(d))/dotProduct(deltaV,deltaV);
		}
	}

	/**
	 * Calculates the dot product of the given vectors of length 2
	 * @param vector1
	 * 		  A given vector of length 2
	 * @param vector2
	 * 		  A given vector of length 2
	 * @return Return the dot product of the two vectors
	 * 		   | result == vector1[0]*vector2[0]+vector1[1]*vector2[1]		
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
	 * @param  ship2
	 * 		   The ship named ship2
	 * @return If the time to collision is not finite, return null
	 * 		   | if (time == Double.POSITIVE_INFINITY) result == null
	 * @return result == the position at time of collision between ship and ship2
	 */
	
	public double[] getCollisionPosition(Ship ship2) throws NullPointerException, IllegalArgumentException {
		double time = getTimeToCollision(ship2);
		if (time == Double.POSITIVE_INFINITY) return null;
		return getPositionAfterMovingForAPeriodOf(time);
	}


}
