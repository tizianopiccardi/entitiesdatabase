package entitiesdb.query.approximate;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.query.objects.StatementBody;
import entitiesdb.query.tables.QueryRecordMatrix;

public class ApproximationManager {

	public static ApproximateResultSet getApproximateResultSet(EntitiesDAO dao, StatementBody stmtBody) {
		
		Object attribute = null;
		Object value = null;
		QueryRecordMatrix matchingRecords = null;
		
					
			
		ApproximateResultSet aResultSet = new ApproximateResultSet();
			
		
		int fieldsCount = stmtBody.properties.size();
		float percentValue = (float) (100.0 / fieldsCount);

		for (int i = 0 ; i < fieldsCount ; i++ ) {
			
			
			attribute = stmtBody.getProperties().get(i).getAttributeObject();
			value = stmtBody.getProperties().get(i).getValueObject();
			
			matchingRecords = new QueryRecordMatrix(dao, stmtBody.getEntityObject(), attribute, value);

			aResultSet.add(matchingRecords, percentValue);
			
			//System.out.println(matchingRecords);
			
		}
		
		return null;
		
	}
	
	
}
