package controller;

import java.util.Calendar;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.Card;
import entity.Payment;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;

/**
 * @author HoangNhatMinh-20183955
 */
public class PaymentController extends BaseController {
	private Card card;

	private InterbankInterface interbank;

	/**
	 * Validate the input date which should be in the format "mm/yy", and then
	 * return a {@link java.lang.String String} representing the date in the
	 * required format "mmyy" .
	 * 
	 * @param date - the {@link java.lang.String String} represents the input date
	 * @return {@link java.lang.String String} - date representation of the required
	 *         format
	 * @throws InvalidCardException - if the string does not represent a valid date
	 *                              in the expected format
	 */
	private String getExpiredDate(String date) throws PaymentException {
		String[] strs = date.split("/");
		if (strs.length != 2) {
			throw new PaymentException("Invalid card");
		}

		String expiredDate = null;
		int month = -1;
		int year = -1;

		try {
			month = Integer.parseInt(strs[0]);
			year = Integer.parseInt(strs[1]);
			if (month < 1 || month > 12 || year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100) {
				throw new PaymentException("Invalid card");
			}
			expiredDate = strs[0] + strs[1];

		} catch (Exception ex) {
			throw new PaymentException("Invalid card");
		}

		return expiredDate;
	}

	public String transact(String cardCode, String owner, String expiredDate, String cvv,
			int amount, String content) {
		String result = "GIAO DICH THAT BAI!";
		try {
			this.card = new Card(cardCode, owner, Integer.parseInt(cvv), getExpiredDate(expiredDate));

			this.interbank = new InterbankSubsystem();
			if(amount >= 0) {
				Payment payment = interbank.pay(card, amount, content);
			} else {
				Payment payment = interbank.refund(card, -amount, content);
			}

			result = "GIAO DICH THANH CONG!";
		} catch (PaymentException | UnrecognizedException ex) {
			result = ex.getMessage();
		}
		return result;
	}

	public void emptyRented(){
        //Bike.getBike().empty();
    }
}