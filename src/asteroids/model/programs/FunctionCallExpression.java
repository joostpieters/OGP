package asteroids.model.programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class FunctionCallExpression extends Expression {
	private String functionName;
	private List<Expression<?>> actualArgs;

	public FunctionCallExpression(String functionName,
			List<Expression<?>> actualArgs, SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
	}

	@Override
	public Object evaluate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return "[FunctionCallExpression: " + functionName + ": + " + actualArgs.toString() +"]";
	}

}
