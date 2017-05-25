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
	 *         | new.getPosition().equals(new OrderedPair(x,y))
	 * @post   The new velocity of the ship is equal to the given velocity.
	 *         | new.getVelocity().equals(new OrderedPair(xVelocity,yVelocity))
	 * @post   The new radius of the ship is equal to the given radius.
	 *         | new.getRadius() == radius
	 * @throws IllegalArgumentException
	 *         The given initial position or radius is not valid.
	 *         | !isValidPosition(new OrderedPair(x,y))||!isValidRadius(radius)
	 */
	protected Entity(double x, double y, double xVelocity,
			double yVelocity, double radius) throws IllegalArgumentException {
		
		this.setPosition(new OrderedPair(x,y));
		this.setVelocity(new OrderedPair(xVelocity,yVelocity));
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
	 * @param  newPosition
	 * 	       | A potential position for an entity
	 * @return Returns the validity of the potential position for an entity.
	 *         | result == !((Double.isNaN(newPosition.getX())||(Double.isNaN(newPosition.getY()))
	 */
	@Raw
	private static boolean isValidPosition(OrderedPair newPosition) {
		if (Double.isNaN(newPosition.getX())) return false;
		if (Double.isNaN(newPosition.getY())) return false;
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
	public OrderedPair getPosition() {
		return position;
	}

	private OrderedPair position;

	/**
	 * Set the position to the given position.
	 * @param  newPosition
	 * 	       The x-and y-coordinate of this entity
	 * @post   The new value of the position of this entity equals position.
	 *         | new.getPosition().equals(newPosition)
	 * @throws IllegalArgumentException
	 *         The given position is not valid.
	 *         | !isValidPosition(newPosition)
	 *
	 */
	@Raw
	protected void setPosition(OrderedPair newPosition) throws IllegalArgumentException {
		if(!isValidPosition(newPosition)) throw new IllegalArgumentException("The given position is not valid.");
		this.position = newPosition;
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
	public OrderedPair getVelocity() {
		return velocity;
	}

	/**
	 * @param  orderedPair
	 * 	       The x-and y-velocity of this entity
	 * @post   If the given velocity is not valid, nothing happens.
	 *         | if ( (Double.isNaN(newVelocity.getX())) || (Double.isNaN(newVelocity.getY())) )
	 * @post   If the given velocity is slower than the speed of light, the new velocity is set to the given velocity.
	 *         | speed = newVelocity.getLength()
	 * 	       | if (speed <= getMaxSpeed()) new.getVelocity().equals(newVelocity)
	 * @post   If the given velocity is faster than the speed of light, the new velocity is given the same direction as the given velocity, but the speed of light.
	 *         | speed = newVelocity.getLength()
	 *         | new.getVelocity().equals(newVelocity.multiply(getMaxSpeed()/speed))
	 */
	protected void setVelocity(OrderedPair newVelocity) {
		if (Double.isNaN(newVelocity.getX())) return;
		if (Double.isNaN(newVelocity.getY())) return;
		double speed = newVelocity.getLength();
		if (speed <= getMaxSpeed()) this.velocity = newVelocity;
		else this.velocity = newVelocity.multiply(getMaxSpeed()/speed);
	}

	/**
	 * Return the speed of this entity as type double.
	 * @return Returns the speed of this entity.
	 *         | result == getVelocity().getLength()
	 */
	@Raw
	public double getSpeed() {
		return getVelocity().getLength();
	}

	private OrderedPair velocity = new OrderedPair(0, 0);

	
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
	 * 		   | new.getRadius() = radius
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
	 *         | result.equals(getPosition().sum(getVelocity().multiply(dt))
	 * @throws IllegalArgumentException
	 *         The given time difference is not valid.
	 *         | !isValidDt(dt)
	 */
	@Raw
	public OrderedPair getPositionAfterMovingForAPeriodOf(double dt) {
		if (!isValidDt(dt)) throw new IllegalArgumentException("The given time lapse is invalid");
		return getPosition().sum(getVelocity().multiply(dt));
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
	 *         | result == getPositionDifference(entity2).getLength()
	 * @throws IllegalArgumentException
	 *         Entity2 is not created
	 *         | entity2 == null
	 */
	@Raw
	public double getDistanceBetweenCenters(@Raw Entity entity2) throws NullPointerException {
		if (entity2 == null) throw new IllegalArgumentException("The second entity does not exist.");
		return getPositionDifference(entity2).getLength();
	}

	/**
	 * Return the difference in position of this entity and entity2
	 * @param  entity2
	 * 	       The entity named entity2.
	 * @return Return the difference in position between this entity and entity2.
	 *         | result.equals(getPosition().getDifference(entity2.getPosition()))
	 * @throws IllegalArgumentException
	 *         Entity2 is not created
	 *         | entity2 == null
	 */
	@Raw
	public OrderedPair getPositionDifference(@Raw Entity entity2) throws IllegalArgumentException {
		if (entity2 == null) throw new NullPointerException("The second entity does not exist.");
		return getPosition().getDifference(entity2.getPosition());
	}

	/**
	 * Return the difference in velocity between this entity and entity2
	 * @param  entity2
	 * 	       The entity named entity2.
	 * @return Return the difference in velocity between this entity and entity2.
	 *         | result.equals(getVelocity().getDifference(entity2.getVelocity()))
	 * @throws IllegalArgumentException
	 *         Entity2 is not created
	 *         | entity2 == null
	 *         
	 */
	@Raw
	public OrderedPair getVelocityDifference(@Raw Entity entity2) throws IllegalArgumentException {
		if (entity2 == null) throw new IllegalArgumentException("The second entity does not exist.");
		return getVelocity().getDifference(entity2.getVelocity());
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
	 *         | result == (this.getDistanceBetween(entity2)/(this.getRadius()+entity2.getRadius()) <= -0.01)
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
	 *         | result == min({dt | Math.sqrt(ship2.getPositionAfterMovingForAPeriodOf(dt).dotProduct(this.getPositionAfterMovingForAPeriodOf(dt))) == ship2.getRadius() + this.getRadius()})
	 * @throws IllegalArgumentException
	 *         Ship2 is not created or the two ships overlap
	 *         | ship2 == null || this.overlap(ship2)
	 */
	public double getTimeToCollision(Entity ship2) throws IllegalArgumentException {
		if (ship2 == null) throw new IllegalArgumentException("The second ship does not exist.");
		if (this.overlap(ship2)) throw new IllegalArgumentException("The ships overlap.");
		OrderedPair deltaR = this.getPositionDifference(ship2);
		OrderedPair deltaV = this.getVelocityDifference(ship2);
		if (deltaR.dotProduct(deltaV) >= 0) return Double.POSITIVE_INFINITY;
		double d = Math.pow(deltaV.dotProduct(deltaR), 2) - (deltaV.dotProduct(deltaV))*(deltaR.dotProduct(deltaR)-Math.pow(this.getRadius()+ship2.getRadius(), 2));
		if (d <= 0) return Double.POSITIVE_INFINITY;
		else return Math.max(0, -(deltaR.dotProduct(deltaV)+Math.sqrt(d))/(deltaV.dotProduct(deltaV)));
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
	 *         | positionDifference = this.getPositionAfterMovingForAPeriodOf(time).getDifference(ship2.getPositionAfterMovingForAPeriodOf(time));
	 *         | radiusRatio = this.getRadius()/(this.getRadius()+ship2.getRadius());
	 *         | result.equals(thisPosition.sum(positionDifference.multiply(radiusRatio))
	 */
	public OrderedPair getCollisionPosition(Entity ship2) throws IllegalArgumentException {
		double time = this.getTimeToCollision(ship2);
		if (time == Double.POSITIVE_INFINITY) return null;
		OrderedPair thisPosition = this.getPositionAfterMovingForAPeriodOf(time);
		OrderedPair otherPosition = ship2.getPositionAfterMovingForAPeriodOf(time);
		OrderedPair positionDifference = thisPosition.getDifference(otherPosition);
		double radiusRatio = this.getRadius()/(this.getRadius()+ship2.getRadius());
		OrderedPair collisionPosition = thisPosition.sum(positionDifference.multiply(radiusRatio));
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
	 * @post  This entity is removed from its world
	 * 		  | !(this.world.(new.getEntities().contains(this)) 
	 */
	public void terminate() {
		isTerminated = true;
		if(this.getWorld() != null) getWorld().removeEntity(this);
	}
	
	/**
	 * Return the termination status of this entity as type boolean
	 * @return Returns the termination status of this entity
	 *         | result == isTerminated
	 */
	public boolean isTerminated() {
		return isTerminated;
	}

	private boolean isTerminated = false;	
	
	/**
	 * Return the number of seconds until the first collision between this entity and the nearest boundary within its trajectory.
	 * @return Return the time to collision between this entity and the perpendicular boundary
	 *         Returns the smallest time dt such that the distance between this entity after moving for a time of dt is 0
	 *         | result == min(xTime, yTime)
	 * @throws IllegalArgumentException
	 *         Ship2 is not created or the two ships overlap
	 *         | ship2 == null || this.overlap(ship2)
	 */
	public double getTimeCollisionBoundary() throws IllegalArgumentException {
		if (getWorld()== null) return Double.POSITIVE_INFINITY;
		double xTime = Double.POSITIVE_INFINITY; double yTime = Double.POSITIVE_INFINITY;
		OrderedPair velocity = getVelocity();
		OrderedPair position = getPosition();
		if(velocity.getX() > 0) xTime = (getWorld().getSize()[0]-position.getX()-getRadius())/velocity.getX();
		if(velocity.getX() < 0) xTime = -(position.getX()-getRadius())/velocity.getX();
		if(velocity.getY() > 0) yTime = (getWorld().getSize()[1]-position.getY()-getRadius())/velocity.getY();
		if(velocity.getY() < 0) yTime = -(position.getY()-getRadius())/velocity.getY();
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
	 *         | result == xDistance <= yDistance ? new OrderedPair(xBoundary,positionAtEntityCollisionBoundary.getY()) : new OrderedPair(positionAtEntityCollisionBoundary.getX(),yBoundary)
	 */
	public OrderedPair getPositionCollisionBoundary() {
		OrderedPair positionAtEntityCollisionBoundary = this.getPositionAfterMovingForAPeriodOf(this.getTimeCollisionBoundary());
		if(positionAtEntityCollisionBoundary == null || !Double.isFinite(positionAtEntityCollisionBoundary.getX()) || !Double.isFinite(positionAtEntityCollisionBoundary.getY())) 
			return null;
		OrderedPair velocity = this.getVelocity();
		double xBoundary = velocity.getX() > 0 ? getWorld().getSize()[0] : 0;
		double yBoundary = velocity.getY() > 0 ? getWorld().getSize()[1] : 0;
		double xDistance = velocity.getX() > 0 ? xBoundary - positionAtEntityCollisionBoundary.getX() : positionAtEntityCollisionBoundary.getX();
		double yDistance = velocity.getY() > 0 ? yBoundary - positionAtEntityCollisionBoundary.getY() : positionAtEntityCollisionBoundary.getY();
		
		return xDistance <= yDistance ? new OrderedPair(xBoundary,positionAtEntityCollisionBoundary.getY()) : new OrderedPair(positionAtEntityCollisionBoundary.getX(),yBoundary);
	}

	/**
	 * Set the negation of the velocity vector in question upon collision with a boundary, thereby bouncing off the boundary.
	 * @post   If the entity bounces off the vertical border the x-component of the velocity is negated
	 *         | if(getPositionCollisionBoundary()[0] == 0 || getPositionCollisionBoundary()[0] == getWorld().getSize()[0])
	 *         |   new.getVelocity().getX() == -getVelocity().getX()
	 * @post   If the entity bounces off the horizontal border the y-component of the velocity is reversed
	 *         | if(getPositionCollisionBoundary()[1] == 0 || getPositionCollisionBoundary()[1] == getWorld().getSize()[1])
	 *         |   new.getVelocity().getY() == -getVelocity().getY()
	 */
	public void collideBoundary() {
		OrderedPair position = getPositionCollisionBoundary();
		if (position == null) return;
		if(position.getX() == 0 || position.getX() == getWorld().getSize()[0]) setVelocity(new OrderedPair(-getVelocity().getX(),getVelocity().getY()));
		if(position.getY() == 0 || position.getY() == getWorld().getSize()[1]) setVelocity(new OrderedPair(getVelocity().getX(),-getVelocity().getY()));
	}
	
	public abstract void collide(Entity entity);
}