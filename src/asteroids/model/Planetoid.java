package asteroids.model;

public class Planetoid extends MinorPlanet {

	private double totalTraveledDistance;

	public Planetoid(double x, double y, double xVelocity, double yVelocity,
			double radius, double totalTraveledDistance) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		setTotalTraveledDistance(totalTraveledDistance);
		if (getRadius() < getMinRadius()) terminate();
	}

	private void setTotalTraveledDistance(double totalTraveledDistance) {
		this.totalTraveledDistance = totalTraveledDistance;
		
	}
	
	@Override
	public double getRadius() {
		return super.getRadius() - totalTraveledDistance * 0.000001;
		
	}

	public double getTotalTraveledDistance() {
		return totalTraveledDistance;
	}

	@Override
	public double getDensity() {
		return density;
	}
	
	private static final double density = 0.917E12;
	
	public void move(double dt) {
		super.move(dt);
		setTotalTraveledDistance(getTotalTraveledDistance() + getSpeed()*dt);
		if (getRadius() < getMinRadius()) terminate();
	}
	
	public void terminate(){
		double radius = getRadius();
		World world = getWorld();
		super.terminate();
		if (radius >= 30){
			OrderedPair position = getPosition();
			double speed = 1.5*getSpeed();
			double direction = 2*Math.PI*Math.random();
			world.addEntity(new Asteroid(position.getX()+radius/2*Math.sin(direction),position.getY()+radius/2*Math.cos(direction),speed*Math.sin(direction),speed*Math.cos(direction),radius/2));
			world.addEntity(new Asteroid(position.getX()-radius/2*Math.sin(direction),position.getY()-radius/2*Math.cos(direction),-speed*Math.sin(direction),-speed*Math.cos(direction),radius/2));
		}
	}

	@Override
	public void collide(Entity entity) {
		if (entity instanceof Ship) {
			double shipRadius = entity.getRadius();
			OrderedPair newPosition = new OrderedPair(shipRadius + Math.random()*(getWorld().getSize()[0]-2*shipRadius),shipRadius + Math.random()*(getWorld().getSize()[1]-2*shipRadius));
			entity.setPosition(newPosition);
			for (Entity entityOther: getWorld().getEntities()) if(entityOther != entity && entityOther.overlap(entity)) entity.terminate();
		}
		else super.collide(entity);
	}

}
