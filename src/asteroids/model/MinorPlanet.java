package asteroids.model;

public abstract class MinorPlanet extends Entity {
	/**
	 * Create a new ship with the given position, velocity, radius and
	 * orientation (in radians).
	 * @param  x
	 *         The x-coordinate of the position of this minor planet.
	 * @param  y
	 *         The y-coordinate of the position of this minor planet.
	 * @param  xVelocity
	 *         The x-coordinate of the velocity of this minor planet.
	 * @param  yVelocity
	 *         The y-coordinate of the velocity of this minor planet.
	 * @param  radius
	 *         The radius of this new ship.
	 * @param  orientation
	 *         The orientation of this new ship.
	 * @post   The new position of the minor planet is equal to the given position.
	 * @post   The new velocity of the minor planet is equal to the given velocity.
	 * @post   The new radius of the minor planet is equal to the given radius.
	 * @throws IllegalArgumentException
	 *         The given initial position or radius is not valid.
	 */
	protected MinorPlanet(double x, double y, double xVelocity,
			double yVelocity, double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
	}

	/**
	 * Return the mass of this minor planet.
	 * @return Returns the mass of this minor planet. 
	 */
	@Override
	public double getMass() {
		return 4/3.*Math.PI*Math.pow(getRadius(), 3)*getDensity();
	}

	/**
	 * Return the minimum radius of this minor planet.
	 * @return Returns the minimum radius of this minor planet. 
	 */
	@Override
	public double getMinRadius() {
		return minRadius;
	}
	
	private static final double minRadius = 5;

	/**
	 * Resolve collisions between this minor planet and another entity
	 * 
	 * @param  entity
	 * 	       The entity named entity
	 * @effect If this minor planet collides with a bullet, both entities will be terminated
	 * @effect If this minor planet collides with another minor planet, they bounce off each other with adjusted velocities
	 */
	@Override
	public void collide(Entity entity) {
		if (entity instanceof MinorPlanet) {
			double mi = this.getMass();double mj = entity.getMass();
			OrderedPair deltaR = this.getPositionDifference(entity);
			OrderedPair deltaV = this.getVelocityDifference(entity);
			double sigma = deltaR.getLength();
			double j = 2*mi*mj*(deltaV.dotProduct(deltaR))/(sigma*(mi+mj));
			double jx = j*deltaR.getX()/sigma;double jy = j*deltaR.getY()/sigma;
			OrderedPair oldVelocityi = this.getVelocity();
			OrderedPair oldVelocityj = entity.getVelocity();
			OrderedPair newVelocityi = new OrderedPair(oldVelocityi.getX()+jx/mi,oldVelocityi.getY()+jy/mi);
			OrderedPair newVelocityj = new OrderedPair(oldVelocityj.getX()-jx/mj,oldVelocityj.getY()-jy/mj);
			this.setVelocity(newVelocityi);entity.setVelocity(newVelocityj);
		} else if (entity instanceof Bullet) {
			this.terminate();
			entity.terminate();
		}
	}

}
