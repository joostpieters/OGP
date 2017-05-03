package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class IfStatement extends Statement {
	private Expression<Boolean> condition;
	private Statement ifBody;
	private Statement elseBody;
	private boolean executingIfBody;
	private boolean executingElseBody;
	private boolean failedToAdvanceTime;

	public IfStatement(Expression<Boolean> condition, Statement ifBody,
			Statement elseBody, SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		failedToAdvanceTime = false;
		if(!executingIfBody && !executingElseBody){
			if (condition.evaluate()) executingIfBody = true;
			else {
				if (elseBody == null) return;
				executingElseBody = true;
			}
		}
		if(executingIfBody) {
			ifBody.execute();
			if (ifBody.failedToAdvanceTime()) {
				failedToAdvanceTime = true;
				return;
			} else executingIfBody = false;
		}
		if(executingElseBody) {
			elseBody.execute();
			if (elseBody.failedToAdvanceTime()) {
				failedToAdvanceTime = true;
				return;
			} else executingElseBody = false;
		}
		return;
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		condition.setProgram(program);
		ifBody.setProgram(program);
		if(elseBody != null) elseBody.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[IfStatement: if " + condition + " then " + ifBody + " else " + elseBody + "]";
	}

	@Override
	public boolean failedToAdvanceTime() {
		return failedToAdvanceTime;
	}

	private void setFailedToAdvanceTime(boolean b) {
		failedToAdvanceTime = b;
	}

}
