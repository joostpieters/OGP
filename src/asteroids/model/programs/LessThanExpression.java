package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class LessThanExpression extends Expression {
	Expression e1;
	Expression e2;

	public LessThanExpression(Expression e1, Expression e2,
			SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public Boolean evaluate() {
		Object e1Evaluated = e1.evaluate();Object e2Evaluated = e2.evaluate();
		if(!(e1Evaluated instanceof Double && e2Evaluated instanceof Double)) throw new IllegalArgumentException();
		return ((Double)e1Evaluated < (Double)e2Evaluated);
	}

}
