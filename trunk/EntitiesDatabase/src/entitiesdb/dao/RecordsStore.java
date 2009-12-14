package entitiesdb.dao;

import java.io.File;
import java.util.ArrayList;
import com.sleepycat.je.*;
import com.sleepycat.persist.*;

import entitiesdb.types.*;


public class RecordsStore {

	private EntityStore recordsStore;
	
	private PrimaryIndex<Long, Record> recordsIndex;
	private SecondaryIndex<String, Long, Record> recordByEntityIndex;
	private SecondaryIndex<String, Long, Record> recordByAttributeIndex;
	private SecondaryIndex<String, Long, Record> recordByValueIndex;

	
	public RecordsStore(Environment dbEnv) {

		StoreConfig storeConfig = new StoreConfig();
		storeConfig.setAllowCreate(true);
		storeConfig.setTransactional(true);
		recordsStore = new EntityStore(dbEnv, "entities_database_records",
				storeConfig);

		recordsIndex = recordsStore.getPrimaryIndex(Long.class, Record.class);

		recordByEntityIndex = recordsStore.getSecondaryIndex(recordsIndex, String.class, "Entity");

		recordByAttributeIndex = recordsStore.getSecondaryIndex(recordsIndex, String.class, "Attribute");

		recordByValueIndex = recordsStore.getSecondaryIndex(recordsIndex, String.class, "Value");

	}
	
	public void close() {
		recordsStore.close();
	}
	
	
	

	public ArrayList<Record> getRecords(String e, String a, String v) {

		//no join required: select * from tbl
		if(e == null && a == null && v == null)
			return getAllRecords();
		
		EntityJoin<Long, Record> join = new EntityJoin<Long, Record>(recordsIndex);
			
		if (e!=null)
			join.addCondition(recordByEntityIndex, e);
		if (a!=null)
			join.addCondition(recordByAttributeIndex, a);
		if (v!=null)
			join.addCondition(recordByValueIndex, v);

		ForwardCursor<Record> cursor = join.entities();
	
		ArrayList<Record> out = new ArrayList<Record>();
		for (Record r : cursor) {
				out.add(r);
		}
		
		cursor.close();
		return out;
	}
	

	public ArrayList<Record> getAllRecords() {
		
		EntityCursor<Record> cursor = recordsIndex.entities();
		ArrayList<Record> out = new ArrayList<Record>();
		for (Record r : cursor) {
				out.add(r);
		}
		cursor.close();
		return out;
	}	
	
}