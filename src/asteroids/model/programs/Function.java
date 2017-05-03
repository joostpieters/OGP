package asteroids.model.programs;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Function {
	private String name;
	private Statement body;
	private SourceLocation sourceLocation;
	private Program program;

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

	public void setProgram(Program program) {
		this.program = program;
		body.setProgram(program);
	}

}
