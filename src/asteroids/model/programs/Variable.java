package asteroids.model.programs;

import asteroids.model.Entity;

public class Variable {
	
	private String variableName;
	private Object value;

	public Variable(String variableName, Object value){
		this.variableName = variableName;
		this.setValue(value);
	}
	
	public void setValue(Object value) {
		if(!canHaveAsValue(value)) throw new IllegalArgumentException();
		this.value = value;
	}
	
	private boolean canHaveAsValue(Object value) {
		return getValue()== null || (value instanceof Boolean && getValue() instanceof Boolean)
			||(value instanceof Double && getValue() instanceof Double)
			||(value instanceof Entity && getValue() instanceof Entity);
	}
	
	public Object getValue(){
		return value;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return variableName;
	}
	
	public String toString(){
		return "[Variable: " + variableName + ", " + value + "]";
	}

}
