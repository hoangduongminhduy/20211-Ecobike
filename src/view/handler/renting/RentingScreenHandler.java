package view.handler.renting;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.ReturnBikeController;
import entity.Bike;
import entity.BikeType;
import entity.Renting;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.Config;
import util.DateTimeUtil;
import view.handler.BaseScreenHandler;
import view.handler.returnbike.ReturnBikeScreenHandler;

public class RentingScreenHandler extends BaseScreenHandler {

	@FXML
    private TextField batteryText;

    @FXML
    private ImageView image;

    @FXML
    private TextField licensePlateText;

    @FXML
    private TextField priceText;

    @FXML
    private Button returnBikeBtn;

    @FXML
    private TextField startRentalText;

    @FXML
    private TextField timeRentedText;

    @FXML
    private TextField userIDText;
    
    private Renting bikeRenting;
    
    public RentingScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		
		Renting newRenting = new Renting();
		Bike newBike = new Bike(1, "BIKE_0001");
		newBike.setBatteryStatus("100%");
		newRenting.setBike(newBike);
		newRenting.setStartime(LocalDateTime.now());
		newRenting.setEndtime(LocalDateTime.now());

		this.bikeRenting = newRenting;
	}
    
    public RentingScreenHandler(Stage stage, String screenPath, Renting renting) throws IOException {
		super(stage, screenPath);
		
		this.bikeRenting = renting;
	}

    
    public long calculateFee(Renting renting) {
		long price = 0;
		long useTimeInMinutes = renting.getUseTime() / 60;
		
		if(useTimeInMinutes < 10)
    		price = 0;
    	else if(useTimeInMinutes < 30)
    		price = 10000;
    	else 
    		price = 10000 + (useTimeInMinutes - 30) /15 * 3000;
		
		Bike bike = renting.getBike();
    	
    	if(bike.getBikeType() == BikeType.ELECTRIC.getValue() || bike.getBikeType() == BikeType.COUPLE.getValue()) {
    		return (long) (price * 1.5);
    	}
    		
    	return price;
	}

    @FXML
    void onClickReturnBikeBtn(ActionEvent event) throws IOException {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("Bạn có muốn trả xe không?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
    		timer.stop();
    		Stage stage = (Stage) userIDText.getScene().getWindow();
    		ReturnBikeScreenHandler returnBikeScreenHandler = new ReturnBikeScreenHandler(stage, Config.RETURN_BIKE_SCREEN_PATH, bikeRenting);
    		
			returnBikeScreenHandler.setScreenTitle("Return bike screen");
			returnBikeScreenHandler.show();
    	}
    }
    
    public void initializeText() {
    	userIDText.setText("1");
    	licensePlateText.setText(bikeRenting.getBike().getBikeCode());
    	startRentalText.setText(DateTimeUtil.FormatDateTime(bikeRenting.getStartime()));
    	batteryText.setText(bikeRenting.getBike().getBatteryStatus());
    	
    	
//    	Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                String string = new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis());
//                timeRentedText.setText(string);
//            }
//        }, 0, 1000);
       
        timer.start();
    }
    
    public AnimationTimer getTimer() {
    	return this.timer;
    }
    
    AnimationTimer timer = new AnimationTimer() {
        private long timestamp;
        private long time = 0;
        private long fraction = 0;

        @Override
        public void start() {
            // current time adjusted by remaining time from last run
            timestamp = System.currentTimeMillis() - fraction;
            super.start();
        }

        @Override
        public void stop() {
            super.stop();
            // save leftover time not handled with the last update
            fraction = System.currentTimeMillis() - timestamp;
        }

        @Override
        public void handle(long now) {
        	bikeRenting.setEndtime(LocalDateTime.now());
            timeRentedText.setText(DateTimeUtil.SecondsToHHMMString(bikeRenting.getUseTime()));
            priceText.setText(String.valueOf(calculateFee(bikeRenting)) + " vnd");
        }
    };
}

