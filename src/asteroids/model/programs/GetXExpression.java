package asteroids.model.programs;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class GetXExpression extends Expression {
	Expression e;
	
	public GetXExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e = e;
	}

	@Override
	public Double evaluate() {
		Object eEvaluated = e.evaluate();
		if(!(eEvaluated instanceof Entity)) throw new IllegalArgumentException();
		return ((Entity)eEvaluated).getPosition()[0];
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		e.setProgram(program);
	}
	
	public String toString() {
		return "[GetXExpression: " + e.toString() + "]";
	}

}
