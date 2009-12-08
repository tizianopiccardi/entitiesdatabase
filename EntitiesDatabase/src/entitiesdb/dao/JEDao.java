package entitiesdb.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import com.sleepycat.je.*;
import com.sleepycat.persist.*;

import entitiesdb.types.*;


public class JEDao {
	private Environment databaseEnvironment;
	private EntityStore entityStore;
	private File databaseDirectory = new File("db/");
	private PrimaryIndex<Long, Record> recordsIndex;

	private SecondaryIndex<String, Long, ?> recordByEntityIndex;
	private SecondaryIndex<String, Long, ?> recordByAttributeIndex;
	private SecondaryIndex<String, Long, Record> recordByValueIndex;

	private static JEDao dao = null;
	
	
	private JEDao() {
		
	}
	
	public static JEDao getInstance() {
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

	/**
	 * Apre la base dati. Se la base dati non � gi� stata creata, la crea.
	 */

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
	
	
	private void setAllIndexes() {
		recordsIndex = entityStore.getPrimaryIndex(Long.class, Record.class);

		recordByEntityIndex = entityStore.getSecondaryIndex(recordsIndex,
				String.class, "Entity");

		recordByAttributeIndex = entityStore.getSecondaryIndex(recordsIndex,
				String.class, "Attribute");

		recordByValueIndex = entityStore.getSecondaryIndex(recordsIndex,
				String.class, "Value");

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
			if (!existsRecord(record)) {
				//if (!existsEntity(record.getEntityId())) //non esiste l'entit�
				//	recordsIndex.putNoReturn(new Record(record.getEntityId(),record.getAttribute(), record.getValue()));
				recordsIndex.putNoReturn(record);
			}
		} catch (DatabaseException ex) {
			throw new DaoException(ex);
		}
	}
	
	public boolean existsRecord(Record r) {
		return false;
	}
	
	public boolean existsEntity(String string) {
		return true;
	}
	
	public boolean isEmpty() {
		return recordsIndex.count() == 0;
	}

	
	
	
	
	
	
	
	
	
	private ArrayList<Record> getRecordsList(Iterable<Record> c) {
		ArrayList<Record> out = new ArrayList<Record>();
		for (Record r : c) {
				out.add(r);
		}
		return out;
	}
	
	public ArrayList<Record> getAllRecords() {
		EntityCursor<Record> cursor = recordsIndex.entities();
		ArrayList<Record> out = getRecordsList(recordsIndex.entities());
		cursor.close();
		return out;
	}	
	
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Record> getRecords(String e, String a, String v) {

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
	
	public static ArrayList<Record> getRecords(Record r) {
		return getRecords(r.getEntityId(), r.getAttribute(), r.getValue());

	}
	
	
	

}