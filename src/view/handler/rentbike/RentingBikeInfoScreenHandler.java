package view.handler.rentbike;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import entity.Bike;
import entity.BikeRental;
import entity.BikeType;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RentingBikeInfoScreenHandler {

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
    
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    
    private BikeRental bikeRental;
    private Bike bike;
    
    public void initializeBike(BikeRental bikeRental, Bike bike) {
    	this.bike = bike;
    	this.bikeRental = bikeRental;
    }

    @FXML
    void onClickReturnBikeBtn(ActionEvent event) throws IOException {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("Bạn có muốn trả xe không?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
    		timer.stop();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/fxml/payment_screen.fxml"));
    		Parent parent = loader.load();
    		Stage stage = (Stage) userIDText.getScene().getWindow();
    		stage.setScene(new Scene(parent));
    		stage.show();
    	}
    }
    
    public void initializeText() {
    	userIDText.setText("1");
    	licensePlateText.setText(bike.getLicensePlate());
    	startRentalText.setText(formatter.format(bikeRental.getStartRental()));
    	batteryText.setText(bikeRental.getBatteryStatus());
    	
    	
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
    
    private long calculatePrice() {
    	long price;
    	
    	if(bikeRental.getTimeRented() < 10)
    		price = 0;
    	else if(bikeRental.getTimeRented() < 30)
    		price = 10000;
    	else 
    		price = 10000 + (bikeRental.getTimeRented() - 30)/15 * 3000;
    	
    	if(bike.getBikeType() == BikeType.ELECTRIC.getValue() || bike.getBikeType() == BikeType.COUPLE.getValue()) {
    		return (long) (price * 1.5);
    	}else {
    		return price;
    	}
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
            long newTime = System.currentTimeMillis();
            if (timestamp + 1000 <= newTime) {
                long deltaT = (newTime - timestamp) / 1000;
                time += deltaT;
                timestamp += 1000 * deltaT;
                timeRentedText.setText(Long.toString(time/60) + " phút " + Long.toString(time%60)+" giây");
                bikeRental.setTimeRented(time/60);
                priceText.setText(String.valueOf(calculatePrice()) + " vnd");
            }
        }
    };

}
