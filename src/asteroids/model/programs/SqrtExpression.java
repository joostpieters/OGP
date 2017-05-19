package asteroids.model.programs;

import java.util.List;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class SqrtExpression extends Expression<Double> {
	Expression<Double> e;

	public SqrtExpression(Expression<Double> e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e = e;
	}

	@Override
	public Double evaluate() {
		return Math.sqrt(e.evaluate());
	}

	@Override
	public Double evaluate(List<Expression> actualArgs, Set<Variable> localVariables) throws IllegalArgumentException {
		return Math.sqrt(e.evaluate(actualArgs, localVariables));
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
