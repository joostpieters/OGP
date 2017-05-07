package asteroids.model.programs;

import java.util.Optional;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement<T> extends Statement {
	private Expression<T> value;
	private String variableName;
	private Function function;

	public AssignmentStatement(String variableName, Expression<T> value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.variableName = variableName;
		this.value = value;
	}

	@Override
	public void execute() {
		if (getFunction() != null) {
			Optional<Variable> variableToAssignTo = getFunction().getVariables().stream().filter(variable -> variable.getName().equals(variableName)).findFirst();
			if(variableToAssignTo.isPresent()) variableToAssignTo.get().setValue(value.evaluate());
			else getFunction().addVariable(new Variable<T>(variableName, value.evaluate()));
		} else{
			Optional<Variable> variableToAssignTo = getProgram().getVariables().stream().filter(variable -> variable.getName().equals(variableName)).findFirst();
			if(variableToAssignTo.isPresent()) variableToAssignTo.get().setValue(value.evaluate());
			else getProgram().addVariable(new Variable<T>(variableName, value.evaluate()));
		}
	}
	
	public void setProgram(Program program) {
		super.setProgram(program);
		value.setProgram(program);
	}

	public Function getFunction() {
		return function;
	}

	@Override
	public void setFunction(Function function) throws IllegalArgumentException {
		this.function = function;
		value.setFunction(function);
	}
	
	@Override
	public String toString() {
		return "[AssigmentStatement: " + variableName + " <- " + value +"]";
	}

}
