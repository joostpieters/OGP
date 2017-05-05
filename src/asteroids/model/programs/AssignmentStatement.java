package asteroids.model.programs;

import java.util.Optional;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement<T> extends Statement {
	private Expression<T> value;
	private String variableName;

	public AssignmentStatement(String variableName, Expression<T> value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.variableName = variableName;
		this.value = value;
	}

	@Override
	public void execute() {
		Optional<Variable> variableToAssignTo = getProgram().getVariables().stream().filter(variable -> variable.getName().equals(variableName)).findFirst();
		if(variableToAssignTo.isPresent()) variableToAssignTo.get().setValue(value.evaluate());
		else getProgram().getVariables().add(new Variable<T>(variableName, value.evaluate()));
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
