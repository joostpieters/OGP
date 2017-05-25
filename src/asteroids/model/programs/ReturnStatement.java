package asteroids.model.programs;

import java.util.Optional;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends ProgramElement implements Statement {
	private Expression value;

	public ReturnStatement(Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.value = value;
	}

	@Override
	public void execute() {
		throw new IllegalArgumentException();
		
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		value.setProgram(program);
	}

	@Override
	public Optional execute(Object[] actualArgs, Set<Variable> localVariables) {
		return Optional.of(value.evaluate(actualArgs, localVariables));
	}

	@Override
	public boolean hasActiveBreakStatement() {
		return false;
	}

	@Override
	public boolean failedToAdvanceTime() {
		return false;
	}
	
	@Override
	public String toString(){
		return "[ReturnStatement]";
	}

}
