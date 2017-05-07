package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class IfStatement extends Statement {
	private Expression<? extends Boolean> condition;
	private Statement ifBody;
	private Statement elseBody;
	private boolean executingIfBody;
	private boolean executingElseBody;
	private boolean failedToAdvanceTime;

	public IfStatement(Expression<? extends Boolean> condition, Statement ifBody,
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
				setFailedToAdvanceTime(true);
				return;
			} else executingIfBody = false;
		}
		if(executingElseBody) {
			elseBody.execute();
			if (elseBody.failedToAdvanceTime()) {
				setFailedToAdvanceTime(true);
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
	public boolean failedToAdvanceTime() {
		return failedToAdvanceTime;
	}

	private void setFailedToAdvanceTime(boolean b) {
		failedToAdvanceTime = b;
	}

	@Override
	public void setFunction(Function function) throws IllegalArgumentException {
		condition.setFunction(function);
		ifBody.setFunction(function);
		if(elseBody != null) elseBody.setFunction(function);
	}

	@Override
	public String toString() {
		return "[IfStatement: if " + condition + " then " + ifBody + " else " + elseBody + "]";
	}

}
