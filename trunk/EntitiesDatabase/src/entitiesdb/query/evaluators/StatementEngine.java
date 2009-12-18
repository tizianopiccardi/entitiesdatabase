package entitiesdb.query.evaluators;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.language.analysis.DepthFirstAdapter;
import entitiesdb.language.node.AAttribute;
import entitiesdb.language.node.ABodyOptdefinition;
import entitiesdb.language.node.AEntityValue;
import entitiesdb.language.node.AEntitybody;
import entitiesdb.language.node.AEntitypattern;
import entitiesdb.language.node.AHead;
import entitiesdb.language.node.AIdeAttributetype;
import entitiesdb.language.node.AIdeEntitytype;
import entitiesdb.language.node.AListAttributes;
import entitiesdb.language.node.ASingleAttributes;
import entitiesdb.language.node.AStringValue;
import entitiesdb.language.node.AVariableAttributetype;
import entitiesdb.language.node.AVariableEntitytype;
import entitiesdb.language.node.AVariableValue;
import entitiesdb.query.QueryEnvironment;
import entitiesdb.query.StatementBody;
import entitiesdb.query.StatementProperty;
import entitiesdb.query.StatementPropertyList;
import entitiesdb.types.Variable;

public class StatementEngine extends DepthFirstAdapter{

	EntitiesDAO dao = null;
	QueryEnvironment env = null;
	
	public StatementEngine(EntitiesDAO d, QueryEnvironment env) {
		this.dao = d;
		this.env = env;
	}
	
	
	
	
	public void caseAHead(AHead node) {
		node.getEntitytype().apply(this);
		node.getEntitybody().apply(this);
		
		//Ottengo il riferimento al entity
		Object entity = env.getNodeVal(node.getEntitytype());

		//Ottengo la lista delle proprietà che deve avere
		StatementPropertyList propertyList = (StatementPropertyList) env.getNodeVal(node.getEntitybody());
		
		env.setNodeVal(node, new StatementBody(entity, propertyList));
	}	
	
	
	public void caseAEntitypattern(AEntitypattern node) {
		//System.out.println("QueryEngine.caseAEntitypattern()");

		node.getEntitytype().apply(this);
		node.getEntitybody().apply(this);
		
		//Ottengo il riferimento al entity
		Object entity = env.getNodeVal(node.getEntitytype());

		//Ottengo la lista delle proprietà che deve avere
		StatementPropertyList propertyList = (StatementPropertyList) env.getNodeVal(node.getEntitybody());
		
		env.setNodeVal(node, new StatementBody(entity, propertyList));
		

		
	}	
	
	
	
	
	
	
	public void caseAEntitybody(AEntitybody node) {
		//System.out.println("QueryEngine.caseAEntitybody()");
		
		node.getAttributes().apply(this);
		env.setNodeVal(node, env.getNodeVal(node.getAttributes()));

	}
	
	

	
	public void caseASingleAttributes(ASingleAttributes node) {
		
		//System.out.println("QueryEngine.caseASingleAttributes()");
		
		node.getAttribute().apply(this);
		
		StatementProperty p = (StatementProperty) env.getNodeVal(node.getAttribute());
		
		
		StatementPropertyList list = new StatementPropertyList();
		list.add(p);
		env.setNodeVal(node, list);

		
	}
	

	public void caseAListAttributes(AListAttributes node) {
		
		//System.out.println("QueryEngine.caseAListAttributes()");
		node.getList().apply(this);
		
		StatementPropertyList list = (StatementPropertyList) env.getNodeVal(node.getList());
		
		node.getAttribute().apply(this);
		
		StatementProperty p = (StatementProperty) env.getNodeVal(node.getAttribute());
		list.add(p);
		
		env.setNodeVal(node, list);

		
	}
	
	
	public void caseAAttribute(AAttribute node) {
		
		//System.out.println("QueryEngine.caseAAttribute()");
		node.getAttributetype().apply(this);
		node.getValue().apply(this);
		
		Object a = env.getNodeVal(node.getAttributetype());
		Object v = env.getNodeVal(node.getValue());
		////System.out.println("Value: " +v);
		
		env.setNodeVal(node, new StatementProperty(a, v));
		////System.out.println("Attribute: " + a);
		
		
	}	
		
	
	
	public void caseAIdeAttributetype(AIdeAttributetype node) {
		
		env.setNodeVal(node, node.getIdentifier().getText());
		
		//System.out.println("QueryEngine.caseAIdeAttributetype()");
		
	}	
	
	public void caseAVariableAttributetype(AVariableAttributetype node) {
		
		//System.out.println("QueryEngine.caseAVariableAttributetype()");
		
		String e = node.getVariable().getText();
		env.setNodeVal(node, new Variable(e));
	}	
	
	
	
	public void caseAEntityValue(AEntityValue node) {
		
		//System.out.println("QueryEngine.caseAEntityValue()");
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
		
		//System.out.println("QueryEngine.caseAStringValue()");

		env.setNodeVal(node, node.getString().getText());
		
	}	
	


	public void caseAVariableValue(AVariableValue node) {
		
		//System.out.println("QueryEngine.AVariableValue()");
		
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
		
		//System.out.println("QueryEngine.caseABodyOptdefinition()");
		
		
	}		
	
	
	public void caseAVariableEntitytype(AVariableEntitytype node) {
		
		//System.out.println("QueryEngine.caseAVariableEntitytype()");
		String e = node.getVariable().getText();
		env.setNodeVal(node, new Variable(e));
		
	}
	
	public void caseAIdeEntitytype(AIdeEntitytype node) {
		
		//System.out.println("QueryEngine.caseAIdeEntitytype()");
		
		env.setNodeVal(node, node.getIdentifier().getText());
		
	}
	
	
}
