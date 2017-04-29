package asteroids.model.programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public abstract class ActionStatement extends Statement {

	public ActionStatement(SourceLocation location) {
		super(location);
	}
}