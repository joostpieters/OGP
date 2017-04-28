package asteroids.model.programs;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class GetYExpression extends Expression {
	Expression e;
	
	public GetYExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e = e;
	}

	@Override
	public Double evaluate() {
		Object eEvaluated = e.evaluate();
		if(!(eEvaluated instanceof Entity)) throw new IllegalArgumentException();
		return ((Entity)eEvaluated).getPosition()[1];
	}

}
