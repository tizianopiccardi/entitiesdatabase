package testing;


import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.types.Record;

public class FillDb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		final int NUMERO_DI_ENTITY = 100;
		final int NUMERO_DI_ATTRIBUTI = 5;
		
		final String [] attributi_fissi = {"name", "works", "lives", "city", "year", "surname", "description", "in", "where"};
		final String [] valori_fissi = {"'abc'", "'John'", "'Mary'", "'uni'", "'Trento'", "'computer'", "'real'", "'bla'", "'Test'"};
		
		
		
		
		EntitiesDAO dao = new EntitiesDAO(new File("db/"));			
		putExamples(dao, attributi_fissi, valori_fissi, NUMERO_DI_ENTITY, NUMERO_DI_ATTRIBUTI);
		//System.out.println(dao.getEntityDatabase() + "\n"+dao.getRecordsDatabase());
		System.out.println("OK");
		dao.close();
	}
	
	public static String getRandomString(int l) {
		String out ="";
		Random r = new Random();
		for (int i=0;i<l;i++)
			out += (char) ((int) 'a' + r.nextInt(26));
	
		return out;
	}
	
	
	public static String getRandomStringUpper() {
		String out ="";
		Random r = new Random();
		for (int i=0;i<3;i++)
			out += (char) ((int) 'A' + r.nextInt(26));
	
		return out;
	}
	
	public static void putExamples(EntitiesDAO dao, String[] attributes, String [] values, int eCount, int aCount) {
		
		Random rnd = new Random();
		
		ArrayList<String> entityHistory = new ArrayList<String>();
		
		for (int i = 0 ; i < eCount; i++) {
			
			String entity = FillDb.getRandomStringUpper();
			entityHistory.add(entity);
			
			for(int j = 0 ; j < aCount ; j++) {
				
				
				int attributeIndex = rnd.nextInt(attributes.length*2);
				String attribute = (attributeIndex < attributes.length)?attributes[attributeIndex]:FillDb.getRandomString(8);
				
				
				int valueIndex = rnd.nextInt(attributes.length*3);
				
				String value = (valueIndex < values.length) ? values[valueIndex] : 
								(valueIndex < values.length*2)?"'"+FillDb.getRandomString(8)+"'":
									entityHistory.get(rnd.nextInt(entityHistory.size()));
				
				
				Record rec = new Record(entity, attribute, value);
				System.out.println(rec);
				dao.put(rec);
				
			}
		}
		
	
	}
	
	

}
