package asteroids.model.programs;

import java.util.List;

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

}
