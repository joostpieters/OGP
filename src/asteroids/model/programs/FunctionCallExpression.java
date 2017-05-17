package asteroids.model.programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class FunctionCallExpression extends Expression<Object> {
	private String functionName;
	private List<Expression> actualArgs;
	private boolean hasActiveBreakStatement;

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
		Function function =  getProgram().getFunction(functionName);
		Object result = function.evaluate(actualArgs);
		if (function.hasActiveBreakStatement()) setHasActiveBreakStatement(true);
		else setHasActiveBreakStatement(false);
		return result;
	}

	@Override
	public Object evaluate(List<Expression> actualArgs) throws IllegalArgumentException {
		setHasActiveBreakStatement(false);
		// TODO Auto-generated method stub
		Function function =  getProgram().getFunction(functionName);
		Object result = function.evaluate(this.actualArgs);
		if (function.hasActiveBreakStatement()) {
			setHasActiveBreakStatement(true);
		}
		else setHasActiveBreakStatement(false);
		return result;
	}
	
	public boolean hasActiveBreakStatement() {
		return hasActiveBreakStatement;
	}

	public void setHasActiveBreakStatement(boolean hasActiveBreakStatement) {
		this.hasActiveBreakStatement = hasActiveBreakStatement;
	}

	@Override
	public String toString() {
		return "[FunctionCallExpression: " + functionName + ": + " + actualArgs.toString() +"]";
	}

}
