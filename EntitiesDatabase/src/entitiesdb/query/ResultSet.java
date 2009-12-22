package entitiesdb.query;

import entitiesdb.query.tables.BufferTable;
import entitiesdb.types.Variable;

public class ResultSet {
	

	public StatementBody[] queryResult;
	
	public ResultSet(BufferTable table, StatementBody head) {
		queryResult = new StatementBody[table.getRowsCount()];
		
		
		for (int i = 0 ; i < table.getRowsCount() ; i++) {
			
			/**
			 * New row in the result set
			 */
			queryResult[i] = new StatementBody();
			
			/**
			 * If the entity isn't explicit (Variable) I get the right string from the table
			 */
			if (head.entity instanceof Variable)
				queryResult[i].setEntity(table.getField(i, head.entity.toString()));
			else
				queryResult[i].setEntity(head.entity);

			
			for (StatementProperty p : head.getProperties()) {
				
				/**
				 * New Property to add to the row (i.e. has: $x -> has: 'Car')
				 */
				StatementProperty resultProp = new StatementProperty();
				
				/**
				 * If in the request (head) is a variable a get the right value from the table
				 * else I copy the same string
				 */
				if (p.getAttributeObject() instanceof Variable) 
					resultProp.setAttribute(table.getField(i, p.getAttributeObject().toString()));
				else
					resultProp.setAttribute(p.getAttribute());
				
				if (p.getValueObject() instanceof Variable) 
					resultProp.setValue(table.getField(i, p.getValueObject().toString()));
				else
					resultProp.setValue(p.getValue());
				
				queryResult[i].properties.add(resultProp);

			}
			
			
		}
		
		
		
	}
	
	@Override
	public String toString() {
		String out = "";
		
		for (int i = 0 ; i < queryResult.length ; i++) {
			out += queryResult[i].toString() + "\n";
		}
		
		return out;
	}
	
	
}
