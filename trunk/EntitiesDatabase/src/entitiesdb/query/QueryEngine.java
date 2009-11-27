package entitiesdb.query;

import java.util.Hashtable;

import entitiesdb.language.analysis.DepthFirstAdapter;
import entitiesdb.language.node.*;
import entitiesdb.record.EntityId;
import entitiesdb.record.Value;
import entitiesdb.record.Variable;
import entitiesdb.record.Value.ValueType;

public class QueryEngine extends DepthFirstAdapter {


	private QueryEnvironment env = new QueryEnvironment();
	
	public QueryEngine() {
		super();
	}
	
	/*
	public void caseASingleBody(ASingleBody node) {
		node.getEntity().apply(this);
		
	}
	*/
	public void caseADefEntity(ADefEntity node) {
		EntityId e = (EntityId) env.getNodeVal(node.getVartype());
		
		
	}
	
/*	public void caseAIdEntity(AIdEntity node) {
		//System.out.println(node.getAttributes());
	}
	
	public void caseAStringValue(AStringValue node) {
		//System.out.println(node.getAttributes());
	}*/
	
	public void caseAVariableVartype(AVariableVartype node) {
		env.setNodeVal(node, new Variable());
	}
	
	
	/*
	public void caseAAtomicValuetype(AAtomicValuetype node) {
		evaluatedNodes.put(node, new Value(node.getString().toString(), ValueType.ATOM));
	}*/
	/*
	public void caseAAtomicValuetype(AAtomicValuetype node) {
		evaluatedNodes.put(node, new Value(node.getString().toString(), ValueType.ATOM));
	}
	
	
	public void caseAEntityValuetype(AEntityValuetype node) {
		evaluatedNodes.put(node, new EntityId(node.getIdentifier().toString()));
	}
	
	
	
	
	
	public void caseASinglePattern(ASinglePattern node) {
		node.getValuetype().apply(this);
		System.out.println(node.getValuetype());
	}
	*/
	
}
