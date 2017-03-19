package asteroids.model;

import java.util.*;

import asteroids.part2.CollisionListener;

public class World {

	private double width;
	private double height;

	public World(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public void addShip(Ship ship) {
		if(!canHaveAsShip(ship)) throw new IllegalArgumentException("This world cannot have the given ship as ship.");
		ships.add(ship);
		ship.setWorld(this);
	}

	private boolean canHaveAsShip(Ship ship) {
		return (ship.getWorld() == this || ship.getWorld() == null);
	}

	public Set<Ship> getShips() {
		return ships;
	}
	
	private Set<Ship> ships = new HashSet<Ship>();
	public void removeShip(Ship ship) {
		if(ship.getWorld() != this) throw new IllegalArgumentException("The given ship is not part of this world.");
		ships.remove(ship);
		ship.removeWorld();
	}

	public Set<Bullet> getBullets() {
		return bullets;
	}

	private Set<Bullet> bullets = new HashSet<Bullet>();
	public void removeBullet(Bullet bullet) {
		if(bullet.getWorld() != this) throw new IllegalArgumentException();
		bullets.remove(bullet);
		bullet.removeWorld();
	}
	
	public void addBullet(Bullet bullet) {
		if(!canHaveAsBullet(bullet)) throw new IllegalArgumentException("This world cannot have the given bullet as bullet.");
		bullets.add(bullet);
		bullet.setWorld(this);
	}

	private boolean canHaveAsBullet(Bullet bullet) {
		if (bullet.getWorld() == this) return true;
		if (bullet.getShip() != null) return false; 
		return (bullet.getWorld() == null);
	}
	
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

	public boolean isTerminated() {
		return isTerminated;
	}

	public double[] getSize() {
		return new double[]{width,height};
	}

	public Set<Entity> getEntities() {
		Set<Entity> entities = new HashSet<Entity>();
		entities.addAll(bullets);
		entities.addAll(ships);
		return entities;
	}

	public double getTimeNextCollision() {
		double timeNextCollision = Double.POSITIVE_INFINITY;
		for (Entity entity1 : getEntities()){
			timeNextCollision = Math.min(timeNextCollision, entity1.getTimeCollisionBoundary());
			for (Entity entity2 : getEntities()){
				timeNextCollision = Math.min(timeNextCollision, entity1.getTimeToCollision(entity2));
			}
		}
		return timeNextCollision;
	}

	public double[] getPositionNextCollision() {
		Entity nextCollidingObject = getNextCollidingObjects()[0];
		return nextCollidingObject.getPositionAfterMovingForAPeriodOf(getTimeNextCollision());
	}
	
	private Entity[] getNextCollidingObjects() {
		Entity[] entities = new Entity[2];
		double timeNextCollision = Double.POSITIVE_INFINITY;
		for (Entity entity1 : getEntities()){
			if (timeNextCollision > entity1.getTimeCollisionBoundary()){
				timeNextCollision = entity1.getTimeCollisionBoundary();
				entities = new Entity[]{entity1,null};
			}
			for (Entity entity2 : getEntities()){
				if (timeNextCollision > entity1.getTimeToCollision(entity2)){
					timeNextCollision = entity1.getTimeToCollision(entity2);
					entities = new Entity[]{entity1,entity2};
				}
			}
		}
		return entities;
	}

	public void evolve(double dt, CollisionListener collisionListener) {
		double tC = getTimeNextCollision();
		Entity[] entities = getNextCollidingObjects();
		while (tC <= dt){
			for(Entity entity: getEntities()) entity.move(dt);
			//TODO Resolve collisions
			
			dt = dt - tC;
			tC = getTimeNextCollision();
		}
		for(Entity entity: getEntities()) entity.move(dt);
		
	}

	public Object getEntityAt(double x, double y) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
