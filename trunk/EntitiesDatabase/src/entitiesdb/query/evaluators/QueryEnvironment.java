package entitiesdb.query.evaluators;

import java.util.Enumeration;
import java.util.Hashtable;

import entitiesdb.language.node.Node;

public class QueryEnvironment {
	
	private Hashtable<Node, Object> evaluatedNodes = new Hashtable<Node, Object>();

	public void setNodeVal(Node k, Object o) {
		evaluatedNodes.put(k, o);

	}
	
	
	public void dump() {
		Enumeration<Node> keys = evaluatedNodes.keys();
		while( keys.hasMoreElements() ) {
		  Object key = keys.nextElement();
		  Object value = evaluatedNodes.get(key);
		  System.out.println(key + "= " + value );
		}
		 System.out.println("\n");
	}
	
	public Object getNodeVal(Node k) {
		Object o = evaluatedNodes.get(k);
		//evaluatedNodes.remove(k);
		return o;
	}

	
}
