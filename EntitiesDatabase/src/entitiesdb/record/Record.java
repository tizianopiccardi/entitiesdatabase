package entitiesdb.record;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;

@Entity
public class Record {
	
	@PrimaryKey(sequence="IdRecord")
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	private String entityId;
	private String name;
	private Value value;

	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}

	public String toString() {
		
		return "Entity: " + entityId + " | Name: " + name + " | Value: " + value.getValue() + " | ValueType: "+ value.getType();
		
	}


}
