package asteroids.model.programs;

import java.util.List;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class GetVYExpression extends Expression<Double> {
	private Expression<? extends Entity> e;
	
	public GetVYExpression(Expression<? extends Entity> e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e = e;
	}

	@Override
	public Double evaluate() throws IllegalArgumentException {
		return e.evaluate().getVelocity()[1];
	}

	@Override
	public Double evaluate(List<Expression> actualArgs) throws IllegalArgumentException {
		return e.evaluate(actualArgs).getVelocity()[1];
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		e.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[GetVYExpression: " + e.toString() + "]";
	}

}
