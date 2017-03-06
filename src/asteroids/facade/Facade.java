package asteroids.facade;

import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Facade implements IFacade {

	@Override
	public Ship createShip() throws ModelException {
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
	public void move(Ship ship, double dt) throws ModelException {
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
		} catch(NullPointerException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2)
			throws ModelException {
		try {
			return ship1.getTimeToCollision(ship2);
		} catch(NullPointerException exc){
			throw new ModelException(exc.getMessage());
		} catch(IllegalArgumentException exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2)
			throws ModelException {
		try {
			return ship1.getCollisionPosition(ship2);
		} catch(NullPointerException exc){
			throw new ModelException(exc.getMessage());
		} catch(IllegalArgumentException exc) {
			throw new ModelException(exc.getMessage());
		}
	}

}