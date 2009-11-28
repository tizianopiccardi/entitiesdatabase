package entitiesdb.main;


import java.io.File;
import java.util.Collection;
import entitiesdb.dao.JEDao;
import entitiesdb.record.Attribute;
import entitiesdb.record.EntityId;
import entitiesdb.record.Record;
import entitiesdb.record.Value;
import entitiesdb.record.Value.ValueType;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JEDao dao = new JEDao(new File("db/"));
		try {
			dao.open();
			
			Record [] storeList = {
					new Record("I1", "", "", ValueType.NOTYPE),
					new Record("I1", "name", "John", ValueType.ATOM),
					new Record("I1", "works", "H1", ValueType.ENTITY),
					new Record("H1", "name", "Hospital 123", ValueType.ATOM),					
					new Record("H1", "city", "TN", ValueType.ENTITY),	
					new Record("I2", "", "", ValueType.NOTYPE),
					new Record("I2", "name", "Mary", ValueType.ATOM),					
					new Record("I2", "married", "I1", ValueType.ENTITY),					
					new Record("I1", "married", "I2", ValueType.ENTITY),					
					new Record("I2", "live_near", "H1", ValueType.ENTITY),			
					new Record("TN", "", "", ValueType.NOTYPE),
					new Record("TN", "name", "Trento", ValueType.ATOM),					
					new Record("I1", "lives", "TN", ValueType.ENTITY),					
					new Record("I2", "lives", "TN", ValueType.ENTITY),					
					new Record("I5", "", "", ValueType.NOTYPE),					
					new Record("H1", "director_is", "I1", ValueType.ENTITY),					
					new Record("H1", "name", "Ospedale 123", ValueType.ATOM),
					new Record("E1", "", "", ValueType.NOTYPE),
					new Record("I2", "works", "E1", ValueType.ENTITY),
					new Record("E1", "city", "TN", ValueType.ENTITY),
					new Record("E1", "name", "Airport", ValueType.ATOM)
			};

			if (dao.isEmpty()) {
				for (int i = 0 ; i < storeList.length ; i++)
					dao.store(storeList[i]);
			}
			
			
			System.out.println("Contenuto completo:");
			Collection<? extends Record> records;
			records = dao.getRecords();
			for (Record r : records) {
				System.out.println(r);
			}
			System.out.print("\n\n\n");

			
			//$x(name: $y, city: 'TN')
			Record [] rArray = {
					new Record(null, new Attribute("name"), null),
					new Record(null, new Attribute("city"), new Value("TN", ValueType.ENTITY))
			};
			System.out.println(dao.getEntities(rArray));
			
			//$z(works: $j)
			Record [] rArray2 = {
					new Record(null, new Attribute("works"), null)
			};
			System.out.println(dao.getEntities(rArray2));
			
			//$x(name: $y, city: 'TN'), $z(works: $j)
			
			
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
	}

}
