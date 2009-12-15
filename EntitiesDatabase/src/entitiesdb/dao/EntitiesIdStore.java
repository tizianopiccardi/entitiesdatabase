package entitiesdb.dao;


import java.util.ArrayList;

import com.sleepycat.je.Environment;
import com.sleepycat.persist.*;

import entitiesdb.dao.RecordsStore.RecordsList;
import entitiesdb.types.EntityId;
import entitiesdb.types.Record;


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

	public long count() {
		return recordsIndex.count();
	}
	

	public boolean addEntity(String id) {
		return recordsIndex.putNoOverwrite(new EntityId(id));
	}
	
	public boolean delete(String id) {
		return recordsIndex.delete(id);
	}
	
	
	public EntityIdsList getAllEntities() {
		EntityCursor<EntityId> cursor = recordsIndex.entities();
		EntityIdsList out = new EntityIdsList();
		for (EntityId r : cursor) {
				out.add(r);
		}
		cursor.close();
		return out;
	}
	
	
	
	public boolean exists(String id) {
		EntityId eId = recordsIndex.get(id);
		return (eId == null) ? false : true;
	}
	
	public void close() {
		store.close();
	}

	
	
	//definisco cosa restituiscono le query
	public class EntityIdsList extends ArrayList<EntityId> {
		private static final long serialVersionUID = 6456196145395912215L;
	}
	
	
}
