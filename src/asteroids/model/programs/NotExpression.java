package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class NotExpression extends Expression {
	Expression e;

	public NotExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e = e;
	}

	@Override
	public Boolean evaluate() {
		Object eEvaluated = e.evaluate();
		if(!(eEvaluated instanceof Boolean)) throw new IllegalArgumentException();
		return !((Boolean)eEvaluated);
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		e.setProgram(program);
	}
	
	public String toString() {
		return "[NotExpression: " + e + "]";
	}

}
