package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class GetDirectionExpression extends Expression {

	public GetDirectionExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
	}

	@Override
	public Double evaluate() {
		//TODO
		return ship.getDirection();
	}

}
