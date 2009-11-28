package entitiesdb.record;

import com.sleepycat.persist.model.KeyField;
import com.sleepycat.persist.model.Persistent;

@Persistent
public class Attribute {

	@KeyField(1)
	public String label;
	
	public Attribute() {
		
	}
	
	public Attribute(String lbl) {
		this.label = lbl;
	}

	
	
	public final String getLabel() {
		return label;
	}

	public final void setLabel(String label) {
		this.label = label;
	}

	public String toString() {
		return label;
	}
	
	public boolean equals(Object o) {
		return ((Attribute)o).getLabel().equals(this.getLabel());
	}
	
	
}
