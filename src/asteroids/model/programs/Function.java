package asteroids.model.programs;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class Function {
	private String name;
	private Statement body;
	private SourceLocation sourceLocation;

	public Function(String functionName, Statement body,
			SourceLocation sourceLocation) {
		this.name = functionName;
		this.body = body;
		this.sourceLocation = sourceLocation;
	}
	
	public Object evaluate(List<Expression> actualArgs){
		body.execute();
		
		return null;
	}

}
