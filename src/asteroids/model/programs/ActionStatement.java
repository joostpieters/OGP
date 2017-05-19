package asteroids.model.programs;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import asteroids.part3.programs.SourceLocation;

public abstract class ActionStatement extends Statement {

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
	public Optional execute(List<Expression> actualArgs, Set<Variable> localVariables){
		throw new IllegalArgumentException("Action statements cannot occur in function bodies");
	}
}