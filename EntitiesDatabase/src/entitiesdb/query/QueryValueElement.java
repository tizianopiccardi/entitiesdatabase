package entitiesdb.query;

public class QueryValueElement  implements QueryElement{

	Object value;
	QueryElementTypes type;

	public QueryValueElement(Object o, QueryElementTypes t) {
		this.value = o;
		this.type = t;
	}

	


	public Object getValue() {
		return value;
	}




	public void setValue(Object value) {
		this.value = value;
	}




	public QueryElementTypes getType() {
		return type;
	}




	public void setType(QueryElementTypes type) {
		this.type = type;
	}




	public String toString() {
		return value.toString() + " (" + type + ")";
	}
}
