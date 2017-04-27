package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public abstract class ActionStatement extends Statement {

	public ActionStatement(SourceLocation location) {
		// TODO Auto-generated constructor stub
	}
	
	public abstract Action returnAction();
}

enum Action {
	TURN, FIRE, THRUST_ON, THRUST_OFF, SKIP;
}