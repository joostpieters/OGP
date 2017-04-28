package asteroids.model.programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class AssigmentStatement extends Statement {
	Expression value;
	private String variableName;

	public AssigmentStatement(String variableName, Expression value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.variableName = variableName;
		this.value = value;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	public void setShip(Ship ship) {
		value.setShip(ship);
	}

}
