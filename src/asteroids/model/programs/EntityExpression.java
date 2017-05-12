package asteroids.model.programs;

import java.util.List;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public abstract class EntityExpression extends Expression<Entity> {

	public EntityExpression(SourceLocation location){
		//TODO
		super(location);
	}
	
	public void setProgram(Program program) {
		 super.setProgram(program);
	}
	
	public Entity evaluate(List<Expression> actualArgs){
		return evaluate();
	}

}
