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

		
		for (int i = 0 ; i < stmtBody.getProperties().size() ; i++) {
			attribute = stmtBody.getProperties().get(i).getAttributeObject();
			value = stmtBody.getProperties().get(i).getValueObject();
			
			if (value instanceof StatementBody){
				
				BufferTable downLevelTable = BufferTablesManager.getNewTable(dao, (StatementBody) value);
				matchingRecords = new QueryRecordMatrix(dao, stmtBody.getEntityObject(), attribute, null);
				if (!table.isInitialized()) {
					table = downLevelTable;
					table.joinOnRecordValue(matchingRecords);
				}
				else
					table.mergeAndJoin(matchingRecords, downLevelTable);
			}
			else {		
				matchingRecords = new QueryRecordMatrix(dao, stmtBody.getEntityObject(), attribute, value);
				if (!table.isInitialized()) table.allocate(matchingRecords);
				else table.merge(matchingRecords);
			}
			
			
		}
		
		return table;
	}	
}
