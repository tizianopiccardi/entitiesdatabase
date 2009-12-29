package entitiesdb.query.objects;


public class OrderBy {

	String varName;
	OrderDirection direction = OrderDirection.ASC;
	
	public OrderBy(String s, OrderDirection dir) {
		varName=s;
		direction = dir;
	}
	
	public void setDirection(OrderDirection dir) {
		direction = dir;
	}
	
	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public OrderDirection getDirection() {
		return direction;
	}

	public enum OrderDirection {
		ASC, DESC;
	}
	
}
