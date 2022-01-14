package subsystem.interbank;

import java.util.Map;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.Card;
import entity.Payment;
import util.*;

public class InterbankSubsystemController {

	private static final String PUBLIC_KEY = "BLBOH0iy74M=";
	private static final String SECRET_KEY = "BSpBGt5MFwY=";
	private static final String PAY_COMMAND = "pay";
	private static final String VERSION = "1.0.0";

	private static InterbankSubsystemBoundary interbankBoundary = new InterbankSubsystemBoundary();

	public Payment refund(Card card, int amount, String contents) {
		return null;
	}
	
	private String generateData(Map<String, Object> data) {
		return ((MyMap) data).toJSON();
	}

	public Payment pay(Card card, int amount, String content) {
		Map<String, Object> transaction = new MyMap();

		try {
			transaction.putAll(MyMap.toMyMap(card));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new PaymentException("InvalidCard");
		}
		transaction.put("command", PAY_COMMAND);
		transaction.put("transactionContent", content);
		transaction.put("amount", amount);
		transaction.put("createdAt", Util.getToday());

		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);

		String responseText = interbankBoundary.query(Config.PROCESS_TRANSACTION_URL, generateData(requestMap));
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnrecognizedException();
		}

		return makePaymentTransaction(response);
	}

	private Payment makePaymentTransaction(MyMap response) {
		if (response == null)
			return null;
		MyMap transMap = (MyMap) response.get("transaction");
		Card card = new Card((String) transMap.get("cardCode"), (String) transMap.get("owner"),
				Integer.parseInt((String) transMap.get("cvvCode")), (String) transMap.get("dateExpired"));
		Payment trans = new Payment((String) response.get("errorCode"), card,
				(String) transMap.get("transactionId"), (String) transMap.get("transactionContent"),
				Integer.parseInt((String) transMap.get("amount")), (String) transMap.get("createdAt"));

		switch (trans.getErrorCode()) {
		case "00":
			break;
		case "01":
			throw new PaymentException("InvalidCard");
		case "02":
			throw new PaymentException("NotEnoughBalance");
		case "03":
			throw new PaymentException("InternalServerError");
		case "04":
			throw new PaymentException("SuspiciousTransaction");
		case "05":
			throw new PaymentException("NotEnoughTransactionInfo");
		case "06":
			throw new PaymentException("InvalidVersion");
		case "07":
			throw new PaymentException("InvalidTransactionAmount");
		default:
			throw new UnrecognizedException();
		}

		return trans;
	}

}
