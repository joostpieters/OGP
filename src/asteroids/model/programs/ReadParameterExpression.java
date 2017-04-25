package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class ReadParameterExpression extends Expression {

	private String parameterName;

	public ReadParameterExpression(String parameterName,
			SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		this.parameterName = parameterName;
	}

}
