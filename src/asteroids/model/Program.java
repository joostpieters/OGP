package asteroids.model;

import java.util.List;

import asteroids.model.programs.Function;
import asteroids.model.programs.Statement;

public class Program {

	private List<Function> functions;
	private Statement main;
	private List<Statement> variables;

	public Program(List<Function> functions, Statement main) {
		this.functions = functions;
		this.main = main;
	}

}
