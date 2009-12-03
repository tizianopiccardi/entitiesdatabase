package entitiesdb.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import com.sleepycat.je.*;
import com.sleepycat.persist.*;

import entitiesdb.query.EntitiesArrayList;
import entitiesdb.record.*;
import entitiesdb.record.Value.ValueType;


public class JEDao {
	private Environment databaseEnvironment;
	private EntityStore entityStore;
	private File databaseDirectory = new File("db/");
	private PrimaryIndex<Long, Record> recordsIndex;

	private SecondaryIndex<EntityId, Long, ?> recordByEntityIndex;
	private SecondaryIndex<Attribute, Long, ?> recordByAttributeIndex;
	private SecondaryIndex<Value, Long, Record> recordByValueIndex;

	private static JEDao dao = null;
	
	
	private JEDao() {
		
	}
	
	public static JEDao getInstance() {
		//if (dao == null) {
		//	dao = new JEDao();
		//}
		return dao;
	}
	
	public static void open() throws DaoException {
		try {
			if (dao == null) {
				dao = new JEDao();
			}
			dao.jeOpen();
		} catch (DatabaseException ex) {
			throw new DaoException(ex);
		}
	}
	
	
	/*
	public JEDao(File databaseDirectory) {
		this.databaseDirectory = databaseDirectory;
	}*/

	/**
	 * Apre la base dati. Se la base dati non è già stata creata, la crea.
	 */
	/*public void open() throws DaoException {
		try {
			jeOpen();
		} catch (DatabaseException ex) {
			throw new DaoException(ex);
		}
	}*/

	private void jeOpen() throws DatabaseException {

		EnvironmentConfig environmentConfig = new EnvironmentConfig();
		environmentConfig.setAllowCreate(true);
		environmentConfig.setTransactional(true);

		databaseEnvironment = new Environment(databaseDirectory,
				environmentConfig);
		StoreConfig storeConfig = new StoreConfig();
		storeConfig.setAllowCreate(true);
		storeConfig.setTransactional(true);
		entityStore = new EntityStore(databaseEnvironment, "entities_database",
				storeConfig);

		this.setAllIndexes();

	}

	/** Chiude la base dati. */
	public static void close() throws DaoException {
		try {
			dao.jeClose();
		} catch (DatabaseException ex) {
			throw new DaoException(ex);
		}
	}

	private void jeClose() throws DatabaseException {
		try {
			entityStore.close();
		} finally {
			databaseEnvironment.cleanLog();
			databaseEnvironment.close();
		}
	}

	/*
	 * public void delete(Record record) throws DaoException { try { if
	 * (records.delete(record.getId()) == false) { throw new
	 * DaoException("No record to remove."); }
	 * 
	 * } catch (DatabaseException ex) { throw new DaoException(ex); } }
	 */

	public Collection<? extends Record> getRecords() throws DaoException {
		try {
			ArrayList<Record> result = new ArrayList<Record>();
			EntityCursor<Record> cursor = recordsIndex.entities();
			try {
				jeFill(result, cursor);
			} finally {
				cursor.close();
			}
			return result;
		} catch (DatabaseException ex) {
			throw new DaoException(ex);
		}
	}

	private void jeFill(ArrayList<Record> list, EntityCursor<Record> cursor)
			throws DatabaseException {
		for (Record r : cursor) {
			list.add(r);
		}
	}

	public void store(Record record) throws DaoException {
		try {
			if (getEntities(record).size() == 0) {
				if (getEntities(record.getEntityId(), null, null).size()==0) //non esiste l'entità
					recordsIndex.putNoReturn(new Record(record.getEntityId(), new Attribute(""), Value.DEFAULT()));
				recordsIndex.putNoReturn(record);
			}
		} catch (DatabaseException ex) {
			throw new DaoException(ex);
		}
	}

	/*
	 * public void update(Record record) throws DaoException { try { if
	 * (records.contains(record.getId()) == false) { throw new
	 * DaoException("Cannot find a record to update"); }
	 * records.putNoReturn(record);
	 * 
	 * } catch (DatabaseException ex) { throw new DaoException(ex); } }
	 */

	@SuppressWarnings("unchecked")
	public void getRecordByEntity(EntityId id) {

		EntityCursor<Record> sec_cursor = (EntityCursor<Record>) recordByEntityIndex
				.subIndex(id).entities();

		try {
			for (Record seci : sec_cursor) {
				System.out.println(seci);
			}
		} finally {
			sec_cursor.close();
		}

	}

	@SuppressWarnings("unchecked")
	public void getRecordByAttribute(Attribute attribute) {

		EntityCursor<Record> sec_cursor = (EntityCursor<Record>) recordByAttributeIndex
				.subIndex(attribute).entities();
		try {
			for (Record seci : sec_cursor) {
				System.out.println(seci);
			}
		} finally {
			sec_cursor.close();
		}

	}

	public void getRecordByValue(Value value) {

		EntityCursor<Record> sec_cursor = (EntityCursor<Record>) recordByValueIndex
				.subIndex(value).entities();
		try {
			for (Record seci : sec_cursor) {
				System.out.println(seci);
			}
		} finally {
			sec_cursor.close();
		}

	}

	private void setAllIndexes() {
		recordsIndex = entityStore.getPrimaryIndex(Long.class, Record.class);

		recordByEntityIndex = entityStore.getSecondaryIndex(recordsIndex,
				EntityId.class, "Entity");

		recordByAttributeIndex = entityStore.getSecondaryIndex(recordsIndex,
				Attribute.class, "Attribute");

		recordByValueIndex = entityStore.getSecondaryIndex(recordsIndex,
				Value.class, "Value");

	}

	public boolean isEmpty() {
		return recordsIndex.count() == 0;
	}

	
	
	public ArrayList<EntityId> getEntities(Record [] rArray) {
		
		//No Conditions
		if (rArray.length == 0) return this.getAllEntities();	
		
		ArrayList<EntityId> out = JEDao.getEntities(rArray[0]); 

		for (int i = 1 ; i < rArray.length ; i++) {
			 out.retainAll(JEDao.getEntities(rArray[i]));
		}
		
		return out;
	}
	
	
	
	public static EntitiesArrayList getEntities(Record r) {
		return getEntities(r.getEntityId(), r.getAttribute(), r.getValue());
	}
	
	
	@SuppressWarnings("unchecked")
	public static EntitiesArrayList getEntities(EntityId e, Attribute a, Value v) {

		//no join required: select entity from tbl
		if(e == null && a == null && v == null)
			return dao.getAllEntities();
		
		EntityJoin join = new EntityJoin(dao.recordsIndex);
			
		if (e!=null)
			join.addCondition(dao.recordByEntityIndex, e);
		if (a!=null)
			join.addCondition(dao.recordByAttributeIndex, a);
		if (v!=null)
			join.addCondition(dao.recordByValueIndex, v);

		ForwardCursor<Record> cursor = join.entities();
		EntitiesArrayList out = dao.getEntityList(cursor);
		cursor.close();
		return out;
	}
	
	public EntitiesArrayList getAllEntities() {
		EntityCursor<Record> cursor = recordsIndex.entities();
		
		EntitiesArrayList out =  getEntityList(cursor);
		cursor.close();
		return out;
	}
	
	private EntitiesArrayList getEntityList(Iterable<Record> c) {
		EntitiesArrayList out = new EntitiesArrayList();
		for (Record r : c) {
			if (!out.contains(r.getEntityId()))
				out.add(r.getEntityId());
		}
		return out;
	}
	
	
	
	
	
	
	
	
	public ArrayList<Value> getAllValues() {
		EntityCursor<Record> cursor = recordsIndex.entities();
		
		ArrayList<Value> out = getValuesList(cursor);
		cursor.close();
		return out;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Value> getValues(EntityId e, Attribute a, Value v) {

		//no join required: select entity from tbl
		if(e == null && a == null && v == null)
			return dao.getAllValues();
		
		EntityJoin join = new EntityJoin(dao.recordsIndex);
			
		if (e!=null)
			join.addCondition(dao.recordByEntityIndex, e);
		if (a!=null)
			join.addCondition(dao.recordByAttributeIndex, a);
		if (v!=null)
			join.addCondition(dao.recordByValueIndex, v);

		ForwardCursor<Record> cursor = join.entities();
		ArrayList<Value> out = dao.getValuesList(cursor);
		cursor.close();
		return out;
	}
	
	
	private ArrayList<Value> getValuesList(Iterable<Record> c) {
		ArrayList<Value> out = new ArrayList<Value>();
		for (Record r : c) {
			if (!out.contains(r.getEntityId()))
				out.add(r.getValue());
		}
		return out;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Record> getAllRecords() {
		EntityCursor<Record> cursor = recordsIndex.entities();
		
		ArrayList<Record> out = getRecordsList(cursor);
		cursor.close();
		return out;
	}	
	
	public static ArrayList<Record> getRecords(Record r) {
		return getRecords(r.getEntityId(), r.getAttribute(), r.getValue());
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Record> getRecords(EntityId e, Attribute a, Value v) {

		//no join required: select entity from tbl
		if(e == null && a == null && v == null)
			return dao.getAllRecords();
		
		EntityJoin join = new EntityJoin(dao.recordsIndex);
			
		if (e!=null)
			join.addCondition(dao.recordByEntityIndex, e);
		if (a!=null)
			join.addCondition(dao.recordByAttributeIndex, a);
		if (v!=null)
			join.addCondition(dao.recordByValueIndex, v);

		ForwardCursor<Record> cursor = join.entities();
		ArrayList<Record> out = dao.getRecordsList(cursor);
		cursor.close();
		return out;
	}
	
	
	private ArrayList<Record> getRecordsList(Iterable<Record> c) {
		ArrayList<Record> out = new ArrayList<Record>();
		for (Record r : c) {
			//if (!out.contains(r))
				out.add(r);
		}
		return out;
	}
	
	
	
	
	
	
	
	
	
	

}