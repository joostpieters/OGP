package asteroids.model;

import java.util.*;

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
		if(ship.getWorld() != null) return false;
		return true;
	}

	public Set<Ship> getShips() {
		return ships;
	}
	
	private Set<Ship> ships = new HashSet<Ship>();
	
	public void removeShip(Ship ship) {
		if(ship.getWorld() != this) throw new IllegalArgumentException("This world does not contain the given ship.");
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

	
}
