package asteroids.model.programs;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement extends ProgramElement implements Statement {
	private Expression value;
	private String variableName;
	private boolean hasActiveBreakStatement;

	public AssignmentStatement(String variableName, Expression value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.variableName = variableName;
		this.value = value;
	}

	@Override
	public void execute() {
		setHasActiveBreakStatement(false);
		try{
			getProgram().getFunction(variableName);
			throw new IllegalArgumentException("A function has the same name as this global variable");
		} catch (NoSuchElementException e){}
		Optional<Variable> variableToAssignTo = getProgram().getVariables().stream().filter(variable -> variable.getName().equals(variableName)).findFirst();
		if(variableToAssignTo.isPresent()) variableToAssignTo.get().setValue(value.evaluate());
		else getProgram().addVariable(new Variable(variableName, value.evaluate()));
		if (value instanceof FunctionCallExpression && ((FunctionCallExpression)value).hasActiveBreakStatement()) setHasActiveBreakStatement(true);
	}
	
	private void setHasActiveBreakStatement(boolean b) {
		this.hasActiveBreakStatement = b;
	}
	
	public boolean hasActiveBreakStatement(){
		return hasActiveBreakStatement;
	}

	@Override
	public Optional execute(Object[] actualArgs, Set<Variable> localVariables){
		Optional<Variable> variableToAssignTo = localVariables.stream().filter(variable -> variable.getName().equals(variableName)).findFirst();
		if(variableToAssignTo.isPresent()) variableToAssignTo.get().setValue(value.evaluate(actualArgs, localVariables));
		else localVariables.add(new Variable(variableName, value.evaluate(actualArgs, localVariables)));
		if (value instanceof FunctionCallExpression && ((FunctionCallExpression)value).hasActiveBreakStatement()) setHasActiveBreakStatement(true);
		return Optional.empty();
	}
	
	public void setProgram(Program program) {
		super.setProgram(program);
		value.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[AssigmentStatement: " + variableName + " <- " + value +"]";
	}

	@Override
	public boolean failedToAdvanceTime() {
		return false;
	}

}
