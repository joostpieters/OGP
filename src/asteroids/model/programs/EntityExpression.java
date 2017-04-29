package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public abstract class EntityExpression extends Expression {

	public EntityExpression(SourceLocation location){
		//TODO
		super(location);
	}
	
	public void setProgram(Program program) {
		 super.setProgram(program);
	}

}
