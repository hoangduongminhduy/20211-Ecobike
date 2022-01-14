package util;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Config {

	// api constants
	public static final String CARD_CODE = "kscq2_group8_2021";
	public static final String OWNER = "Group 8";
	public static final String CVV = "924";
	public static final String EXPIRED_DATE = "1125";
	public static final String APP_CODE = "BLBOH0iy74M=";
	public static final String SECRET_KEY = "BSpBGt5MFwY=";
	public static final String TRANSACTION_COMMAND = "pay";	
	public static final int AMOUNT = 50000;
	
	public static final String GET_BALANCE_URL = "https://ecopark-system-api.herokuapp.com/api/card/balance/" + CARD_CODE;
	public static final String GET_VEHICLECODE_URL = "https://ecopark-system-api.herokuapp.com/api/get-vehicle-code/1rjdfasdfas";
	public static final String PROCESS_TRANSACTION_URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
	public static final String RESET_URL = "https://ecopark-system-api.herokuapp.com/api/card/reset";

	// demo data
	public static final String POST_DATA = "{"
			+ " \"secretKey\": \"" + SECRET_KEY + "\" ,"
			+ " \"transaction\": {"
			+ " \"command\": \"" + TRANSACTION_COMMAND + "\" ,"
			+ " \"cardCode\": \"" + CARD_CODE + "\" ,"
			+ " \"owner\": \""+ OWNER +"\" ,"
			+ " \"cvvCode\": \""+ CVV +"\" ,"
			+ " \"dateExpried\": \""+ EXPIRED_DATE +"\" ,"
			+ " \"transactionContent\": \"Pei debt\" ,"
			+ " \"amount\": "+ AMOUNT +" "
			+ "}"
		+ "}";
	
	public static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMTg2MDlfZ3JvdXAxXzIwMjAiLCJpYXQiOjE1OTkxMTk5NDl9.y81pBkM0pVn31YDPFwMGXXkQRKW5RaPIJ5WW5r9OW-Y";
	public static String CURRENCY = "VND";

	// static resource
	public static final String IMAGE_PATH = "asset/img";
	public static final String HOME_PATH  = "/view/fxml/home.fxml";
	public static final String PAYMENT_SCREEN_PATH = "/view/fxml/payment.fxml";
	public static final String SPLASH_SCREEN_PATH = "/view/fxml/splash.fxml";
	public static final String BIKE_DETAIL_SCREEN_PATH = "/view/fxml/bike_detail.fxml";
	public static final String RETURN_BIKE_SCREEN_PATH = "/view/fxml/return_bike.fxml";
	public static final String RENTING_SCREEN_PATH = "/view/fxml/renting.fxml";
}
