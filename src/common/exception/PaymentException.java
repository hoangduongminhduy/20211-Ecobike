package common.exception;;

public class PaymentException extends RuntimeException {
	public static final String INVALID_EXPIRED_DATE_MESSAGE = "Invalid expired date of card";
	
	public PaymentException(String message) {
		super("ERROR: " + message);
	}
}
