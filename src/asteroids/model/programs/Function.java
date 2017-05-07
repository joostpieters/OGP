package asteroids.model.programs;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Function {
	private String name;
	private Statement body;
	private SourceLocation sourceLocation;
	private Program program;
	private Set<Variable> variables = new HashSet<Variable>();

	public Function(String functionName, Statement body,
			SourceLocation sourceLocation) {
		this.name = functionName;
		this.body = body;
		body.setFunction(this);
		this.sourceLocation = sourceLocation;
	}
	
	public Object evaluate(List<Expression> actualArgs){
		variables = new HashSet<Variable>();
		body.execute();
		return null;
	}

	public void setProgram(Program program) {
		this.program = program;
		body.setProgram(program);
	}
	
	public Set<Variable> getVariables() {
		return new HashSet<Variable>(variables);
	}
	
	public void addVariable(Variable variable) {
		variables.add(variable);
	}

	public Object getVariable(String variableName) throws NoSuchElementException {
		// TODO Auto-generated method stub
		return getVariables().stream().filter(variable -> variable.getName().equals(variableName)).findFirst().get().getValue();
	}

}
