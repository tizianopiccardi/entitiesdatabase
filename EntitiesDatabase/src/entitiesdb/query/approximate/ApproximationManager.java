package entitiesdb.query.approximate;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.query.objects.StatementBody;
import entitiesdb.query.tables.QueryRecordMatrix;

public class ApproximationManager {

	/**
	 * This method returns the approximate resultset 
	 * @param dao
	 * @param stmtBody
	 * @return
	 */
	public static ApproximateResultSet getApproximateResultSet(EntitiesDAO dao, StatementBody stmtBody) {
		
		Object attribute = null;
		Object value = null;
		QueryRecordMatrix matchingRecords = null;
		
					
		
		ApproximateResultSet aResultSet = new ApproximateResultSet();
			
		/**
		 * Get the number of fields in this body and compute their width (%)
		 */
		int fieldsCount = stmtBody.properties.size();
		float percentValue = (float) (100.0 / fieldsCount);

		for (int i = 0 ; i < fieldsCount ; i++ ) {

			attribute = stmtBody.getProperties().get(i).getAttributeObject();
			value = stmtBody.getProperties().get(i).getValueObject();
			
			
			/**
			 * If the value is a Statement, I need to get the sublist
			 */
			if (value instanceof StatementBody) {
				/**
				 * The sublist and the matching records with the current body field
				 */
				ApproximateResultSet subList = ApproximationManager.getApproximateResultSet(dao, (StatementBody) value);
				matchingRecords = new QueryRecordMatrix(dao, stmtBody.getEntityObject(), attribute, null);
				
				/**
				 * Join the sublist with the matching records. This will remove the useless entity IDs.
				 */
				subList.joinOnValue(matchingRecords);
				
				/**
				 * Now I update the percentage with the real value gotten by the width of the current field...
				 */
				subList.updateWidth(percentValue);
				
				/**
				 * ...and finally I merge the sublist with the main one
				 */
				aResultSet.add(subList);
				
			}
			
			else {
				/**
				 * If this is a simple field, I get the records and I add them to the main list.
				 */
				matchingRecords = new QueryRecordMatrix(dao, stmtBody.getEntityObject(), attribute, value);
				aResultSet.add(matchingRecords, percentValue);
			}

			
		}
		
		return aResultSet;
		
	}
	
	
}
