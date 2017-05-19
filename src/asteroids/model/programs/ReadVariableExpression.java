package asteroids.model.programs;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class ReadVariableExpression extends Expression<Object> {

	private String variableName;
	
	public ReadVariableExpression(String variableName,
			SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.variableName = variableName;
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
	}

	@Override
	public String toString() {
		return "[ReadVariableExpression: " + variableName + "]";
	}

	@Override
	public Object evaluate() {
		// TODO Auto-generated method stub
		return getProgram().getVariable(variableName).getValue();
	}
	
	public Object evaluate(List<Expression> actualArgs, Set<Variable> localVariables){
		//TODO implement local variables
		Optional<Variable> localVariable = localVariables.stream().filter(variable -> variable.getName().equals(variableName)).findFirst();
		if(localVariable.isPresent()) return localVariable.get().getValue();
		else return getProgram().getVariable(variableName).getValue();
	}

}
