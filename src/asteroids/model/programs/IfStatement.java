package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class IfStatement extends Statement {
	private Expression condition;
	private Statement ifBody;
	private Statement elseBody;
	private boolean executingIfBody = false;
	private boolean executingElseBody = false;
	boolean advancedTime;

	public IfStatement(Expression condition, Statement ifBody,
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
		advancedTime = false;
		if(!executingIfBody && !executingElseBody){
			if ((boolean) condition.evaluate()) executingIfBody = true;
			else executingElseBody = true;
		}
		if(executingIfBody) {
			ifBody.execute();
			if (ifBody.advancedTime()) {
				advancedTime = true;
				return;
			} else executingIfBody = false;
		}
		if(executingElseBody) {
			elseBody.execute();
			if (elseBody.advancedTime()) {
				advancedTime = true;
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
		elseBody.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[IfStatement: if " + condition + " then " + ifBody + " else " + elseBody + "]";
	}

}
