package entitiesdb.query;

public class QueryProperty {

	QueryElement attribute;
	QueryElement value;
	
	public QueryProperty(QueryElement a, QueryElement v) {
		attribute = a;
		value = v;
	}

	public QueryProperty(Object a, Object v) {
		// TODO Auto-generated constructor stub
		attribute =(QueryElement)a;
		value = (QueryElement)v;
	}
	
	public String toString() {
		return "[" + attribute + ":" + value + "]";
	}

	public QueryElement getAttribute() {
		return attribute;
	}

	public void setAttribute(QueryElement attribute) {
		this.attribute = attribute;
	}

	public QueryElement getValue() {
		return value;
	}

	public void setValue(QueryElement value) {
		this.value = value;
	}
	
	
	
	
	
	
	
}
