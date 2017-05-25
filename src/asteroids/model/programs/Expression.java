package asteroids.model.programs;

import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public interface Expression<T> {
	T evaluate() throws IllegalArgumentException;
	
	T evaluate(Object[] actualArgs, Set<Variable> localVariables) throws IllegalArgumentException;
	
	void setProgram(Program program);
	
	Program getProgram();
}
