package entitiesdb.query.evaluators;

import java.util.ArrayList;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.language.analysis.DepthFirstAdapter;
import entitiesdb.language.node.AComplexQuery;
import entitiesdb.language.node.AInsertMain;
import entitiesdb.language.node.AListBody;
import entitiesdb.language.node.AQueryMain;
import entitiesdb.language.node.ASimpleQuery;
import entitiesdb.language.node.ASingleBody;
import entitiesdb.query.QueryEnvironment;
import entitiesdb.query.ResultSet;
import entitiesdb.query.StatementBody;
import entitiesdb.query.StatementProperty;
import entitiesdb.query.tables.BufferTable;
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
		System.out.println(env.getNodeVal(node.getQuery()));
	}
	
	

	public void caseASimpleQuery(ASimpleQuery node) {
		
		node.getBody().apply(this);

		//Table
		BufferTable table = (BufferTable) env.getNodeVal(node.getBody());

		
		//head is: $x(has: $y, ...) :- ...
		node.getHead().apply(new StatementEngine(dao, env));
		StatementBody head = (StatementBody) env.getNodeVal(node.getHead());
		
		//Set this node as a new result set
		env.setNodeVal(node, new ResultSet(table, head));

	}
	
	public void caseAComplexQuery(AComplexQuery node) {
		
		
		node.getConditions().apply(this);
		
		//It's the same of caseASimpleQuery
		node.getBody().apply(this);
		BufferTable table = (BufferTable) env.getNodeVal(node.getBody());
		node.getHead().apply(new StatementEngine(dao, env));
		StatementBody head = (StatementBody) env.getNodeVal(node.getHead());
		env.setNodeVal(node, new ResultSet(table, head));

	}
	
	public void caseASingleBody(ASingleBody node) {
		
		//(_ : _, ...)
		//System.out.println("QueryEngine.caseASingleBody()");
		
		
		node.getEntitypattern().apply(new StatementEngine(dao, env));
		
		StatementBody stmtBody = (StatementBody) env.getNodeVal(node.getEntitypattern());

		BufferTable table = BufferTablesManager.getNewTable(dao, stmtBody);
		
		env.setNodeVal(node, table);
		//System.out.println(table);

	}	
	
	public void caseAListBody(AListBody node) {
		
		//(_ : _, ...), (_ : _, ...), ...
		
		node.getEntitypattern().apply(new StatementEngine(dao, env));

		node.getBody().apply(this);
		
		
		
		
	}	
		
}
