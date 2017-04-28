package asteroids.model.programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public abstract class ActionStatement extends Statement {

	private Ship ship;

	public ActionStatement(SourceLocation location) {
		super(location);
	}
	
	public abstract Action returnAction();
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
}

enum Action {
	TURN, FIRE, THRUST_ON, THRUST_OFF, SKIP;
}