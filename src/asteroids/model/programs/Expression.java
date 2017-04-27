package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public abstract class Expression {
	private SourceLocation sourceLocation;
	
	protected Expression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}

	public abstract Object evaluate();

}
