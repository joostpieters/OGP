package asteroids.model.programs;

import java.util.List;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class SequenceStatement extends Statement {

	private List<Statement> statements;

	public SequenceStatement(List<Statement> statements,
			SourceLocation sourceLocation) {
		super(sourceLocation);
		this.statements = statements;
	}

	@Override
	public void execute() {
		
		
	}
	
	public String toString() {
		return "[SequenceStatement: " + statements.toString() + "]";
	}
	
	public void setShip(Ship ship) {
		for(Statement statement : statements) statement.setShip(ship);
	}

}
