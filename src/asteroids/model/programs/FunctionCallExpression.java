package asteroids.model.programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class FunctionCallExpression extends Expression<Object> {
	private String functionName;
	private List<Expression> actualArgs;

	public FunctionCallExpression(String functionName,
			List<Expression> actualArgs, SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.functionName = functionName;
		this.actualArgs = actualArgs;
	}

	@Override
	public Object evaluate() {
		// TODO Auto-generated method stub
		return getProgram().getFunction(functionName).evaluate(actualArgs);
	}

	@Override
	public Object evaluate(List<Expression> actualArgs) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return "[FunctionCallExpression: " + functionName + ": + " + actualArgs.toString() +"]";
	}

}
