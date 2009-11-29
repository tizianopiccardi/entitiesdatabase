package entitiesdb.query;


import java.util.ArrayList;

import entitiesdb.dao.DaoException;
import entitiesdb.dao.JEDao;
import entitiesdb.language.analysis.DepthFirstAdapter;
import entitiesdb.language.node.*;
import entitiesdb.record.Attribute;
import entitiesdb.record.EntityId;
import entitiesdb.record.Record;
import entitiesdb.record.Value;
import entitiesdb.record.Variable;
import entitiesdb.record.Value.ValueType;

public class QueryEngine extends DepthFirstAdapter {


	private QueryEnvironment env = new QueryEnvironment();
	private JEDao dao = null;
	
	public QueryEngine(JEDao dao) {
		super();
		this.dao = dao;
		try {
			dao.open();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@SuppressWarnings("unchecked")
	public void caseASimpleInsert(ASimpleInsert node) {
		
		node.getInsertbody().apply(this);
		
		ArrayList<Record> list = (ArrayList<Record>) env.getNodeVal(node.getInsertbody());
		
		String eId = node.getIdentifier().getText();
		for (Record r : list) {
			r.setEntityId(new EntityId(eId));
			try {
				dao.store(r);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}	

	public void caseAAttributeInsertbody(AAttributeInsertbody node) {
		node.getAttributes().apply(this);
		
		env.setNodeVal(node, env.getNodeVal(node.getAttributes()));
	}
	
	public void caseASingleAttributes(ASingleAttributes node) {
		node.getAttribute().apply(this);

		ArrayList<Record> singleElement = new ArrayList<Record>();
		singleElement.add((Record) env.getNodeVal(node.getAttribute()));
		env.setNodeVal(node, singleElement);
	}	
	
	@SuppressWarnings("unchecked")
	public void caseAListAttributes(AListAttributes node) {

		node.getList().apply(this);
		ArrayList<Record> list = (ArrayList<Record>) env.getNodeVal(node.getList());
		
		node.getAttribute().apply(this);

		Record r = (Record) env.getNodeVal(node.getAttribute());
		list.add(r);
		env.setNodeVal(node, list);
		
	}
	
	
	public void caseAElementAttribute(AElementAttribute node) {

		node.getVartype().apply(this);
		node.getValue().apply(this);
		
		Attribute a = new Attribute( env.getNodeVal(node.getVartype()).toString());
		Value v = (Value) env.getNodeVal(node.getValue());

		env.setNodeVal(node, new Record(null, a, v));
		
	}
	
	public void caseAIdeVartype(AIdeVartype node) {
		env.setNodeVal(node, node.getIdentifier().getText());
	}	
	
	public void caseAStringValue(AStringValue node) {
		env.setNodeVal(node, new Value(node.getString().getText(), ValueType.ATOM));
	}	
	
	public void caseAEntityValue(AEntityValue node) {
		node.getEntity().apply(this);
		env.setNodeVal(node, env.getNodeVal(node.getEntity()));
	}
	
	public void caseAIdEntity(AIdEntity node) {
		node.getVartype().apply(this);
		env.setNodeVal(node, new Value(env.getNodeVal(node.getVartype()).toString(), ValueType.ENTITY));
	}		
}
