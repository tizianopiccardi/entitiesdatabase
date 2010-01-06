package entitiesdb.query.approximate;

import java.util.Hashtable;

public class EntityAndAccuracyTable extends Hashtable<String, Float> {

	private static final long serialVersionUID = 3361231606766169293L;
	
	
	@Override
	public synchronized Float put(String key, Float value) {
		if (this.containsKey(key)) {
			value += this.get(key);
		}
		return super.put(key, value);
	}

}
