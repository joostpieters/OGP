package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends Statement {
	private Expression value;
	private Function function;

	public ReturnStatement(Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.value = value;
	}

	@Override
	public void execute() {
		if (function == null) throw new IllegalArgumentException();
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
	}
	
	@Override
	public String toString(){
		return "[ReturnStatement]";
	}

	@Override
	public void setFunction(Function function) throws IllegalArgumentException {
		this.function = function;
		value.setFunction(function);
	}

}
