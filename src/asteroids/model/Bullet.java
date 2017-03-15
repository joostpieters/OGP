package asteroids.model;

public class Bullet extends Entity {

	protected Bullet(double x, double y, double xVelocity, double yVelocity,
			double radius, double orientation) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius, orientation);
		// TODO Auto-generated constructor stub
	}

	public void setShip(Ship ship) {
		// TODO Auto-generated method stub
		
	}

	public Ship getShip() {
		// TODO Auto-generated method stub
		return null;
	}

	public Ship getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	public void fire(double[] startPosition, double orientation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isValidRadius(double radius) {
		// TODO Auto-generated method stub
		return false;
	}

}
