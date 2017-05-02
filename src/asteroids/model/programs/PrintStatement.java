package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement {

	private Expression value;

	public PrintStatement(Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}
	
	@Override
	public void execute() {
		Object evaluatedValue = value.evaluate();
		if (evaluatedValue == null) System.out.println("null");
		else System.out.println(evaluatedValue.toString());
		getProgram().addResult(evaluatedValue);
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		value.setProgram(program);
	}

	@Override
	public String toString(){
		return "[PrintStatement: " + value.toString() + "]";
	}
}