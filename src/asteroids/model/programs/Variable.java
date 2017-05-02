package asteroids.model.programs;

public class Variable<T> {
	
	private String variableName;
	private T value;

	public Variable(String variableName, T value){
		this.variableName = variableName;
		this.setValue(value);
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public T getValue(){
		return value;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return variableName;
	}

}
