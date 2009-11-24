package entitiesdb.main;

import java.io.File;
import java.util.Collection;

import entitiesdb.dao.DaoException;
import entitiesdb.dao.JEDao;
import entitiesdb.record.EntityValue;
import entitiesdb.record.Record;
import entitiesdb.record.StringValue;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JEDao dao = new JEDao(new File("db"));
		try {
			dao.open();
			
			Record record = new Record();
			record.setEntityId("ABC");
			record.setName("nome");
			record.setValue(new StringValue("pippo"));
			
			Record record2 = new Record();
			record2.setEntityId("ABC");
			record2.setName("is");
			record2.setValue(new EntityValue("ABC"));
			
			dao.store(record);
			dao.store(record2);
			
			
			Collection<? extends Record> records;
			records = dao.getRecords();
			for (Record r : records) {
				System.out.println(r);
			}
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	}

}
