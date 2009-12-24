package entitiesdb.query.objects;


public class Condition {

	public String left;
	
	public Object right;
	
	public Condition(String l, Object r) {
		left = l;
		right = r;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return left + " : " + right;
	}
	
}
