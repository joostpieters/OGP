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
			double mi = this.getMass();double mj = entity.getMass();
			double[] deltaR = this.getPositionDifference(entity);
			double[] deltaV = this.getVelocityDifference(entity);
			double sigma = dotProduct(deltaR,deltaR);
			double j = 2*mi*mj*(dotProduct(deltaV,deltaR))/(sigma*(mi+mj));
			double jx = j*deltaR[0]/sigma;double jy = j*deltaR[1]/sigma;
			double[] oldVelocityi = this.getVelocity();
			double[] oldVelocityj = entity.getVelocity();
			double[] newVelocityi = new double[]{oldVelocityi[0]+jx/mi,oldVelocityi[1]+jy/mi};
			double[] newVelocityj = new double[]{oldVelocityj[0]-jx/mj,oldVelocityj[1]-jy/mj};
			this.setVelocity(newVelocityi);entity.setVelocity(newVelocityj);		}
		else super.collide(entity);
	}
	
	public void move(double dt) throws IllegalArgumentException {
		setPosition(getPositionAfterMovingForAPeriodOf(dt));
	}

	
	
}
