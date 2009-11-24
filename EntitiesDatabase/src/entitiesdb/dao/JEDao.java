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
import com.sleepycat.persist.StoreConfig;

import entitiesdb.record.Record;

/** Incompleto */
public class JEDao {
	private Environment databaseEnvironment;
	private EntityStore entityStore;
	private File databaseDirectory;
	private PrimaryIndex<Long, Record> records;

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
		entityStore = new EntityStore(databaseEnvironment, "jetutorial",
				storeConfig);
		records = entityStore.getPrimaryIndex(Long.class, Record.class);
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
	public void delete(Record record) throws DaoException {
		try {
			if (records.delete(record.getId()) == false) {
				throw new DaoException("No record to remove.");
			}

		} catch (DatabaseException ex) {
			throw new DaoException(ex);
		}
	}*/


	public Collection<? extends Record> getRecords() throws DaoException {
		try {
			ArrayList<Record> result = new ArrayList<Record>();
			EntityCursor<Record> cursor = records.entities();
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
			records.putNoReturn(record);
		} catch (DatabaseException ex) {
			throw new DaoException(ex);
		}
	}

/*
	public void update(Record record) throws DaoException {
		try {
			if (records.contains(record.getId()) == false) {
				throw new DaoException("Cannot find a record to update");
			}
			records.putNoReturn(record);

		} catch (DatabaseException ex) {
			throw new DaoException(ex);
		}
	}*/

}