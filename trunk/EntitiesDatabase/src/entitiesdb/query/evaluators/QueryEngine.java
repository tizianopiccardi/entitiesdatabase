package entitiesdb.query.evaluators;

import java.util.ArrayList;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.dao.ApproximateQueryStore.EntityAndAccuracyList;
import entitiesdb.language.analysis.DepthFirstAdapter;
import entitiesdb.language.node.AApproxMain;
import entitiesdb.language.node.AAscOrderby;
import entitiesdb.language.node.AComplexQuery;
import entitiesdb.language.node.ADeleteMain;
import entitiesdb.language.node.ADesOrderby;
import entitiesdb.language.node.AEqualCondition;
import entitiesdb.language.node.AInsertMain;
import entitiesdb.language.node.AListBody;
import entitiesdb.language.node.AListConditions;
import entitiesdb.language.node.AQueryMain;
import entitiesdb.language.node.ASimpleQuery;
import entitiesdb.language.node.ASingleBody;
import entitiesdb.language.node.ASingleConditions;
import entitiesdb.query.ResultSet;
import entitiesdb.query.ResultSetApproximate;
import entitiesdb.query.ResultSetStandard;
import entitiesdb.query.approximate.ApproximationManager;
import entitiesdb.query.objects.Condition;
import entitiesdb.query.objects.OrderBy;
import entitiesdb.query.objects.StatementBody;
import entitiesdb.query.objects.StatementProperty;
import entitiesdb.query.objects.OrderBy.OrderDirection;
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
		
		if(node.getEntitybody()!=null) {
			node.getEntitybody().apply(new StatementEngine(dao, env));

			ArrayList<StatementProperty> propertyList = (ArrayList<StatementProperty>) env.getNodeVal(node.getEntitybody());
	
			for (int i = 0 ; i < propertyList.size() ; i++) {
				Record r = new Record(node.getIdentifier().getText(), 
						(String)propertyList.get(i).getAttribute(), 
						(String)propertyList.get(i).getValue());
				dao.put(r);
			}
		}
		else
			dao.addEntity(node.getIdentifier().getText());
		
		this.resultSet = new ResultSetStandard();
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public void caseADeleteMain(ADeleteMain node) {
		
		if(node.getEntitybody()!=null) {
			node.getEntitybody().apply(new StatementEngine(dao, env));

			ArrayList<StatementProperty> propertyList = (ArrayList<StatementProperty>) env.getNodeVal(node.getEntitybody());
	
			for (int i = 0 ; i < propertyList.size() ; i++) {
				Record r = new Record(node.getIdentifier().getText(), 
						(String)propertyList.get(i).getAttribute(), 
						(String)propertyList.get(i).getValue());
				dao.deleteRecord(r);
			}
		}
		else
			dao.deleteEntity(node.getIdentifier().getText());
		
		this.resultSet = new ResultSetStandard();
	}
	
	
	public void caseAQueryMain(AQueryMain node) {
		node.getQuery().apply(this);
		this.resultSet = (ResultSetStandard) env.getNodeVal(node.getQuery());
		
	}
	
	

	public void caseASimpleQuery(ASimpleQuery node) {
		
		node.getBody().apply(this);

		//Table
		BufferTable table = (BufferTable) env.getNodeVal(node.getBody());

		
		if (node.getOrderby()!=null) {
			node.getOrderby().apply(this);
			OrderBy orderBy = (OrderBy) env.getNodeVal(node.getOrderby());
			if (orderBy != null)
				table.sort(orderBy.getVarName(), (orderBy.getDirection()==OrderDirection.ASC ? true : false) );
		}
		
		//head is: $x(has: $y, ...) :- ...
		node.getHead().apply(new StatementEngine(dao, env));
		StatementBody head = (StatementBody) env.getNodeVal(node.getHead());
		
		//Set this node as a new result set
		ResultSetStandard queryRes = new ResultSetStandard(table, head);
		if (node.getDistinct()!=null) 
			queryRes.distinct();
		
		env.setNodeVal(node, queryRes);

	}
	
	@SuppressWarnings("unchecked")
	public void caseAComplexQuery(AComplexQuery node) {
		
		//It's the same of caseASimpleQuery
		node.getBody().apply(this);
		BufferTable table = (BufferTable) env.getNodeVal(node.getBody());
		
		
		//For the moment the table is without condition, so let's apply them :)
		node.getConditions().apply(this);
		table.applyConditions((ArrayList<Condition>) env.getNodeVal(node.getConditions()));
		

		if (node.getOrderby()!=null) {
			node.getOrderby().apply(this);
			OrderBy orderBy = (OrderBy) env.getNodeVal(node.getOrderby());
			if (orderBy != null)
				table.sort(orderBy.getVarName(), (orderBy.getDirection()==OrderDirection.ASC ? true : false) );
		}
		
		
		node.getHead().apply(new StatementEngine(dao, env));
		StatementBody head = (StatementBody) env.getNodeVal(node.getHead());
		
		ResultSetStandard queryRes = new ResultSetStandard(table, head);
		if (node.getDistinct()!=null) 
			queryRes.distinct();
		env.setNodeVal(node, new ResultSetStandard(table, head));

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
		
		table.cartesian(localTable);

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
	
	
	
	public void caseAAscOrderby(AAscOrderby node) {
		env.setNodeVal(node, new OrderBy(node.getVariable().getText(), OrderDirection.ASC));
	}

	public void caseADesOrderby(ADesOrderby node) {
		env.setNodeVal(node, new OrderBy(node.getVariable().getText(), OrderDirection.DESC));
	}
	
	///////////////////////////////////////////////////////
	
	public void caseAApproxMain(AApproxMain node) {
		
		node.getEntitypattern().apply(new StatementEngine(dao, env));
		
		StatementBody stmtBody = (StatementBody) env.getNodeVal(node.getEntitypattern());
		
		int limit = -1;
		if (node.getLimit()!=null)
			limit = Integer.parseInt(node.getLimit().getText());
	/*
		 resultSet = ApproximationManager.
													getApproximateResultSet(dao, stmtBody).
													getResultsList(limit);
		
		ResultSetApproximate rs = new ResultSetApproximate(dao, resultSet);
		
		this.resultSet = rs;*/
		EntityAndAccuracyList resultSet = ApproximationManager.getApproximateResultSet(dao, stmtBody, limit);
		ResultSetApproximate rs = new ResultSetApproximate(dao, resultSet);
		this.resultSet = rs;
		//ApproximationManager.getApproximateResultSet(dao, stmtBody);
		//System.out.println(dao.getApproximateStore());
		
	}
}
