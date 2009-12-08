package entitiesdb.query;

import java.util.Enumeration;
import java.util.Hashtable;

import entitiesdb.language.node.Node;

public class QueryEnvironment {
	
	private Hashtable<Node, Object> evaluatedNodes = new Hashtable<Node, Object>();
	private Hashtable<String, Object> localEnvironment = new Hashtable<String, Object>();
	
	public void setNodeVal(Node k, Object o) {
		evaluatedNodes.put(k, o);

	}
	
	
	public void dump() {
		Enumeration keys = evaluatedNodes.keys();
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
	
	public void setVariable(String k, Object o) {
		localEnvironment.put(k, o);
	}
	
	public Object getValiable(Node k) {
		return localEnvironment.get(k);
	}
	
	
}