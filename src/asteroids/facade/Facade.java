package asteroids.facade;

import java.util.Collection;
import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.part2.CollisionListener;
import asteroids.model.Bullet;
import asteroids.part2.facade.IFacade;
import asteroids.model.World;
import asteroids.util.ModelException;

public class Facade implements IFacade {

	@Override
	public Ship createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double orientation, double mass)
			throws ModelException {
		try {	
			return new Ship(x,y,xVelocity,yVelocity,radius,orientation, mass);
		} catch(IllegalArgumentException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return ship.getPosition();
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		return ship.getVelocity();
	}

	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		return ship.getRadius();
	}

	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		return ship.getOrientation();
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		ship.turn(angle);
	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2)
			throws ModelException {
		try {
			return ship1.getDistanceBetween(ship2);
		} catch(IllegalArgumentException exc){
			throw new ModelException(exc.getMessage());
		}
		
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.overlap(ship2);
		} catch(IllegalArgumentException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2)
			throws ModelException {
		try {
			return ship1.getTimeToCollision(ship2);
		} catch(IllegalArgumentException exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2)
			throws ModelException {
		try {
			return ship1.getCollisionPosition(ship2);
		} catch(IllegalArgumentException exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public void terminateShip(Ship ship) throws ModelException {
		ship.terminate();
		
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		return ship.isTerminated();
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		return ship.getMass();
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.isThrusterActive();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		if (active) ship.thrustOn();
		else ship.thrustOff();
		
	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getAcceleration();
	}

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		return new Bullet(x,y,xVelocity,yVelocity,radius);
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		bullet.terminate();
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isTerminated();
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return bullet.getPosition();
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return bullet.getVelocity();
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		return bullet.getMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return bullet.getShip();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return bullet.getSource();
	}

	@Override
	public World createWorld(double width, double height) throws ModelException {
		return new World(width, height);
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		world.terminate();
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		return world.isTerminated();
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		return world.getSize();
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return world.getShips();
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return world.getBullets();
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		world.addShip(ship);
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		world.removeShip(ship);
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		world.addBullet(bullet);
		
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		world.removeBullet(bullet);
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return ship.getBullets();
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getNbBullets();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		try{
			ship.loadBullet(bullet);
		}catch(IllegalArgumentException e){
			throw new ModelException(e.getMessage());
		}
		
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		ship.loadBullet((Bullet[])(bullets.toArray()));
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		ship.removeBullet(bullet);
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		ship.fireBullet();
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return ((Entity)object).getTimeCollisionBoundary();
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		return ((Entity)object).getPositionCollisionBoundary();
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		if (!(entity1 instanceof Entity && entity2 instanceof Entity)) throw new ModelException("The given objects are not entities.");
		return ((Entity)entity1).getTimeToCollision((Entity)entity2);
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		if (!(entity1 instanceof Entity && entity2 instanceof Entity)) throw new ModelException("The given objects are not entities.");
		return ((Entity)entity1).getCollisionPosition((Entity)entity2);
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		return world.getTimeNextCollision();
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		return world.getPositionNextCollision();
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		world.evolve(dt,collisionListener);
		
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return world.getEntityAt(x,y);
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getEntities();
	}

}
