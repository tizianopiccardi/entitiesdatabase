package entitiesdb.record;

public interface ValueInterface {

	
	public ValueType getType();
	
	public static enum ValueType {
		NOTYPE, ENTITY, ATOM;
	};
	
}
