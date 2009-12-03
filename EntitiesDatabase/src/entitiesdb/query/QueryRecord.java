package entitiesdb.query;

import entitiesdb.record.Record;

public class QueryRecord extends Record {
	
	
	public String idBound, attributeBound, valueBound = null;
	
	
	
	public String getIdBound() {
		return idBound;
	}
	public void setIdBound(String idBound) {
		this.idBound = idBound;
	}
	public String getAttributeBound() {
		return attributeBound;
	}
	public void setAttributeBound(String attributeBound) {
		this.attributeBound = attributeBound;
	}
	public String getValueBound() {
		return valueBound;
	}
	public void setValueBound(String valueBound) {
		this.valueBound = valueBound;
	}
	
	
	
	
	
	
	
	
/*
	Object id;
	Object attribute;
	Object value;
	
	QueryRecordTypes idType = QueryRecordTypes.IDE;
	QueryRecordTypes attributeType = QueryRecordTypes.IDE ;
	QueryRecordTypes valueType = QueryRecordTypes.ATOM;
	
	public QueryRecord(Object i, Object a, Object v) {
		id = i;
		attribute = a;
		value = v;
	}
	
	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	public Object getAttribute() {
		return attribute;
	}
	public void setAttribute(Object attribute) {
		this.attribute = attribute;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	public static enum QueryRecordTypes {
		ATOM, VAR, IDE
	}*/
	
	
}
