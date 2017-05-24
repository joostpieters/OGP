package asteroids.model;

import java.util.*;
import java.util.stream.Collectors;

import asteroids.part2.CollisionListener;

/**
 * A class of worlds with a given width and height.
 * @ see implementation
 * @version 3.1
 * @author  Sander Leyssens & Sarah Joseph
 */


public class World {

	private double width;
	private double height;

	/**
	 * Create a new world with the given width and height.
	 * 
	 * @param  width
	 *         The width of this new world
	 * @param  height
	 *         The height of this new world.
	 * @post   The new width of the world is equal to the given width if the given width is greater than or equal to 0, and 0 otherwise.
	 *         | new.getWidth = width >= 0 ? width : 0
	 * @post   The new height of this world is equal to the given height if the given height is greater than or equal to 0, and 0 otherwise.
	 *         | this.height = height >= 0 ? height : 0
	 */
	public World(double width, double height) {
		this.width = width >= 0 ? (Double.isFinite(width) ? width : Double.MAX_VALUE) : 0;
		this.height = height >= 0 ? (Double.isFinite(height) ? height : Double.MAX_VALUE) : 0;
	}
	
	public double getWidth(){
		return width;
	}
	
	public double getHeight(){
		return height;
	}


	/**
	 * Return if this world can have the given ship as ship. The ship must thereby be part of no other world or this world.
	 * @param ship
	 * 	      The given ship
 	 * @return Returns false if the entity is a null pointer.
 	 *        | if (entity == null) return false
 	 *        Returns false if the entity is a bullet which belongs to any ship.
	 *        | if (entity instanceof Bullet && ((Bullet)entity).getShip() != null) result == false
	 *        Returns false if the ship belongs to any world other than this world.
	 *        | if (!(ship.getWorld() == null || ship.getWorld() != this)) result == false
	 *        Returns false if either the ship or this world are terminated.
	 *        | if (ship.isTerminated() || this.isTerminated()) result == false
	 *        Returns false if the ship overlaps with any entities of this world.
	 *        | if (for some entity in this.getEntities() ship.overlap(entity)) result == false
	 *        Returns true otherwise.
	 *        | result == true
	 */
	private boolean canHaveAsEntity(Entity entity) {//TODO edge
		if (entity == null) return false;
		if (entity instanceof Bullet && ((Bullet)entity).getShip() != null) return false;
		if (!(entity.getWorld() == this || entity.getWorld() == null))
			return false;
		if (entity.isTerminated() || this.isTerminated())
			return false;
		for (Entity entity2: this.getEntities()){
			if (entity.overlap(entity2))
				return false;
		}
		if ((entity.getRadius() > entity.getPosition()[0] 
				|| entity.getRadius() > entity.getPosition()[1]
				|| getWidth() < entity.getPosition()[0] + entity.getRadius() 
				|| getHeight() < entity.getPosition()[1] + entity.getRadius())){
			return false;}
		return true;
	}
	
	private Set<Entity> entities = new HashSet<Entity>();

	/**
	 * Return all the ships on this world as a hashed set.
	 * @return Returns the ships of this world
	 *         | result == ships
	 */
	public Set<Ship> getShips() {
		return getEntities().stream().filter(t -> t instanceof Ship).map(t->(Ship)t).collect(Collectors.toSet());
	}
	
	/**
	 * Return all the asteroids on this world.
	 */
	public Set<Asteroid> getAsteroids() {
		return getEntities().stream().filter(t -> t instanceof Asteroid).map(t->(Asteroid)t).collect(Collectors.toSet());
	}
	
	/**
	 * Add an entity to this world.
	 */
	public void addEntity(Entity entity) {
	if(!canHaveAsEntity(entity)) throw new IllegalArgumentException("This world cannot have the given entity.");
	entities.add(entity);
	entity.setWorld(this);
	}
	
	/**
	 * Return all the planetoids on this world.
	 */
	public Set<Planetoid> getPlanetoids() {
		return getEntities().stream().filter(t -> t instanceof Planetoid).map(t->(Planetoid)t).collect(Collectors.toSet());
	}

	/**
	 * Return the bullets within this world as hashed set.
	 * @return Returns the bullets of this world
	 *         | result == bullets
	 */
	public Set<Bullet> getBullets() {
		return getEntities().stream().filter(t -> t instanceof Bullet).map(t->(Bullet)t).collect(Collectors.toSet());
	}
	
	/**
	 * Terminate the given entity from this world
	 * @Pre    The given entity is part of this world
	 * @effect   The given entity is removed from the hashed set entity and removed from this world
	 *         | entity.remove(bullet) && entity.removeWorld();
	 * @throws IllegalArgumentException
	 *         The given entity is null or is not part of this world.
	 *         | entity == null || entity.getWorld() != this
	 */
	public void removeEntity(Entity entity) {
		if(entity == null || entity.getWorld() != this) throw new IllegalArgumentException();
		entities.remove(entity);
		entity.removeWorld();
	}
	
	/**
	 * Remove all entities of this world.
	 * @effect All entities are removed from this world
	 *         | for each entity in getEntities(): this.removeEntity(ship)
	 * @effect The boolean isTerminated is set to true
	 *         | isTerminated = true
	 */
	public void terminate(){
		for(Entity entity : getEntities()){
			this.removeEntity(entity);
		}
		isTerminated = true;
	}

	private boolean isTerminated = false;

	/**
	 * Return the status of termination of this world.
	 * @return Returns whether the world has been terminated
	 *         | result == isTerminated
	 */
	public boolean isTerminated() {
		return isTerminated;
	}

	/**
	 * Return the width and height of this world as array of type double.
	 * @return Returns the size of this world
	 *         | result == new double[]{this.width,this.height}
	 */
	public double[] getSize() {
		return new double[]{this.width,this.height};
	}
	
	/**
	 * Return all the entities part of this world.
	 * @return Returns all the ships and all the bullets of this world
	 *         | result == {entity | entity in getBullets() || entity in getShips()} 
	 */
	public Set<Entity> getEntities() {
		return new HashSet<Entity>(entities);
	}


	/**
	 * Return the number of seconds until the next collision between two different objects
	 * of this world.
	 * @return Return 0 if two entities overlap.
	 *         if (for some entity1, entity2 in getEntities() with entity1 != entity2: entity1.overlaps(entity2) result == 0
	 * 	       Return the time for which the time to collision is the smallest between 
	 *         two entities of this world or an entity and a boundary of this world
	 *         | minTimeObjectCollision == min{entity1.getTimeToCollision(entity1) | for each entity1, entity2 in getEntities() with entity1 != entity2}
	 *         | minTimeBoundaryCollision == min{entity.getTimeCollisionBoundary() | for each entity in getEntities()}
	 *         | result == min(minTimeObjectCollision, minTimeBoundaryCollision)
	 */
	public double getTimeNextCollision() {
		double timeNextCollision = Double.POSITIVE_INFINITY;
		for (Entity entity1 : getEntities()){
			timeNextCollision = Math.min(timeNextCollision, entity1.getTimeCollisionBoundary());
			for (Entity entity2 : getEntities()){
				
				if (entity1 != entity2) {
					if (entity1.overlap(entity2)) return 0;
					timeNextCollision = Math.min(timeNextCollision, entity1.getTimeToCollision(entity2));
				}
			}
		}
		return timeNextCollision;
	}

	/**
	 * Return the position with the smallest time to collision between two objects of this world.
	 * @return Return an infinite position if the next collision never happens. 
	 *         if(getNextCollidingObjects()[0] == null) result == {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
	 * @return Return the next boundary collision if only one colliding object is found.
	 *         if (getNextCollidingObjects()[1] == null) result ==  getNextCollidingObjects()[0].getPositionCollisionBoundary();
	 * @return Return the position at time of collision between two objects of this world.
	 *         | result == getNextCollidingObjects()[0].getCollisionPosition(getNextCollidingObjects()[1])
	 */
	public double[] getPositionNextCollision() {
		Entity[] nextCollidingObjects = getNextCollidingObjects();
		if(nextCollidingObjects[0] == null) return new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
		if (nextCollidingObjects[1] == null) return nextCollidingObjects[0].getPositionCollisionBoundary();
		else return nextCollidingObjects[0].getCollisionPosition(nextCollidingObjects[1]);
	}
	
	/**
	 * Return the next collision between two different object of this world.
	 * @return Return the colliding objects for which the time to collision is the smallest, 
	 *         between two entities of this world or an entity and a boundary of this world 
	 *         | see implementation
	 * 
	 */
	public Entity[] getNextCollidingObjects() {
		Entity[] entities = new Entity[]{null,null};
		double timeNextCollision = Double.POSITIVE_INFINITY;
		for (Entity entity1 : getEntities()){
			if (timeNextCollision > entity1.getTimeCollisionBoundary()){
				timeNextCollision = entity1.getTimeCollisionBoundary();
				entities = new Entity[]{entity1,null};
			}
			for (Entity entity2 : getEntities()){
				if (entity1 != entity2) {
					if (entity1.overlap(entity2)) return new Entity[]{entity1,entity2};
					
					if (timeNextCollision > entity1.getTimeToCollision(entity2)){
						timeNextCollision = entity1.getTimeToCollision(entity2);
						entities = new Entity[]{entity1,entity2};
					}
				}
				
			}
		}
		return entities;
	}

	/**
	 * All entities are moved to their predicted position after time dt dependent on other objects in the world
	 * @param  dt
	 * 	       The time of movement of this ship.
	 * @param  collisionListener
	 *         Invoked when the entity is about to collide with a boundary or other entity
	 * @effect  All entities are moved to their predicted position after time dt
	 * 	       | for each entity in getEntities(): entity.move(dt)  
	 * @Throws IllegalArgumentException
	 *         | dt < 0 or Double.isNaN(dt)
	 */
	
	public void evolve(double dt, CollisionListener collisionListener) {
		if(dt < 0) throw new IllegalArgumentException("dt has to be positive");
		if (Double.isNaN(dt)) throw new IllegalArgumentException("dt has to be a number");
		double tC = getTimeNextCollision();
		double[] position = getPositionNextCollision();
		Entity[] entities = getNextCollidingObjects();
		while (tC <= dt){
			for(Entity entity: getEntities()) {
				if (entity instanceof Ship && ((Ship) entity).getProgram() != null) {
					((Ship) entity).executeProgram(tC);
				}
				entity.move(tC);
			}
			if(entities[1] == null){
				if(collisionListener != null) collisionListener.boundaryCollision(entities[0], position[0], position[1]);
				entities[0].collideBoundary();
			}
			else {
				if(collisionListener != null) collisionListener.objectCollision(entities[0], entities[1], position[0], position[1]);
				entities[0].collide((entities[1].getClass().cast(entities[1])));
			}
			
			dt = dt - tC;
			tC = getTimeNextCollision();
			position = getPositionNextCollision();
			entities = getNextCollidingObjects();
		}
		for(Entity entity: getEntities()) {
			if (entity instanceof Ship && ((Ship) entity).getProgram() != null) {
				((Ship) entity).executeProgram(dt);
			}
			entity.move(dt);
		}
		
	}
	
	/**
	 * Return an entity at the given position if one exists, null otherwise.
	 * @param  x
	 * 	       | the given x coordinate
	 * @param  y
	 * 	       | the given y coordinate
	 * @return Returns the entity at the given position if one exists.
	 *         | if (for some entity in getEntities(): entity.getPosition().equals({x,y})) result == entity
	 * @return Returns null if no entity exists at the given position.
	 * 		   | result == null
	 */
	public Object getEntityAt(double x, double y) {
		for (Entity entity : getEntities())
			if (entity.getPosition()[0] == x && entity.getPosition()[1] == y) return entity;
		return null;
	}

	
}
