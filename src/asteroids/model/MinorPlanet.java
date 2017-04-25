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
	
	public void collide(Entity entity) {
		this.terminate();
		entity.terminate();
	}
	
	public void collide(Bullet bullet) {
		this.terminate();
		bullet.terminate();
	}
	
	public void collide(MinorPlanet minorPlanet) {
		double mi = this.getMass();double mj = minorPlanet.getMass();
		double sigma = this.getRadius() + minorPlanet.getRadius();
		double[] deltaR = this.getPositionDifference(minorPlanet);
		double[] deltaV = this.getVelocityDifference(minorPlanet);
		double j = 2*mi*mj*(dotProduct(deltaV,deltaR))/(sigma*(mi+mj));
		double jx = j*deltaR[0]/sigma;double jy = j*deltaR[1]/sigma;
		double[] oldVelocityi = this.getVelocity();
		double[] oldVelocityj = minorPlanet.getVelocity();
		double[] newVelocityi = new double[]{oldVelocityi[0]+jx/mi,oldVelocityi[1]+jy/mi};
		double[] newVelocityj = new double[]{oldVelocityj[0]-jx/mj,oldVelocityj[1]-jy/mj};
		this.setVelocity(newVelocityi);minorPlanet.setVelocity(newVelocityj);
	}

}
