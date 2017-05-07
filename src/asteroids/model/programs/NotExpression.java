package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class NotExpression extends Expression<Boolean> {
	private Expression<? extends Boolean> e;

	public NotExpression(Expression<? extends Boolean> e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e = e;
	}

	@Override
	public Boolean evaluate() {
		return !e.evaluate();
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		e.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[NotExpression: " + e + "]";
	}

}
