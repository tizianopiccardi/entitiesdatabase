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
	private EntityId entityId = null;
	@SecondaryKey(name="Attribute", relate=Relationship.MANY_TO_ONE)
	private Attribute attribute = null;
	@SecondaryKey(name="Value", relate=Relationship.MANY_TO_ONE)
	private Value value = null;

	
	
	public Record() {
		
	}
	
	public Record(String e, String a, String v, ValueType t) {
		this.setEntityId(new EntityId(e));
		this.setAttribute(new Attribute(a));
		this.setValue(new Value(v, t));
	}
	
	public Record(EntityId e, Attribute a, Value v) {
		this.setEntityId(e);
		this.setAttribute(a);
		this.setValue(v);
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	


	public final EntityId getEntityId() {
		return entityId;
	}

	public final void setEntityId(EntityId entityId) {
		this.entityId = entityId;
	}


	
	
	public final Attribute getAttribute() {
		return attribute;
	}

	public final void setAttribute(Attribute attribute) {
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

	
	public boolean equals(Object o) {
		Record r = (Record)o;
		return r.getEntityId().equals(this.getEntityId()) &&
				r.getAttribute().equals(this.getAttribute()) &&
				r.getValue().equals(this.getValue());
	}

}
