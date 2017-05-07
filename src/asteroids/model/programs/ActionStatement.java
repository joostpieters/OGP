package asteroids.model.programs;

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
	
	public void setFunction(Function function) throws IllegalArgumentException {
		throw new IllegalArgumentException("Action statements can't be contained in a function body");
	}
}