package entitiesdb.record;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import entitiesdb.record.Value.ValueType;




@Entity
public class Record {
	
	@PrimaryKey(sequence="IdRecord")
	private long id;

	@SecondaryKey(name="Entity", relate=Relationship.MANY_TO_ONE)
	private String entityId;
	@SecondaryKey(name="Attribute", relate=Relationship.MANY_TO_ONE)
	private String attribute;
	@SecondaryKey(name="Value", relate=Relationship.MANY_TO_ONE)
	private Value value;

	
	
	public Record() {
		
	}
	
	public Record(String e, String a, String v, ValueType t) {
		this.setEntityId(e);
		this.setAttribute(a);
		this.setValue(new Value(v, t));
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public final String getAttribute() {
		return attribute;
	}

	public final void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	
	
	public String toString() {
		
		return "ID: " + id+ " | Entity: " + entityId + " | Attribute: " + attribute + 
				" | Value: " + value.getValue() + " | ValueType: "+ value.getType();
		
	}


}
