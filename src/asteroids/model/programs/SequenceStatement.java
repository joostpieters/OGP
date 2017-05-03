package asteroids.model.programs;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class SequenceStatement extends Statement {

	private List<Statement> statements;
	private boolean hasActiveBreakStatement;
	private boolean failedToAdvanceTime;

	public SequenceStatement(List<Statement> statements,
			SourceLocation sourceLocation) {
		super(sourceLocation);
		this.statements = statements;
	}

	@Override
	public void execute() {
		failedToAdvanceTime = false;
		hasActiveBreakStatement = false;
		SourceLocation location = getProgram().getCurrentLocation();
		for(Statement statement: statements) {
			SourceLocation statementLocation = statement.getSourceLocation();
			if(statementLocation.getLine()> location.getLine()||(statementLocation.getLine()==location.getLine()&&statementLocation.getColumn()>=location.getColumn())){
				statement.execute();
				if(statement.failedToAdvanceTime()){
					failedToAdvanceTime = true;
					return;
				}
				if(statement instanceof BreakStatement) {
					hasActiveBreakStatement = true;
					return;
				}
			}
			
		}
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		for(Statement statement : statements) statement.setProgram(program);
	}

	@Override
	public boolean failedToAdvanceTime(){
		return failedToAdvanceTime;
	}

	@Override
	public boolean hasActiveBreakStatement() {
		return hasActiveBreakStatement;
	}

	@Override
	public String toString() {
		return "[SequenceStatement: " + statements.toString() + "]";
	}

}
