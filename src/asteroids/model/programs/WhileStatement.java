package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends Statement {
	Expression condition;
	Statement body;
	boolean executingBody;
	boolean advancedTime;

	public WhileStatement(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		advancedTime = false;
		if(!executingBody){
			if((boolean) condition.evaluate()) executingBody = true;
			else return;
		}
		if (body.hasActiveBreakStatement()) {
			executingBody = false;
			return;
		}
		body.execute();
		if (body.advancedTime()){
			advancedTime = true;
		} else executingBody = false;
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		condition.setProgram(program);
		body.setProgram(program);
	}
	
	@Override
	public boolean advancedTime() {
		return advancedTime;
	}
	
	@Override
	public String toString() {
		return "[WhileStatement: while " + condition + " do " + body + "]";
	}

}
