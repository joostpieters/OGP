package asteroids.facade;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.part2.CollisionListener;
import asteroids.model.Bullet;
import asteroids.model.Asteroid;
import asteroids.part3.facade.IFacade;
import asteroids.model.Planetoid;
import asteroids.model.Program;
import asteroids.part3.programs.IProgramFactory;
import asteroids.model.World;
import asteroids.model.programs.ProgramFactory;
import asteroids.util.ModelException;

public class Facade implements IFacade {

	@Override
	public Ship createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double orientation, double mass)
			throws ModelException {
		try {	
			return new Ship(x,y,xVelocity,yVelocity,radius,orientation, mass);
		} catch(IllegalArgumentException exc){
			throw new ModelException(exc);
		} catch(AssertionError e){
			throw new ModelException(e);
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
		try{
			ship.turn(angle);
		} catch (AssertionError e){
			throw new ModelException(e);
		}
	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2)
			throws ModelException {
		try {
			return ship1.getDistanceBetween(ship2);
		} catch(IllegalArgumentException exc){
			throw new ModelException(exc);
		}
		
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.overlap(ship2);
		} catch(IllegalArgumentException exc){
			throw new ModelException(exc);
		}
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2)
			throws ModelException {
		try {
			return ship1.getTimeToCollision(ship2);
		} catch(IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2)
			throws ModelException {
		try {
			return ship1.getCollisionPosition(ship2);
		} catch(IllegalArgumentException exc) {
			throw new ModelException(exc);
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
		try{
			world.addEntity(ship);
		}catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		try{
			world.removeEntity(ship);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		try{
			world.addEntity(bullet);
		} catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
		
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		world.removeEntity(bullet);
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
			throw new ModelException(e);
		}
		
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		Bullet[] bulletsAsArray = new Bullet[bullets.size()];
		bullets.toArray(bulletsAsArray);
		try{
			ship.loadBullet(bulletsAsArray);
		} catch (IllegalArgumentException e){
			throw new ModelException(e);
		}
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		try{
			ship.removeBullet(bullet);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
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
		try{
			world.evolve(dt,collisionListener);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return world.getEntityAt(x,y);
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getEntities();
	}

	@Override
	public int getNbStudentsInTeam() {
		return 2;
	}

	@Override
	public Set<? extends Asteroid> getWorldAsteroids(World world)
			throws ModelException {
		return world.getAsteroids();
	}

	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid)
			throws ModelException {
		world.addEntity(asteroid);
		
	}

	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid)
			throws ModelException {
		world.removeEntity(asteroid);		
	}

	@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world)
			throws ModelException {
		return world.getPlanetoids();
	}

	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid)
			throws ModelException {
		try{
			world.addEntity(planetoid);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
		
	}

	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid)
			throws ModelException {
		world.removeEntity(planetoid);
		
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius) throws ModelException {
		return new Asteroid(x,y,xVelocity,yVelocity,radius);
	}

	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {
		asteroid.terminate();
	}

	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid)
			throws ModelException {
		return asteroid.isTerminated();
	}

	@Override
	public double[] getAsteroidPosition(Asteroid asteroid)
			throws ModelException {
		return asteroid.getPosition();
	}

	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid)
			throws ModelException {
		return asteroid.getVelocity();
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
			return asteroid.getRadius();
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		// TODO Auto-generated method stub
		return asteroid.getMass();
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		// TODO Auto-generated method stub
		return asteroid.getWorld();
	}

	@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity,
			double yVelocity, double radius, double totalTraveledDistance)
			throws ModelException {
		// TODO Auto-generated method stub
		return new Planetoid(x, y, xVelocity, yVelocity, radius, totalTraveledDistance);
	}

	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		planetoid.terminate();
	}

	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid)
			throws ModelException {
		// TODO Auto-generated method stub
		return planetoid.isTerminated();
	}

	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid)
			throws ModelException {
		// TODO Auto-generated method stub
		return planetoid.getPosition();
	}

	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid)
			throws ModelException {
		// TODO Auto-generated method stub
		return planetoid.getVelocity();
	}

	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return planetoid.getRadius();
	}

	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return planetoid.getMass();
	}

	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid)
			throws ModelException {
		// TODO Auto-generated method stub
		return planetoid.getTotalTraveledDistance();
	}

	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return planetoid.getWorld();
	}

	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getProgram();
	}

	@Override
	public void loadProgramOnShip(Ship ship, Program program)
			throws ModelException {
		ship.loadProgram(program);
		
	}

	@Override
	public List<Object> executeProgram(Ship ship, double dt)
			throws ModelException {
		try{
			return ship.executeProgram(dt);
		} catch (Exception exc) {
			throw new ModelException(exc);
		} catch (AssertionError e){
			throw new ModelException(e);
		}
		
	}

	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory()
			throws ModelException {
		// TODO Auto-generated method stub
		try{
			return new ProgramFactory();
		} catch (ClassCastException e){
			throw new ModelException(e);
		}
	}

}
