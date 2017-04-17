package asteroids.model;

import java.util.*;

import asteroids.part2.CollisionListener;

/**
 * A class of worlds with a given width and height.
 * @Invar  for each bullet in getBullets(): bullet.getWorld() == this
 * @Invar  for each ship in getShip(): ship.getWorld() == this
 * @version 2.5
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
	 * @post   The new width of the world is equal to the given width.
	 *         | this.width = width
	 * @post   The new height of this world is equal to the given height.
	 *         | this.height = height
	 */
	public World(double width, double height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Add a ship to this world.
	 * @param ship
	 * 	      The given ship
	 * @Pre   The given ship is valid.
	 * @effect  The given ship is part of this world
	 *        | ship.setWorld(this)
	 */
	public void addShip(Ship ship) {
		if(!canHaveAsShip(ship)) throw new IllegalArgumentException("This world cannot have the given ship as ship.");
		ships.add(ship);
		ship.setWorld(this);
	}

	/**
	 * Return if this world can have the given ship as ship. The ship must thereby be part of no other world or this world.
	 * @param ship
	 * 	      The given ship
	 * @return Returns false if the ship belongs to any world other than this world.
	 *        | if (!(ship.getWorld() == null || ship.getWorld() != this)) result == false
	 *        Returns false if either the ship or this world are terminated.
	 *        | if (ship.isTerminated() || this.isTerminated()) result == false
	 *        Returns false if the ship overlaps with any entities of this world.
	 *        | if (for some entity in this.getEntities() ship.overlap(entity)) result == false
	 *        Returns true otherwise.
	 *        | result == true
	 */
	private boolean canHaveAsShip(Ship ship) {//TODO edge
		if (!(ship.getWorld() == this || ship.getWorld() == null))
			return false;
		if (ship.isTerminated() || this.isTerminated())
			return false;
		for (Entity entity: this.getEntities()){
			if (ship.overlap(entity))
				return false;
		}
		return true;
	}

	/**
	 * Return all the ships on this world as a hashed set.
	 * @return Returns the ships of this world
	 *         | result == ships
	 */
	public Set<Ship> getShips() {
		return ships;
	}
	
	private Set<Ship> ships = new HashSet<Ship>();
	
	/**
	 * Terminate this ship from this world.
	 * @Pre    The ship is part of this world
	 * @post   The world which the ship was set to does not contain the ship anymore and the ship
	 *         to which this world was set to does not contain this world anymore
	 *         | ships.remove(ship) && ship.removeWorld();
	 * @throws IllegalArgumentException
	 *         The given ship is not part of this world.
	 *         | ship.getWorld() != this
	 */
	public void removeShip(Ship ship) {
		if(ship.getWorld() != this) throw new IllegalArgumentException("The given ship is not part of this world.");
		ships.remove(ship);
		ship.removeWorld();
	}

	/**
	 * Return the bullets within this world as hashed set.
	 * @return Returns the bullets of this world
	 *         | result == bullets
	 */
	public Set<Bullet> getBullets() {
		return bullets;
	}

	private Set<Bullet> bullets = new HashSet<Bullet>();
	
	/**
	 * Terminate the given bullet from this world
	 * @Pre    The given bullet is part of this world
	 * @effect   The given bullet is removed from the hashed set bullets and removed from this world
	 *         | bullets.remove(bullet) && bullet.removeWorld();
	 * @throws IllegalArgumentException
	 *         The given bullet is not part of this world.
	 *         | bullet.getWorld() != this
	 */
	public void removeBullet(Bullet bullet) {
		if(bullet.getWorld() != this) throw new IllegalArgumentException();
		bullets.remove(bullet);
		bullet.removeWorld();
	}
	
	/**
	 * Add the given bullet to this world.
	 * @param bullet
	 * 	      The given bullet
	 * @Pre   The given bullet is valid.
	 * @effect  The given bullet is added to this world.
	 *        | bullet.setShip(this)
	 * @throws IllegalArgumentException
	 *        The given bullet is not part of this world
	 *        | bullet.getWorld() != this
	 */
	public void addBullet(Bullet bullet) throws IllegalArgumentException {
		if(!canHaveAsBullet(bullet)) throw new IllegalArgumentException("This world cannot have the given bullet as bullet.");
		double[] position = bullet.getPosition();
		double radius = bullet.getRadius();
		double distance = position[0]-radius;
		distance = Math.min(distance, this.getSize()[0]-position[0]-radius);
		distance = Math.min(distance, position[1]-radius);
		distance = Math.min(distance, this.getSize()[1]-position[1]-radius);
		if(distance < 0) bullet.terminate();
		else{
			bullets.add(bullet);
			bullet.setWorld(this);
		}
	}

	/**
	 * Return if this world can have the given bullet as bullet. The bullet must thereby be part of no world
	 *        or this world and the bullet must be loaded onto a ship.
	 * @param bullet
	 * 	      The given bullet
	 * @return Returns false if the bullet does belongs to any ship.
	 *        | if (bullet.getShip() != null) result == false;
	 *        Returns false if the bullet belongs to any world other than this world.
	 *        | if (!(bullet.getWorld() == null || bullet.getWorld() != this)) result == false
	 *        Returns false if either the bullet or this world are terminated.
	 *        | if (bullet.isTerminated() || this.isTerminated()) result == false
	 *        Returns false if the bullet overlaps with any entities of this world.
	 *        | if (for some entity in this.getEntities() bullet.overlap(entity)) result == false
	 *        Returns true otherwise.
	 *        | result == true
	 */
	private boolean canHaveAsBullet(Bullet bullet) {//TODO edge
		if (bullet.getShip() != null) return false; 
		if (!(bullet.getWorld() == this || bullet.getWorld() == null))
			return false;
		if (bullet.isTerminated() || this.isTerminated()) return false;
		for (Entity entity: getEntities()){
			if (bullet.overlap(entity))
				return false;
		}
		return true;
	}
	
	/**
	 * Remove all ships and all bullets of this world.
	 * @effect All ships are removed from this world
	 *         | for each ship in getShips(): this.removeShip(ship)
	 * @effect All bullets are removed from this world
	 *         | for each bullet in getBullets(): this.removeBullet(bullet)
	 * @effect The boolean isTerminated is set to true
	 *         | isTerminated = true
	 */
	public void terminate(){
		for(Ship ship : getShips()){
			this.removeShip(ship);
		}
		for(Bullet bullet : getBullets()){
			this.removeBullet(bullet);
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
		Set<Entity> entities = new HashSet<Entity>();
		entities.addAll(getBullets());
		entities.addAll(getShips());
		return entities;
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
	 *   
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
			for(Entity entity: getEntities()) entity.move(tC);
			if(entities[1] == null){
				if(collisionListener != null) collisionListener.boundaryCollision(entities[0], position[0], position[1]);
				entities[0].collideBoundary();
			}
			else {
				if(collisionListener != null) collisionListener.objectCollision(entities[0], entities[1], position[0], position[1]);
				entities[0].collide(entities[1]);
			}
			
			dt = dt - tC;
			tC = getTimeNextCollision();
			position = getPositionNextCollision();
			entities = getNextCollidingObjects();
		}
		for(Entity entity: getEntities()) entity.move(dt);
		
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
		for (Entity entity : getEntities()) {
			if (entity.getPosition().equals(new double[]{x,y})) return entity;
		}
		return null;
	}

	
}
