package entitiesdb.query.tables;

import java.util.ArrayList;
import java.util.Collection;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.types.Record;
import entitiesdb.types.Variable;

public class QueryRecordMatrix extends ArrayList<String[]>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8671790673143331001L;


	//Area certa
	ArrayList<VarsBounder> varBounder = new ArrayList<VarsBounder>();
	EntitiesDAO dao = null;
	
	public QueryRecordMatrix(EntitiesDAO dao, Object e, Object a, Object v) {
		this.dao = dao;
		
		Record patternRecord = new Record();
		
		//L'entità è esplicita
		if ( e instanceof String )
			patternRecord.setEntityId(e.toString());
		else
			this.addBound(((Variable) e).getName(), 0);
		
		
		//L'attributo è esplicito
		if ( a instanceof String )
			patternRecord.setAttribute(a.toString());
		else
			this.addBound(((Variable) a).getName(), 1);
		
		
		//Il valore è esplicito
		if ( v instanceof String )
			patternRecord.setValue(v.toString());
		else
			this.addBound(((Variable) v).getName(), 2);
		
		
		
		this.fillMatrix(dao.getRecords(patternRecord));
		
	}
	

	
	public void addBound(String varName, int index) {
		varBounder.add(new VarsBounder(varName, index));
	}
	
	
	
	public void fillMatrix(Collection<? extends Record> c) {
		String [] recArray;
		for (Record r : c) {
			recArray = new String[3];
			recArray[0] = r.getEntityId();
			recArray[1] = r.getAttribute();
			recArray[2] = r.getValue();
			this.add(recArray);
		}
	}
	
	
	public String getEntity(int pos) {
		return this.get(pos)[0];
	}
	
	/*
	
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
	}*/
	
	
	
	/*
	
	public ArrayList<String> getEntitiesSet() {
		return entitiesSet;
	}


	public void setEntitiesSet(ArrayList<String> entitiesSet) {
		this.entitiesSet = entitiesSet;
	}*/

/*
	public String toString() {
		

		String out = "Id bound: " + idBound + " | Attribute Bound: "+ attributeBound + " | Value Bound: " +valueBound +"\n";

		out += "Entity presenti: "+entitiesSet+"\n";
		
		for (int i = 0 ; i < this.size() ; i++)
			out+= this.get(i) + "\n";
		
		out+="-----------";
		
		return out;
	}*/
	
	/*
	public String [][] getMatrix() {
		
		String [][] out = new String[size()][3]; 
		for (int i = 0 ; i < size() ; i ++) {
			out[i][0] = this.get(i).getEntityId();
			out[i][1] = this.get(i).getAttribute();
			out[i][2] = this.get(i).getValue();
		}
		
		return out;
	}*/
	/*
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
	}*/
	
	
	public ArrayList<VarsBounder> getBounds() {
		return this.varBounder;
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
