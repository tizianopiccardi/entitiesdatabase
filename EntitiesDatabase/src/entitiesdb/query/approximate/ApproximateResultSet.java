package entitiesdb.query.approximate;

import entitiesdb.query.tables.QueryRecordMatrix;


public class ApproximateResultSet {


	public EntityAndAccuracyTable entities= new EntityAndAccuracyTable();

	
	public void add(QueryRecordMatrix qrm, float p) {
		
		for (int i = 0 ; i < qrm.size() ; i++ ) {
			entities.put(qrm.getEntity(i), p);
		}
		
		System.out.println(entities);
			
	}
	
	
	
	
}
