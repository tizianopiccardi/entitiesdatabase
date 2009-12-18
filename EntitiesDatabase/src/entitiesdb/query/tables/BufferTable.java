package entitiesdb.query.tables;

import java.util.Enumeration;

import entitiesdb.query.tables.QueryRecordMatrix.VarsBounderList;


public class BufferTable {

	/**
	 * This table, contains the data in this format:
	 * 
	 * Query: $x(lives: $y, works: $z)
	 * 
	 * id	$x	$y	$z
	 * ---------------
	 * I1 | I1	TN	MI
	 * I2 |	I2	TN	TN
	 * I1 |	I1	MI	MI
	 * I3 |	I3	TO	TN
	 * 
	 */
	
	String [] index;
	
	String [][] table;
	
	//link: metadata.get("$x") -> 0
	Metadata metadata = new Metadata();
	
	ResultSetInfo rsInfo = new ResultSetInfo();
	
	
	//constructor
	public BufferTable(QueryRecordMatrix records) {
		
		VarsBounderList varBounds = records.getBounds();
		
		int rows = records.size();
		int cols = varBounds.size();
		
		table = new String[rows][cols];
		
		index = new String[rows];
		
		
		/** Metadata
		 * {$y=1, $x=0}
		 */
		for (int j = 0 ; j < cols ; j++) 
			metadata.put(varBounds.get(j).name, j);

		/** Data
		 * I2 | I2 TN 
		 * JB | JB TRC 
		 * I1 | I1 TN 
		 * I1 | I1 MI 
		 */
		for (int i = 0 ; i < records.size() ; i ++) {
			index[i] = records.getEntity(i);
			//[I1, 12, I3] and [I1: 3, I2:1, I3: 1]
			rsInfo.add(records.getEntity(i));
			for (int j = 0 ; j < cols ; j++) 
				table[i][j] = records.get(i)[varBounds.get(j).index];
		}
		
		
	}
	
	
	
	
	public void merge(QueryRecordMatrix records) {
		
		//this.checkDuplicateVar(records.getBounds());
		
		int rows = getMatrixHeight(records.getResultSetInfo());
		int cols = getMatrixCols(records.getBounds());

		String [][] newTable = new String[rows][cols];
		String [] newIndex = new String[rows];
		
		/**
		 * print(newIndex | newTable);
		 * 
		 * null | null null null 
		 * null | null null null 
		 * null | null null null 
		 * null | null null null 
		 * null | null null null 
		 */
		
		
		records.getBounds().cleanEntityBound();
		//update metadata
		for (int k = 0 ; k < records.getBounds().size() ; k++) 
			this.metadata.put(records.getBounds().get(k).name, this.metadata.size());

		
		//if (true) return;
		int rowCounter = 0;
		rsInfo = new ResultSetInfo();
		for (int i = 0 ; i < table.length ; i ++) {
			
			
			for (int j = 0 ; j < records.size() ; j ++ ) {
				if (index[i].equals(records.getEntity(j))) {
					
					rsInfo.add(index[i]);
					
					// I1 | ...
					newIndex[rowCounter] = index[i];
					
					//.. | TN MI ...
					int rowSize = table[i].length;
					System.arraycopy(table[i], 0, newTable[rowCounter], 0, rowSize);
					
					
					for (int k = 0 ; k < records.getBounds().size() ; k++) {
						newTable[rowCounter][rowSize+k] = records.get(j)[records.getBounds().get(k).index];
					}
					
					rowCounter++;
				}
			}
			
			
		}
		
		this.table = newTable;
		this.index = newIndex;

	}

	/**
	 * This function computes how many rows needs the new matrix.<br>
	 * SUM [ if l.get(i).count==r[j] then (l[i].count * r[j].count) ]
	 * @param rsI
	 * @return
	 */
	private int getMatrixHeight(ResultSetInfo rsI) {
		int rows = 0;
	    Enumeration<String> keys = rsInfo.entityMap.keys();
	    while (keys.hasMoreElements()) {
	      String key = keys.nextElement();
	      int value = rsInfo.entityMap.get(key);
	      rows += value * rsI.getCount(key);
	    }
		return rows;
	}
	
	private int getMatrixCols(VarsBounderList varB) {
		int cols = this.metadata.size();
		
		for (int i = 0 ; i < varB.size() ; i ++)
			//se l'oggetto non esiste aggiungo una colonna
			if (this.metadata.get(varB.get(i).name)==null)
				cols++;
		
		return cols;
	}
	
	
	
	public String toString() {
		String out="Metadata: "+metadata.toString() + "\n";
		for (int i = 0; i < table.length ; i++){
			out += index[i] + " | ";
			for (int j = 0 ; j < table[i].length ; j ++)
				out+=table[i][j] + " ";
			out+="\n";
		}
		return out;
	}
	
}
