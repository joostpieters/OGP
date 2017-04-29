package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends Statement {

	public ReturnStatement(Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	public void setProgram(Program program) {
		super.setProgram(program);
	}
	
	public String toString(){
		return "[ReturnStatement]";
	}

}
