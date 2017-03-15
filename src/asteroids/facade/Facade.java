package asteroids.facade;

import java.util.Collection;
import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.part2.CollisionListener;
import asteroids.part2.facade.Bullet;
import asteroids.part2.facade.IFacade;
import asteroids.part2.facade.World;
import asteroids.util.ModelException;

public class Facade implements IFacade {

	@Override
	public Entity createShip() throws ModelException {
		return new Ship();
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double orientation)
			throws ModelException {
		try {	
			return new Ship(x,y,xVelocity,yVelocity,radius,orientation);
		} catch(IllegalArgumentException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public double[] getShipPosition(Entity ship) throws ModelException {
		return ship.getPosition();
	}

	@Override
	public double[] getShipVelocity(Entity ship) throws ModelException {
		return ship.getVelocity();
	}

	@Override
	public double getShipRadius(Entity ship) throws ModelException {
		return ship.getRadius();
	}

	@Override
	public double getShipOrientation(Entity ship) throws ModelException {
		return ship.getOrientation();
	}

	@Override
	public void move(Entity ship, double dt) throws ModelException {
		try {	
			ship.move(dt);
		} catch(IllegalArgumentException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		ship.thrust(amount);
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		ship.turn(angle);
	}

	@Override
	public double getDistanceBetween(Entity ship1, Entity ship2)
			throws ModelException {
		try {
			return ship1.getDistanceBetween(ship2);
		} catch(IllegalArgumentException exc){
			throw new ModelException(exc.getMessage());
		}
		
	}

	@Override
	public boolean overlap(Entity ship1, Entity ship2) throws ModelException {
		try {
			return ship1.overlap(ship2);
		} catch(IllegalArgumentException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public double getTimeToCollision(Entity ship1, Entity ship2)
			throws ModelException {
		try {
			return ship1.getTimeToCollision(ship2);
		} catch(IllegalArgumentException exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public double[] getCollisionPosition(Entity ship1, Entity ship2)
			throws ModelException {
		try {
			return ship1.getCollisionPosition(ship2);
		} catch(IllegalArgumentException exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction,
			double mass) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void terminateShip(Entity ship) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTerminatedShip(Entity ship) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getShipMass(Entity ship) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getShipWorld(Entity ship) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShipThrusterActive(Entity ship) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setThrusterActive(Entity ship, boolean active) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getShipAcceleration(Entity ship) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity getBulletShip(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity getBulletSource(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public World createWorld(double width, double height) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addShipToWorld(World world, Entity ship) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeShipFromWorld(World world, Entity ship) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Entity ship) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNbBulletsOnShip(Entity ship) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void loadBulletOnShip(Entity ship, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadBulletsOnShip(Entity ship, Collection<Bullet> bullets) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBulletFromShip(Entity ship, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fireBullet(Entity ship) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

}
