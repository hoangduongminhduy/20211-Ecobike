package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import entity.Parking;

/**
 * @author HoangLV
 */
public class HomeController extends BaseController {
	
	 ObservableList<Parking> listParks = FXCollections.observableArrayList();
	 private  ParkingController parkingController;

	/**
	 * Method validate string input from user
	 * @param infor: string input from user
	 * @return true if string valid 
	 * @return false if string null or contain special character
	 */
	public boolean validateInfor(String infor) {
			if (infor == null || infor.trim().isEmpty()) {
		        System.out.println("Incorrect format of string");
		        return false;
		     }
			Pattern letter = Pattern.compile("[a-zA-z]");
		    Pattern digit = Pattern.compile("[0-9]");
		    Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
		     
	        Matcher hasLetter = letter.matcher(infor);
	        Matcher hasDigit = digit.matcher(infor);
	        Matcher hasSpecial = special.matcher(infor);

	        return (hasLetter.find() || hasDigit.find()) && !hasSpecial.find();
	   
	    }
	/**
	 * Method search parking and render to screen
	 * @param string: string from user need validate
	 */
	public ObservableList<Parking> searchParking(String string) {
		listParks = getParkingData();
		ObservableList<Parking> resultParks = FXCollections.observableArrayList();
		for(Parking parking: listParks) {
			if(parking.getAddress().toLowerCase().indexOf(string) > -1) {
				resultParks.add(parking);
			}
			else if(parking.getName().toLowerCase().indexOf(string) > -1) {
				resultParks.add(parking);
			}
		}
		return resultParks;
	}
	
	/**
	 * Method set view for a parking
	 * @param fxmlLoader
	 * @param parking
	 */
	public void  setViewParking( FXMLLoader fxmlLoader, Parking parking) {
		parkingController = fxmlLoader.getController();
		parkingController.setData(parking);
	}

}
