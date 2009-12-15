package entitiesdb.types;

import com.sleepycat.persist.model.*;


@Entity
public class EntityId {
	
	@PrimaryKey
	private String id;

	public EntityId() {
		
	}
	
	public EntityId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String toString() {
		return id;
	}

}
