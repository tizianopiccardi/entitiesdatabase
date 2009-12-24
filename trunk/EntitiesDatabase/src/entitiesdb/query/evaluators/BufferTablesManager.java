package entitiesdb.query.evaluators;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.query.objects.StatementBody;
import entitiesdb.query.tables.BufferTable;
import entitiesdb.query.tables.QueryRecordMatrix;

public class BufferTablesManager {

	
	public static BufferTable getNewTable(EntitiesDAO dao, StatementBody stmtBody) {
		////////////////////////////////////////////////////////////////////////
		Object attribute = stmtBody.getProperties().get(0).getAttributeObject();
		Object value = stmtBody.getProperties().get(0).getValueObject();
		QueryRecordMatrix matchingRecords = new QueryRecordMatrix(dao, stmtBody.getEntityObject(), attribute, value);
		
		
		BufferTable table = new BufferTable(matchingRecords);
		
		for (int i = 1 ; i < stmtBody.getProperties().size() ; i++) {
			attribute = stmtBody.getProperties().get(i).getAttributeObject();
			value = stmtBody.getProperties().get(i).getValueObject();
			matchingRecords = new QueryRecordMatrix(dao, stmtBody.getEntityObject(), attribute, value);

			table.merge(matchingRecords);
		}
		
		return table;
	}
	
}
