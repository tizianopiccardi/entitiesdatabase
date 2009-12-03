package entitiesdb.query;

import java.util.ArrayList;
import entitiesdb.dao.JEDao;
import entitiesdb.language.analysis.DepthFirstAdapter;
import entitiesdb.language.node.*;
import entitiesdb.query.QueryEnvironment;
import entitiesdb.record.Attribute;
import entitiesdb.record.EntityId;
import entitiesdb.record.Record;
import entitiesdb.record.Value;
import entitiesdb.record.Value.ValueType;



public class QueryEngine extends DepthFirstAdapter {


	private QueryEnvironment env = new QueryEnvironment();
	//private JEDao dao;
	
	public QueryEngine() {
		super();
	//	this.dao = dao;
	}
	
	
	public void caseAQueryMain(AQueryMain node) {
		node.getQuery().apply(this);
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
			EntitiesArrayList entities = JEDao.getEntities(new Record(entity, attribute, value));
			
			
			matchingEntities.addAll(entities);
			
			matchingEntities.retainAll(entities);
			
			//System.out.println(new Record(entity, attribute, value));
			
			//System.out.println(entities);
		}
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
		
		Attribute a = new Attribute(node.getIdentifier().getText());
		env.setNodeVal(node, new QueryAttributeElement(a, QueryElementTypes.IDE));
		
		System.out.println("QueryEngine.caseAIdeAttributetype()");
		
	}	
	
	public void caseAVariableAttributetype(AVariableAttributetype node) {
		
		System.out.println("QueryEngine.caseAVariableAttributetype()");
		
		String e = node.getVariable().getText();
		env.setNodeVal(node, new QueryAttributeElement(e, QueryElementTypes.VAR));
	}	
	
	
	
	public void caseAEntityValue(AEntityValue node) {
		
		System.out.println("QueryEngine.caseAEntityValue()");
		node.getOptdefinition().apply(this);
		node.getIdentifier().apply(this);
		
		Object optBody = env.getNodeVal(node.getOptdefinition());
		
		if (optBody==null) {
			//NO FORMA x(...)
			//String v = node.getIdentifier().getText();
			Value v = new Value(node.getIdentifier().getText(), ValueType.ENTITY);
			env.setNodeVal(node, new QueryValueElement(v, QueryElementTypes.IDE));
			
		}
		else {
			//riproduci quello che c'è all'inizio per creare il set di entity
		}
		
		
	}
	
	
	public void caseAStringValue(AStringValue node) {
		
		System.out.println("QueryEngine.caseAStringValue()");
		//System.out.println("SONO QUI");
		String value = node.getString().getText();
		Value v = new Value(value.substring(1, value.length()-1), ValueType.ATOM);
		env.setNodeVal(node, new QueryValueElement(v, QueryElementTypes.STRING));
		
	}	
	


	public void caseAVariableValue(AVariableValue node) {
		
		System.out.println("QueryEngine.AVariableValue()");
		
		node.getOptdefinition().apply(this);
		
		
		Object optBody = env.getNodeVal(node.getOptdefinition());
		
		if (optBody==null) {
			//NO FORMA x(...)
			String v = node.getVariable().getText();
			env.setNodeVal(node, new QueryValueElement(v, QueryElementTypes.VAR));
			
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
		env.setNodeVal(node, new QueryEntityElement(e, QueryElementTypes.VAR));
		
	}
	
	public void caseAIdeEntitytype(AIdeEntitytype node) {
		
		System.out.println("QueryEngine.caseAIdeEntitytype()");
		
		EntityId e = new EntityId(node.getIdentifier().getText());
		env.setNodeVal(node, new QueryEntityElement(e, QueryElementTypes.IDE));
		
	}
	
	
}
