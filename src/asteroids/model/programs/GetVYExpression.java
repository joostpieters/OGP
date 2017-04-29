package asteroids.model.programs;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class GetVYExpression extends Expression {
	Expression e;
	
	public GetVYExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e = e;
	}

	@Override
	public Double evaluate() {
		Object eEvaluated = e.evaluate();
		if(!(eEvaluated instanceof Entity)) throw new IllegalArgumentException();
		return ((Entity)eEvaluated).getVelocity()[1];
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		e.setProgram(program);
	}
	
	public String toString() {
		return "[GetVYExpression: " + e.toString() + "]";
	}

}
