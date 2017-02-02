package controller;

public class DispatchException extends RuntimeException {
	private static final long serialVersionUID = 7882239946560485215L;

	public DispatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
