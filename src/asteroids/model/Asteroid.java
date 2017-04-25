package asteroids.model;

public class Asteroid extends MinorPlanet {

	public Asteroid(double x, double y, double xVelocity, double yVelocity,
			double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getDensity() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void collide(Ship ship){
		// TODO Auto-generated method stub
	}

}
