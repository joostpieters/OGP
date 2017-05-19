package asteroids.model.programs;

import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class GetXExpression extends Expression<Double> {
	private Expression<? extends Entity> e;
	
	public GetXExpression(Expression<? extends Entity> e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e = e;
	}

	@Override
	public Double evaluate() throws IllegalArgumentException {
		return e.evaluate().getPosition()[0];
	}

	@Override
	public Double evaluate(Object[] actualArgs, Set<Variable> localVariables) throws IllegalArgumentException {
		return e.evaluate(actualArgs, localVariables).getPosition()[0];
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		e.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[GetXExpression: " + e.toString() + "]";
	}

}
