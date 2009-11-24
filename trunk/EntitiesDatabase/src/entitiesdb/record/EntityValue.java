package entitiesdb.record;

import com.sleepycat.persist.model.Persistent;

@Persistent
public class EntityValue extends Value implements ValueInterface {

	public EntityValue(){
		super();
	}
	
	public EntityValue(String s) {
		super(s);
	}

	@Override
	public ValueType getType() {
		return ValueType.ENTITY;
	}

}
