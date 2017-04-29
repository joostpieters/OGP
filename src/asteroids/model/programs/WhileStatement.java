package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends Statement {
	Expression condition;
	Statement body;
	boolean executingBody;

	public WhileStatement(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.condition = condition;
		this.body = body;
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		if(!executingBody){
			if((boolean) condition.evaluate()) executingBody = true;
			else return true;
		}
		if (body.hasActiveBreakStatement()) {
			executingBody = false;
			return true;
		}
		boolean ended = body.execute();
		if (ended) {
			executingBody = false;
			return false;
		}
		else return false;
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		condition.setProgram(program);
		body.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[WhileStatement: while " + condition + " do " + body + "]";
	}

}
