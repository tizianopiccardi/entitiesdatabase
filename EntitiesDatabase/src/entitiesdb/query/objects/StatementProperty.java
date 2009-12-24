package entitiesdb.query.objects;



public class StatementProperty {

	Object attribute;
	Object value;
	
	public StatementProperty() {
	}

	public StatementProperty(Object a, Object v) {
		// TODO Auto-generated constructor stub
		attribute = a;
		value = v;
	}

	public String getAttribute() {
		if (attribute instanceof String)
			return attribute.toString();
		return null;
	}
	
	public Object getAttributeObject() {
		return attribute;
	}

	public void setAttribute(Object attribute) {
		this.attribute = attribute;
	}


	public String getValue() {
		if (value instanceof String)
			return value.toString();
		return null;
	}
	
	public Object getValueObject() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}

	public String toString() {
		return "" + attribute + ":" + value + "";
	}


}


