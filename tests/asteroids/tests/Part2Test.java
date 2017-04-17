package asteroids.tests;


import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.facade.Facade;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

import asteroids.part2.CollisionListener;


public class Part2Test {

	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}

	@Test
	public void testCreateWorld() throws ModelException {
		World world = facade.createWorld(1000, 800);
		assertEquals(1000, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(800, facade.getWorldSize(world)[1], EPSILON);
		assertTrue(facade.getWorldShips(world).isEmpty());
		assertTrue(facade.getWorldBullets(world).isEmpty());
	}

	@Test
	public void testCreateShip() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 200, 10, -10, 20, Math.PI, 1.1E18);
		assertNotNull(ship);
		double[] position = ship.getPosition();
		assertNotNull(position);
		assertEquals(100, position[0], EPSILON);
		assertEquals(200, position[1], EPSILON);
		assertEquals(20, ship.getRadius(), EPSILON);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipXIsNan() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(Double.NaN, 200, 10, -10, 20, -Math.PI, 1.1E18);
	}
	
	@Test
	public void testPosition() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(10, 20, 0, 0, 20, 0, 1.1E18);
		double [] position = ship.getPosition();
		assertNotNull(position);
		assertEquals(10, position[0], EPSILON);
		assertEquals(20, position[1], EPSILON);
	}
	
	@Test(expected = ModelException.class)
	public void testPositionIsNan() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(Double.NaN, 200, 10, -10, 20, -Math.PI, 1.1E18);
	}
	
	@Test 
	public void testRadius() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(0, 0, 0, 0, 25, 0, 1.1E18);
		double radius = ship.getRadius();
		assertEquals(25, radius, EPSILON);
	}
	
	@Test 
	public void testOrientation() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(0, 0, 0, 0, 25, Math.PI, 1.1E18);
		double orientation = ship.getOrientation();
		assertEquals(Math.PI, orientation, EPSILON);
	}
	
	@Test 
	public void testMass() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(0, 0, 0, 0, 25, Math.PI, 1.1E18);
		double mass = ship.getMass();
		assertEquals(1.1E18, mass, EPSILON);
	}
	
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusNegative() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 200, 10, -10, -20, -Math.PI, 1.1E18);
	}
	
	@Test
	public void testMove() throws ModelException { 
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0, 1.1E18);
		ship.move(1);
		double[] position = ship.getPosition();
		assertNotNull(position);
		assertEquals(130, position[0], EPSILON);
		assertEquals(85, position[1], EPSILON);
	}
	
	@Test
	public void testVelocity() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(0, 0, 10, 20, 20, 0, 1.1E18);
		double [] velocity = ship.getVelocity();
		assertNotNull(velocity);
		assertEquals(10, velocity[0], EPSILON);
		assertEquals(20, velocity[1], EPSILON);
	}
	
	@Test
	public void testThrust() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(0, 0, 0, 0, 20, 0, 1.1E18);
		ship.thrust(2);
		double[] velocity = ship.getVelocity();
		assertNotNull(velocity);
		assertEquals(2, velocity[0], EPSILON);
		assertEquals(0, velocity[1], EPSILON);
	}
	
	@Test
	public void testThrustVelocityUpperLimit() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(0, 0, 0, 0, 20, 0, 1.1E18);
		ship.thrust(200000000);
		double[] velocity = ship.getVelocity();
		assertNotNull(velocity);
		assertEquals(300000, velocity[0], EPSILON);
	}
	
	@Test
	public void testThrustVelocityLowerLimit() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(0, 0, 0, 0, 20, 0, 1.1E18);
		ship.thrust(-100);
		double[] velocity = ship.getVelocity();
		assertNotNull(velocity);
		assertEquals(0, velocity[0], EPSILON);
	}
	
	@Test
	public void testTurn() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(0, 0, 0, 0, 20, Math.PI/2, 1.1E18);
		ship.turn(Math.PI/2);
		double orientation = ship.getOrientation();
		assertNotNull(orientation);
		assertEquals(Math.PI, (Math.PI/2) + (Math.PI/2), EPSILON);
	}
	
	@Test
	public void testWorld() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(0, 0, 0, 0, 20, Math.PI/2, 1.1E18);
		Bullet bullet = facade.createBullet(100, 120, 10, 5, 50);
		facade.addShipToWorld(world, ship);
		facade.addBulletToWorld(world, bullet);
		assertEquals(ship.getWorld(), world);
		assertEquals(bullet.getWorld(), world);
	}
	
	@Test
	public void testTerminate() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(0, 0, 0, 0, 20, Math.PI/2, 1.1E18);
		Bullet bullet = facade.createBullet(100, 120, 10, 5, 50);
		ship.terminate();
		bullet.terminate();
		world.terminate();
		boolean terminatedShip = (ship.getWorld() == null);
		assertEquals(true, terminatedShip);
		boolean terminatedBullet = (bullet.getWorld() == null);
		assertEquals(true, terminatedBullet);
		assertEquals(true, world.isTerminated());
	}
		
	@Test
	public void testgetDistanceBetween() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Entity ship1 = facade.createShip(0, 50, 0, 0, 25, 0, 1.1E18);
		Entity ship2 = facade.createShip(0, 0, 0, 0, 25, 0, 1.1E18);
		double distance = ship1.getDistanceBetween(ship2);
		assertEquals(0, distance, EPSILON);
	}
	
	@Test
	public void testOverlap() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		Ship ship2 = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		assertEquals(ship.getPosition()[0], ship2.getPosition()[0], EPSILON);
		assertEquals(ship.getPosition()[1], ship2.getPosition()[1], EPSILON);
	}
	
	@Test
	public void testoverlapTrue() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Entity ship1 = facade.createShip(0, 25, 0, 0, 25, 0, 1.1E18);
		Entity ship2 = facade.createShip(0, 0, 0, 0, 25, 0, 1.1E18);
		boolean overlap = ship1.overlap(ship2);
		assertEquals(true, overlap);
	}
	
	@Test
	public void testoverlapFalse() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Entity ship1 = facade.createShip(0, 100, 0, 0, 25, 0, 1.1E18);
		Entity ship2 = facade.createShip(0, 0, 0, 0, 25, 0, 1.1E18);
		boolean overlap = ship1.overlap(ship2);
		assertEquals(false, overlap);
	}
	
	@Test
	public void testgetTimeToCollision() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Entity ship1 = facade.createShip(80, 0, 0, 0, 20, 0, 1.1E18);
		Entity ship2 = facade.createShip(0, 0, 20, 0, 20, 0, 1.1E18);
		double time = ship1.getTimeToCollision(ship2);
		assertNotNull(time);
		assertEquals(1.98, time, EPSILON);
	}
	
	@Test
	public void testgetTimeToCollisionUpperLimit() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Entity ship1 = facade.createShip(80, 80, 0, 0, 20, 0, 1.1E18);
		Entity ship2 = facade.createShip(0, 0, 20, 0, 20, 0, 1.1E18);
		double time = ship1.getTimeToCollision(ship2);
		assertNotNull(time);
		assertEquals(Double.POSITIVE_INFINITY, time, EPSILON);
	}
	
	@Test
	public void testgetCollisionPosition() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Entity ship1 = facade.createShip(80, 0, 0, 0, 20, 0, 1.1E18);
		Entity ship2 = facade.createShip(0, 0, 20, 0, 20, 0, 1.1E18);
		double[] position = ship1.getCollisionPosition(ship2);
		assertEquals(59.8, position[0], EPSILON);
		assertEquals(0, position[1], EPSILON);
	}

	@Test
	public void testLoadBulletOnShipOverlappingBullets() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
		Bullet bullet2 = facade.createBullet(130, 110, 10, 5, 30);
		facade.loadBulletOnShip(ship, bullet1);
		facade.loadBulletOnShip(ship, bullet2);
		assertEquals(2, facade.getNbBulletsOnShip(ship));
	}

	@Test
	public void testEvolveShipWithActiveThruster() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, 10, 0, 50, Math.PI, 1.1E18);
		facade.addShipToWorld(world, ship);
		facade.setThrusterActive(ship, true);
		assertEquals(1000.0, facade.getShipAcceleration(ship), EPSILON);
		assertTrue(facade.isShipThrusterActive(ship));
		facade.evolve(world, 1, null);
		assertEquals(-990, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testgetTimeNextCollision() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(1000, 1000, 0, 0, 500, 0, 1.0E20);
		Ship ship2 = facade.createShip(2000, 1000, -100, 0, 500, Math.PI, 1.0E20);
		world.addShip(ship);
		world.addShip(ship2);
		assertEquals(-0.1, ship.getTimeToCollision(ship2), EPSILON);
	}
	
	@Test
	public void testSource() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(0, 0, 0, 0, 20, Math.PI/2, 1.1E18);
		Bullet bullet1 = facade.createBullet(0, 0, 10, 5, 50);
		bullet1.setSource(ship);
		assertEquals(bullet1.getSource(), ship);
	}
	
	
	@Test
	public void testShip() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(0, 0, 0, 0, 20, Math.PI/2, 1.1E18);
		Bullet bullet1 = facade.createBullet(0, 0, 100, 0, 5);
		facade.addShipToWorld(world, ship);
		facade.loadBulletOnShip(ship, bullet1);
		bullet1.setShip(ship);
		assertEquals(bullet1.getShip(), ship);
	}
	
	@Test 
	public void testCollision() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship2 = facade.createShip(500,200, 0, 0, 50, 0, 1.0E20);
		Bullet bullet1 = facade.createBullet(200, 200, 100, 0, 5);
		facade.addShipToWorld(world, ship2);
		facade.addBulletToWorld(world, bullet1);
		double time = bullet1.getTimeToCollision(ship2);
		world.evolve(time, null);
		assertTrue(bullet1.isTerminated());
		assertTrue(ship2.isTerminated());
	}
	
	@Test 
	public void testBulletInBound() throws ModelException {
		World world = facade.createWorld(50, 50);
		Bullet bullet1 = facade.createBullet(100, 100, 0, 0, 5);
		world.addBullet(bullet1);
		assertTrue(bullet1.isTerminated());
	}
	
	@Test 
	public void testFireBullet() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(200, 200, 0, 0, 50, 0, 1.0E20);
		Ship ship2 = facade.createShip(500,200, 0, 0, 50, 0, 1.0E20);
		Bullet bullet1 = facade.createBullet(200, 200, 100, 0, 5);
		facade.addShipToWorld(world, ship);
		facade.addShipToWorld(world, ship2);
		facade.loadBulletOnShip(ship, bullet1);
		assertEquals(1, facade.getNbBulletsOnShip(ship));
		ship.fireBullet();
		double time = (bullet1.getTimeToCollision(ship2))/2;
		world.evolve(time, null);
		assertEquals(bullet1.getVelocity()[0], 250, EPSILON);
	}
	
	@Test 
	public void testTimeToCollisionBoundary() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(200, 200, 100, 0, 50, 0, 1.0E20);
		facade.addShipToWorld(world, ship);
		world.evolve(ship.getTimeCollisionBoundary(), null);
		assertEquals(ship.getPosition()[0]+ship.getRadius(),5000, EPSILON);
	}
	
	@Test 
	public void testPositionToCollisionBoundary() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(200, 200, 100, 0, 50, 0, 1.0E20);
		facade.addShipToWorld(world, ship);
		double[] positionToCollision = ship.getPositionCollisionBoundary();
		world.evolve(ship.getTimeCollisionBoundary(), null);
		assertEquals(ship.getPosition()[0]+ship.getRadius(),positionToCollision[0], EPSILON);
		assertEquals(ship.getPosition()[1],positionToCollision[1], EPSILON);
	}
	
	@Test 
	public void testTimeToCollisionEntity() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(200, 200, 100, 0, 50, 0, 1.0E20);
		Ship ship2 = facade.createShip(1000, 200, 0, 0, 50, 0, 1.0E20);
		facade.addShipToWorld(world, ship);
		facade.addShipToWorld(world, ship2);
		world.evolve(ship.getTimeToCollision(ship2), null);
		assertEquals(ship.getDistanceBetweenCenters(ship2), 1.01*(ship.getRadius() + ship2.getRadius()), EPSILON);
	}
	
	@Test 
	public void testPositionToCollisionEntity() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(200, 200, 100, 0, 50, 0, 1.0E20);
		Ship ship2 = facade.createShip(1000, 200, 0, 0, 50, 0, 1.0E20);
		facade.addShipToWorld(world, ship);
		facade.addShipToWorld(world, ship2);
		double[] positionToCollision = ship.getCollisionPosition(ship2);
		world.evolve(ship.getTimeToCollision(ship2), null);
		assertEquals(positionToCollision[0], ship.getPosition()[0] + 1.01 * ship.getRadius(), EPSILON);
		assertEquals(positionToCollision[1], ship.getPosition()[1], EPSILON);
	}
	
	@Test 
	public void testTimeNextCollision() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(300, 200, 100, 0, 50, 0, 1.0E20);
		Ship ship2 = facade.createShip(1000, 200, 0, 0, 50, 0, 1.0E20);
		Ship ship3 = facade.createShip(100, 200, 1000, 0, 50, 0, 1.0E20);
		facade.addShipToWorld(world, ship);
		facade.addShipToWorld(world, ship2);
		facade.addShipToWorld(world, ship3);
		world.evolve(world.getTimeNextCollision(), null);
		assertEquals(ship.getDistanceBetweenCenters(ship3), 1.01*(ship.getRadius() + ship3.getRadius()), EPSILON);
	}
	
	@Test 
	public void testPositionNextCollision() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(300, 200, 100, 0, 50, 0, 1.0E20);
		Ship ship2 = facade.createShip(1000, 200, 0, 0, 50, 0, 1.0E20);
		Ship ship3 = facade.createShip(100, 200, 1000, 0, 50, 0, 1.0E20);
		facade.addShipToWorld(world, ship);
		facade.addShipToWorld(world, ship2);
		facade.addShipToWorld(world, ship3);
		double[] positionToCollision = world.getPositionNextCollision();
		world.evolve(world.getTimeNextCollision(), null);
		assertEquals(positionToCollision[0], ship.getPosition()[0] - 1.01 * ship.getRadius(), EPSILON);
		assertEquals(positionToCollision[1], ship.getPosition()[1], EPSILON);
	}
	
	@Test 
	public void testEvolve() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Bullet bullet1 = facade.createBullet(200, 200, 100, 0, 5);
		facade.addBulletToWorld(world, bullet1);
		for (int i = 2; i >= 0; i--){
			assertEquals(bullet1.getRemainingBounces(), i, EPSILON);
			world.evolve(world.getTimeNextCollision(), null);
		}
		assertTrue(bullet1.isTerminated());
		
	}
	
}








