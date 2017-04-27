package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement {

	private Expression value;

	public PrintStatement(Expression value, SourceLocation sourceLocation) {
		this.value = value;
	}
	
	public void execute() {
		System.out.println(value.evaluate().toString());
	}
	
}