package asteroids.model.programs;

public class Variable {
	
	private String variableName;
	private Object value;

	public Variable(String variableName, Object value){
		this.variableName = variableName;
		this.setValue(value);
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public Object getValue(){
		return value;
	}

	public Object getName() {
		// TODO Auto-generated method stub
		return variableName;
	}

}
