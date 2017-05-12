package asteroids.model.programs;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class MultiplicationExpression extends Expression<Double> {
	private Expression<? extends Double> e1;
	private Expression<? extends Double> e2;

	public MultiplicationExpression(Expression<? extends Double> e1, Expression<? extends Double> e2,
			SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public Double evaluate() {
		return e1.evaluate() * e2.evaluate();
	}

	@Override
	public Double evaluate(List<Expression> actualArgs) throws IllegalArgumentException {
		return e1.evaluate(actualArgs) * e2.evaluate(actualArgs);
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		e1.setProgram(program);
		e2.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[MultiplicationExpression: " + e1 + " * " + e2 +"]";
	}

}
