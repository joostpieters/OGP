package asteroids.model.programs;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class SequenceStatement extends Statement {

	private List<Statement> statements;
	private boolean hasActiveBreakStatement;
	private boolean advancedTime;

	public SequenceStatement(List<Statement> statements,
			SourceLocation sourceLocation) {
		super(sourceLocation);
		this.statements = statements;
	}

	@Override
	public void execute() {
		advancedTime = false;
		hasActiveBreakStatement = false;
		double line = getProgram().getCurrentLine();
		for(Statement statement: statements) {
			if(statement.getSourceLocation().getLine()> line){
				statement.execute();
			}
			if(statement.advancedTime()){
				advancedTime = true;
				return;
			}
			if(statement instanceof BreakStatement) {
				hasActiveBreakStatement = true;
				return;
			}
		}
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		for(Statement statement : statements) statement.setProgram(program);
	}

	@Override
	public boolean advancedTime(){
		return advancedTime;
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
