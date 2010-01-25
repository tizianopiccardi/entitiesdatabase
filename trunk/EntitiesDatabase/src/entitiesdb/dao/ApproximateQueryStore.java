package entitiesdb.dao;


import java.util.ArrayList;

import com.sleepycat.je.Environment;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;
import com.sleepycat.persist.StoreConfig;

import entitiesdb.query.tables.QueryRecordMatrix;
import entitiesdb.types.EntityAndAccuracy;


public class ApproximateQueryStore {

	public EntityStore store;
	public PrimaryIndex<String, EntityAndAccuracy> entitiesIndex;
	private SecondaryIndex<Float, String, EntityAndAccuracy> percentageIndex;
	
	public ApproximateQueryStore(Environment dbEnv) {

		StoreConfig storeConfig = new StoreConfig();
		storeConfig.setAllowCreate(true);
		//storeConfig.setTransactional(true);
		store = new EntityStore(dbEnv, "approx_table",
				storeConfig);


		entitiesIndex = store.getPrimaryIndex(String.class, EntityAndAccuracy.class);
		percentageIndex = store.getSecondaryIndex(entitiesIndex, Float.class, "Accuracy");

		
	}
	
	
	public void reset() {
		EntityCursor<EntityAndAccuracy> cursor = entitiesIndex.entities();
		 try {
		     for (EntityAndAccuracy entity : cursor) {
		         cursor.delete();
		     }
		 } finally {
		     cursor.close();
		 }
	}
	
	public boolean contains(String id) {
		return entitiesIndex.contains(id);
	}
	
	public EntityAndAccuracy get(String id) {
		return entitiesIndex.get(id);
	}
	
	
	public void delete(String id) {
		 entitiesIndex.delete(id);
	}
	
	public void deleteByPrefix(String prefix) {
		
		char[] ca = prefix.toCharArray();
		final int lastCharIndex = ca.length - 1;
		ca[lastCharIndex]++;
		
		EntityCursor<EntityAndAccuracy> cursor = entitiesIndex.entities(prefix, true, String.valueOf(ca), false);
		 try {
		     for (EntityAndAccuracy entity : cursor) {
		         cursor.delete();
		     }
		 } finally {
		     cursor.close();
		 }
	}

	
	public void add(QueryRecordMatrix qrm, float p) {
		for (int i = 0 ; i < qrm.size() ; i++) {
			this.put(qrm.getEntity(i), p);
		}

	}	

	
	public void put(EntityAndAccuracy e) {
		this.put(e.getEntity(), e.getAccuracy());
	}
	
	public void put(String entity, float p) {
		EntityAndAccuracy tmp = entitiesIndex.get(entity);
		
		
		if (tmp != null) 
			p+=tmp.getAccuracy();

		entitiesIndex.put(new EntityAndAccuracy(entity, p));
	}
	/*
	public void simplePut(String entity, float p) {
		entitiesIndex.put(new EntityAndAccuracy(entity, p));
	}*/
	
	public long count() {
		return entitiesIndex.count();
	}
	
	public void close() {
		store.close();
	}

	
	
	
	public EntityAndAccuracyList getEntities(int limit) {
		EntityCursor<EntityAndAccuracy> cursor = percentageIndex.entities();
		EntityAndAccuracyList out = new EntityAndAccuracyList();
		EntityAndAccuracy r = null;
		
		int couter = 0;
		while ((r = cursor.prev())!=null) {
			if (couter < limit || limit < 0) {
				out.add(r);
				couter++;
			}
			else break;
		}
		
		/*int couter = 0;
		if ((r = cursor.last())!=null) 
			while (r != null) {
				if (couter < limit || limit < 0) {
					out.add(r);
					couter++;
				}
				else break;
				r = cursor.prev();
			}*/
		
		cursor.close();
		return out;

	}
	
	public EntityAndAccuracyList getAllEntities() {
		EntityCursor<EntityAndAccuracy> cursor = entitiesIndex.entities();
		EntityAndAccuracyList out = new EntityAndAccuracyList();
		for (EntityAndAccuracy r : cursor) {
				out.add(r);
		}
		cursor.close();
		return out;
	}
	
	public EntityAndAccuracyList getAllEntitiesByAccuracy() {
		EntityCursor<EntityAndAccuracy> cursor = percentageIndex.entities();
		EntityAndAccuracyList out = new EntityAndAccuracyList();
		for (EntityAndAccuracy r : cursor) {
				out.add(r);
		}
		cursor.close();
		return out;
	}
	

	
	@Override
	public String toString() {
		String out = "EntityAndAccuracy: \n";
	
		EntityCursor<EntityAndAccuracy> cursor = entitiesIndex.entities();
		for (EntityAndAccuracy r : cursor) {
			out += r+"\n";
		}
		cursor.close();

		return out+"\n";
	}
	
	public class EntityAndAccuracyList extends ArrayList<EntityAndAccuracy> {
		private static final long serialVersionUID = 6456196145395912215L;
	}
}
