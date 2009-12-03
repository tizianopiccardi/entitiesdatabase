package entitiesdb.query;

import java.util.ArrayList;

import entitiesdb.dao.JEDao;
import entitiesdb.record.Record;


public class RecordFinder {

	ArrayList<Record> records = new ArrayList<Record>();
	public QueryRecord queryRecord;
	
	public RecordFinder(QueryRecord qr) {
		queryRecord = qr;
		fillList();
	}
	
	private void fillList() {
		
		


		records = JEDao.getRecords(queryRecord);
		
		
		
	}

	public ArrayList<Record> getRecords() {
		return records;
	}

	public void setRecords(ArrayList<Record> records) {
		this.records = records;
	}

	public QueryRecord getQueryRecord() {
		return queryRecord;
	}

	public void setQueryRecord(QueryRecord queryRecord) {
		this.queryRecord = queryRecord;
	}
	
	
	
	
	
	public String getEntityBound() {
		return queryRecord.getIdBound();
	}
	
	public String getAttributeBound() {
		return queryRecord.getAttributeBound();
	}
	
	public String getValueBound() {
		return queryRecord.getValueBound();
	}
	
}
