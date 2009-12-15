package entitiesdb.types;

public class Variable {
	
	String name;
	
	public Variable(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	@Override
	public String toString() {
		return name.toString();
	}


	
}
