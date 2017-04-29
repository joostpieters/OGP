package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class DoubleLiteralExpression extends Expression {
	double value;

	public DoubleLiteralExpression(double value, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.value = value;
	}

	@Override
	public Double evaluate() {
		return value;
	}

	@Override
	public void setProgram(Program program) {
		// do nothing
		super.setProgram(program);
	}

}
