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



	VarsBounderList varBounder = new VarsBounderList();
	EntitiesDAO dao = null;
	
	ResultSetInfo resultSetInfo = new ResultSetInfo();
	
	public ResultSetInfo getResultSetInfo() {
		return resultSetInfo;
	}



	public void setResultSetInfo(ResultSetInfo resultSetInfo) {
		this.resultSetInfo = resultSetInfo;
	}



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
		if (v!=null)
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
			//l'entity
			this.getResultSetInfo().add(recArray[0]);
		}
	}
	
	
	public String getEntity(int pos) {
		return this.get(pos)[0];
	}
	public String getAttribute(int pos) {
		return this.get(pos)[1];
	}
	public String getValue(int pos) {
		return this.get(pos)[2];
	}
	
	public VarsBounderList getBounds() {
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
	public class VarsBounderList extends ArrayList<VarsBounder> {
		private static final long serialVersionUID = 9165442400150274840L;
		public void cleanEntityBound() {
			if (this.get(0).index==0)//entity is bounded
				this.remove(0);
		}
		public boolean isAttributeBounded() {
			for (VarsBounder v : this)
				if (v.index==1) return true;
			return false;
		}
		public boolean isEntityBounded() {
			for (VarsBounder v : this)
				if (v.index==0) return true;
			return false;
		}
		public boolean isValueyBounded() {
			for (VarsBounder v : this)
				if (v.index==2) return true;
			return false;
		}
		public String getAttributeBound() {
			for (VarsBounder v : this)
				if (v.index==1) return v.name;
			return null;
		}
	}
	
	
	
	@Override
	public String toString() {
		String out = "";
		for (int i = 0; i < this.size() ; i++)
			out += this.get(i)[0] + "| " + this.get(i)[1] + " : "+this.get(i)[2]+"\n";
		return out;
	}
	
}

