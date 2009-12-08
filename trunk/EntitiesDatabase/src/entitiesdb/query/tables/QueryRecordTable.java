package entitiesdb.query.tables;

import java.util.ArrayList;
import java.util.Collection;

import entitiesdb.dao.JEDao;
import entitiesdb.types.Record;
import entitiesdb.types.Variable;

public class QueryRecordTable extends ArrayList<Record> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8671790673143331001L;

	
	public String idBound, attributeBound, valueBound = null;
	public int boundCount = 0;

	public ArrayList<String> entitiesSet = new ArrayList<String>();
	
	
	public QueryRecordTable(Object e, Object a, Object v) {
		
		Record recordModel = new Record();
		
		//L'entità è esplicita
		if ( e instanceof String )
			recordModel.setEntityId(e.toString());
		else
			this.setIdBound(((Variable) e).getName());
		
		
		//L'attributo è esplicito
		if ( a instanceof String )
			recordModel.setAttribute(a.toString());
		else
			this.setAttributeBound(((Variable) a).getName());
		
		
		//Il valore è esplicito
		if ( v instanceof String )
			recordModel.setValue(v.toString());
		else
			this.setValueBound(((Variable) v).getName());
		
		
		
		this.addAll(JEDao.getRecords(recordModel));
		
		}
	
	
	public boolean addAll(Collection<? extends Record> c) {
		
		for (Record rec: c ) {
			if(!entitiesSet.contains(rec.getEntityId())) entitiesSet.add(rec.getEntityId());
			this.add(rec);
		}
		return true;
	}
	
	
	public String getIdBound() {
		return idBound;
	}


	public void setIdBound(String idBound) {
		this.boundCount++;
		this.idBound = idBound;
	}


	public String getAttributeBound() {
		return attributeBound;
	}


	public void setAttributeBound(String attributeBound) {
		this.boundCount++;
		this.attributeBound = attributeBound;
	}


	public String getValueBound() {
		return valueBound;
	}


	public void setValueBound(String valueBound) {
		this.boundCount++;
		this.valueBound = valueBound;
	}
	
	
	
	
	
	public ArrayList<String> getEntitiesSet() {
		return entitiesSet;
	}


	public void setEntitiesSet(ArrayList<String> entitiesSet) {
		this.entitiesSet = entitiesSet;
	}


	public String toString() {
		

		String out = "Id bound: " + idBound + " | Attribute Bound: "+ attributeBound + " | Value Bound: " +valueBound +"\n";

		out += "Entity presenti: "+entitiesSet+"\n";
		
		for (int i = 0 ; i < this.size() ; i++)
			out+= this.get(i) + "\n";
		
		out+="-----------";
		
		return out;
	}
	
	
	public String [][] getMatrix() {
		
		String [][] out = new String[size()][3]; 
		for (int i = 0 ; i < size() ; i ++) {
			out[i][0] = this.get(i).getEntityId();
			out[i][1] = this.get(i).getAttribute();
			out[i][2] = this.get(i).getValue();
		}
		
		return out;
	}
	
	public VarsBounder[] getBounds() {
		VarsBounder [] out = new VarsBounder[boundCount];
		
		int i = 0; 
		if (this.idBound != null){
			out[i] = new VarsBounder(this.idBound, 0);
			i++;
		}
		if (this.attributeBound != null){
			out[i] = new VarsBounder(this.valueBound, 1);
			i++;
		}
		if (this.valueBound != null){
			out[i] = new VarsBounder(this.valueBound, 2);
			i++;
		}
		return out;
	}
	
	public class VarsBounder {
		
		public int index;
		public String name;
		
		public VarsBounder(String n, int i) {
			index = i;
			name = n;
		}
		
	}
	
	
	
}
