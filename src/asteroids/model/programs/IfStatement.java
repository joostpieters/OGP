package asteroids.model.programs;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class IfStatement extends Statement {
	private Expression<? extends Boolean> condition;
	private Statement ifBody;
	private Statement elseBody;
	private boolean executingIfBody;
	private boolean executingElseBody;
	private boolean failedToAdvanceTime;
	private boolean hasActiveBreakStatement;

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
		setHasActiveBreakStatement(false);
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
			} else {
				if(ifBody.hasActiveBreakStatement()) setHasActiveBreakStatement(true);
				else setHasActiveBreakStatement(false);
				executingIfBody = false;
			}
		}
		if(executingElseBody) {
			elseBody.execute();
			if (elseBody.failedToAdvanceTime()) {
				setFailedToAdvanceTime(true);
				return;
			} else {
				if(elseBody.hasActiveBreakStatement()) setHasActiveBreakStatement(true);
				else setHasActiveBreakStatement(false);
				executingElseBody = false;
			}
		}
		return;
	}

	@Override
	public Optional execute(List<Expression> actualArgs, Set<Variable> localVariables) {
		setHasActiveBreakStatement(false);
		failedToAdvanceTime = false;
		if(!executingIfBody && !executingElseBody){
			if (condition.evaluate(actualArgs, localVariables)) executingIfBody = true;
			else {
				if (elseBody == null) return Optional.empty();
				executingElseBody = true;
			}
		}
		if(executingIfBody) {
			Optional result = ifBody.execute(actualArgs, localVariables);
			if(ifBody.hasActiveBreakStatement()) setHasActiveBreakStatement(true);
			else setHasActiveBreakStatement(false);
			return result;
		}
		if(executingElseBody) {
			Optional result = elseBody.execute(actualArgs, localVariables);
			if(elseBody.hasActiveBreakStatement()) setHasActiveBreakStatement(true);
			else setHasActiveBreakStatement(false);
			return result;
		}
		return Optional.empty();
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
	public String toString() {
		return "[IfStatement: if " + condition + " then " + ifBody + " else " + elseBody + "]";
	}

	@Override
	public boolean hasActiveBreakStatement() {
		return hasActiveBreakStatement;
	}

	public void setHasActiveBreakStatement(boolean hasActiveBreakStatement) {
		this.hasActiveBreakStatement = hasActiveBreakStatement;
	}

}
