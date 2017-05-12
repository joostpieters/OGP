package asteroids.model.programs;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class ReadParameterExpression extends Expression<Object> {

	private String parameterName;
	private Function function;

	public ReadParameterExpression(String parameterName,
			SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.parameterName = parameterName;
	}
	
	@Override
	public Object evaluate() {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException("Read parameter expression can only be used inside function bodies");
	}

	@Override
	public Object evaluate(List<Expression> actualArgs) throws IndexOutOfBoundsException {
		int argIndex = Integer.parseInt(parameterName.substring(1, parameterName.length()));
		return actualArgs.get(argIndex).evaluate();
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
	}

	@Override
	public String toString() {
		return "[ReadParameterExpression: " + parameterName + "]";
	}

}
