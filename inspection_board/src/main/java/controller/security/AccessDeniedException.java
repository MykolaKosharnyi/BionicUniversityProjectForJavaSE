package controller.security;

public class AccessDeniedException extends RuntimeException {
	private static final long serialVersionUID = 2587384739763809186L;

	public AccessDeniedException(String message) {
        super(message);
    }
}
