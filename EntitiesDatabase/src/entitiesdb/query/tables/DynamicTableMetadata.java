package entitiesdb.query.tables;

import java.util.Hashtable;

public class DynamicTableMetadata extends Hashtable<String, Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7179252827528533885L;
	public int size = 0;

	public Integer get(String k) {
		if (super.get(k) == null) {
			this.put(k, size);
			size++;
		}
		return super.get(k);
	}
	
	public String toString() {
		return super.toString();
	}
	
}
