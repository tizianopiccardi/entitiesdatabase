package entitiesdb.query.approximate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import entitiesdb.query.tables.QueryRecordMatrix;

/**
 * This is the core of the approximate query system
 * @author Tiziano
 *
 */
public class ApproximateResultSet {


	/**
	 * This is an Hashtable that contains as key the entity ID and as value its
	 * percentage.
	 */
	public EntityAndAccuracyTable entities = new EntityAndAccuracyTable();

	
	/**
	 * Add all entities to the table. The source is a result of a query
	 * @param qrm
	 * @param p
	 */
	public void add(QueryRecordMatrix qrm, float p) {
		for (int i = 0 ; i < qrm.size() ; i++ )
			entities.put(qrm.getEntity(i), p);
	}
	
	/**
	 * Add all entities to the table. The source is an another table
	 * @param ars
	 * @param p
	 */
	public void add(ApproximateResultSet ars) {
		Enumeration<String> keys = ars.entities.keys();
		while( keys.hasMoreElements() ) {
		  String key = keys.nextElement();
		  entities.put(key, ars.entities.get(key));
		}
	}
	
	
	/**
	 * Generate a list containing elements of type EntityAndAccuracy.<br>
	 * This is the method called at the end of the computations.
	 * @return
	 */
	public ArrayList<EntityAndAccuracy> getResultsList(int limit) {

		ArrayList<EntityAndAccuracy> out = new ArrayList<EntityAndAccuracy>(entities.size());
		Enumeration<String> keys = entities.keys();
		while( keys.hasMoreElements() ) {
		  String key = keys.nextElement();
		  out.add(new EntityAndAccuracy(key, entities.get(key)));
		}

		Collections.sort(out);
		
		//da ottimizzare
		if (limit > 0)
			out = new ArrayList<EntityAndAccuracy>(out.subList(0, limit));
		
		return out;
	}

	
	/**
	 * If this list is generated in a lower level when I climb up, 
	 * I need to update all the percantages
	 * @param p
	 */
	public void updateWidth(float p) {
		Enumeration<String> keys = entities.keys();
		while( keys.hasMoreElements() ) {
		  String key = keys.nextElement();
		  entities.simplePut(key, entities.get(key)*p/100);
		}
	}	
	
	/**
	 * If this list is generated in a lower level when I climb up, 
	 * I need to keep only the elements that match on the value field.<br>
	 * <br>
	 * if fieldTop.value = list[i].entityID then <br>
	 * 		entityID is OK<br>
	 * <br>
	 * @param qrm
	 */
	public void joinOnValue(QueryRecordMatrix qrm) {
		
		EntityAndAccuracyTable out = new EntityAndAccuracyTable();
		
		for (int i = 0 ; i < qrm.size() ; i++) {
			Float percent = this.entities.get(qrm.get(i)[2]);
			if (percent!=null)
				out.put(qrm.get(i)[0], percent);
		}
		
		this.entities = out;
		
	}
	
	public int size() {
		return entities.size();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getResultsList(-1).toString();
	}
	
	
	
}
