package asteroids.model.programs;

import java.util.List;
import java.util.Set;

import asteroids.model.Program;
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
		this.actualArgs = actualArgs;	}

	@Override
	public Object evaluate() {
		// TODO Auto-generated method stub
		Function function =  getProgram().getFunction(functionName);
		Object[] actualArgsEvaluated = actualArgs.stream().map(arg -> arg.evaluate()).toArray();
		Object result = function.evaluate(actualArgsEvaluated);
		if (function.hasActiveBreakStatement()) setHasActiveBreakStatement(true);
		else setHasActiveBreakStatement(false);
		return result;
	}

	@Override
	public Object evaluate(Object[] actualArgs, Set<Variable> localVariables) throws IllegalArgumentException {
		setHasActiveBreakStatement(false);
		// TODO Auto-generated method stub
		Function function =  getProgram().getFunction(functionName);
		Object[] actualArgsEvaluated = this.actualArgs.stream().map(arg -> arg.evaluate(actualArgs, localVariables)).toArray();
		Object result = function.evaluate(actualArgsEvaluated);
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
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		for (Expression arg: actualArgs) arg.setProgram(getProgram());
	}

}
