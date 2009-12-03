package entitiesdb.query;

public class QueryAttributeElement implements QueryElement {

	Object attribute;
	QueryElementTypes type;

	public QueryAttributeElement(Object o, QueryElementTypes t) {
		this.attribute = o;
		this.type = t;
	}




	public Object getAttribute() {
		return attribute;
	}




	public void setAttribute(Object attribute) {
		this.attribute = attribute;
	}




	public QueryElementTypes getType() {
		return type;
	}




	public void setType(QueryElementTypes type) {
		this.type = type;
	}




	public String toString() {
		return attribute.toString() + " (" + type + ")";
	}
}
