package entitiesdb.query;

public class QueryEntityElement implements QueryElement {

	Object entity;
	QueryElementTypes type;

	public QueryEntityElement(Object o, QueryElementTypes t) {
		this.entity = o;
		this.type = t;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public QueryElementTypes getType() {
		return type;
	}

	public void setType(QueryElementTypes type) {
		this.type = type;
	}


	public String toString() {
		return entity.toString() + " (" + type + ")";
	}
	

	
	
}
