package asteroids.model;

public class Asteroid extends MinorPlanet {

	public Asteroid(double x, double y, double xVelocity, double yVelocity,
			double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double getDensity() {
		return density;
	}
	
	private static final double density = 2.65E12;

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
