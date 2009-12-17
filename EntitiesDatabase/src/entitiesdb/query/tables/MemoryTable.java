package entitiesdb.query.tables;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import entitiesdb.query.tables.QueryRecordMatrix.VarsBounderList;
/*

public class MemoryTable {

	//Indice mantenuto con l'entità a cui la riga fa riferimento
	public ArrayList<String> entities = new ArrayList<String>();
	
	//Formato <righe<colonne>>
	public ArrayList<DynamicTableRow> table = new ArrayList<DynamicTableRow>();
	
	//contiene $x è in posizione 1
	public DynamicTableMetadata tableMetadata = new DynamicTableMetadata();
	
	
	public String[][] matrix;
	
	//public boolean empty = true;
	//public int colums = 0;//?
	
	//public Hashtable<String, Integer> entityMap = new Hashtable<String, Integer>();
	
	public ResultSetInfo tableInfo = new ResultSetInfo();
	
	public MemoryTable(QueryRecordMatrix rMatrix) {
		
		VarsBounderList vbl = rMatrix.getBounds();
		matrix = new String[rMatrix.size()][vbl.size()];
		
		for (int md = 0 ; md < vbl.size() ; md ++) 
			this.tableMetadata.put(vbl.get(md).name, md);
		
		
		
		

		for (int i = 0 ; i < rMatrix.size() ; i ++) {
			
			
			this.entities.add(rMatrix.getEntity(i));
			this.tableInfo.add(rMatrix.getEntity(i));
			
			for (int j = 0 ; j < vbl.size() ; j++) {
				//vb[j].name; //nome 
				//vb[j].index; //posizione in rMatrix
				matrix[i][j] = rMatrix.get(i)[vbl.get(j).index];
			}
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//primo resultset
		//System.out.println(rMatrix);
		
		String tmp;
		for (int i = 0 ; i < rMatrix.size() ; i ++) {
			
			
			this.entities.add(rMatrix.getEntity(i));
			this.tableInfo.add(rMatrix.getEntity(i));
			//nuova riga da aggiungere
			//DynamicTableRow row = new DynamicTableRow();
			
			
			for (int md = 0 ; md < vbl.size() ; md ++) 
				this.tableMetadata.put(vbl.get(md).name, md);
			
			DynamicTableRow row = new DynamicTableRow();
			
			for (int j = 0 ; j < vbl.size() ; j++) {
				//vb[j].name; //nome 
				//vb[j].index; //posizione in rMatrix
				
				tmp = rMatrix.get(i)[vbl.get(j).index];
				//System.out.print(vb.get(j).name +" = ");
				//System.out.println(tmp);

				row.add(tmp);
				//row.add(e)
				
			}
			
			table.add(row);
	}
	
	}

	


	public void merge(QueryRecordMatrix matchingRecords) {
		// TODO Auto-generated method stub
		
		//System.out.println(matchingRecords);
		System.out.println(stringMatrix());
		int mHeight = getMatrixHeight(tableInfo, matchingRecords.getResultSetInfo());
		System.out.println(mHeight);
		
		String [][] matrixNew = new String[mHeight][2];
		
		/*ArrayList<DynamicTableRow> newTbl = new ArrayList<DynamicTableRow>();
		
		for (DynamicTableRow row : table) {
			
		}*/
		
		/*
	}
	
	/*
	
	private int getMatrixHeight(ResultSetInfo fldLeft, ResultSetInfo fldRight) {
		
		System.out.println(fldLeft);
		System.out.println(fldRight);
		
		int rows = 0;
		
	    Enumeration keys = fldLeft.entityMap.keys();
	    while (keys.hasMoreElements()) {
	    	
	      String key = (String) keys.nextElement();
	      int value = fldLeft.entityMap.get(key);
	      
	      rows += value * fldRight.getCount(key);

	    }
		
		return rows;
	}
	
	
	private int getMatrixWidth(DynamicTableMetadata fldLeft, DynamicTableMetadata fldRight) {
		
		System.out.println(fldLeft);
		System.out.println(fldRight);
		
		int rows = 0;
		/*
	    Enumeration keys = fldLeft.entityMap.keys();
	    while (keys.hasMoreElements()) {
	    	
	      String key = (String) keys.nextElement();
	      int value = fldLeft.entityMap.get(key);
	      
	      rows += value * fldRight.getCount(key);

	    }*/
		/*
		return rows;
	}
	/*
	
	private MultiKeyMap table = MultiKeyMap.decorate(new ReferenceMap());
	private int size = 0;
	public MemoryTable(QueryRecordMatrix rMatrix) {
		//primo inserimento
		ArrayList<VarsBounder> vb = rMatrix.getBounds();

		String tmp;
		for (int i = 0 ; i < rMatrix.size() ; i ++, size++) {
			for (int j = 0 ; j < vb.size() ; j++) {
				//vb[j].name; //nome 
				//vb[j].index; //posizione in rMatrix
				
				tmp = rMatrix.get(i)[vb.get(j).index];
				table.put(i, vb.get(j).name, tmp);
				
			}
			
		}
		System.out.println(size);
	}
	
	
	public void merge (QueryRecordMatrix rMatrix) {
		for (int i = 0 ; i < size ; i++) {
			
		}
	}
	
	
	public String toString() {
		return table.toString();
	}
	
	/*
	
	public void join(QueryRecordMatrix recordsMatrix) {
		

		if (empty) {
			//bla bla
			this.empty = false;
			
			//primo inserimento
			ArrayList<VarsBounder> vb = recordsMatrix.getBounds();

			String tmp;
			for (int i = 0 ; i < recordsMatrix.size() ; i ++) {
				
				
				this.entities.add(recordsMatrix.getEntity(i));
				
				//nuova riga da aggiungere
				//DynamicTableRow row = new DynamicTableRow();
				
				
				for (int md = 0 ; md < vb.size() ; md ++) 
					this.tableMetadata.put(vb.get(md).name, md);
				
				DynamicTableRow row = new DynamicTableRow();
				
				for (int j = 0 ; j < vb.size() ; j++) {
					//vb[j].name; //nome 
					//vb[j].index; //posizione in rMatrix
					
					tmp = recordsMatrix.get(i)[vb.get(j).index];
					//System.out.print(vb.get(j).name +" = ");
					//System.out.println(tmp);

					row.add(tmp);
					//row.add(e)
					
				}
				
				table.add(row);
				//System.out.println("----------");
				
			}
			
			
		}
		else {
			//bla bla
		}
		
	}*//*
	
	
	public String toString() {
		

		String out = "\n\nDynamic table DUMP\n";
		
		out += tableMetadata.toString()+"\n";
		
		for (int i = 0 ; i < this.table.size() ; i++) {
			out+= this.entities.get(i) + " | " + this.table.get(i) + "\n";
		}
			
		
		out+="-----------";
		
		return out;
	}



	public String stringMatrix() {
		String out="";
		for (int i = 0; i < matrix.length ; i++){
			for (int j = 0 ; j < matrix[i].length ; j ++)
				out+=matrix[i][j] + " ";
			out+="\n";
		}
		return out;
	}
	
	
}
*/