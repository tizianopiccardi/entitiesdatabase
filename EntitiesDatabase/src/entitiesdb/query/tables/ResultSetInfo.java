package entitiesdb.query.tables;

import java.util.ArrayList;
import java.util.Hashtable;

public class ResultSetInfo {

	public Hashtable<String, Integer> entityMap = new Hashtable<String, Integer>();
	
	public ArrayList<String> entitiesArray = new ArrayList<String>();
	
	
	public void add(String entity) {
		if (this.entityMap.get(entity)==null) {
			this.entityMap.put(entity, 1);
			this.entitiesArray.add(entity);
		}
		else
			this.entityMap.put(entity, this.entityMap.get(entity)+1);
	}
	
	
	public int getCount(String e) {
		return (entityMap.get(e)==null) ? 0 : entityMap.get(e);
	}
	
	
	public String toString() {
		return entityMap.toString();
	}
	
}
