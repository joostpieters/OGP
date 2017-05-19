package asteroids.model.programs;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement {

	private Expression value;

	public PrintStatement(Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}
	
	@Override
	public void execute() {
		Object evaluatedValue = value.evaluate();
		if (evaluatedValue == null) System.out.println("null");
		else System.out.println(evaluatedValue.toString());
		getProgram().addResult(evaluatedValue);
	}
	
	@Override
	public Optional execute(List<Expression> actualArgs, Set<Variable> localVariables) {
		throw new IllegalArgumentException("Print statements can't occur in the body of functions.");
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		value.setProgram(program);
	}

	@Override
	public String toString(){
		return "[PrintStatement: " + value.toString() + "]";
	}
}