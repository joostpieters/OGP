package asteroids.model.programs;

import java.util.Optional;
import java.util.Set;

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
	public Optional execute(Object[] actualArgs, Set<Variable> localVariables) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
