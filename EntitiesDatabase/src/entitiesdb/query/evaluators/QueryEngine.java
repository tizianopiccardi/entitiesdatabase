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
import entitiesdb.query.tables.DynamicTable;
import entitiesdb.query.tables.QueryRecordMatrix;
import entitiesdb.types.Record;




public class QueryEngine extends DepthFirstAdapter {

	
	private DynamicTable resultEnvironment = new DynamicTable();
	private QueryEnvironment env = new QueryEnvironment();
	
	private EntitiesDAO dao = null;
	
	public QueryEngine(EntitiesDAO d) {
		super();
		this.dao = d;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public void caseAInsertMain(AInsertMain node) {
		node.getEntitybody().apply(new StatementEngine(dao, env));
		
		ArrayList<StatementProperty> propertyList = (ArrayList<StatementProperty>) env.getNodeVal(node.getEntitybody());
		
		dao.addEntity(node.getIdentifier().getText());
		
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
		System.out.println("QueryEngine.caseASingleBody()");
		
		
		node.getEntitypattern().apply(new StatementEngine(dao, env));
		
		
		StatementBody stmtBody = (StatementBody) env.getNodeVal(node.getEntitypattern());
		
		System.out.println(stmtBody);
		
		
		/////////////////////////////////////////////////
		//EntitiesArrayList matchingEntities = new EntitiesArrayList();
		
		for (int i = 0 ; i < stmtBody.getProperties().size() ; i++) {
			//System.out.println(i);
			Object attribute = stmtBody.getProperties().get(i).getAttributeObject();
			Object value = stmtBody.getProperties().get(i).getValueObject();
			
			//QueryRecordTable matchingRecords = new QueryRecordTable(entity, attribute, value);
			QueryRecordMatrix matchingRecords = new QueryRecordMatrix(dao, stmtBody.getEntityObject(), attribute, value);
			//resultTable.process(matchingRecords);
		
			resultEnvironment.join(matchingRecords);

		}
		
		//env.setNodeVal(node, matchingEntities);
		////////////////////////////////////////////////////////////////////////
		System.out.println(resultEnvironment);
		
		System.out.println(dao);
		//EntityFinder entityF = (EntityFinder) env.getNodeVal(node.getEntitypattern());
		//entityF.getMatchingEntities();
		//System.out.println(entityF);
	}	
	
	public void caseAListBody(AListBody node) {
		
		//(_ : _, ...), (_ : _, ...), ...
		
		node.getEntitypattern().apply(this);
		
		//EntityFinder entityF = (EntityFinder) env.getNodeVal(node.getEntitypattern());
		
		node.getEntitypattern().apply(this);
		
		
		
		
	}	
		
	/*
	@SuppressWarnings("unchecked")
	public void caseAEntitypattern(AEntitypattern node) {
		System.out.println("QueryEngine.caseAEntitypattern()");

		node.getEntitytype().apply(this);
		node.getEntitybody().apply(this);
		
		//Ottengo il riferimento al entity
		Object entity = env.getNodeVal(node.getEntitytype());

		//Ottengo la lista delle proprietà che deve avere
		ArrayList<StatementProperty> propertyList = (ArrayList<StatementProperty>) env.getNodeVal(node.getEntitybody());
		
		EntitiesArrayList matchingEntities = new EntitiesArrayList();
		
		for (int i = 0 ; i < propertyList.size() ; i++) {
			Object attribute = propertyList.get(i).getAttribute();
			Object value = propertyList.get(i).getValue();
			
			//QueryRecordTable matchingRecords = new QueryRecordTable(entity, attribute, value);
			QueryRecordMatrix matchingRecords = new QueryRecordMatrix(dao, entity, attribute, value);
			//resultTable.process(matchingRecords);
			
			resultEnvironment.join(matchingRecords);

		}
		
		env.setNodeVal(node, matchingEntities);
		

		
	}		
	/*
	
	public void caseAEntitybody(AEntitybody node) {
		System.out.println("QueryEngine.caseAEntitybody()");
		
		node.getAttributes().apply(this);
		
		env.setNodeVal(node, env.getNodeVal(node.getAttributes()));

	}
	
	

	
	public void caseASingleAttributes(ASingleAttributes node) {
		
		System.out.println("QueryEngine.caseASingleAttributes()");
		
		node.getAttribute().apply(this);
		
		StatementProperty p = (StatementProperty) env.getNodeVal(node.getAttribute());
		
		
		ArrayList<StatementProperty> list = new ArrayList<StatementProperty>();
		list.add(p);
		env.setNodeVal(node, list);

		
	}
	
	@SuppressWarnings("unchecked")
	public void caseAListAttributes(AListAttributes node) {
		
		System.out.println("QueryEngine.caseAListAttributes()");
		node.getList().apply(this);
		
		ArrayList<StatementProperty> list = (ArrayList<StatementProperty>) env.getNodeVal(node.getList());
		
		node.getAttribute().apply(this);
		
		StatementProperty p = (StatementProperty) env.getNodeVal(node.getAttribute());
		list.add(p);
		
		env.setNodeVal(node, list);

		
	}
	
	
	public void caseAAttribute(AAttribute node) {
		
		System.out.println("QueryEngine.caseAAttribute()");
		node.getAttributetype().apply(this);
		node.getValue().apply(this);
		
		Object a = env.getNodeVal(node.getAttributetype());
		Object v = env.getNodeVal(node.getValue());
		//System.out.println("Value: " +v);
		
		env.setNodeVal(node, new StatementProperty(a, v));
		//System.out.println("Attribute: " + a);
		
		
	}	
		
	
	
	public void caseAIdeAttributetype(AIdeAttributetype node) {
		
		env.setNodeVal(node, node.getIdentifier().getText());
		
		System.out.println("QueryEngine.caseAIdeAttributetype()");
		
	}	
	
	public void caseAVariableAttributetype(AVariableAttributetype node) {
		
		System.out.println("QueryEngine.caseAVariableAttributetype()");
		
		String e = node.getVariable().getText();
		env.setNodeVal(node, new Variable(e));
	}	
	
	
	
	public void caseAEntityValue(AEntityValue node) {
		
		System.out.println("QueryEngine.caseAEntityValue()");
		node.getOptdefinition().apply(this);
		node.getIdentifier().apply(this);
		
		Object optBody = env.getNodeVal(node.getOptdefinition());
		
		if (optBody==null) {
			//NO FORMA x(...)
			env.setNodeVal(node, node.getIdentifier().getText());
		}
		else {
			//riproduci quello che c'è all'inizio per creare il set di entity
		}
		
		
	}
	
	
	public void caseAStringValue(AStringValue node) {
		
		System.out.println("QueryEngine.caseAStringValue()");

		env.setNodeVal(node, node.getString().getText());
		
	}	
	


	public void caseAVariableValue(AVariableValue node) {
		
		System.out.println("QueryEngine.AVariableValue()");
		
		node.getOptdefinition().apply(this);
		
		
		Object optBody = env.getNodeVal(node.getOptdefinition());
		
		if (optBody==null) {
			//NO FORMA x(...)

			env.setNodeVal(node, new Variable(node.getVariable().getText()));
			
		}
		else {
			//riproduci quello che c'è all'inizio per creare il set di entity
		}
			
		
		
		
		

	}		
	
	
	public void caseABodyOptdefinition(ABodyOptdefinition node) {
		
		System.out.println("QueryEngine.caseABodyOptdefinition()");
		
		
	}		
	
	
	public void caseAVariableEntitytype(AVariableEntitytype node) {
		
		System.out.println("QueryEngine.caseAVariableEntitytype()");
		String e = node.getVariable().getText();
		env.setNodeVal(node, new Variable(e));
		
	}
	
	public void caseAIdeEntitytype(AIdeEntitytype node) {
		
		System.out.println("QueryEngine.caseAIdeEntitytype()");
		
		env.setNodeVal(node, node.getIdentifier().getText());
		
	}
	*/
	
}
