package entitiesdb.types;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

@Entity
public class Record {
	
	@PrimaryKey(sequence="IdRecord")
	private long id;

	@SecondaryKey(name="Entity", relate=Relationship.MANY_TO_ONE)
	private String entityId = null;
	@SecondaryKey(name="Attribute", relate=Relationship.MANY_TO_ONE)
	private String attribute = null;
	@SecondaryKey(name="Value", relate=Relationship.MANY_TO_ONE)
	private String value = null;

	
	
	public Record() {
		
	}
	
	public Record(String e, String a, String v) {
		entityId = e;
		attribute = a;
		value = v;
	}
	

	
	
	
	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	


	
	public String toString() {
		return "ID: " + id+ (this.attribute.equals("") ? " definition of " +entityId : " | Entity: " + entityId + " | Attribute: " + attribute + 
				" | Value: " + value);
	}

	
	public boolean equals(Object o) {
		Record r = (Record)o;
		return r.getEntityId().equals(this.getEntityId()) &&
				r.getAttribute().equals(this.getAttribute()) &&
				r.getValue().equals(this.getValue());
	}

}
