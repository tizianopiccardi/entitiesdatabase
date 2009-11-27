package entitiesdb.record;

public class Variable {

	String value = null;
	
	@Override
	public String toString() {
		return value;
	}

	public final String getValue() {
		return value;
	}

	public final void setValue(String value) {
		this.value = value;
	}
	
}
