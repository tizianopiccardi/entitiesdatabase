package entitiesdb.query.approximate;

public class EntityAndAccuracy {

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
	
	
}
