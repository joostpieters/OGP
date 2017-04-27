package asteroids.model;

import java.util.ArrayList;
import java.util.List;

import asteroids.model.programs.Function;
import asteroids.model.programs.Statement;
import asteroids.part3.programs.SourceLocation;

public class Program {

	private List<Function> functions;
	private Statement main;
	private List<Statement> variables;
	private double timeLeftToExecute;
	private SourceLocation currentLocation;

	public Program(List<Function> functions, Statement main) {
		this.functions = functions;
		this.main = main;
		timeLeftToExecute = 0;
	}
	
	public List<Object> execute(double dt) {
		timeLeftToExecute = timeLeftToExecute + dt;
		List<Object> results = new ArrayList<Object>();
		while(timeLeftToExecute > 0.2){
			main.execute();
		}
		return results;
		
	}

}
