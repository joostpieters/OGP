package asteroids.model.programs;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class SequenceStatement extends Statement {

	private List<Statement> statements;
	private int executedStatements = 0;
	private boolean hasActiveBreakStatement;

	public SequenceStatement(List<Statement> statements,
			SourceLocation sourceLocation) {
		super(sourceLocation);
		this.statements = statements;
	}

	@Override
	public boolean execute() {
		if(executedStatements == 0 || hasActiveBreakStatement()) hasActiveBreakStatement = false;
		boolean ended = statements.get(executedStatements).execute();
		if (statements.get(executedStatements) instanceof BreakStatement) {
			executedStatements = 0;
			hasActiveBreakStatement = true;
		}
		if (ended) executedStatements++;
		if (executedStatements == statements.size()){
			executedStatements = 0;
			return true;
		} else return false;
	}
	
	public void setProgram(Program program) {
		super.setProgram(program);
		for(Statement statement : statements) statement.setProgram(program);
	}

	public String toString() {
		return "[SequenceStatement: " + statements.toString() + "]";
	}

	public boolean hasActiveBreakStatement() {
		return hasActiveBreakStatement;
	}

}
