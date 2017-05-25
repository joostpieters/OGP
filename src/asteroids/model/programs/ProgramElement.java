package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class ProgramElement {
	
	protected ProgramElement(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}

	private SourceLocation sourceLocation;
	private Program program;
	
	public void setProgram(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return program;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
}
