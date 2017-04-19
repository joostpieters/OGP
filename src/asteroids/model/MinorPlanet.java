package asteroids.model;

public abstract class MinorPlanet extends Entity {

	protected MinorPlanet(double x, double y, double xVelocity,
			double yVelocity, double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMinRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void collide(Entity entity) {
		// TODO Auto-generated method stub

	}

}
