package entitiesdb.query.objects;


public class DataTypes {

	
	public static enum Types {

		VARIABLE, STRING, BUFFERTABLE, STATEMENTENGINE, QUERYRECORDMATRIX, ERROR;

		public static Types getType(Object o) {
			try {
				return valueOf(o.getClass().getSimpleName().toUpperCase());
			} catch (Exception ex) {
				return ERROR;
			}
		}
	}
	
}
