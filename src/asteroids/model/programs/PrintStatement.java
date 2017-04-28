package asteroids.model.programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement {

	private Expression value;

	public PrintStatement(Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}
	
	public void execute() {
		System.out.println(value.evaluate().toString());
	}
	
	public String toString(){
		return "[PrintStatement: " + value.toString() + "]";
	}
	
	public void setShip(Ship ship) {
		value.setShip(ship);
	}
}