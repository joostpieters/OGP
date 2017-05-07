package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class ChangeSignExpression extends Expression<Double> {
	private Expression<? extends Double> e;

	public ChangeSignExpression(Expression<? extends Double> e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e = e;
	}

	@Override
	public Double evaluate() {
		return -e.evaluate();
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		e.setProgram(program);
	}
	
	public String toString() {
		return "[ChangeSignExpression: " + e + "]";
	}

}
