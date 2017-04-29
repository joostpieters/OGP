package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement {

	private Expression value;

	public PrintStatement(Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}
	
	public boolean execute() {
		Object evaluatedValue = value.evaluate();
		System.out.println(evaluatedValue.toString());
		getProgram().getResults().add(evaluatedValue);
		return true;
	}
	
	public void setProgram(Program program) {
		super.setProgram(program);
		value.setProgram(program);
	}

	public String toString(){
		return "[PrintStatement: " + value.toString() + "]";
	}
}