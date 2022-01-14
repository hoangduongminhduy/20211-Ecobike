package view.handler.payment;

import java.io.IOException;
import controller.PaymentController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.handler.BaseScreenHandler;

public class PaymentScreenHandler extends BaseScreenHandler {

	@FXML
	private Button backBtn;
	@FXML
	private Button cancelBtn; 
	@FXML
	private Button payBtn;
	
	// Account information fields
	@FXML
	private TextField cardCode;
	@FXML
	private TextField owner;
	@FXML
	private TextField cvv;
	@FXML
	private TextField expiredDate;
	@FXML
	private PasswordField secretKey;
	
	// Payment information fields
	@FXML
	private Text depositAmountTF;
	@FXML
	private Text rentAmountTF;
	@FXML
	private Text refundAmountTF;
	@FXML
	private Text lastAmountTF;
	
	private int depositAmount;
	private int rentAmount;
	private int refundAmount;
	private int lastAmount;

	
	public PaymentScreenHandler(Stage stage, String screenPath, int deposit, int rent, int refund) throws IOException {
		super(stage, screenPath);
		setBController(new PaymentController());
		updateAmounts(deposit, rent, refund);
	}
	
	private void updateAmounts(int deposit, int rent, int refund) {
		this.depositAmount = deposit;
		this.rentAmount = rent;
		this.refundAmount = refund;
		if(refund > 0)
			this.lastAmount = rent - refund;
		else
			this.lastAmount = deposit;
		
		this.depositAmountTF.setText(String.valueOf(deposit));
		this.rentAmountTF.setText(String.valueOf(rent));
		this.refundAmountTF.setText(String.valueOf(refund));
		this.lastAmountTF.setText(String.valueOf(this.lastAmount));
	}

	
	@FXML
	void onPayBtn() throws IOException{
		PaymentController paymentController = (PaymentController) getBController();
		String response = paymentController.transact(cardCode.getText(), owner.getText(), expiredDate.getText(), cvv.getText(),
				lastAmount, "");
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(String.valueOf("GIAO DICH THANH CONG!"));
		alert.showAndWait();
		updateAmounts(0, 0, 0);
	}
	
	@FXML
	void onCancelBtn() throws IOException{
	}
	
	@FXML
	void onBackBtn() throws IOException{
	}
}
