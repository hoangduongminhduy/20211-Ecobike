package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import entity.Parking;

/**
 * @author HoangLv
 * 
 */

public class ParkingController {
	   @FXML
	    private Text addressPark;

	    @FXML
	    private ImageView imgPark;

	    @FXML
	    private Text namePark;
	    
	    private Parking parking;
	    
	    /**
	     * Method pass data to screen
	     * @param parking
	     */
	    public void setData(Parking parking ) {
	    	 Image image = new Image(getClass().getResourceAsStream(parking.getImgSrc()));
	    	 imgPark.setImage(image);
	    	 addressPark.setText(parking.getAddress());
	    	 namePark.setText(parking.getName()); 
	    }

		public Parking getParking() {
			return parking;
		}

}
