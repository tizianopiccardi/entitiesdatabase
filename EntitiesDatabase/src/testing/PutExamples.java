package testing;


import java.io.File;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.types.Record;

public class PutExamples {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		EntitiesDAO dao = new EntitiesDAO(new File("db/"));			
		
		putExamples(dao);

		
		System.out.println(dao.getEntityDatabase() + "\n");
		System.out.println(dao.getRecordsDatabase());
					
		
		dao.close();
	}
	
	
	
	public static void putExamples(EntitiesDAO dao) {
		try {
			
			Record [] storeList = {
					
					new Record("TR", "name", "'Trentino'"),
					new Record("IT", "name", "'Italia'"),
					new Record("I1", "name", "'John'"),
					new Record("CC", "name", "'Mike'"),
					new Record("TN", "name", "'Trento'"),	
					new Record("H1", "name", "'Hospital 123'"),					
					new Record("H1", "city", "TN"),	
					
					new Record("I1", "works", "H1"),

					new Record("I2", "name", "'Mary'"),					
					new Record("I2", "married", "I1"),					
					new Record("I1", "married", "I2"),					
					new Record("I2", "live_near", "H1"),			
					new Record("TN", "name", "'Trento'"),					
					new Record("I1", "lives", "TN"),					
					new Record("I2", "lives", "TN"),									
					new Record("H1", "director_is", "I1"),					
					new Record("H1", "name", "'Ospedale 123'"),
					
					new Record("E1", "city", "TN"),
					new Record("E1", "name", "'Airport'"),
					new Record("I2", "works", "E1"),
					new Record("TRC", "name", "'Trento'"),
					new Record("TRC", "locatedIn", "TR"),
					new Record("TRC", "country", "IT"),
					new Record("JB", "married", "CC"),
					new Record("JB", "lives", "TRC"),

					
					new Record("MI", "name", "'Milano'"),
					
					new Record("PUB1", "name", "'Apogeo'"),
					new Record("PUB1", "city", "MI"),
					new Record("PUB1", "owner", "I2"),
					
					new Record("P1", "year", "'1978'"),
					new Record("P1", "author", "'Porky Pig'"),
					new Record("P1", "title", "'For sure P=NP'"),
					new Record("P1", "pages", "'1'"),
					
					new Record("P2", "year", "'1983'"),			
					new Record("P2", "author", "'Miller'"),
					new Record("P2", "title", "'Entity model for dummies'"),
					new Record("P2", "extrainfo", "'Bla bla bla'"),
					new Record("P2", "publisher", "PUB1"),
					
					new Record("P3", "year", "'1978'"),						
					new Record("P3", "author", "'Miller'"),						
					new Record("P3", "title", "'Something of interesting'"),	
					new Record("P3", "author", "'Smith'"),
					new Record("P3", "publisher", "PUB1"),
					
					new Record("P4", "year", "'1980'"),						
					new Record("P4", "author", "'Mr. X'"),						
					new Record("P4", "title", "'A rubbish article'"),
										
					
					
			};			


			for (int i = 0 ; i < storeList.length ; i++)
				dao.put(storeList[i]);


			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
