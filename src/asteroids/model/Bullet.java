package asteroids.model;

import asteroids.util.ModelException;

public class Bullet extends Entity {

	protected Bullet(double x, double y, double xVelocity, double yVelocity,
			double radius, double orientation) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius, orientation);
		// TODO Auto-generated constructor stub
	}

	public void setShip(Ship ship) {
		if ((this.getWorld() == null) || (ship == null)){
			this.ship = ship;
		}
	}
	
	private Ship ship;
	

	public Ship getShip() {
		return this.ship;
	}

	public Ship getSource() {
		return this.source;
	}
	
	public void setSource(Ship ship) {
		this.source = ship;
	}
	
	private Ship source;

	public void fire() {
		double orientationFire = this.getShip().getOrientation();
		double distanceBetweenCenters = (this.getRadius() + this.getShip().getRadius());
		double xPosition = distanceBetweenCenters * Math.cos(orientationFire);
		double yPosition = distanceBetweenCenters * Math.sin(orientationFire);		
		this.setPosition(new double[]{xPosition, yPosition});
		double xVelocity = 250 * Math.cos(orientationFire);
		double yVelocity = 250 * Math.sin(orientationFire);
		this.setVelocity(new double[]{xVelocity, yVelocity});
		this.setSource(this.getShip());
		this.setWorld(this.getWorld());
		this.setShip(null);
		
	}

	@Override
	public double getMass() {
		double radius = getRadius();
		double mass = 3/4 * Math.PI * Math.pow(radius, 3) * minDensity;
		return mass;
		
	}

	public double getDensity(){
		return minDensity;
	}
	
	@Override
	public boolean isValidRadius(double radius) {
		return (radius > minRadius);
	}
	
	
	private static final double minDensity = 7.8*Math.pow(10, 12);
	
	private static final double minRadius = 1;
	
}
