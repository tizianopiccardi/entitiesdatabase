package entitiesdb.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;



public class EntitiesArrayList extends ArrayList<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7859091787871906635L;

	
	public boolean doIntersection(Collection<? extends String> c) {
	    Iterator<? extends String> listIte = c.iterator();
	    
	    //int i=-1;
	    while ( listIte.hasNext() ){
	     
	    	//if ((i=this.indexOf(listIte.next()))==-1)    	
	    	//	this.remove(i);
	    		
	    		this.add(listIte.next());
	    }
	    
	    this.retainAll(c);
	    
		return true;
	}
	
	public boolean add(String e) {
		
		if (!this.contains(e))
			return super.add(e);
		
		return false;
		
	}
	
	
}
