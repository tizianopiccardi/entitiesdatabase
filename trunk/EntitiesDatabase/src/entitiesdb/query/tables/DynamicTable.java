package entitiesdb.query.tables;

import java.util.ArrayList;

import entitiesdb.query.tables.QueryRecordMatrix.VarsBounder;


public class DynamicTable {

	//Indice mantenuto con l'entità a cui la riga fa riferimento
	public ArrayList<String> entities = new ArrayList<String>();
	
	//Formato <righe<colonne>>
	public ArrayList<DynamicTableRow> table = new ArrayList<DynamicTableRow>();
	
	//contiene $x è in posizione 1
	public DynamicTableMetadata tableMetadata = new DynamicTableMetadata();
	
	public boolean empty = true;
	public int colums = 0;//?
	
	
	public void join(QueryRecordMatrix recordsMatrix) {
		

		new ArrayList<String[]>();
		
		
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

					row.getRow().add(tmp);
					//row.add(e)
					
				}
				
				table.add(row);
				//System.out.println("----------");
				
			}
			
			
		}
		else {
			//bla bla
		}
		
	}
	
	
	public String toString() {
		

		String out = "\n\nDynamic table DUMP\n";
		
		out += tableMetadata.toString()+"\n";
		
		for (int i = 0 ; i < this.table.size() ; i++) {
			out+= this.entities.get(i) + " | " + this.table.get(i) + "\n";
		}
			
		
		out+="-----------";
		
		return out;
	}
	
	
}
