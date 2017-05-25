package asteroids.model.programs;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Function {
	private String name;
	private Statement body;
	private SourceLocation sourceLocation;
	private Program program;
	private boolean hasActiveBreakStatement;

	public Function(String functionName, Statement body,
			SourceLocation sourceLocation) {
		this.name = functionName;
		this.body = body;
		this.sourceLocation = sourceLocation;
	}
	
	public Object evaluate(Object[] actualArgs){
		setHasActiveBreakStatement(false);
		Set<Variable> localVariables = new HashSet<Variable>();
		try{
			Optional result = body.execute(actualArgs,localVariables);
			if (body.hasActiveBreakStatement()) {
				setHasActiveBreakStatement(true);
				return null;
			}
			else setHasActiveBreakStatement(false);
			return result.get();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Functions need a return statement");
		}
	}

	private void setHasActiveBreakStatement(boolean b) {
		hasActiveBreakStatement = b;
	}

	public void setProgram(Program program) {
		this.program = program;
		body.setProgram(program);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public boolean hasActiveBreakStatement() {
		// TODO Auto-generated method stub
		return hasActiveBreakStatement;
	}

}
