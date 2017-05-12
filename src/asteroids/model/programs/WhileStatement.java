package asteroids.model.programs;

import java.util.List;
import java.util.Optional;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends Statement {
	private Expression<Boolean> condition;
	private Statement body;
	private boolean executingBody;
	private boolean failedToAdvanceTime;

	public WhileStatement(Expression<Boolean> condition, Statement body,
			SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		failedToAdvanceTime = false;
		if(!executingBody){
			if(condition.evaluate()) executingBody = true;
			else return;
		}
		body.execute();
		if (body.failedToAdvanceTime()) {
			failedToAdvanceTime = true;
			return;
		}
		
		while (condition.evaluate() && !body.hasActiveBreakStatement()){
			getProgram().setCurrentLocation(this.getSourceLocation());
			body.execute();
			if (body.failedToAdvanceTime()) {
				failedToAdvanceTime = true;
				return;
			}
		}
		executingBody = false;
	}
	
	@Override
	public Optional execute(List<Expression> actualArgs) {
		// TODO Auto-generated method stub
		failedToAdvanceTime = false;
		if(!executingBody){
			if(condition.evaluate(actualArgs)) executingBody = true;
			else return Optional.empty();
		}
		Optional result = body.execute(actualArgs);
		if (result.isPresent()) return result;
		while (condition.evaluate(actualArgs) && !body.hasActiveBreakStatement()){
			getProgram().setCurrentLocation(this.getSourceLocation());
			result = body.execute(actualArgs);
			if(result.isPresent()) return result;
		}
		executingBody = false;
		return Optional.empty();
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		condition.setProgram(program);
		body.setProgram(program);
	}
	
	@Override
	public boolean failedToAdvanceTime() {
		return failedToAdvanceTime;
	}
	
	@Override
	public String toString() {
		return "[WhileStatement: while " + condition + " do " + body + "]";
	}

}
