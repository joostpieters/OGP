package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class EqualityExpression extends Expression {
	private Expression e1;
	private Expression e2;

	public EqualityExpression(Expression e1, Expression e2,
			SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public Boolean evaluate() {
		Object e1Evaluated = e1.evaluate();Object e2Evaluated = e2.evaluate();
		return e1Evaluated.equals(e2Evaluated);
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		e1.setProgram(program);
		e2.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[EqualityExpression: " + e1 + " == " + e2 +"]";
	}

}
