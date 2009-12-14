package entitiesdb.dao;


import com.sleepycat.je.Environment;
import com.sleepycat.persist.*;
import entitiesdb.types.EntityId;


public class EntitiesIdStore {
	//private Environment databaseEnvironment;
	private EntityStore store;
	private PrimaryIndex<String, EntityId> recordsIndex;

	
	public EntitiesIdStore(Environment dbEnv) {
		//databaseEnvironment = dbEnv;

		StoreConfig storeConfig = new StoreConfig();
		storeConfig.setAllowCreate(true);
		storeConfig.setTransactional(true);
		store = new EntityStore(dbEnv, "entities_database_entitiesid",
				storeConfig);

		//Primary key configuration
		recordsIndex = store.getPrimaryIndex(String.class, EntityId.class);
		
	}

	

	public boolean addEntity(String id) {
		return recordsIndex.putNoOverwrite(new EntityId(id));
	}
	
	
	public boolean exists(String id) {
		EntityId eId = recordsIndex.get(id);
		return (eId == null) ? false : true;
	}
	
	public void close() {
		store.close();
	}
	
}
