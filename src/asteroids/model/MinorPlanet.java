package asteroids.model;

public abstract class MinorPlanet extends Entity {

	protected MinorPlanet(double x, double y, double xVelocity,
			double yVelocity, double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
	}

	@Override
	public double getMass() {
		return 4/3*Math.PI*Math.pow(getRadius(), 3)*getDensity();
	}

	@Override
	public double getMinRadius() {
		return 5;
	}

	@Override
	public void collide(Entity entity) {
		// TODO Auto-generated method stub

	}

}
