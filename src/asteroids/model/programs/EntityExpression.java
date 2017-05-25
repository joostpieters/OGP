package asteroids.model.programs;

import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public abstract class EntityExpression extends ProgramElement implements Expression<Entity> {

	public EntityExpression(SourceLocation location){
		//TODO
		super(location);
	}
	
	@Override
	public void setProgram(Program program) {
		 super.setProgram(program);
	}
	
	@Override
	public Entity evaluate(Object[] actualArgs, Set<Variable> localVariables){
		return evaluate();
	}

}
