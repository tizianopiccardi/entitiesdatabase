package entitiesdb.record;

import com.sleepycat.persist.model.KeyField;
import com.sleepycat.persist.model.Persistent;

@Persistent
public class Value {

	@KeyField(1)
	public String value;
	@KeyField(2)
	private ValueType type;

	public Value() {
		
	}
	
	public Value(String s, ValueType valueType) {
		value = s;
		type = valueType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public ValueType getType() {
		return type;
	}
	
	public static enum ValueType {
		NOTYPE, ENTITY, ATOM, VARIABLE;
	};
	
	public String toString() {
		return value + "("+type.toString()+")";
	}
	
	public boolean equals(Object o) {
		Value v = (Value)o;
		return v.getType() == this.getType() && v.getValue().endsWith(this.getValue());
	}
	 
}
