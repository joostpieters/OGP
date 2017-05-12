package asteroids.model.programs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	private Set<Variable> variables = new HashSet<Variable>();
	private List<Expression> actualArgs = new ArrayList<Expression>();

	public Function(String functionName, Statement body,
			SourceLocation sourceLocation) {
		this.name = functionName;
		this.body = body;
		this.sourceLocation = sourceLocation;
	}
	
	public Object evaluate(List<Expression> actualArgs){
		variables = new HashSet<Variable>();
		this.actualArgs = actualArgs;
		try{
			return body.execute(actualArgs).get();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Functions need a return statement");
		}
		
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

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
