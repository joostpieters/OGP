package asteroids.model.programs;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement<T> extends Statement {
	private Expression<T> value;
	private String variableName;
	private boolean hasActiveBreakStatement;

	public AssignmentStatement(String variableName, Expression<T> value,
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
		else getProgram().addVariable(new Variable<T>(variableName, value.evaluate()));
		if (value instanceof FunctionCallExpression && ((FunctionCallExpression)value).hasActiveBreakStatement()) setHasActiveBreakStatement(true);
	}
	
	private void setHasActiveBreakStatement(boolean b) {
		this.hasActiveBreakStatement = b;
	}
	
	public boolean hasActiveBreakStatement(){
		return hasActiveBreakStatement;
	}

	public Optional execute(List<Expression> actualArgs){
		Optional<Variable> variableToAssignTo = getFunction().getVariables().stream().filter(variable -> variable.getName().equals(variableName)).findFirst();
		if(variableToAssignTo.isPresent()) variableToAssignTo.get().setValue(value.evaluate());
		else getFunction().addVariable(new Variable<T>(variableName, value.evaluate()));
	}
	
	public void setProgram(Program program) {
		super.setProgram(program);
		value.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[AssigmentStatement: " + variableName + " <- " + value +"]";
	}

}
