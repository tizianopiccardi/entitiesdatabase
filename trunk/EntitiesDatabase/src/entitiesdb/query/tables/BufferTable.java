package entitiesdb.query.tables;

import java.util.ArrayList;
import java.util.Enumeration;

import entitiesdb.query.conditions.Condition;
import entitiesdb.query.tables.QueryRecordMatrix.VarsBounderList;

/**
 * This is a memory table that processes the resultSet
 * @author Tiziano
 *
 */
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
	 * id: this.index
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
	
	
	
	/**
	 * Merge this memory table with a new matrix gotten by query on the DB
	 * @param records
	 */
	public void merge(QueryRecordMatrix records) {

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
	
	
	
	/**
	 * Computes how many columns needs the new table (after the join)
	 * @param varB
	 * @return
	 */
	private int getMatrixCols(VarsBounderList varB) {
		int cols = this.metadata.size();
		
		for (int i = 0 ; i < varB.size() ; i ++)
			//if the object doesn't exist I add a column
			if (this.metadata.get(varB.get(i).name)==null)
				cols++;
		
		return cols;
	}
	
	/**
	 * Get the name of the entity at the passed row
	 * @param rowIndex
	 * @return
	 */
	public String getEntity(int rowIndex) {		
		return this.index[rowIndex];
	}
	
	/**
	 * Get a String that is the value of a variable at a specified row
	 * @param rowIndex
	 * @param rowName
	 * @return
	 */
	public String getField(int rowIndex, String rowName) {		
		return table[rowIndex][metadata.get(rowName)];
	}
	
	/**
	 * Number of rows in the table
	 * @return
	 */
	public int getRowsCount() {
		return table.length;
	}
	
	/**
	 * Number of columns in the table
	 * @return
	 */
	public int getColsCount() {
		return metadata.size();
	}
	
	
	/**
	 * This method refresh the table with the passed conditions
	 * @param cList
	 */
	public void applyConditions(ArrayList<Condition> cList) {
		String [][] newTable = new String[getRowsCount()][getColsCount()];
		String [] newIndex = new String[getRowsCount()];
		
		int rowCounter = 0;
		for (int i = 0 ; i < table.length ; i ++)
			if (conditionsRespected(table[i], cList)) {
				System.arraycopy(table[i], 0, newTable[rowCounter], 0, table[i].length);
				newIndex[rowCounter]=index[i];
				rowCounter++;
			}
		
		
		/**
		 * Now the table is clean and I need to resize it
		 */
		String [][] resultTable = new String[rowCounter][getColsCount()];
		String [] resultIndex = new String[getRowsCount()];
		
		System.arraycopy (newTable,0,resultTable,0,rowCounter);
		System.arraycopy (newIndex,0,resultIndex,0,rowCounter);

		this.table = resultTable;
		this.index = resultIndex;
		
	}
	
	/**
	 * Check if this row respects the conditions
	 * @param row
	 * @param cList
	 * @return
	 */
	private boolean conditionsRespected(String [] row, ArrayList<Condition> cList) {
		
		boolean out = true;
		for (int i = 0 ; i < cList.size() ; i++) {
			Condition c = cList.get(i);
			out &= row[metadata.get(c.left)].equals(c.right.toString());
		}
		
		return out;
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
