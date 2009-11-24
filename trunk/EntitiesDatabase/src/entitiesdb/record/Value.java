package entitiesdb.record;

import com.sleepycat.persist.model.Persistent;

import entitiesdb.record.ValueInterface.ValueType;

@Persistent
public class Value {


	public String value;

	public Value() {
		
	}
	
	public Value(String s) {
		value = s;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public ValueType getType() {
		return ValueType.NOTYPE;
	}
	
}
