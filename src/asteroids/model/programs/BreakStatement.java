package asteroids.model.programs;

import java.util.List;
import java.util.Optional;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class BreakStatement extends Statement {

	public BreakStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		return;
	}
	
	public String toString() {
		return "[BreakStatement]";
	}

	@Override
	public void setProgram(Program program) {
		// TODO Auto-generated method stub
		super.setProgram(program);
	}
	
	public boolean hasActiveBreakStatement(){
		return true;
	}

	@Override
	public Optional execute(List<Expression> actualArgs) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
