package asteroids.model.programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public abstract class EntityExpression extends Expression {
	private Ship ship;

	public EntityExpression(SourceLocation location){
		//TODO
		super(location);
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}

}
