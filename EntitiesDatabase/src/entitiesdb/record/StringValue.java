package entitiesdb.record;

import com.sleepycat.persist.model.Persistent;

@Persistent
public class StringValue extends Value implements ValueInterface {

	public StringValue() {
		super();
	}
	
	public StringValue(String s) {
		super(s);
	}

	@Override
	public ValueType getType() {
		return ValueType.ATOM;
	}
	
	
}
