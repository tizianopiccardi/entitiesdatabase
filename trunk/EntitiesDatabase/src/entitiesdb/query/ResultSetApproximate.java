package entitiesdb.query;

import java.util.ArrayList;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.dao.RecordsStore.RecordsList;
import entitiesdb.query.objects.StatementBody;
import entitiesdb.query.objects.StatementProperty;
import entitiesdb.types.EntityAndAccuracy;

/**
 * ResultSet of an approximate query
 * @author Tiziano
 *
 */
public class ResultSetApproximate implements ResultSet {

	/**
	 * The result array
	 */
	public ResultSetApproximateElement[] entities;
	
	public ResultSetApproximate(EntitiesDAO dao, ArrayList<EntityAndAccuracy> resultSet) {
		
		entities = new ResultSetApproximateElement[resultSet.size()];
		
		/**
		 * For each entity in the result set I get the body with all its attributes
		 */
		for (int i = 0; i  < resultSet.size() ; i++) {
			
			/**
			 * Get all records with the current entity ID
			 */
			RecordsList rList = dao.getRecords(resultSet.get(i).getEntity());
			
			/**
			 * I create a body for this row...
			 */
			StatementBody sb = new StatementBody(resultSet.get(i).entity);
			for (int j = 0 ; j < rList.size() ; j++) {
				String attribute = rList.get(j).getAttribute();
				String value = rList.get(j).getValue();
				sb.addProperties(new StatementProperty(attribute, value));
			}
			
			/**
			 * ...and I add it to the result array with the percentage
			 */
			entities[i] = new ResultSetApproximateElement(sb, resultSet.get(i).accuracy);
			
		}
			
			
			
	}
	
	
	
	@Override
	public String toString() {
		String out = "";
		
		for (int i = 0 ; i < entities.length; i++) {
			out+= entities[i].toString()+"\n";
		}
		
		return out;
	}
	
	
	/**
	 * This is a single element of the result set
	 * @author Tiziano
	 *
	 */
	public class ResultSetApproximateElement {
		float percentage;
		StatementBody body;
		public ResultSetApproximateElement(StatementBody sb, float p) {
			percentage = p;
			body = sb;
		}
		public float getPercentage() {
			return percentage;
		}
		public StatementBody getBody() {
			return body;
		}
		@Override
		public String toString() {
			return "[" + percentage +"%] - " + body.toString();
		}
	}
	
}
