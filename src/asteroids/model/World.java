package asteroids.model;

import java.util.*;

import asteroids.part2.CollisionListener;

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
	 * @post  The given ship is part of this world
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
	 * @Pre   The this does not belong to any world yet or belongs to this world
	 *        | if ship.getWorld() == null result == true
	 *        | if ship.getWorld() == this result == true
	 */
	private boolean canHaveAsShip(Ship ship) {
		if (!(ship.getWorld() == this || ship.getWorld() == null))
			return false;
		for (Entity entity: getEntities()){
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
	 * @post   The given bullet is removed from the hashed set bullets and removed from this world
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
	 * @post  The given bullet is added to this world.
	 *        | bullet.setShip(this)
	 * @throws IllegalArgumentException
	 *        The given bullet is not part of this world
	 *        | bullet.getWorld() != this
	 */
	public void addBullet(Bullet bullet) {
		if(!canHaveAsBullet(bullet)) throw new IllegalArgumentException("This world cannot have the given bullet as bullet.");
		bullets.add(bullet);
		bullet.setWorld(this);
	}

	/**
	 * Return if this world can have the given bullet as bullet. The bullet must thereby be part of no world
	 *        or this world and the bullet must be loaded onto a ship.
	 * @param bullet
	 * 	      The given bullet
	 * @return The given bullet is part of this world
	 * 		  | if (bullet.getWorld() == this) then result == true
	 * @return The bullet belongs to a ship
	 *        | if (bullet.getShip() != null) the result == false
	 * @return The bullet does not belong to any world
	 *        | if ((bullet.getWorld() == null) then result == false
     */
	private boolean canHaveAsBullet(Bullet bullet) {
		if (bullet.getShip() != null) return false; 
		if (!(bullet.getWorld() == this || bullet.getWorld() == null))
			return false;
		for (Entity entity: getEntities()){
			if (bullet.overlap(entity))
				return false;
		}
		return true;
	}
	
	/**
	 * Remove all ships and all bullets of this world.
	 * @post   All ships are removed from this world
	 *         | for(Ship ship : ships) { this.removeShip(ship)
	 * @post   All bullets are removed from this world
	 *         | for(Bullet bullet: bullets) { this.removeBullet(bullet)
	 * @post   The boolean isTerminated is set to true
	 *         |  isTerminated = true
	 */
	public void terminate(){
		for(Ship ship : ships){
			this.removeShip(ship);
		}
		for(Bullet bullet : bullets){
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
	 *         | result == new double[]{width,height}
	 */
	public double[] getSize() {
		return new double[]{width,height};
	}
	
	/**
	 * Return all the entities part of this world.
	 * @return Returns all the ships and all the bullets of this world
	 *         | result == entities
	 */
	public Set<Entity> getEntities() {
		Set<Entity> entities = new HashSet<Entity>();
		entities.addAll(bullets);
		entities.addAll(ships);
		return entities;
	}


	/**
	 * Return the number of seconds until the next collision between two different objects
	 * of this world.
	 * @Pre    If the collision is between entities, it is between two different entities
	 *         | (entity1 != entity2)
	 * @return Return the collision for which the time to collision is the smallest between 
	 *         two entities of this world or an entity and a boundary of this world
	 *         | timeNextCollision = Math.min(timeNextCollision, entity1.getTimeToCollision(entity2));
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
	 * @return Return the position at time of collision between two objects of this world
	 *         | result == nextCollidingObject.getPositionAfterMovingForAPeriodOf(getTimeNextCollision())
	 */
	public double[] getPositionNextCollision() {
		Entity[] nextCollidingObjects = getNextCollidingObjects();
		if (nextCollidingObjects[1] == null) return nextCollidingObjects[0].getPositionCollisionBoundary();
		else return nextCollidingObjects[0].getCollisionPosition(nextCollidingObjects[1]);
	}
	
	/**
	 * Return the next collision between two different object of this world.
	 * @return Return the colliding objects for which the time to collision is the smallest, 
	 *         between two entities of this world or an entity and a boundary of this world 
	 *         | result == entities
	 */
	private Entity[] getNextCollidingObjects() {
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
	 * @post   All entities are moved to their predicted position after time dt
	 * 	       | for(Entity entity: getEntities()) entity.move(dt)   
	 */
	public void evolve(double dt, CollisionListener collisionListener) {
		double tC = getTimeNextCollision();
		double[] position = getPositionNextCollision();
		Entity[] entities = getNextCollidingObjects();
		while (tC <= dt){
			for(Entity entity: getEntities()) entity.move(tC);
			if(entities[1] == null){
				entities[0].collideBoundary();
				collisionListener.boundaryCollision(entities[0], position[0], position[1]);
			}
			else {
				entities[0].collide(entities[1]);
				collisionListener.objectCollision(entities[0], entities[1], position[0], position[1]);
			}
			
			dt = dt - tC;
			tC = getTimeNextCollision();
			position = getPositionNextCollision();
			entities = getNextCollidingObjects();
		}
		for(Entity entity: getEntities()) entity.move(dt);
		
	}
	
	/**
	 * Return an entity as the given position.
	 * @param  x
	 * 	       | the given x coordinate
	 * @param  y
	 * 	       | the given y coordinate
	 * @return Returns the entity at the given position
	 *         | result == entity
	 * @return Returns no entity at the given position
	 * 		   | result == null
	 */
	public Object getEntityAt(double x, double y) {
		for (Entity entity : getEntities()) {
			if (entity.getPosition().equals(new double[]{x,y})) return entity;
		}
		return null;
	}

	
}
