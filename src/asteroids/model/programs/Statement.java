package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public abstract class Statement {

	private SourceLocation location;

	public Statement(SourceLocation location) {
		// TODO Auto-generated constructor stub
		this.location = location;
	}

	public abstract void execute();

}
