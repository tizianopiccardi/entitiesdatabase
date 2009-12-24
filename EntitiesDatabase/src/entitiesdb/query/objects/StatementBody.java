package entitiesdb.query.objects;

import java.util.ArrayList;


public class StatementBody {

	public Object entity;
	
	public ArrayList<StatementProperty> properties = new ArrayList<StatementProperty>();
	
	public StatementBody(Object e) {
		entity = e;
	}
	
	public StatementBody() {
	}
	
	public StatementBody(Object e, ArrayList<StatementProperty> p) {
		entity = e;
		properties = p;
	}

	public String getEntity() {
		if (entity instanceof String)
			return entity.toString();
		return null;
	}
	
	public Object getEntityObject() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public ArrayList<StatementProperty> getProperties() {
		return properties;
	}

	public void setProperties(ArrayList<StatementProperty> properties) {
		this.properties = properties;
	}
	
	public String toString() {
		return entity.toString() + " | " + properties.toString();
	}
	
	
}
