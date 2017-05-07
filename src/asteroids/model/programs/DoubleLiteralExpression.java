package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class DoubleLiteralExpression extends Expression<Double> {
	private double value;

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
	
	@Override
	public String toString() {
		return "[DoubleLiteralExpression: " + value + "]";
	}

	@Override
	public void setFunction(Function function) {
		// TODO Auto-generated method stub
		
	}

}
