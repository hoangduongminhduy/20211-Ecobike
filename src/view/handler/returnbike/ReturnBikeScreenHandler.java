package view.handler.returnbike;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.ReturnBikeController;
import entity.Bike;
import entity.Renting;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.Config;
import util.DateTimeUtil;
import view.handler.BaseScreenHandler;
import view.handler.payment.PaymentScreenHandler;
import view.handler.rentbike.RentBikeScreenHandler;
import view.handler.renting.RentingScreenHandler;

public class ReturnBikeScreenHandler extends BaseScreenHandler {
	
	@FXML
	private Text returnBikeHeader;
	
	@FXML
	private Hyperlink startTime;
	
	@FXML
	private Hyperlink endTime;
	
	@FXML
	private Hyperlink useTime;
	
	@FXML
	private Hyperlink useCost;
	
	@FXML
	private Hyperlink vatCost;
	
	@FXML
	private Hyperlink totalCost;
	
	private Renting renting;
	
	public ReturnBikeController getController() {
		return (ReturnBikeController) super.getBController();
	}
	
	public ReturnBikeScreenHandler(Stage stage, String screenPath, Renting bikeRenting) throws IOException {
		super(stage, screenPath);
		this.renting = bikeRenting;
		
		super.setBController(new ReturnBikeController(renting));
		renting = getController().getRentingInfo();
		
		returnBikeHeader.setText("Thông tin trả xe - " + renting.getBike().getBikeCode());
		
		startTime.setText(DateTimeUtil.FormatDateTime(renting.getStartime()));
		endTime.setText(DateTimeUtil.FormatDateTime(renting.getEndtime()));
		
		useTime.setText(DateTimeUtil.SecondsToDateTimeString(getController().getRentingInfo().getUseTime()));
		
		long useCostMoney = getController().calculateFee(renting);
		long vatCostMoney = useCostMoney / 10;
		long totalCostMoney = useCostMoney + vatCostMoney;
		
		useCost.setText(String.valueOf(useCostMoney) + " VND");
		vatCost.setText(String.valueOf(vatCostMoney) + " VND");
		totalCost.setText(String.valueOf(totalCostMoney) + " VND");
	}

	void returnBike() {
		
	}
	
	public void cancelReturnBike() throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("Bạn có muốn trả xe không?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
    		Stage stage = (Stage) returnBikeHeader.getScene().getWindow();
    		RentingScreenHandler rentingScreenHandler = new RentingScreenHandler(stage, Config.RENTING_SCREEN_PATH, renting);
    		rentingScreenHandler.setScreenTitle("Renting bike screen");
    		rentingScreenHandler.initializeText();
    		rentingScreenHandler.getTimer().start();
    		rentingScreenHandler.show();
    	}
	}
	
	public void confirmReturnBike() throws IOException {
		Stage stage = (Stage) returnBikeHeader.getScene().getWindow();
		PaymentScreenHandler rentingBikeScreenHandler = new PaymentScreenHandler(stage, Config.PAYMENT_SCREEN_PATH, 5000, (int)renting.getCost(), 5000); // 
		rentingBikeScreenHandler.setScreenTitle("Payment screen");
		rentingBikeScreenHandler.show();
	}
	
	void notifyError() {}
}
