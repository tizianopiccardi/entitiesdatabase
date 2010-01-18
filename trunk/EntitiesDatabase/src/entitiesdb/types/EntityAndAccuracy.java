package entitiesdb.types;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

/**
 * Simple Object that contains an ID and his accuracy
 * @author Tiziano
 *
 */
@Entity
public class EntityAndAccuracy implements Comparable<EntityAndAccuracy>{

	@PrimaryKey
	public String entity;
	@SecondaryKey(name="Accuracy", relate=Relationship.MANY_TO_ONE)
	public float accuracy;
	
	public EntityAndAccuracy(){}
	
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
	
	public void setEntity(String entity) {
		this.entity = entity;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
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
