package entitiesdb.query.evaluators;

import java.util.ArrayList;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.language.analysis.DepthFirstAdapter;
import entitiesdb.language.node.AComplexQuery;
import entitiesdb.language.node.AEqualCondition;
import entitiesdb.language.node.AInsertMain;
import entitiesdb.language.node.AListBody;
import entitiesdb.language.node.AListConditions;
import entitiesdb.language.node.AQueryMain;
import entitiesdb.language.node.ASimpleQuery;
import entitiesdb.language.node.ASingleBody;
import entitiesdb.language.node.ASingleConditions;
import entitiesdb.query.ResultSet;
import entitiesdb.query.objects.Condition;
import entitiesdb.query.objects.StatementBody;
import entitiesdb.query.objects.StatementProperty;
import entitiesdb.query.tables.BufferTable;
import entitiesdb.types.Record;




public class QueryEngine extends DepthFirstAdapter {

	public ResultSet resultSet = null;
	
	private QueryEnvironment env = new QueryEnvironment();
	
	private EntitiesDAO dao = null;
	
	public QueryEngine(EntitiesDAO d) {
		super();
		this.dao = d;
	}
	
	public ResultSet getResultSet() {
		return resultSet;
	}

	@SuppressWarnings("unchecked")
	public void caseAInsertMain(AInsertMain node) {
		node.getEntitybody().apply(new StatementEngine(dao, env));
		
		ArrayList<StatementProperty> propertyList = (ArrayList<StatementProperty>) env.getNodeVal(node.getEntitybody());

		for (int i = 0 ; i < propertyList.size() ; i++) {
			Record r = new Record(node.getIdentifier().getText(), 
					(String)propertyList.get(i).getAttribute(), 
					(String)propertyList.get(i).getValue());
			dao.put(r);
		}
		this.resultSet = new ResultSet();
	}
	
	
	public void caseAQueryMain(AQueryMain node) {
		node.getQuery().apply(this);
		this.resultSet = (ResultSet) env.getNodeVal(node.getQuery());
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
	
	@SuppressWarnings("unchecked")
	public void caseAComplexQuery(AComplexQuery node) {
		
		

		
		//It's the same of caseASimpleQuery
		node.getBody().apply(this);
		BufferTable table = (BufferTable) env.getNodeVal(node.getBody());
		
		
		//Here I have the table with no condition, so let's apply them :)
		node.getConditions().apply(this);
		
		table.applyConditions((ArrayList<Condition>) env.getNodeVal(node.getConditions()));
		
		
		node.getHead().apply(new StatementEngine(dao, env));
		StatementBody head = (StatementBody) env.getNodeVal(node.getHead());
		env.setNodeVal(node, new ResultSet(table, head));

	}
	
	public void caseASingleBody(ASingleBody node) {
		
		//(_ : _, ...)
	
		node.getEntitypattern().apply(new StatementEngine(dao, env));
		
		StatementBody stmtBody = (StatementBody) env.getNodeVal(node.getEntitypattern());

		BufferTable table = BufferTablesManager.getNewTable(dao, stmtBody);
		
		env.setNodeVal(node, table);


	}	
	
	public void caseAListBody(AListBody node) {
		
		//(_ : _, ...), (_ : _, ...), ...
		
		node.getBody().apply(this);
		BufferTable table = (BufferTable) env.getNodeVal(node.getBody());
		

		node.getEntitypattern().apply(new StatementEngine(dao, env));
		StatementBody stmtBody = (StatementBody) env.getNodeVal(node.getEntitypattern());
		BufferTable localTable = BufferTablesManager.getNewTable(dao, stmtBody);
		
		table.join(localTable);

		env.setNodeVal(node, table);


	}	

	////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Conditions area
	 */
	
	
	
	/**
	 * This block return an ArrayList of condition
	 */
	public void caseASingleConditions(ASingleConditions node) {
		node.getCondition().apply(this);
		
		ArrayList<Condition> cList = new ArrayList<Condition>();
		cList.add((Condition) env.getNodeVal(node.getCondition()));
		
		env.setNodeVal(node, cList);
		
	}
	
	@SuppressWarnings("unchecked")
	public void caseAListConditions(AListConditions node) {
		
		node.getConditions().apply(this);
		node.getCondition().apply(this);
		
		
		ArrayList<Condition> cList = new ArrayList<Condition>();
		cList.add((Condition) env.getNodeVal(node.getCondition()));
		cList.addAll((ArrayList<Condition>) env.getNodeVal(node.getConditions()));

		env.setNodeVal(node, cList);
	}

	
	public void caseAEqualCondition(AEqualCondition node) {
		node.getValue().apply(new StatementEngine(dao, env));
		Condition c = new Condition(node.getVariable().getText(), env.getNodeVal(node.getValue()));
		env.setNodeVal(node, c);
	}
	
	
	
	

	
}