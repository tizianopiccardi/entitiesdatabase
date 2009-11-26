package entitiesdb.dao;

public class DaoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4577790951694290243L;

	public DaoException() {
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}