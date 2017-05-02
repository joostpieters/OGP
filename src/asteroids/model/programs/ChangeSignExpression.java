package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class ChangeSignExpression extends Expression {
	private Expression e;

	public ChangeSignExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e = e;
	}

	@Override
	public Double evaluate() {
		Object eEvaluated = e.evaluate();
		if(!(eEvaluated instanceof Double)) throw new IllegalArgumentException();
		return -((Double)eEvaluated);
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
