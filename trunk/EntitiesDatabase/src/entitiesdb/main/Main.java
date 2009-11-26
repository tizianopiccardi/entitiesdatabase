package entitiesdb.main;

import java.io.File;
import java.util.Collection;

import entitiesdb.dao.DaoException;
import entitiesdb.dao.JEDao;
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
			
			Record record = new Record("I1", "name", "John", ValueType.ATOM);
			
			Record record2 = new Record("I1", "works", "H1", ValueType.ENTITY);

			Record record3 = new Record("H1", "name", "Hospital 123", ValueType.ATOM);
			
			Record record4 = new Record("H1", "city", "TN", ValueType.ENTITY);
			
			Record record5 = new Record("I2", "name", "Mary", ValueType.ATOM);
			
			Record record6 = new Record("I2", "married", "I1", ValueType.ENTITY);
			
			Record record7 = new Record("I1", "married", "I2", ValueType.ENTITY);
			
			Record record8 = new Record("I2", "live_near", "H1", ValueType.ENTITY);
			
			Record record9 = new Record("TN", "name", "Trento", ValueType.ATOM);
			
			Record record10 = new Record("I1", "lives", "TN", ValueType.ENTITY);
			
			Record record11 = new Record("I2", "lives", "TN", ValueType.ENTITY);
			
			Record record12 = new Record("I5", "", "", ValueType.NOTYPE);
			
			dao.store(record);
			dao.store(record2);
			dao.store(record3);
			dao.store(record4);
			dao.store(record5);
			dao.store(record6);
			dao.store(record7);
			dao.store(record8);
			dao.store(record9);
			dao.store(record10);
			dao.store(record11);
			dao.store(record12);
			
			dao.getRecordByEntity("TN");
			System.out.print("\n\n\n");
			dao.getRecordByAttribute("name");
			System.out.print("\n\n\n");
			dao.getRecordByValue(new Value("Mary", ValueType.ATOM));
			System.out.print("\n\n\n");
			/*
			Collection<? extends Record> records;
			records = dao.getRecords();
			for (Record r : records) {
				System.out.println(r);
			}
			*/
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	}

}
