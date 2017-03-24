package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

public class Bullet extends Entity {

	public Bullet(double x, double y, double xVelocity, double yVelocity,
			double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
	}
	
	public int getRemainingBounces(){
		return this.remainingBounces;
	}
	
	private void removeABounce(){
		remainingBounces--;
	}
	
	private int remainingBounces = 2;

	@Raw
	public void setShip(@Raw Ship ship) {
		if (ship == null || ship.getBullets().contains(this)){
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
		double[] positionShip = source.getPosition();
		double distanceBetweenCenters = (this.getRadius() + source.getRadius());
		double xPosition = positionShip[0] + distanceBetweenCenters * Math.cos(orientationFire);
		double yPosition = positionShip[1] + distanceBetweenCenters * Math.sin(orientationFire);
		double xVelocity = 250 * Math.cos(orientationFire);
		double yVelocity = 250 * Math.sin(orientationFire);
		this.setSource(source);
		source.removeBullet(this);
		source.getWorld().addBullet(this);
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


	@Override
	public void collide(Entity entity) {
		entity.terminate();
		this.terminate();
	}
	
	public void collideBoundary() {
		super.collideBoundary();
		this.removeABounce();
		if(this.getRemainingBounces() < 0) this.terminate();
	}
	
}
