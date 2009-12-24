package entitiesdb.query.tables;

import java.util.Hashtable;

public class Metadata extends Hashtable<String, Integer>{
	private static final long serialVersionUID = 1763064514495268697L;

	public Integer put(String key, Integer val) {
		
		if (this.exists(key))
			throw new RuntimeException("Duplicate variable name: " + key);
		else
		return super.put(key, val);
	}
	
	@Override
	public synchronized Integer get(Object key) {
		Integer column = super.get(key);
		if (column!=null)
			return column;
		else
			throw new RuntimeException("No references to the variable: " + key);
	}
	
	public boolean exists(String key) {
		return (super.get(key)==null) ? false : true;
	}
	
}
