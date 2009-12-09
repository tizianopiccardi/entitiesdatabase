package entitiesdb.query.tables;

import java.util.ArrayList;
import entitiesdb.query.tables.QueryRecordTable.VarsBounder;


public class DynamicTable {

	//Indice mantenuto con l'entità a cui la riga fa riferimento
	public ArrayList<String> entities = new ArrayList<String>();
	
	//Formato <righe<colonne>>
	public ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	
	//contiene $x è in posizione 1
	public DynamicTableMetadata tableMetadata = new DynamicTableMetadata();
	
	public boolean empty = true;
	public int colums = 0;
	
	
	public void join(QueryRecordTable recordsListObject) {
		
		
		
		

		//String [][] a = new String[10][10];
		//arra
		//table.get(0).set(1, "ciao");
		
		if (empty) {
			//bla bla
			this.empty = false;
			
			//primo inserimento
			VarsBounder[] vb = recordsListObject.getBounds();
			String[][] rMatrix = recordsListObject.getMatrix();
			//Record r;
			String tmp;
			for (int i = 0 ; i < recordsListObject.size() ; i ++) {
				
				
				this.entities.add(recordsListObject.get(i).getEntityId());
				
				for (int j = 0 ; j < vb.length ; j++) {
					//vb[j].name; //nome 
					//vb[j].index; //posizione in rMatrix
					
					tmp = rMatrix[i][vb[j].index];
					System.out.println(vb[j].name);
					System.out.println(tmp);
					System.out.println("----------");
					//this.table.add(e)
					
				}
				
				/*r = recordsListObject.get(i);
				
				
				
				if (recordsListObject.idBound != null)
					this.table.get(i).add(r.getEntityId());*/
				
				
			}
			
			
		}
		else {
			//bla bla
		}
		
	}
	
	
	public String toString() {
		

		String out = "Dynamic table DUMP\n";
		
		for (int i = 0 ; i < this.table.size() ; i++) {
			out+= this.entities.get(i) + " | " + this.table.get(i) + "\n";
		}
			
		
		out+="-----------";
		
		return out;
	}
	
	
}
