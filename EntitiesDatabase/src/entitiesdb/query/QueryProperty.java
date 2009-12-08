package entitiesdb.query;

public class QueryProperty {

	Object attribute;
	Object value;
	

	public QueryProperty(Object a, Object v) {
		// TODO Auto-generated constructor stub
		attribute = a;
		value = v;
	}

	public Object getAttribute() {
		return attribute;
	}

	public void setAttribute(Object attribute) {
		this.attribute = attribute;
	}


	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}

	public String toString() {
		return "[" + attribute + ":" + value + "]";
	}

	
}
