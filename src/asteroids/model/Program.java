package asteroids.model;

import java.util.ArrayList;
import java.util.HashSet;
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
	private Set<Variable> variables = new HashSet<Variable>();
	private List<Object> results = new ArrayList<Object>();
	private double timeLeftToExecute;
	private SourceLocation currentLocation = new SourceLocation(0, 0);
	private Ship ship;

	public Program(List<Function> functions, Statement main) {
		this.functions = functions;
		this.main = main;
		main.setProgram(this);
		for(Function function: functions) function.setProgram(this);
		timeLeftToExecute = 0;
		System.out.println(main.toString());
	}
	
	public List<Object> execute(double dt) throws IllegalArgumentException {
		timeLeftToExecute = timeLeftToExecute + dt;
		main.execute();
		if (!main.failedToAdvanceTime()) {
			if(main.hasActiveBreakStatement()) throw new IllegalArgumentException("Break statements cannot occur outside function bodies");
			currentLocation = new SourceLocation(0, 0);
			List<Object> resultsToThrow = results; 
			results = null;
			return resultsToThrow;
		}
		return null;
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

	public Variable getVariable(String variableName) throws NoSuchElementException {
		return getVariables().stream().filter(variable -> variable.getName().equals(variableName)).findFirst().get();
	}
	
	public void addVariable(Variable variable){
		variables.add(variable);
	}

	public void advanceTimer() {
		timeLeftToExecute -= 0.2;
	}

	public double getTimer() {
		// TODO Auto-generated method stub
		return timeLeftToExecute;
	}

	public void addResult(Object result) {
		results.add(result);
	}

	public SourceLocation getCurrentLocation() {
		// TODO Auto-generated method stub
		return currentLocation;
	}

	public void setCurrentLocation(SourceLocation currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Set<Variable> getVariables() {
		return new HashSet<Variable>(variables);
	}

	public Function getFunction(String functionName) throws NoSuchElementException {
		// TODO Auto-generated method stub
		return this.functions.stream().filter(function -> function.getName().equals(functionName)).reduce((first, second) -> second).get();
	}

}
