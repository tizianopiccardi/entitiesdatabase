package entitiesdb.query.evaluators;

import java.util.ArrayList;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.language.analysis.DepthFirstAdapter;
import entitiesdb.language.node.AInsertMain;
import entitiesdb.language.node.AListBody;
import entitiesdb.language.node.AQueryMain;
import entitiesdb.language.node.ASimpleQuery;
import entitiesdb.language.node.ASingleBody;
import entitiesdb.query.QueryEnvironment;
import entitiesdb.query.StatementBody;
import entitiesdb.query.StatementProperty;
import entitiesdb.query.process.BufferTable;
import entitiesdb.query.tables.QueryRecordMatrix;
import entitiesdb.types.Record;




public class QueryEngine extends DepthFirstAdapter {

	
	private QueryEnvironment env = new QueryEnvironment();
	
	private EntitiesDAO dao = null;
	
	public QueryEngine(EntitiesDAO d) {
		super();
		this.dao = d;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public void caseAInsertMain(AInsertMain node) {
		node.getEntitybody().apply(this);
		
		ArrayList<StatementProperty> propertyList = (ArrayList<StatementProperty>) env.getNodeVal(node.getEntitybody());
		
		for (int i = 0 ; i < propertyList.size() ; i++) {
			Record r = new Record(node.getIdentifier().getText(), 
					(String)propertyList.get(i).getAttribute(), 
					(String)propertyList.get(i).getValue());
			dao.put(r);
		}
		
	}
	
	
	public void caseAQueryMain(AQueryMain node) {
		node.getQuery().apply(this);
		//System.out.println(this.resultEnvironment);
	}
	
	

	public void caseASimpleQuery(ASimpleQuery node) {
		//Valuto il body
		node.getBody().apply(this);
	}
	
	
	
	public void caseASingleBody(ASingleBody node) {
		
		//(_ : _, ...)
		//System.out.println("QueryEngine.caseASingleBody()");
		
		
		node.getEntitypattern().apply(new StatementEngine(dao, env));
		
		
		StatementBody stmtBody = (StatementBody) env.getNodeVal(node.getEntitypattern());

		////////////////////////////////////////////////////////////////////////
		//System.out.println(resultEnvironment);
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
		
		System.out.println(table);

	}	
	
	public void caseAListBody(AListBody node) {
		
		//(_ : _, ...), (_ : _, ...), ...
		
		node.getEntitypattern().apply(this);

		node.getEntitypattern().apply(this);
		
		
		
		
	}	
		
}
