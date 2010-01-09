package entitiesdb.query.evaluators;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.query.objects.StatementBody;
import entitiesdb.query.tables.BufferTable;
import entitiesdb.query.tables.QueryRecordMatrix;

public class BufferTablesManager {

	public static BufferTable getNewTable(EntitiesDAO dao, StatementBody stmtBody) {
		////////////////////////////////////////////////////////////////////////
		Object attribute = null;
		Object value = null;
		
		QueryRecordMatrix matchingRecords = null;
		BufferTable table = new BufferTable();

		/**
		 * The body is: $x()
		 */
		if (stmtBody.getProperties().size()==0) 
			table.allocate(new QueryRecordMatrix(dao, stmtBody.getEntityObject(), null, null));
		else
		
			for (int i = 0 ; i < stmtBody.getProperties().size() ; i++) {
				/**
				 * Takes the object: String / Variable / StatementBody (-> only for value)
				 */
				attribute = stmtBody.getProperties().get(i).getAttributeObject();
				value = stmtBody.getProperties().get(i).getValueObject();
				
				if (value instanceof StatementBody){
					/**
					 * This value is a definition statement,
					 * then I process it recursively and I get a table to join with the current
					 */
					BufferTable downLevelTable = BufferTablesManager.getNewTable(dao, (StatementBody) value);
					matchingRecords = new QueryRecordMatrix(dao, stmtBody.getEntityObject(), attribute, null);
					/**
					 * The current table could be empty (i.e. the subdefinition is the first field of the query)
					 * and in this case, the join doesn't work.<br>
					 * I use the subtable as the main one and join the record in the format like (I1, lives, TN)
					 * on the third element (that is the value) and the entity index of the table.
					 */
					if (!table.isInitialized()) {
						table = downLevelTable;
						table.joinOnRecordValue(matchingRecords);
					}
					else
						table.mergeAndJoin(matchingRecords, downLevelTable);
				}
				else {
					/**
					 * This statement field is simple. Merge it with the current table.
					 */
					matchingRecords = new QueryRecordMatrix(dao, stmtBody.getEntityObject(), attribute, value);
					if (!table.isInitialized()) table.allocate(matchingRecords);
					else table.merge(matchingRecords);
				}
				
				
			}
		
		return table;
	}	
}
