package entitiesdb.main;


import java.io.File;
import java.util.Collection;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.dao.JEDao;
import entitiesdb.types.Record;

public class MainNew {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try {
			/*JEDao.open();
			JEDao dao = JEDao.getInstance();
			

			Record [] storeList = {
					new Record("I1", "name", "'John'"),
					new Record("I1", "works", "H1"),
					new Record("H1", "name", "'Hospital 123'"),					
					new Record("H1", "city", "TN"),	
					new Record("I2", "name", "'Mary'"),					
					new Record("I2", "married", "I1"),					
					new Record("I1", "married", "I2"),					
					new Record("I2", "live_near", "H1"),			
					new Record("TN", "name", "'Trento'"),					
					new Record("I1", "lives", "TN"),					
					new Record("I2", "lives", "TN"),									
					new Record("H1", "director_is", "I1"),					
					new Record("H1", "name", "'Ospedale 123'"),
					new Record("I2", "works", "E1"),
					new Record("E1", "city", "TN"),
					new Record("E1", "name", "'Airport'"),
					new Record("JB", "married", "CC"),
					new Record("JB", "lives", "TRC"),
					new Record("TRC", "name", "'Trento'"),
					new Record("TRC", "locatedIn", "TR"),
					new Record("TRC", "country", "IT")
			};

			if (dao.isEmpty()) {
				for (int i = 0 ; i < storeList.length ; i++)
					JEDao.storeAttribute(storeList[i]);
			}
			
			
			System.out.println("Contenuto completo:");
			Collection<? extends Record> records;
			records = JEDao.getRecords();
			for (Record r : records) {
				System.out.println(r);
			}
			System.out.print("\n\n\n");

			
			JEDao.close();*/
			
			
			EntitiesDAO dao = new EntitiesDAO(new File("db/"));
			
			dao.addEntity("I3");
			
			System.out.println(dao.entityExists("I3"));
			
			dao.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
	}

}
