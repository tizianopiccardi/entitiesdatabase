package entitiesdb.query.tables;

import java.util.Hashtable;

public class Metadata extends Hashtable<String, Integer>{
	private static final long serialVersionUID = 1763064514495268697L;

	public Integer put(String key, Integer val) {
		
		if (this.get(key)!=null)
			throw new RuntimeException("Duplicate variable name: " + key);
		else
		return super.put(key, val);
	}
	
}
