package entitiesdb.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;
import com.sleepycat.persist.StoreConfig;

import entitiesdb.record.Attribute;
import entitiesdb.record.EntityId;
import entitiesdb.record.Record;
import entitiesdb.record.Value;


public class JEDao {
	private Environment databaseEnvironment;
	private EntityStore entityStore;
	private File databaseDirectory;
	private PrimaryIndex<Long, Record> recordsIndex;

	private SecondaryIndex<EntityId, Long, ?> recordByEntityIndex;
	private SecondaryIndex<Attribute, Long, ?> recordByAttributeIndex;	
	private SecondaryIndex<Value, Long, Record> recordByValueIndex;		
	

	public JEDao(File databaseDirectory) {
		this.databaseDirectory = databaseDirectory;
	}

	/**
	 * Apre la base dati. Se la base dati non è già stata creata, la crea.
	 */
	public void open() throws DaoException {
		try {
			jeOpen();
		} catch (DatabaseException ex) {
			throw new DaoException(ex);
		}
	}

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
	public void close() throws DaoException {
		try {
			jeClose();
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
			recordsIndex.putNoReturn(record);
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
		
		EntityCursor<Record> sec_cursor = (EntityCursor<Record>) recordByValueIndex.subIndex(value).entities();
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

}