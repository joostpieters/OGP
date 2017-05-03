package asteroids.model;

public abstract class MinorPlanet extends Entity {

	protected MinorPlanet(double x, double y, double xVelocity,
			double yVelocity, double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
	}

	@Override
	public double getMass() {
		return 4/3.*Math.PI*Math.pow(getRadius(), 3)*getDensity();
	}

	@Override
	public double getMinRadius() {
		return minRadius;
	}
	
	private static final double minRadius = 5;

	@Override
	public void collide(Entity entity) {
		if (entity instanceof MinorPlanet) {
			double mi = this.getMass();double mj = entity.getMass();
			double[] deltaR = this.getPositionDifference(entity);
			double[] deltaV = this.getVelocityDifference(entity);
			double sigma = Math.sqrt(dotProduct(deltaR,deltaR));
			double j = 2*mi*mj*(dotProduct(deltaV,deltaR))/(sigma*(mi+mj));
			double jx = j*deltaR[0]/sigma;double jy = j*deltaR[1]/sigma;
			double[] oldVelocityi = this.getVelocity();
			double[] oldVelocityj = entity.getVelocity();
			double[] newVelocityi = new double[]{oldVelocityi[0]+jx/mi,oldVelocityi[1]+jy/mi};
			double[] newVelocityj = new double[]{oldVelocityj[0]-jx/mj,oldVelocityj[1]-jy/mj};
			this.setVelocity(newVelocityi);entity.setVelocity(newVelocityj);
		} else if (entity instanceof Bullet) {
			this.terminate();
			entity.terminate();
		}
	}

}
