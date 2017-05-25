package asteroids.model.programs;

import java.util.Optional;
import java.util.Set;

import asteroids.part3.programs.SourceLocation;

public abstract class ActionStatement extends ProgramElement implements Statement {

	private boolean failedToAdvanceTime;

	public ActionStatement(SourceLocation location) {
		super(location);
	}

	protected void setFailedToAdvanceTime(boolean b) {
		this.failedToAdvanceTime = b;
		
	}
	
	public boolean failedToAdvanceTime(){
		return failedToAdvanceTime;
	}

	@Override
	public boolean hasActiveBreakStatement() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Optional execute(Object[] actualArgs, Set<Variable> localVariables){
		throw new IllegalArgumentException("Action statements cannot occur in function bodies");
	}
}