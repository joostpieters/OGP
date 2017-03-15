package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class Entity {

	/**
	 * Return the validity of a potential position for the ship as type boolean.
	 * @param  position
	 * 	       | A potential position for the ship
	 * @return Returns the validity of the potential position for the ship.
	 *         | result == !((position.length != 2)||(Double.isNaN(position[0]))||(Double.isNaN(position[1])))
	 */
	@Raw
	private static boolean isValidPosition(double[] position) {
		if (position.length != 2) return false;
		if (Double.isNaN(position[0])) return false;
		if (Double.isNaN(position[1])) return false;
		return true;
	}

	/**
	 * Return the position of the ship as an array of length 2, with the
	 * x-coordinate at index 0 and the y-coordinate at index 1.
	 * @return Returns the position of the ship.
	 *         | result.equals(this.position)
	 */
	@Basic
	@Raw
	public double[] getPosition() {
		return position.clone();
	}

	private double[] position = new double[2];

	/**
	 * Set the position to the given position.
	 * @param  position
	 * 	       The x-and y-coordinate of the ship 
	 * @post   The new value of the position of the ship equals position.
	 *         | new.getPosition().equals(position)
	 * @throws IllegalArgumentException
	 *         The given position is not valid.
	 *         | !isValidPosition(position)
	 *
	 */
	@Raw
	protected void setPosition(double[] position) throws IllegalArgumentException {
		if(!isValidPosition(position)) throw new IllegalArgumentException("The given position is not valid.");
		this.position = position.clone();
	}

	public static double SPEED_OF_LIGHT = 300000;

	/**
	 * Return the velocity of the ship as an array of length 2, with the velocity
	 * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
	 * @return Returns the velocity of the ship.
	 *         | result.equals(this.velocity)
	 */
	@Basic
	@Raw
	public double[] getVelocity() {
		return velocity.clone();
	}

	/**
	 * @param  velocity
	 * 	       The x-and y-velocity of the ship
	 * @post   If the given velocity is not valid, nothing happens.
	 *         | if (Double.isNaN(velocity[0]))||(Double.isNaN(velocity[1]))||(velocity.length != 2)
	 * @post   If the given velocity is slower than the speed of light, the new velocity is set to the given velocity.
	 *         | speed = Math.sqrt(dotProduct(velocity, velocity))
	 * 	       | if (Math.sqrt(dotProduct(velocity, velocity)) <= SPEED_OF_LIGHT) new.velocity.equals(velocity)
	 * @post   If the given velocity is faster than the speed of light, the new velocity is given the same direction as the given velocity, but the speed of light.
	 *         | speed = Math.sqrt(dotProduct(velocity, velocity))
	 *         | new.velocity.equals({velocity[0]*SPEED_OF_LIGHT/speed,velocity[1]*SPEED_OF_LIGHT/speed})
	 */
	protected void setVelocity(double[] velocity) {
		if (Double.isNaN(velocity[0])) return;
		if (Double.isNaN(velocity[1])) return;
		if (velocity.length != 2) return;
		double speed = Math.sqrt(dotProduct(velocity, velocity));
		if (speed <= SPEED_OF_LIGHT) this.velocity = velocity.clone();
		else this.velocity = new double[] {velocity[0]*SPEED_OF_LIGHT/speed,velocity[1]*SPEED_OF_LIGHT/speed};
	}

	/**
	 * Return the speed of the ship as type double.
	 * @return Returns the speed of the ship.
	 *         | result == Math.sqrt(dotProduct(this.getVelocity(), this.getVelocity()))
	 */
	@Raw
	public double getSpeed() {
		return Math.sqrt(dotProduct(this.getVelocity(), this.getVelocity()));
	}

	private double[] velocity = new double[2];

	/**
	 * Return the validity of a potential radius for any ship as type boolean.
	 * @param  radius
	 * 	       | A potential radius for the ship
	 * @return Returns the validity of the potential radius for the ship.
	 *         | result == (radius > minRadius)
	 */
	@Raw
	public abstract boolean isValidRadius(double radius);

	protected double radius;

	protected void setRadius(double radius){
		if(!isValidRadius(radius)) throw new IllegalArgumentException("The radius is invalid.");
		this.radius = radius;
	}
	
	/**
	 * Return the radius of the ship.
	 * @return Returns the radius of the ship.
	 *         | result == this.radius
	 */
	@Basic
	@Raw
	public final double getRadius() {
		return radius;
	}
	  
	/**
	 * Return the validity of the given orientation for any ship. The orientation is a
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

	private double orientation;

	/**
	 * Return the orientation of the ship as type double between 0 and 2*pi.
	 * @return Returns the orientation of the ship.
	 *         | result == this.orientation
	 */
	@Basic
	@Raw
	public double getOrientation() {
		return orientation;
	}

	/**
	 * Set the orientation of the ship to the given position.
	 * @param orientation
	 * 	      The orientation of the ship
	 * @Pre   The given orientation is valid.
	 * 	      | isValidOrientation(orientation)
	 * @post  The new orientation of the ship is equal to the given orientation.
	 *        | new.getOrientation() == orientation
	 */
	@Raw
	protected void setOrientation(double orientation) {
		assert(isValidOrientation(orientation));
		this.orientation = orientation;
	}

	/**
	 * Calculates the dot product of the given vectors of length 2
	 * @param  vector1
	 * 	       A given vector of length 2
	 * @param  vector2
	 * 	       A given vector of length 2
	 * @return Return the dot product of the two vectors
	 * 	       | result.equals(vector1[0]*vector2[0]+vector1[1]*vector2[1])
	 */
	@Raw
	private static double dotProduct(double[] vector1, double[] vector2) {
		return vector1[0]*vector2[0]+vector1[1]*vector2[1];
	}

	/**
	 * Return the validity of the given time difference.
	 * @param  dt
	 * 	       The given time difference.
	 * @return Returns true if the time difference is larger than or equal to 0, false otherwise.
	 *         | result == (dt >= 0)
	 */
	private boolean isValidDt(double dt) {
		return (dt >= 0);
	}

	/**
	 * Update the ship's position, assuming it moves <code>dt</code>
	 * seconds at its current velocity.
	 * @param  dt
	 * 	       The time of movement of the ship.
	 * @post   The position is set to the new position after movement for a period of dt.
	 * 	       | new.getPosition().equals(getPositionAfterMovingForAPeriodOf(dt))
	 * @throws IllegalArgumentException
	 *         The given time difference is not valid.
	 *         | !isValidDt(dt)
	 *         
	 */
	public void move(double dt) throws IllegalArgumentException {
		setPosition(getPositionAfterMovingForAPeriodOf(dt));
	}

	/**
	 * Return the position of the ship as an array of length 2 x-coordinate at 
	 * index 0 and the y-coordinate at index 1, after moving for the given time dt.
	 * @param  dt
	 * @return Returns the position of the ship.
	 *         | result.equals({getPosition()[0]+getVelocity[0]*dt,getPosition[1]+getVelocity[1]*dt})
	 * @throws IllegalArgumentException
	 *         The given time difference is not valid.
	 *         | !isValidDt(dt)
	 */
	@Raw
	private double[] getPositionAfterMovingForAPeriodOf(double dt) {
		if (!isValidDt(dt)) throw new IllegalArgumentException("The given time lapse is invalid");
		double[] position = getPosition();
		double[] velocity = getVelocity();
		return new double[] {position[0]+velocity[0]*dt,position[1]+velocity[1]*dt};
	}

	/**
	 * Return the distance between the ship and <code>ship2</code>.
	 * 
	 * The absolute value of the result of this method is the minimum distance
	 * either ship should move such that both ships are adjacent. Note that the
	 * result must be negative if the ships overlap. The distance between a ship
	 * and itself is 0.
	 * @param  ship2
	 * 	       The ship named ship2.
	 * @return Return 0 if the ship and ship2 are identical.
	 * 	       | if (ship2 == this) result == 0
	 * @return Return the distance between the centers of the ship and ship2, subtracted by the radius of both ships.
	 *         | result == this.getDistanceBetweenCenters(ship2) - this.getRadius() - ship2.getRadius())
	 * @throws IllegalArgumentException
	 *         Ship2 is not created
	 *         | ship2 == null
	 */
	public double getDistanceBetween(Ship ship2) throws NullPointerException {
		if (ship2 == null) throw new IllegalArgumentException("The second ship does not exist.");
		if (ship2 == this) return 0;
		else return (this.getDistanceBetweenCenters(ship2) - this.getRadius() - ship2.getRadius());
	}

	/**
	 * Return the distance between the center of the ship and <code>ship2</code>.
	 * 
	 * @param  ship2
	 * 	       The ship named ship2.
	 * @return Return the distance between the centers of ship and ship2.
	 *         | result == Math.sqrt(dotProduct(this.getPositionDifference(ship2), this.getPositionDifference(ship2))
	 * @throws IllegalArgumentException
	 *         Ship2 is not created
	 *         | ship2 == null
	 */
	@Raw
	public double getDistanceBetweenCenters(@Raw Ship ship2) throws NullPointerException {
		if (ship2 == null) throw new IllegalArgumentException("The second ship does not exist.");
		double[] positionDifference = getPositionDifference(ship2);
		double distance = Math.sqrt(dotProduct(positionDifference, positionDifference));
		return distance;
	}

	/**
	 * Return the difference in position of the ship and ship2
	 * @param  ship2
	 * 	       The ship named ship2.
	 * @return Return the difference in position between ship and ship2.
	 *         | result.equals({ship2.getPosition()[0]-this.getPosition()[0],ship2.getPosition()[1]-this.getPosition()[1]})
	 * @throws IllegalArgumentException
	 *         Ship2 is not created
	 *         | ship2 == null
	 */
	@Raw
	public double[] getPositionDifference(@Raw Ship ship2) throws IllegalArgumentException {
		if (ship2 == null) throw new NullPointerException("The second ship does not exist.");
		return new double[] {ship2.getPosition()[0]-this.getPosition()[0],ship2.getPosition()[1]-this.getPosition()[1]};
	}

	/**
	 * Return the difference in velocity between the ship and ship2
	 * @param  ship2
	 * 	       The ship named ship2.
	 * @return Return the difference in velocity between between ship and ship2.
	 *         | result.equals({ship2.getVelocity()[0]-this.getVelocity()[0],ship2.getVelocity()[1]-this.getVelocity()[1]})
	 * @throws IllegalArgumentException
	 *         Ship2 is not created
	 *         | ship2 == null
	 *         
	 */
	@Raw
	public double[] getVelocityDifference(@Raw Ship ship2) throws IllegalArgumentException {
		if (ship2 == null) throw new IllegalArgumentException("The second ship does not exist.");
		return new double[] {ship2.getVelocity()[0]-this.getVelocity()[0],ship2.getVelocity()[1]-this.getVelocity()[1]};
	}

	/**
	 * Check whether the ship and <code>ship2</code> overlap. A ship
	 * always overlaps with itself.
	 * @param  ship2
	 * 	       The ship named ship2.
	 * @return Return whether ship and ship2 overlap
	 *         | result == (this.getDistanceBetween(ship2) < 0)
	 * @throws IllegalArgumentException
	 *         Ship2 is not created
	 *         | ship2 == null
	 */
	public boolean overlap(Ship ship2) throws IllegalArgumentException {
		if (ship2 == null) throw new IllegalArgumentException("The second ship does not exist.");
		if (this == ship2) return true;
		else return (this.getDistanceBetween(ship2) < 0);
	}

	/**
	 * Return the number of seconds until the first collision between
	 * the ship and <code>ship2</code>, or Double.POSITIVE_INFINITY if
	 * they never collide. A ship never collides with itself.
	 * @param  ship2
	 * 	       The ship named ship2
	 * @return Return the time to collision between ship and ship2 
	 *         Returns the smallest time dt such that the distance between the ships after moving for a time period of dt is 0
	 *         | Math.sqrt(dotProduct(ship2.getPositionAfterMovingForAPeriodOf(dt), this.getPositionAfterMovingForAPeriodOf(dt))) == ship2.getRadius() + this.getRadius()
	 * @throws IllegalArgumentException
	 *         Ship2 is not created or the two ships overlap
	 *         | ship2 == null || this.overlap(ship2)
	 */
	public double getTimeToCollision(Ship ship2) throws IllegalArgumentException {
		if (ship2 == null) throw new IllegalArgumentException("The second ship does not exist.");
		if (this.overlap(ship2)) throw new IllegalArgumentException("The ships overlap.");
		double[] deltaR = this.getPositionDifference(ship2);
		double[] deltaV = this.getVelocityDifference(ship2);
		if (dotProduct(deltaR,deltaV) >= 0) return Double.POSITIVE_INFINITY;
		double d = Math.pow(dotProduct(deltaV,deltaR), 2) - (dotProduct(deltaV,deltaV))*(dotProduct(deltaR,deltaR)-Math.pow(this.getRadius()+ship2.getRadius(), 2));
		if (d <= 0) return Double.POSITIVE_INFINITY;
		else {
			return -(dotProduct(deltaR,deltaV)+Math.sqrt(d))/dotProduct(deltaV,deltaV);
		}
	}

	/**
	 * Return the first position where the ship and <code>ship2</code>
	 * collide, or <code>null</code> if they never collide. A ship never
	 * collides with itself.
	 * 
	 * The result of this method is either null or an array of length 2, where
	 * the element at index 0 represents the x-coordinate and the element at
	 * index 1 represents the y-coordinate.
	 * @param  ship2
	 * 	       The ship named ship2
	 * @return If the time to collision is not finite, return null
	 * 	       | if (getTimeToCollision(ship2) == Double.POSITIVE_INFINITY) result == null
	 * @return Return the position at time of collision between ship and ship2
	 *         | result.equals(this.getPositionAfterMovingForAPeriodOf(this.getTimeToCollision(ship2)))
	 */
	public double[] getCollisionPosition(Ship ship2) throws IllegalArgumentException {
		double time = this.getTimeToCollision(ship2);
		if (time == Double.POSITIVE_INFINITY) return null;
		return this.getPositionAfterMovingForAPeriodOf(time);
	}

	public Entity() {
		super();
	}

}