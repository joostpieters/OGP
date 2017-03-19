package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

public class Bullet extends Entity {

	protected Bullet(double x, double y, double xVelocity, double yVelocity,
			double radius, double orientation) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius, orientation);
		// TODO Auto-generated constructor stub
	}

	@Raw
	public void setShip(@Raw Ship ship) {
		if (this.getShip() == ship || ship == null){
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
		Ship source = this.getShip();
		double orientationFire = source.getOrientation();
		double distanceBetweenCenters = (this.getRadius() + source.getRadius());
		double xPosition = distanceBetweenCenters * Math.cos(orientationFire);
		double yPosition = distanceBetweenCenters * Math.sin(orientationFire);
		double xVelocity = 250 * Math.cos(orientationFire);
		double yVelocity = 250 * Math.sin(orientationFire);
		this.setSource(source);
		source.removeBullet(this);
		this.setWorld(source.getWorld());
		this.setPosition(new double[]{xPosition, yPosition});
		this.setVelocity(new double[]{xVelocity, yVelocity});
	}

	@Override
	public double getMass() {
		double radius = getRadius();
		double mass = 3/4 * Math.PI * Math.pow(radius, 3) * getDensity();
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
