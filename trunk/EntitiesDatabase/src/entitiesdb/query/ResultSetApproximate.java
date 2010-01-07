package entitiesdb.query;

import java.util.ArrayList;

import entitiesdb.dao.EntitiesDAO;
import entitiesdb.dao.RecordsStore.RecordsList;
import entitiesdb.query.approximate.EntityAndAccuracy;
import entitiesdb.query.objects.StatementBody;
import entitiesdb.query.objects.StatementProperty;

public class ResultSetApproximate implements ResultSet {

	public ResultSetApproximateElement[] entities;
	
	public ResultSetApproximate(EntitiesDAO dao, ArrayList<EntityAndAccuracy> resultSet) {
		
		entities = new ResultSetApproximateElement[resultSet.size()];
		
		for (int i = 0; i  < resultSet.size() ; i++) {
			
			RecordsList rList = dao.getRecords(resultSet.get(i).getEntity(), null, null);
			
			StatementBody sb = new StatementBody(resultSet.get(i).entity);
			for (int j = 0 ; j < rList.size() ; j++) {
				String attribute = rList.get(j).getAttribute();
				String value = rList.get(j).getValue();
				sb.addProperties(new StatementProperty(attribute, value));
			}
			
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
