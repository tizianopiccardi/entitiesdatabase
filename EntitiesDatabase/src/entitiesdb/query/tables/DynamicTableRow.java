package entitiesdb.query.tables;

import java.util.ArrayList;

public class DynamicTableRow {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5245675590809219766L;

	
	//public String id;
	public ArrayList<String> row;
	
	
	public DynamicTableRow() {
		//this.id = id;
		this.row = new ArrayList<String>();
	}
	
	public DynamicTableRow(ArrayList<String> r) {
		//this.id = id;
		this.row = r;
	}
	/*
	public String getId() {
		return id;
	}*/
	
	public ArrayList<String> getRow() {
		return row;
	}
	
	public String toString() {
		return row.toString();
	}
	
}
