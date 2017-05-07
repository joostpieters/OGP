package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class GetDirectionExpression extends Expression<Double> {

	public GetDirectionExpression(SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
	}

	@Override
	public Double evaluate() {
		//TODO
		return getProgram().getShip().getOrientation();
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
	}
	
	public String toString() {
		return "[GetDirectionExpression]";
	}

	@Override
	public void setFunction(Function function) {
		// TODO Auto-generated method stub
		
	}

}
