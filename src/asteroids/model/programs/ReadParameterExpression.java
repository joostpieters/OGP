package asteroids.model.programs;

import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class ReadParameterExpression extends ProgramElement implements Expression<Object> {

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
	public Object evaluate(Object[] actualArgs, Set<Variable> localVariables) throws IndexOutOfBoundsException {
		int argIndex = Integer.parseInt(parameterName.substring(1, parameterName.length()));
		System.out.println(actualArgs[argIndex-1]);
		return actualArgs[argIndex-1];
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
