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
	
	public void addProperties(StatementProperty property) {
		this.properties.add(property);
	}
	
	public String toString() {
		return entity.toString() + " | " + properties.toString();
	}
	
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return equals((StatementBody)obj);
	}
	
	public boolean equals(StatementBody obj) {
		
		if (obj.properties.size()!=this.properties.size())
			return false;
		
		boolean out = (obj.entity.equals(this.entity));
		
		for(int i = 0 ; i < obj.properties.size() ; i++) 
			out&=obj.properties.get(i).equals(this.properties.get(i));
		
		return out;
	}	
	
}
