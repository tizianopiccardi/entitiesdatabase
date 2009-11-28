package entitiesdb.record;

public class Variable {

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

	@Override
	public String toString() {
		return (value == null) ? null : value.toString();
	}


	
}
