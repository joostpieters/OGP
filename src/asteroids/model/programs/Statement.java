package asteroids.model.programs;

import java.util.Optional;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public interface Statement {

	void execute();

	Optional execute(Object[] actualArgs, Set<Variable> localVariables) ;

	boolean hasActiveBreakStatement();

	boolean failedToAdvanceTime();
	
	void setProgram(Program program);
	
	Program getProgram();

	SourceLocation getSourceLocation();
}
