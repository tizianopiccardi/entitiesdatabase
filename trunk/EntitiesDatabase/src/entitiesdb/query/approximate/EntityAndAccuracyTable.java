package entitiesdb.query.approximate;

import java.util.Hashtable;

/**
 * Hashtable used during the computation
 * @author Tiziano
 *
 */
public class EntityAndAccuracyTable extends Hashtable<String, Float> {

	private static final long serialVersionUID = 3361231606766169293L;
	
	
	@Override
	public synchronized Float put(String key, Float value) {
		
		/**
		 * Since every percentage is contestual, if this entity is in the list 
		 * I can sum the value.
		 */
		Float v = this.get(key);
		if (v!=null)
			value += v;

		return super.put(key, value);
	}
	

	/**
	 * If I need to add an element without update, I use this one.<br>
	 * I use this method when I update the value.<br>
	 * I.e. This body in the upper level is an field with value 25%. 
	 * 		For each elements I compute: 0.25 * field[i].percentage
	 * @param key
	 * @param value
	 * @return
	 */
	public synchronized Float simplePut(String key, Float value) {
		return super.put(key, value);
	}
	

}
