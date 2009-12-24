package entitiesdb.dao;

import java.io.File;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import entitiesdb.dao.EntitiesIdStore.EntityIdsList;
import entitiesdb.dao.RecordsStore.RecordsList;
import entitiesdb.types.Record;

public class EntitiesDAO {

	
	private Environment databaseEnvironment;
	private EntitiesIdStore idStore;
	private RecordsStore recordStore;
	
	public EntitiesDAO (File databaseDirectory) {
		EnvironmentConfig environmentConfig = new EnvironmentConfig();
		environmentConfig.setAllowCreate(true);
		environmentConfig.setTransactional(true);
		databaseEnvironment = new Environment(databaseDirectory, environmentConfig);
		
		idStore = new EntitiesIdStore(databaseEnvironment);
		recordStore = new RecordsStore(databaseEnvironment);
	}
	
	
	
	public boolean deleteEntity(String id) {
		//SICURI??? Questa entità è disintegrata completamente, esistenza e relazioni
		return idStore.delete(id) && recordStore.delete(id, null, null) && recordStore.delete(null, null, id);
	}
	
	public boolean deleteRecord(Record r) {
		return recordStore.delete(r);
	}
	
	
	public boolean addEntity(String id) {
		return idStore.addEntity(id);
	}
	
	public boolean entityExists(String id) {
		return idStore.exists(id);
	}	
	
	public boolean isEmpty() {
		return (recordStore.count() == 0 && idStore.count() == 0) ? true : false;
	}
	
	
	public EntityIdsList getAllEntities() {
		return idStore.getAllEntities();
	}
	
	
	public RecordsList getAllRecords(){
		return recordStore.getAllRecords();
	}
	
	public RecordsList getRecords(String e, String a, String v){
		return recordStore.getRecords(e, a, v);
	}
	
	public RecordsList getRecords(Record r){
		return recordStore.getRecords(r.getEntityId(), r.getAttribute(), r.getValue());
	}
	
	public boolean put(String e, String a, String v) {
		return this.put(new Record(e,a,v));
	}
	
	public boolean put(Record r) {
		if (!idStore.exists(r.getEntityId())) idStore.addEntity(r.getEntityId());
		return recordStore.put(r);
	}	
	
	
	public void close() {
		try {
			idStore.close();
			recordStore.close();
		} finally {
			databaseEnvironment.cleanLog();
			databaseEnvironment.close();
		}
	}
	
	
	
	public EntitiesIdStore getEntityDatabase() {
		return idStore;
	}



	public RecordsStore getRecordsDatabase() {
		return recordStore;
	}



	public String toString() {
		return "\nDATABASE DUMP:\n\n"+ idStore.toString() +
				"========\n"+ recordStore.toString()+"========";
	}
	
	
}
