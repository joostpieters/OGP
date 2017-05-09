package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class of entities involving a position, velocity and radius
 * @Invar  isValidPosition(getPosition())
 * @Invar  isValidRadius(getRadius())
 * 
 * @version 3.1
 * @author  Sander Leyssens & Sarah Joseph
 */
public abstract class Entity {

	/**
	 * Create a new entity with the given position, velocity, radius.
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
	 * @throws IllegalArgumentException
	 *         The given initial position or radius is not valid.
	 *         | !isValidPosition({x,y})||!isValidRadius(radius)
	 */
	protected Entity(double x, double y, double xVelocity,
			double yVelocity, double radius) throws IllegalArgumentException {
		this.setPosition(new double[] {x,y});
		this.setVelocity(new double[] {xVelocity,yVelocity});
		this.setRadius(radius);
	}
	
	/**
	 * Return the mass of this entity.
	 * @return Returns the mass of this entity. 
	 *         | result == 4/3. * PI * pow(getRadius(), 3) * getDensity()
	 */
	public double getMass() {
		return 4/3.*Math.PI*Math.pow(getRadius(), 3)*getDensity();
	}
	
	
	public abstract double getDensity();

	/**
	 * Return the validity of a potential position for an entity as type boolean.
	 * @param  position
	 * 	       | A potential position for an entity
	 * @return Returns the validity of the potential position for an entity.
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
	 * Return the position of this entity as an array of length 2, with the
	 * x-coordinate at index 0 and the y-coordinate at index 1.
	 * @return Returns the position of this ship.
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
	 * 	       The x-and y-coordinate of this entity
	 * @post   The new value of the position of this entity equals position.
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
	
	public double getMaxSpeed(){
		return SPEED_OF_LIGHT;
	}

	public abstract double getMinRadius();
	
	/**
	 * Return the velocity of this entity as an array of length 2, with the velocity
	 * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
	 * @return Returns the velocity of this entity.
	 *         | result.equals(this.velocity)
	 */
	@Basic
	@Raw
	public double[] getVelocity() {
		return velocity.clone();
	}

	/**
	 * @param  velocity
	 * 	       The x-and y-velocity of this entity
	 * @post   If the given velocity is not valid, nothing happens.
	 *         | if (Double.isNaN(velocity[0]))||(Double.isNaN(velocity[1]))||(velocity.length != 2)
	 * @post   If the given velocity is slower than the speed of light, the new velocity is set to the given velocity.
	 *         | speed = Math.sqrt(dotProduct(velocity, velocity))
	 * 	       | if (Math.sqrt(dotProduct(velocity, velocity)) <= getMaxSpeed()) new.velocity.equals(velocity)
	 * @post   If the given velocity is faster than the speed of light, the new velocity is given the same direction as the given velocity, but the speed of light.
	 *         | speed = Math.sqrt(dotProduct(velocity, velocity))
	 *         | new.velocity.equals({velocity[0]*getMaxSpeed()/speed,velocity[1]*getMaxSpeed()/speed})
	 */
	protected void setVelocity(double[] velocity) {
		if (Double.isNaN(velocity[0])) return;
		if (Double.isNaN(velocity[1])) return;
		if (velocity.length != 2) return;
		double speed = Math.sqrt(dotProduct(velocity, velocity));
		if (speed <= getMaxSpeed()) this.velocity = velocity.clone();
		else this.velocity = new double[] {velocity[0]*getMaxSpeed()/speed,velocity[1]*getMaxSpeed()/speed};
	}

	/**
	 * Return the speed of this entity as type double.
	 * @return Returns the speed of this entity.
	 *         | result == Math.sqrt(dotProduct(this.getVelocity(), this.getVelocity()))
	 */
	@Raw
	public double getSpeed() {
		return Math.sqrt(dotProduct(this.getVelocity(), this.getVelocity()));
	}

	private double[] velocity = new double[2];

	
	/**
	 * Return the validity of the given radius for this ship. The radius is type double and larger than 10
	 * @param radius
	 * 	      The ship's radius.
	 * @return Returns the validity of the given radius
	 * 		   | result == radius > getMinRadius()
	 */
	public boolean isValidRadius(double radius){
		return (radius >= getMinRadius());
	}

	protected double radius;
	
	/**
	 * @param  radius
	 * 	       The radius of this entity
	 * @post   The new radius is set to the given radius
	 * 		   | this.radius = radius
	 * @throws IllegalArgumentException
	 *         The given radius is not valid.
	 *         | (!isValidRadius(radius))
	 */
	protected void setRadius(double radius) throws IllegalArgumentException {
		if(!isValidRadius(radius)) throw new IllegalArgumentException("The radius is invalid.");
		this.radius = radius;
	}
	
	/**
	 * Return the radius of this entity.
	 * @return Returns the radius of this entity.
	 *         | result == this.radius
	 */
	@Basic
	@Raw
	public double getRadius() {
		return radius;
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
	protected
	static double dotProduct(double[] vector1, double[] vector2) {
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
	 * Update this entity's position, assuming it moves <code>dt</code>
	 * seconds at its current velocity.
	 * @param  dt
	 * 	       The time of movement of this entity.
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
	 * @return Returns the position of this entity.
	 *         | result.equals({getPosition()[0]+getVelocity[0]*dt,getPosition[1]+getVelocity[1]*dt})
	 * @throws IllegalArgumentException
	 *         The given time difference is not valid.
	 *         | !isValidDt(dt)
	 */
	@Raw
	public double[] getPositionAfterMovingForAPeriodOf(double dt) {
		if (!isValidDt(dt)) throw new IllegalArgumentException("The given time lapse is invalid");
		double[] position = getPosition();
		double[] velocity = getVelocity();
		return new double[] {position[0]+velocity[0]*dt,position[1]+velocity[1]*dt};
	}

	/**
	 * Return the distance between this entity and <code>entity2</code>.
	 * 
	 * The absolute value of the result of this method is the minimum distance
	 * either entity should move such that both entities are adjacent. Note that the
	 * result must be negative if the entities overlap. The distance between a entity
	 * and itself is 0.
	 * @param  entity2
	 * 	       The entity named entity2.
	 * @return Return 0 if this entity and entity2 are identical.
	 * 	       | if (entity2 == this) result == 0
	 * @return Return the distance between the centers of this entity and entity2, subtracted by the radius of both entities.
	 *         | result == this.getDistanceBetweenCenters(entity2) - this.getRadius() - entity2.getRadius())
	 * @throws IllegalArgumentException
	 *         Entity2 is not created
	 *         | entity2 == null
	 */
	public double getDistanceBetween(Entity entity2) throws NullPointerException {
		if (entity2 == null) throw new IllegalArgumentException("The second entity does not exist.");
		if (entity2 == this) return 0;
		else return (this.getDistanceBetweenCenters(entity2) - this.getRadius() - entity2.getRadius());
	}

	/**
	 * Return the distance between the center of this entity and <code>entity2</code>.
	 * 
	 * @param  entity2
	 * 	       The entity named entity2.
	 * @return Return the distance between the centers of this entity and entity2.
	 *         | result == Math.sqrt(dotProduct(this.getPositionDifference(entity2), this.getPositionDifference(entity2))
	 * @throws IllegalArgumentException
	 *         Entity2 is not created
	 *         | entity2 == null
	 */
	@Raw
	public double getDistanceBetweenCenters(@Raw Entity entity2) throws NullPointerException {
		if (entity2 == null) throw new IllegalArgumentException("The second entity does not exist.");
		double[] positionDifference = getPositionDifference(entity2);
		double distance = Math.sqrt(dotProduct(positionDifference, positionDifference));
		return distance;
	}

	/**
	 * Return the difference in position of this entity and entity2
	 * @param  entity2
	 * 	       The entity named entity2.
	 * @return Return the difference in position between this entity and entity2.
	 *         | result.equals({entity2.getPosition()[0]-this.getPosition()[0],entity2.getPosition()[1]-this.getPosition()[1]})
	 * @throws IllegalArgumentException
	 *         Entity2 is not created
	 *         | entity2 == null
	 */
	@Raw
	public double[] getPositionDifference(@Raw Entity entity2) throws IllegalArgumentException {
		if (entity2 == null) throw new NullPointerException("The second entity does not exist.");
		return new double[] {entity2.getPosition()[0]-this.getPosition()[0],entity2.getPosition()[1]-this.getPosition()[1]};
	}

	/**
	 * Return the difference in velocity between this entity and entity2
	 * @param  entity2
	 * 	       The entity named entity2.
	 * @return Return the difference in velocity between this entity and entity2.
	 *         | result.equals({entity2.getVelocity()[0]-this.getVelocity()[0],entity2.getVelocity()[1]-this.getVelocity()[1]})
	 * @throws IllegalArgumentException
	 *         Entity2 is not created
	 *         | entity2 == null
	 *         
	 */
	@Raw
	public double[] getVelocityDifference(@Raw Entity entity2) throws IllegalArgumentException {
		if (entity2 == null) throw new IllegalArgumentException("The second entity does not exist.");
		return new double[] {entity2.getVelocity()[0]-this.getVelocity()[0],entity2.getVelocity()[1]-this.getVelocity()[1]};
	}

	/**
	 * Check whether this entity and <code>entity2</code> overlap. An entity
	 * always overlaps with itself.
	 * @param  entity2
	 * 	       The entity named entity2.
	 * @return Return whether entity and entity2 overlap
	 *         | result == (this.getDistanceBetween(entity2) < 0)
	 * @throws IllegalArgumentException
	 *         entity2 is not created
	 *         | entity2 == null
	 */
	public boolean collidesWith(Entity entity2) throws IllegalArgumentException {
		if (entity2 == null) throw new IllegalArgumentException("The second entity does not exist.");
		if (this == entity2) return false;
		else return (Math.abs(this.getDistanceBetween(entity2)/(this.getRadius()+entity2.getRadius())) < 0.01);
	}
	
	/**
	 * Check whether this entity and <code>entity2</code> overlap. An entity
	 * always overlaps with itself.
	 * @param  entity2
	 * 	       The entity named entity2.
	 * @return Return whether entity and entity2 overlap
	 *         | result == (this.getDistanceBetween(entity2) < 0)
	 * @throws IllegalArgumentException
	 *         entity2 is not created
	 *         | entity2 == null
	 */
	public boolean overlap(Entity entity2) throws IllegalArgumentException {
		if (entity2 == null) throw new IllegalArgumentException("The second entity does not exist.");
		if (this == entity2) return true;
		else return (this.getDistanceBetween(entity2)/(this.getRadius()+entity2.getRadius()) <= -0.01);
	}
	
	/**
	 * Check whether this entity is within boundaries of <code>entity2</code>.
	 * @param  entity2
	 * 	       The entity named entity2.
	 * @return Return whether entity is within boundaries of entity2
	 *         | result == (this.getDistanceBetweenCenters(entity2)+this.getRadius() < 0.99*entity2.getRadius())
	 * @throws IllegalArgumentException
	 *         entity2 is not created
	 *         | entity2 == null
	 */
	public boolean isWithinBoundaries(Entity entity2) throws IllegalArgumentException {
		if (entity2 == null) throw new IllegalArgumentException("The second entity does not exist.");
		else return (this.getDistanceBetweenCenters(entity2)+this.getRadius() < 0.99*entity2.getRadius());
	}
	
	

	/**
	 * Return the number of seconds until the first collision between
	 * the ship and <code>ship2</code>, or Double.POSITIVE_INFINITY if
	 * they never collide. A ship never collides with itself.
	 * @param  ship2
	 * 	       The ship named ship2
	 * @return Return the time to collision between ship and ship2 
	 *         Returns the smallest time such that the distance between the ships after moving for a time is 0
	 *         | result == min({dt | Math.sqrt(dotProduct(ship2.getPositionAfterMovingForAPeriodOf(dt), this.getPositionAfterMovingForAPeriodOf(dt))) == ship2.getRadius() + this.getRadius()})
	 * @throws IllegalArgumentException
	 *         Ship2 is not created or the two ships overlap
	 *         | ship2 == null || this.overlap(ship2)
	 */
	public double getTimeToCollision(Entity ship2) throws IllegalArgumentException {
		if (ship2 == null) throw new IllegalArgumentException("The second ship does not exist.");
		if (this.overlap(ship2)) throw new IllegalArgumentException("The ships overlap.");
		double[] deltaR = this.getPositionDifference(ship2);
		double[] deltaV = this.getVelocityDifference(ship2);
		if (dotProduct(deltaR,deltaV) >= 0) return Double.POSITIVE_INFINITY;
		double d = Math.pow(dotProduct(deltaV,deltaR), 2) - (dotProduct(deltaV,deltaV))*(dotProduct(deltaR,deltaR)-Math.pow(this.getRadius()+ship2.getRadius(), 2));
		if (d <= 0) return Double.POSITIVE_INFINITY;
		else return Math.max(0, -(dotProduct(deltaR,deltaV)+Math.sqrt(d))/dotProduct(deltaV,deltaV));
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
	public double[] getCollisionPosition(Entity ship2) throws IllegalArgumentException {
		double time = this.getTimeToCollision(ship2);
		if (time == Double.POSITIVE_INFINITY) return null;
		double[] thisPosition = this.getPositionAfterMovingForAPeriodOf(time);
		double[] otherPosition = ship2.getPositionAfterMovingForAPeriodOf(time);
		double[] positionDifference = new double[]{otherPosition[0]-thisPosition[0],otherPosition[1]-thisPosition[1]};
		double radiusRatio = this.getRadius()/(this.getRadius()+ship2.getRadius());
		double[] collisionPosition = new double[]{thisPosition[0]+positionDifference[0]*radiusRatio,thisPosition[1]+positionDifference[1]*radiusRatio};
		
		return collisionPosition;
	}

	private World world;
	
	/**
	 * Return the world of this entity.
	 */
	@Raw @Basic
	public World getWorld() {
		return world;
	}

	/**
	 * Set the world of the given entity.
	 * @param world
	 * 	      The given world to this entity
	 * @post  The new world of this entity is equal to the given world.
	 *        | new.getWorld() == world
	 * @throws IllegalArgumentException
	 * 		   This entity belongs to a world
	 * 		   |(!world.getEntities().contains(this))
	 */
	@Raw
	public void setWorld(@Raw World world) {
		if (!world.getEntities().contains(this)) throw new IllegalArgumentException("This method may only be used in a world's addEntity method.");
		this.world = world;
	}
	
	/**
	 * Remove the world this entity is set in.
	 * @post  The new world of this entity is equal null
	 *        | new.getWorld() == null
	 */
	public void removeWorld(){
		this.world = null;
	}

	/**
	 * Terminate this entity
	 * @post  This entity is terminated
	 *        | isTerminated = true
	 */
	public void terminate() {
		isTerminated = true;
		if(this.getWorld() != null) getWorld().removeEntity(this);
	}
	
	/**
	 * Return the termination status of this entity as type boolean
	 * @return Returns the termination status o this entity
	 *         | result == isTerminated
	 */
	public boolean isTerminated() {
		return isTerminated;
	}

	private boolean isTerminated = false;	
	
	/**
	 * Return the number of seconds until the first collision between this entity and the nearest boundary within it's trajectory.
	 * @return Return the time to collision between this entity and the perpendicular boundary
	 *         Returns the smallest time dt such that the distance between this entity after moving for a time of dt is 0
	 *         | min(xTime, yTime)
	 * @throws IllegalArgumentException
	 *         Ship2 is not created or the two ships overlap
	 *         | ship2 == null || this.overlap(ship2)
	 */
	public double getTimeCollisionBoundary() {
		if (getWorld()== null) return Double.POSITIVE_INFINITY;
		double xTime = Double.POSITIVE_INFINITY; double yTime = Double.POSITIVE_INFINITY;
		double[] velocity = getVelocity();
		double[] position = getPosition();
		if(velocity[0] > 0) xTime = (getWorld().getSize()[0]-position[0]-getRadius())/velocity[0];
		if(velocity[0] < 0) xTime = -(position[0]-getRadius())/velocity[0];
		if(velocity[1] > 0) yTime = (getWorld().getSize()[1]-position[1]-getRadius())/velocity[1];
		if(velocity[1] < 0) yTime = -(position[1]-getRadius())/velocity[1];
		// TODO Auto-generated method stub
		if (xTime < 0) xTime = Double.POSITIVE_INFINITY;
		if (yTime < 0) yTime = Double.POSITIVE_INFINITY;
		return Math.min(xTime, yTime);
	}
	
	/**
	 * Return the position of the entity as an array of length 2 x-coordinate at 
	 * index 0 and the y-coordinate at index 1, at the point of collision with the nearest boundary within it's trajectory.
	 * @param  dt
	 * @return Returns the position of this entity at collision with nearest boundary within it's trajectory
	 *         | result == positionAtEntityCollisionBoundary
	 */
	public double[] getPositionCollisionBoundary() {
		double[] positionAtEntityCollisionBoundary = this.getPositionAfterMovingForAPeriodOf(this.getTimeCollisionBoundary());
		if(positionAtEntityCollisionBoundary == null || !Double.isFinite(positionAtEntityCollisionBoundary[0]) || !Double.isFinite(positionAtEntityCollisionBoundary[1])) 
			return null;
		double[] velocity = this.getVelocity();
		double xBoundary = velocity[0] > 0 ? getWorld().getSize()[0] : 0;
		double yBoundary = velocity[1] > 0 ? getWorld().getSize()[1] : 0;
		double xDistance = velocity[0] > 0 ? xBoundary - positionAtEntityCollisionBoundary[0] : positionAtEntityCollisionBoundary[0];
		double yDistance = velocity[1] > 0 ? yBoundary - positionAtEntityCollisionBoundary[1] : positionAtEntityCollisionBoundary[1];;
		
		double[] collisionPosition = xDistance <= yDistance ? new double[]{xBoundary,positionAtEntityCollisionBoundary[1]} : new double[]{positionAtEntityCollisionBoundary[0],yBoundary};
//		if(xDistance < 0) xDistance = Double.POSITIVE_INFINITY;
//		if(yDistance < 0) yDistance = Double.POSITIVE_INFINITY;
//		if(xDistance == Double.POSITIVE_INFINITY && yDistance == Double.POSITIVE_INFINITY) return null;
		return collisionPosition;
	}

	/**
	 * Set the negation of the velocity vector in question upon collision with a boundary, thereby bouncing off the boundary.
	 * @post   If the entity bounces off the vertical border the x-component of the velocity is negated
	 *         | if(getPositionCollisionBoundary()[0] == 0 || getPositionCollisionBoundary()[0] == getWorld().getSize()[0])
	 *         |   new.getVelocity()[0] == -getVelocity()[0]
	 * @post   If the entity bounces off the horizontal border the y-component of the velocity is reversed
	 *         | if(getPositionCollisionBoundary()[1] == 0 || getPositionCollisionBoundary()[1] == getWorld().getSize()[1])
	 *         |   new.getVelocity()[1] == -getVelocity()[1]
	 */
	public void collideBoundary() {
		double[] position = getPositionCollisionBoundary();
		if(position[0] == 0 || position[0] == getWorld().getSize()[0]) setVelocity(new double[]{-getVelocity()[0],getVelocity()[1]});
		if(position[1] == 0 || position[1] == getWorld().getSize()[1]) setVelocity(new double[]{getVelocity()[0],-getVelocity()[1]});
	}

	public abstract void collide(Entity entity);
}