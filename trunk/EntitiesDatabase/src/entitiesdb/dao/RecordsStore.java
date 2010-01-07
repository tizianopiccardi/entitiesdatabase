package entitiesdb.dao;

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
	
	
	public long count() {
		return recordsIndex.count();
	}
	
	//by pattern
	public boolean delete(Record r) {
		return delete(r.getEntityId(), r.getAttribute(), r.getValue());
	}
	public boolean delete(String e, String a, String v) {
		ArrayList<Record> recordList = getRecords(e,a,v);
		boolean out = true;
		for (int i = 0 ; i < recordList.size() ; i++)
			out = out && recordsIndex.delete(recordList.get(i).getId());
		return out;
	}
	
	public boolean exists(Record r) {
		RecordsList rL = this.getRecords(r);
		return (rL.size() == 0) ? false : true;
	}
	
	public boolean put(String e, String a, String v) {
		return this.put(new Record(e,a,v));
	}
	
	public boolean put(Record r) {
		if (!exists(r)) {
			this.recordsIndex.putNoReturn(r);
			return true;
		}
		return false;	
	}

	
	public RecordsList getRecords(Record r) {
		return getRecords(r.getEntityId(), r.getAttribute(), r.getValue());
	}
	public RecordsList getRecords(String e, String a, String v) {

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
	
		RecordsList out = new RecordsList();
		for (Record r : cursor) {
				out.add(r);
		}
		
		cursor.close();
		return out;
	}
	
	public RecordsList getRecords(String e) {
		
		EntityJoin<Long, Record> join = new EntityJoin<Long, Record>(recordsIndex);
		join.addCondition(recordByEntityIndex, e);
		ForwardCursor<Record> cursor = join.entities();
		RecordsList out = new RecordsList();
		
		for (Record r : cursor)
				out.add(r);
		
		cursor.close();
		return out;
	}
	
	public RecordsList getAllRecords() {	
		EntityCursor<Record> cursor = recordsIndex.entities();
		RecordsList out = new RecordsList();
		for (Record r : cursor) {
				out.add(r);
		}
		cursor.close();
		return out;
	}	
	
	//definisco cosa restituiscono le query
	public class RecordsList extends ArrayList<Record> {
		private static final long serialVersionUID = -2666202436382791323L;
	}
	
	
	public String toString() {
		String out = "Records:\n";
		for (Record eId: this.getAllRecords())
			out+= eId.toString()+"\n";
		return out+"\n";
	}
	
}