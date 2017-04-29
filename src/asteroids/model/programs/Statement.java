package asteroids.model.programs;

import java.util.List;
import java.util.Optional;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public abstract class Statement {

	private SourceLocation location;
	private Program program;

	public Statement(SourceLocation location) {
		// TODO Auto-generated constructor stub
		this.location = location;
	}

	public abstract boolean execute();

	public void setProgram(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return program;
	}

	public boolean hasActiveBreakStatement() {
		return false;
	}

}
