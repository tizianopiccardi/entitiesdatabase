package entitiesdb.query.approximate;

/**
 * Simple Object that contains an ID and his accuracy
 * @author Tiziano
 *
 */
public class EntityAndAccuracy implements Comparable<EntityAndAccuracy>{

	public String entity;
	public float accuracy;
	
	public EntityAndAccuracy(String e, float a) {
		entity = e;
		accuracy = a;
	}

	public String getEntity() {
		return entity;
	}

	public float getAccuracy() {
		return accuracy;
	}
	
	@Override
	public String toString() {
		return entity + ": " + accuracy + "%";
	}

	@Override
	public int compareTo(EntityAndAccuracy o) {
		return (accuracy == o.accuracy) ? 0 : (accuracy > o.accuracy?-1:1);
	}
	
}
