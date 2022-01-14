package subsystem;

import entity.Card;
import entity.Payment;
import subsystem.interbank.InterbankSubsystemController;


public class InterbankSubsystem implements InterbankInterface {
	private InterbankSubsystemController ctrl;

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */
	public InterbankSubsystem() {
		String str = new String();
		this.ctrl = new InterbankSubsystemController();
	}

	
	public Payment pay(Card card, int amount, String contents) {
		Payment transaction = ctrl.pay(card, amount, contents);
		return transaction;
	}

	public Payment refund(Card card, int amount, String contents) {
		Payment transaction = ctrl.refund(card, amount, contents);
		return transaction;
	}
}
