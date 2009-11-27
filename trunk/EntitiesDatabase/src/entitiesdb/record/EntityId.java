package entitiesdb.record;

import com.sleepycat.persist.model.KeyField;
import com.sleepycat.persist.model.Persistent;

@Persistent
public class EntityId {

	@KeyField(1)
	public String id;
	
	public EntityId() {
		
	}
	
	public EntityId(String id) {
		this.id = id;
	}

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}
	
	public String toString() {
		return id;
	}
	
	
}
