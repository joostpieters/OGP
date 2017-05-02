package asteroids.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import asteroids.model.programs.Expression;
import asteroids.model.programs.Function;
import asteroids.model.programs.Statement;
import asteroids.model.programs.Variable;
import asteroids.part3.programs.SourceLocation;

public class Program {

	private List<Function> functions;
	private Statement main;
	private Set<Variable> variables;
	private List<Object> results = new ArrayList<Object>();
	private double timeLeftToExecute;
	private double currentLine;
	private Ship ship;

	public Program(List<Function> functions, Statement main) {
		this.functions = functions;
		this.main = main;
		main.setProgram(this);
		timeLeftToExecute = 0;
		System.out.println(main.toString());
	}
	
	public List<Object> execute(double dt) {
		timeLeftToExecute = timeLeftToExecute + dt;
		main.execute();
		if (main.failedToAdvanceTime()) {
			return null;
		}
		currentLine = 0;
		return results;
	}

	public void setShip(Ship ship) {
		// TODO Auto-generated method stub
		if(ship.getProgram() != this) throw new IllegalArgumentException();
		this.ship = ship;
	}

	public Ship getShip() {
		// TODO Auto-generated method stub
		return ship;
	}

	public List<Object> getResults() {
		return results;
	}

	public void assignVariable(String variableName, Expression value) {
		Optional<Variable> variableToAssignTo = variables.stream().filter(variable -> variable.getName().equals(variableName)).findFirst();
		if(variableToAssignTo.isPresent()) variableToAssignTo.get().setValue(value);
		else variables.add(new Variable(variableName, value.evaluate()));
	}

	public Object getVariable(String parameterName) throws NoSuchElementException {
		// TODO Auto-generated method stub
		return variables.stream().filter(variable -> variable.getName().equals(parameterName)).findFirst().get();
	}

	public void advanceTimer() {
		timeLeftToExecute -= 0.2;
	}

	public double getCurrentLine() {
		return currentLine;
	}

	public void setCurrentLine(double currentLine) {
		this.currentLine = currentLine;
	}

	public double getTimer() {
		// TODO Auto-generated method stub
		return timeLeftToExecute;
	}

	public void addResult(Object result) {
		results.add(result);
	}

}
