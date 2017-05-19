package asteroids.model.programs;

import java.util.List;
import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class GetRadiusExpression extends Expression<Double> {
	private Expression<? extends Entity> e;

	public GetRadiusExpression(Expression<? extends Entity> e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e = e;
	}

	@Override
	public Double evaluate() throws IllegalArgumentException {
		return e.evaluate().getRadius();
	}

	@Override
	public Double evaluate(List<Expression> actualArgs, Set<Variable> localVariables) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return e.evaluate(actualArgs, localVariables).getRadius();
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		e.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[GetRadiusExpression: " + e.toString() + "]";
	}

}
