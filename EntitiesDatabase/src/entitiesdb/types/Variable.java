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
	
	
	
	
	
/*
	Object value = null;
	String name;
	boolean bounded = false;
	
	public Variable() {
		
	}
	
	public Variable(String name) {
		this.name = name;
	}
	
	public final Object getValue() {
		return value;
	}

	public final void setValue(Object value) {
		this.value = value;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final boolean isBounded() {
		return bounded;
	}

	public final void setBounded(boolean bounded) {
		this.bounded = bounded;
	}
*/
	@Override
	public String toString() {
		return name.toString();
	}


	
}
