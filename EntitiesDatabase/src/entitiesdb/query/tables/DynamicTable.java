package entitiesdb.query.tables;

import java.util.ArrayList;

import entitiesdb.query.tables.QueryRecordMatrix.VarsBounder;


public class DynamicTable {

	//Indice mantenuto con l'entità a cui la riga fa riferimento
	public ArrayList<String> entities = new ArrayList<String>();
	
	//Formato <righe<colonne>>
	public ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	
	//contiene $x è in posizione 1
	public DynamicTableMetadata tableMetadata = new DynamicTableMetadata();
	
	public boolean empty = true;
	public int colums = 0;
	
	
	public void join(QueryRecordMatrix recordsMatrix) {
		

		if (empty) {
			//bla bla
			this.empty = false;
			
			//primo inserimento
			ArrayList<VarsBounder> vb = recordsMatrix.getBounds();

			String tmp;
			for (int i = 0 ; i < recordsMatrix.size() ; i ++) {
				
				
				this.entities.add(recordsMatrix.get(i)[0]);
				
				for (int j = 0 ; j < vb.size() ; j++) {
					//vb[j].name; //nome 
					//vb[j].index; //posizione in rMatrix
					
					tmp = recordsMatrix.get(i)[vb.get(j).index];
					System.out.print(vb.get(j).name +" = ");
					System.out.println(tmp);

					
				}
				System.out.println("----------");

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
