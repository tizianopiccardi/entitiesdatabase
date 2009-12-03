package entitiesdb.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import entitiesdb.record.EntityId;

public class EntitiesArrayList extends ArrayList<EntityId>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7859091787871906635L;

	
	public boolean addAll(Collection<? extends EntityId> c) {
	    Iterator<? extends EntityId> listIte = c.iterator();
	    while ( listIte.hasNext() ){
	      this.add(listIte.next());
	    }
		return true;
	}
	
	public boolean add(EntityId e) {
		
		if (!this.contains(e))
			return super.add(e);
		
		return false;
		
	}
	
	
}
