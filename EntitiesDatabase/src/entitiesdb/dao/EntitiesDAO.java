package entitiesdb.dao;

import java.io.File;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

public class EntitiesDAO {

	
	private Environment databaseEnvironment;
	private EntitiesIdStore idStore;
	private RecordsStore recordStore;
	
	public EntitiesDAO (File databaseDirectory) {
		EnvironmentConfig environmentConfig = new EnvironmentConfig();
		environmentConfig.setAllowCreate(true);
		environmentConfig.setTransactional(true);
		databaseEnvironment = new Environment(databaseDirectory, environmentConfig);
		
		idStore = new EntitiesIdStore(databaseEnvironment);
		recordStore = new RecordsStore(databaseEnvironment);
	}
	
	
	
	
	
	
	public boolean addEntity(String id) {
		return idStore.addEntity(id);
	}
	
	
	public boolean entityExists(String id) {
		return idStore.exists(id);
	}	
	
	
	
	
	
	
	
	
	
	public void close() {
		try {
			idStore.close();
			recordStore.close();
		} finally {
			databaseEnvironment.cleanLog();
			databaseEnvironment.close();
		}
	}
	
	
}
