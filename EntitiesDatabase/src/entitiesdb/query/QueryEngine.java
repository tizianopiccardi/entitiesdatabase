package entitiesdb.query;

import java.util.Hashtable;

import entitiesdb.language.analysis.DepthFirstAdapter;
import entitiesdb.language.node.*;
import entitiesdb.record.EntityId;
import entitiesdb.record.Value;
import entitiesdb.record.Value.ValueType;

public class QueryEngine extends DepthFirstAdapter {

	private Hashtable<Object, Object> evaluatedNodes = new Hashtable<Object, Object>();
	
	
	public QueryEngine() {
		super();
	}
	
	
	
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
	
	
}
