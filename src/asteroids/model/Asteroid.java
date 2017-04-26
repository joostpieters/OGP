package asteroids.model;

public class Asteroid extends MinorPlanet {

	public Asteroid(double x, double y, double xVelocity, double yVelocity,
			double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getRadius() {
		return radius;
	}
	
	private static final double radius = 5;

	@Override
	public double getDensity() {
		return density;
	}
	
	private static final double density = 0.917E12;

	@Override
	public void collide(Entity entity) {
		if (entity instanceof Ship) {
			entity.terminate();
		}
		else super.collide(entity);
	}
	
	public void move(double dt) throws IllegalArgumentException {
		setPosition(getPositionAfterMovingForAPeriodOf(dt));
	}
}
