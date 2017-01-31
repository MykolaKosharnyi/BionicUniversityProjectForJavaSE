package model.dao.exception;

public class DaoException extends RuntimeException {
	private static final long serialVersionUID = -8233299047747267978L;

	public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
