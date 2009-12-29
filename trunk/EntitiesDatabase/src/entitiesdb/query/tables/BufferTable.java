package entitiesdb.query.tables;

import java.util.ArrayList;
import java.util.Enumeration;

import entitiesdb.query.objects.Condition;
import entitiesdb.query.tables.QueryRecordMatrix.VarsBounderList;
import entitiesdb.types.Variable;

/**
 * This is a memory table that processes the resultSet<br>
 * This class is the core of the query system
 * @author Tiziano
 *
 */
public class BufferTable {

	/**
	 * This table, contains the data in this format:<br>
	 * <br>
	 * Query: $x(lives: $y, works: $z)<br>
	 * <br>
	 * id	$x	$y	$z<br>
	 * ---------------<br>
	 * I1 | I1	TN	MI<br>
	 * I2 |	I2	TN	TN<br>
	 * I1 |	I1	MI	MI<br>
	 * I3 |	I3	TO	TN<br>
	 * <br>
	 * id: this.index<br>
	 */
	
	public boolean initialized = false;
	
	String [] index;
	
	String [][] table;
	
	//link: metadata.get("$x") -> 0
	Metadata metadata = new Metadata();
	
	ResultSetInfo rsInfo = new ResultSetInfo();
	
	public BufferTable() {}
	//constructor
	public BufferTable(QueryRecordMatrix records) {
		this.allocate(records);
	}
	
	
	public boolean isInitialized() {
		return initialized;
	}
	/**
	 * Allocate the table by a resultSet
	 * @param records
	 */
	public void allocate(QueryRecordMatrix records) {
		
		this.initialized = true;
		
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

		int rows = getNewRowsCount(records.getResultSetInfo());
		int cols = getNewColsCount(records.getBounds());

		String [][] newTable = new String[rows][cols];
		String [] newIndex = new String[rows];
		rsInfo = new ResultSetInfo();
		
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
	 * SUM [ if l.get(i)==r[j] then (l[i].count * r[j].count) ]
	 * @param rsI
	 * @return
	 */
	private int getNewRowsCount(ResultSetInfo rsI) {
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
	 * Computes how many columns needs the new table (after the merge)
	 * @param varB
	 * @return
	 */
	private int getNewColsCount(VarsBounderList varB) {
		int cols = this.metadata.size();
		
		for (int i = 0 ; i < varB.size() ; i ++)
			//if the object doesn't exist I add a column
			if (!this.metadata.exists(varB.get(i).name))
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
			if (c.right instanceof Variable)
				out &= row[metadata.get(c.left)].equals( row[metadata.get(c.right.toString())]);
			else
				out &= row[metadata.get(c.left)].equals(c.right.toString());
		}
		
		return out;
	}
	
	/**
	 * Update metadata.
	 * For each entry in the right table metadata, I add it to the left part
	 * with index: (old_right_index + number_of_variabiles_on_left_table)
	 * @param md
	 */
	private void joinMetadata(Metadata md) {
		int metadataStartSize = metadata.size();
		Enumeration<String> keys = md.keys();
		while( keys.hasMoreElements() ) {
		  String key = keys.nextElement();
		  metadata.put(key, md.get(key) + metadataStartSize);
		}
	}
	
	/**
	 * Join the passed table: Cartesian product
	 * @param joinTable
	 */
	public void cartesian(BufferTable joinTable) {
		/**
		 * How may rows and columns need a cartesian product
		 */
		int rows = table.length * joinTable.getRowsCount();
		int cols = metadata.size() + joinTable.metadata.size();
		
		String [][] newTable = new String[rows][cols];
		String [] newIndex = new String[rows];
		
		rsInfo = new ResultSetInfo();

		this.joinMetadata(joinTable.metadata);

		
		/**
		 * Fill the new table
		 */
		
		for (int i = 0 ; i < table.length ; i++) {
			int pageSize = i * joinTable.getRowsCount();
			for(int j = 0 ; j < joinTable.getRowsCount() ; j++) {
				
				int offset = pageSize + j;
				
				/**
				 * Index updated
				 */
				newIndex[offset] = index[i];
				rsInfo.add(index[i]);
				/**
				 * Copy the left part...
				 */
				System.arraycopy (table[i], 0, newTable[offset], 0, table[i].length);
				
				/**
				 * ...and then the right one from the joined table
				 */
				System.arraycopy (joinTable.table[j], 0, newTable[offset], table[i].length, joinTable.table[j].length);
				
			}
			
		}
		
		
		this.table = newTable;
		this.index = newIndex;
		
	}
	
	/**
	 * This method join a table using 2 conditions: <br>
	 * 	- where records[i].value == joinTable[j].id<br>
	 * 	- AND 	table[k].ID == records[i].ID <br>
	 * 
	 * @param records
	 * @param joinTable
	 */
	public void mergeAndJoin(QueryRecordMatrix records, BufferTable joinTable) {
		
		/**
		 * Current columns + columns of new table + if the attribute (of the link stmt) is a var, another one
		 */
		boolean isAttributeBounded = records.getBounds().isAttributeBounded();
		int cols = metadata.size() + joinTable.metadata.size() + (isAttributeBounded?1:0);

		rsInfo = new ResultSetInfo();

		
		
		/**
		 * Metadata UPDATE
		 */
		if (isAttributeBounded) this.metadata.put(records.getBounds().getAttributeBound(), this.metadata.size());
		this.joinMetadata(joinTable.metadata);

		/**
		 * Table filling using a aux table
		 */
		ArrayList<String[]> tempTable = new ArrayList<String[]>();
		ArrayList<String> tempIndex = new ArrayList<String>();
		for (int i = 0 ; i < records.size() ; i++) 
			for (int j = 0 ; j < joinTable.table.length ; j++) 
				/**
				 * If we can join the records with the new table on:
				 * record.value = joinTable.id ...
				 */
				if (records.get(i)[2].equals(joinTable.index[j])) 
					for (int k = 0 ; k < this.table.length ; k++)
						/**
						 * ... and if we can join the records with the current table on:
						 * table[k].id = record.id then...
						 */
						if (records.getEntity(i).equals(this.index[k])) {
							
							/**
							 * ... we have found a new row to add.
							 */
							
							/**
							 * Indexes updating
							 */
							int currentLenght = table[k].length;
							tempIndex.add(index[k]);
							rsInfo.add(index[k]);
							
							String[] row = new String[cols];
							
							/**
							 * I copy the left part from the current table...
							 */
							System.arraycopy(table[k], 0, row, 0, currentLenght);

							/**
							 * ... and if the attribute is a variable, I'll copy it.
							 */
							if (isAttributeBounded) {
								row[currentLenght] = records.get(i)[1];
								currentLenght++;
							}
							
							/**
							 * Eventually, i copy the right side!
							 */
							System.arraycopy(joinTable.table[j], 0, row, currentLenght, joinTable.table[j].length);
							
							tempTable.add(row);

						}
	

		String [][] newTable = new String[tempTable.size()][cols];
		String [] newIndex = new String[tempTable.size()];
		
		newIndex = tempIndex.toArray(newIndex);
		newTable = tempTable.toArray(newTable);
		
		this.table = newTable;
		this.index = newIndex;

		
	}
	
	
	/**
	 * Used in the particular case that the table is empty and is needed
	 * to join a table in this way: $x(lives: $y(name: 'Trento'))<br>
	 * The current table is obtained by the evaluation of $y(name: 'Trento'), 
	 * while the result table in is joined with the set obtained by the query:<br>
	 * $x(lives: $y)
	 * @param records
	 */
	public void joinOnRecordValue(QueryRecordMatrix records) {

		int cols = metadata.size() + records.getBounds().size() ;

		for (int k = 0 ; k < records.getBounds().size() ; k++) 
			this.metadata.put(records.getBounds().get(k).name, this.metadata.size());
		rsInfo = new ResultSetInfo();

		ArrayList<String[]> tempTable = new ArrayList<String[]>();
		ArrayList<String> tempIndex = new ArrayList<String>();
		for (int i = 0 ; i < records.size() ; i++) 
					for (int k = 0 ; k < this.table.length ; k++)
						/**
						 * Join on record.value == index[k]
						 */
						if (records.getValue(i).equals(this.index[k])) {
							
							/**
							 * The new index is obtained by the record.<br>
							 * At the moment this table is one level lower
							 */
							tempIndex.add(records.getEntity(i));
							rsInfo.add(records.getEntity(i));
							
							String[] row = new String[cols];
							System.arraycopy(table[k], 0, row, 0, table[k].length);

							/**
							 * Like in merge method...
							 */
							for (int j = 0 ; j < records.getBounds().size() ; j++)
								row[table[k].length+j] = records.get(i)[records.getBounds().get(j).index];
							
							tempTable.add(row);

						}
	


		String [][] newTable = new String[tempTable.size()][cols];
		String [] newIndex = new String[tempTable.size()];
		
		newIndex = tempIndex.toArray(newIndex);
		newTable = tempTable.toArray(newTable);

		this.table = newTable;
		this.index = newIndex;


	}
	
	
	public String toString() {
		String out = "\nResultSet Info: "+rsInfo.toString();
		out+="\nMetadata: "+metadata.toString() + "\n";
		for (int i = 0; i < table.length ; i++){
			out += index[i] + " | ";
			for (int j = 0 ; j < table[i].length ; j ++)
				out+=table[i][j] + " ";
			out+="\n";
		}
		return out;
	}
	
}
