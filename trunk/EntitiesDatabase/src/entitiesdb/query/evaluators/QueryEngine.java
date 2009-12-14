package entitiesdb.query.evaluators;

import java.util.ArrayList;

import entitiesdb.language.analysis.DepthFirstAdapter;
import entitiesdb.language.node.*;
import entitiesdb.query.EntitiesArrayList;
import entitiesdb.query.QueryEnvironment;
import entitiesdb.query.QueryProperty;
import entitiesdb.query.tables.DynamicTable;
import entitiesdb.query.tables.QueryRecordMatrix;
import entitiesdb.types.Variable;




public class QueryEngine extends DepthFirstAdapter {

	
	private DynamicTable resultEnvironment = new DynamicTable();

	private QueryEnvironment env = new QueryEnvironment();
	
	public QueryEngine() {
		super();
	}
	
	
	public void caseAQueryMain(AQueryMain node) {
		node.getQuery().apply(this);
		System.out.println(this.resultEnvironment);
	}
	
	

	public void caseASimpleQuery(ASimpleQuery node) {
		//Valuto il body
		node.getBody().apply(this);
	}
	
	
	
	public void caseASingleBody(ASingleBody node) {
		
		//(_ : _)
		System.out.println("QueryEngine.caseASingleBody()");
		
		
		node.getEntitypattern().apply(this);
		
		//EntityFinder entityF = (EntityFinder) env.getNodeVal(node.getEntitypattern());
		//entityF.getMatchingEntities();
		//System.out.println(entityF);
	}	
	
	public void caseAListBody(AListBody node) {
		
		//(_ : _, ...)
		
		node.getEntitypattern().apply(this);
		
		//EntityFinder entityF = (EntityFinder) env.getNodeVal(node.getEntitypattern());
		
		node.getEntitypattern().apply(this);
		
		
		
		
	}	
		
	
	@SuppressWarnings("unchecked")
	public void caseAEntitypattern(AEntitypattern node) {
		System.out.println("QueryEngine.caseAEntitypattern()");

		node.getEntitytype().apply(this);
		node.getEntitybody().apply(this);
		
		//Ottengo il riferimento al entity
		Object entity = env.getNodeVal(node.getEntitytype());

		//Ottengo la lista delle proprietà che deve avere
		ArrayList<QueryProperty> propertyList = (ArrayList<QueryProperty>) env.getNodeVal(node.getEntitybody());
		
		EntitiesArrayList matchingEntities = new EntitiesArrayList();
		
		for (int i = 0 ; i < propertyList.size() ; i++) {
			Object attribute = propertyList.get(i).getAttribute();
			Object value = propertyList.get(i).getValue();
			
			//QueryRecordTable matchingRecords = new QueryRecordTable(entity, attribute, value);
			QueryRecordMatrix matchingRecords = new QueryRecordMatrix(entity, attribute, value);
			//resultTable.process(matchingRecords);
			
			resultEnvironment.join(matchingRecords);
			
			//matchingEntities.doIntersection(matchingRecords.getEntitiesSet());
			//System.out.println(matchingEntities);
		}
		
		env.setNodeVal(node, matchingEntities);
		
		//System.out.println(matchingEntities);
		
		//System.out.println(resultTable);
		/*if (entity instanceof String)
			System.out.println(entity);*/
		
		
		
		
		
		
		/*
		QueryEntityElement entityElement = (QueryEntityElement) env.getNodeVal(node.getEntitytype());
		ArrayList<QueryProperty> attributeList = (ArrayList<QueryProperty>) env.getNodeVal(node.getEntitybody());
		
		
		EntityId entity = null;
		if (entityElement.getType()==QueryElementTypes.IDE)
			entity = (EntityId) entityElement.getEntity();
		
		//System.out.println("h: " +entity);
		//System.out.println("b: " +attributeList);
		
		EntitiesArrayList matchingEntities = new EntitiesArrayList();
		
		for (int i = 0 ; i < attributeList.size() ; i ++) {
			QueryProperty property = attributeList.get(i);
			
			QueryAttributeElement attributeElement = (QueryAttributeElement) property.getAttribute();
			Attribute attribute = null;
			if (property.getAttribute().getType() == QueryElementTypes.IDE)
				attribute = (Attribute) attributeElement.getAttribute();
			
			
			
			QueryValueElement valueElement =  (QueryValueElement) property.getValue();
			Value value = null;
			if (property.getValue().getType() == QueryElementTypes.STRING ||
					property.getValue().getType() == QueryElementTypes.IDE)
				value = (Value) valueElement.getValue();
			
			//System.out.println(entity + " " + attribute+  " "+value);
			//System.out.println(property.getValue());
			//new Record(entity, attribute, value);
			//);
			
			/*ArrayList<Record> records = JEDao.getRecords(new Record(entity, attribute, value));
			
			for (int j = 0 ; j < records.size() ; j++) {
				if (!matchingEntities.contains(records.get(j).getEntityId())) {
					matchingEntities.add(records.get(j).getEntityId());
				}
			}*/
		//	EntitiesArrayList entities = JEDao.getEntities(new Record(entity.getId(), attribute.getLabel(), value.getValue(),ValueType.ATOM));
			
			
			//matchingEntities.addAll(entities);
			
		//	matchingEntities.retainAll(entities);
			
			//System.out.println(new Record(entity, attribute, value));
			
			//System.out.println(entities);
		/*}
		System.out.println(matchingEntities);
		/*EntityId e = (EntityId) env.getNodeVal(node.getEntitytype());
		ArrayList<Property> b = (ArrayList<Property>) env.getNodeVal(node.getEntitybody());
		
		EntityFinder eF = new EntityFinder(e, b);

		env.setNodeVal(node, eF);*/
		
	}		
	
	
	public void caseAEntitybody(AEntitybody node) {
		System.out.println("QueryEngine.caseAEntitybody()");
		
		node.getAttributes().apply(this);
		
		env.setNodeVal(node, env.getNodeVal(node.getAttributes()));

	}
	
	

	
	public void caseASingleAttributes(ASingleAttributes node) {
		
		System.out.println("QueryEngine.caseASingleAttributes()");
		
		node.getAttribute().apply(this);
		
		QueryProperty p = (QueryProperty) env.getNodeVal(node.getAttribute());
		
		
		ArrayList<QueryProperty> list = new ArrayList<QueryProperty>();
		list.add(p);
		env.setNodeVal(node, list);

		
	}
	
	public void caseAListAttributes(AListAttributes node) {
		
		System.out.println("QueryEngine.caseAListAttributes()");
		node.getList().apply(this);
		
		ArrayList<QueryProperty> list = (ArrayList<QueryProperty>) env.getNodeVal(node.getList());
		
		node.getAttribute().apply(this);
		
		QueryProperty p = (QueryProperty) env.getNodeVal(node.getAttribute());
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
		
		env.setNodeVal(node, new QueryProperty(a, v));
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
	
	
}
