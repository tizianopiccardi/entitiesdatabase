package testing;

import java.io.File;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.types.EntityAndAccuracy;

public class TestApproximateStore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		EntitiesDAO dao = new EntitiesDAO(new File("db/"));			
		
		
		EntityAndAccuracy[] records = {
				
				new EntityAndAccuracy("I1", 33.33f),
				new EntityAndAccuracy("I2", 33.33f),
				new EntityAndAccuracy("I3", 33.33f),
				new EntityAndAccuracy("I4", 33.33f),
				new EntityAndAccuracy("I5", 33.33f),
				new EntityAndAccuracy("I6", 33.33f),
				
				new EntityAndAccuracy("I3", 33.33f),
				new EntityAndAccuracy("I7", 33.33f),
				new EntityAndAccuracy("I8", 33.33f),
				
				new EntityAndAccuracy("I3", 33.33f),
				new EntityAndAccuracy("I7", 33.33f),
				new EntityAndAccuracy("I8", 33.33f)
				
		};
		
		for (int i = 0 ; i < records.length ; i++)
			dao.getApproximateStore().put(records[i]);
		
		
		
		System.out.println(dao.getApproximateStore() + "\n");
		
		//dao.resetApproximateStore();
		
		dao.close();
		
		

	}

}
