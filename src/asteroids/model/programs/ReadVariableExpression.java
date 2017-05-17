package asteroids.model.programs;

import java.util.List;
import java.util.NoSuchElementException;

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
		return getProgram().getVariable(variableName);
	}
	
	public Object evaluate(List<Expression> actualArgs){
		//TODO implement local variables
		return getProgram().getVariable(variableName);
	}

}
