package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class ReadParameterExpression extends Expression {

	private String parameterName;

	public ReadParameterExpression(String parameterName,
			SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.parameterName = parameterName;
	}
	
	public void setProgram(Program program) {
		super.setProgram(program);
	}

	public String toString() {
		return "[ReadParameterExpression: " + parameterName + "]";
	}

	@Override
	public Object evaluate() {
		// TODO Auto-generated method stub
	}

}