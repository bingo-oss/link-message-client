package link.message.client.exception;

/**
 * @since 3.0.1
 */
@SuppressWarnings("serial")
public class InvalidCodeException extends RuntimeException {

	public InvalidCodeException(String message) {
		super(message);
	}

	public InvalidCodeException(String message, Throwable cause) {
		super(message, cause);
	}
}