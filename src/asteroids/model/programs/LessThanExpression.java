package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class LessThanExpression extends Expression<Boolean> {
	private Expression<Double> e1;
	private Expression<Double> e2;

	public LessThanExpression(Expression<Double> e1, Expression<Double> e2,
			SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public Boolean evaluate() {
		return e1.evaluate() < e2.evaluate();
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		e1.setProgram(program);
		e2.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[LessThanExpression: " + e1 + " < " + e2 +"]";
	}

}
