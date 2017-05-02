package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class SqrtExpression extends Expression {
	Expression e;

	public SqrtExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e = e;
	}

	@Override
	public Double evaluate() {
		Object eEvaluated = e.evaluate();
		if(!(eEvaluated instanceof Double)) throw new IllegalArgumentException();
		return Math.sqrt((Double)eEvaluated);
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		e.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[SqrtExpression: " + e + "]";
	}

}
