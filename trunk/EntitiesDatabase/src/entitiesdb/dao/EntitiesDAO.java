package entitiesdb.dao;

import java.io.File;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.ForwardCursor;

import entitiesdb.dao.EntitiesIdStore.EntityIdsList;
import entitiesdb.dao.RecordsStore.RecordsList;
import entitiesdb.types.EntityAndAccuracy;
import entitiesdb.types.Record;

public class EntitiesDAO {

	
	private Environment databaseEnvironment;
	private EntitiesIdStore idStore;
	private RecordsStore recordStore;
	private ApproximateQueryStore approximateStore;
	
	public EntitiesDAO (File databaseDirectory) {
		
		if (!databaseDirectory.exists())
			databaseDirectory.mkdir();
		
		EnvironmentConfig environmentConfig = new EnvironmentConfig();
		environmentConfig.setAllowCreate(true);
		environmentConfig.setTransactional(true);
		databaseEnvironment = new Environment(databaseDirectory, environmentConfig);
		
		idStore = new EntitiesIdStore(databaseEnvironment);
		recordStore = new RecordsStore(databaseEnvironment);
		
		approximateStore = new ApproximateQueryStore(databaseEnvironment);
		this.resetApproximateStore();
		
		/*for (String s: databaseEnvironment.getDatabaseNames())
			System.out.println(s);*/
		
		//System.out.println("E: " + approximateStore.getAllEntities());
		//System.out.println("A: " +approximateStore.getAllEntitiesByAccuracy());
		
		
		//System.out.println(approximateStore.getDatabaseName());
		//System.out.println(approximateStore.getSecondaryDatabaseName());
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
	
	public RecordsList getRecords(String e){
		return recordStore.getRecords(e);
	}
	
	public RecordsList getRecords(Record r){
		return recordStore.getRecords(r.getEntityId(), r.getAttribute(), r.getValue());
	}
	
	public boolean put(String e, String a, String v) {
		return this.put(new Record(e,a,v));
	}
	
	public boolean put(Record r) {
		
		/**
		 * If the entity doesn't exist, i add it
		 */
		if (!idStore.exists(r.getEntityId())) idStore.addEntity(r.getEntityId());
		
		/**
		 * If the value is an entity and doesn't exist: error!
		 */
		if (r.getValue().charAt(0)!='\'' && !entityExists(r.getValue())) 
			throw new RuntimeException("You are tring to refer a value to a missing entity: " + r.getValue() );
		
		
		return recordStore.put(r);
	}	
	
	
	public void close() {
		try {
			idStore.close();
			recordStore.close();
			approximateStore.close();
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

	public ApproximateQueryStore getApproximateStore() {
		return approximateStore;
	}
	
	public void resetApproximateStore() {
		approximateStore.reset();
	}
	
	public void copyToApproximate(Object e, Object a, Object v, float p, String prefix) {
		if ( !(e instanceof String) ) e = null;
		if ( !(a instanceof String) ) a = null;
		if ( !(v instanceof String) ) v = null;

		ForwardCursor<Record> cursor = recordStore.getRecordsCursor(e, a, v);
		for (Record r : cursor)
			this.getApproximateStore().put(prefix + r.getEntityId(), p);
	
		cursor.close();
	}
	
	
	public void joinOnValuesToApproximate(Object e, Object a, float p, String prefix, String subPrefix) {
		if ( !(e instanceof String) ) e = null;
		if ( !(a instanceof String) ) a = null;
		
		ForwardCursor<Record> cursor = recordStore.getRecordsCursor(e, a, null);
		EntityAndAccuracy eac = null;
		for (Record r : cursor) {
			if ((eac=approximateStore.get(subPrefix+r.getValue()))!=null) {
				float newWeight = eac.getAccuracy()*(p/100.0f);
				approximateStore.put(prefix+r.getEntityId(), newWeight);	
			}
		}
		
		cursor.close();
	}
	

	public String toString() {
		return "\nDATABASE DUMP:\n\n"+ idStore.toString() +
				"========\n"+ recordStore.toString()+"========";
	}
	
	
}
