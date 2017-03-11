package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Ship;
import asteroids.facade.Facade;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Part1Test {
	
	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}

	@Test
	public void testCreateShip() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, -10, 20, Math.PI);
		assertNotNull(ship);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(100, position[0], EPSILON);
		assertEquals(200, position[1], EPSILON);
		assertEquals(20, facade.getShipRadius(ship), EPSILON);
	
	}

	@Test(expected = ModelException.class)// defensive example
	public void testCreateShipXIsNan() throws ModelException {
		facade.createShip(Double.NaN, 200, 10, -10, 20, -Math.PI);
	}

	@Test(expected = ModelException.class)
	public void testCreateShipRadiusNegative() throws ModelException {
		facade.createShip(100, 200, 10, -10, -20, -Math.PI);
	}

	@Test
	public void testMove() throws ModelException { 
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, 1);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(130, position[0], EPSILON);
		assertEquals(85, position[1], EPSILON);
	}
	
	@Test
	//in progress-add upper and lower bound
	public void testThrust() throws ModelException {
		Ship ship = facade.createShip(0, 0, 0, 0, 20, 0);
		facade.thrust(ship, 2);
		double[] velocity = facade.getShipVelocity(ship);
		assertNotNull(velocity);
		assertEquals(2, velocity[0], EPSILON);
		assertEquals(0, velocity[1], EPSILON);
	}
	
	@Test
	//in progress-add upper and lower bound
	public void testTurn() throws ModelException {
		Ship ship = facade.createShip(0, 0, 0, 0, 20, 0);
		facade.turn(ship, Math.PI);
		double orientation = facade.getShipOrientation(ship);
		assertNotNull(orientation);
		assertEquals(Math.PI, orientation, EPSILON);
	}
	
	@Test
	//in progress-add upper and lower bound
	public void testPosition() throws ModelException {
		Ship ship = facade.createShip(10, 20, 0, 0, 20, 0);
		double [] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(10, position[0], EPSILON);
		assertEquals(20, position[1], EPSILON);
	}
	
	@Test
	//in progress-add upper and lower bound
	public void testVelocity() throws ModelException {
		Ship ship = facade.createShip(0, 0, 10, 20, 20, 0);
		double [] velocity = facade.getShipVelocity(ship);
		assertNotNull(velocity);
		assertEquals(10, velocity[0], EPSILON);
		assertEquals(20, velocity[1], EPSILON);
	}
	
	@Test
	//in progress-add upper and lower bound
	public void testRadius() throws ModelException {
		Ship ship = facade.createShip(0, 0, 0, 0, 25, 0);
		double radius = facade.getShipRadius(ship);
		assertEquals(25, radius, EPSILON);
	}
	
	@Test
	//in progress-add upper and lower bound	
	public void testgetDistanceBetween() throws ModelException {
		Ship ship1 = facade.createShip(0, 50, 0, 0, 25, 0);
		Ship ship2 = facade.createShip(0, 0, 0, 0, 25, 0);
		double distance = facade.getDistanceBetween(ship1, ship2);
		assertNotNull(distance);
		assertEquals(0, distance, EPSILON);
	}
	
	@Test
	//in progress-add upper and lower bound
	public void testoverlap() throws ModelException {
		Ship ship1 = facade.createShip(0, 25, 0, 0, 25, 0);
		Ship ship2 = facade.createShip(0, 0, 0, 0, 25, 0);
		boolean overlap = facade.overlap(ship1, ship2);
		assertEquals(true, overlap);
	}
	
	@Test
	//in progress-add upper and lower bound	
	public void testgetTimeToCollision() throws ModelException {
		Ship ship1 = facade.createShip(80, 0, 0, 0, 20, 0);
		Ship ship2 = facade.createShip(0, 0, 20, 0, 20, 0);
		double time = facade.getTimeToCollision(ship1, ship2);
		assertNotNull(time);
		assertEquals(2, time, EPSILON);
	}
	
	@Test
	//in progress-add upper and lower bound	
	public void testgetCollisionPosition() throws ModelException {
		Ship ship1 = facade.createShip(80, 0, 0, 0, 20, 0);
		Ship ship2 = facade.createShip(0, 0, 20, 0, 20, 0);
		double[] position = facade.getCollisionPosition(ship1, ship2);
		assertEquals(80, position[0], EPSILON);
		assertEquals(0, position[1], EPSILON);
	}
	
	
	
	
	
	
	
}
