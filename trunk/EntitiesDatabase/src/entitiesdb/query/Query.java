package entitiesdb.query;

public class Query {

	private String query = "";
	
	private QueryHead head = new QueryHead();
	private QueryBody body = new QueryBody();
	private QueryConditions conditions = new QueryConditions();
	
	public Query(String q) {
		this.query = q;
		
		//idea: fill(this)
	}
	
	

}
