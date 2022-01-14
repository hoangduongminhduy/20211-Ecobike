package view.handler.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import entity.Parking;

public class HomeScreenHandler implements Initializable{
	@FXML
    private Button btnSearch;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private TextField serachValue;
    
    @FXML
    private Text textError;
    
    HomeController homeController = new HomeController();
    
    /**
	 * Method render parking to screen
	 * @param list: list of parking
	 * @param column: index column start
	 * @param row: index row start
	 */
	public void renderParking(ObservableList<Parking> list, int column, int row) {
		try {
            for (Parking parking: list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/parking.fxml"));
                AnchorPane parkingItem = fxmlLoader.load();
                homeController.setViewParking(fxmlLoader, parking);
                if (column == 4) {
                    column = 0;
                    row++;
                }
                grid.add(parkingItem, column++, row); //(child,column,row)
                GridPane.setMargin(parkingItem, new Insets(5));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
	}
	
	/**
	 * Method print note error to screen
	 * @param error
	 */
	public void notifyError(String error) {
		textError.setText("");
		textError.setText(error);
	}
	
	
	/**
	 * Method search parking and render to screen
	 * @param e
	 */
	public void searchParking(ActionEvent e) {
		String searchValue = serachValue.getText().toString().toLowerCase();
		if(homeController.validateInfor(searchValue)) {
			ObservableList<Parking> resultParks = FXCollections.observableArrayList();
			resultParks = homeController.searchParking(searchValue);
			grid.getChildren().clear();
			if(resultParks.size() > 0) {		
				textError.setText("");
				renderParking(resultParks, 0, 1);
			}else {
				notifyError("Không tìm được bãi xe có mã hoặc địa chỉ như trên.");
			}
		}else {
			textError.setText("Mã bãi xe hoặc địa chỉ không chứa ký tự đặc biệt và không được null.");
		}
		
	}
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {    
    	renderParking(homeController.getParkingData(), 0, 3);
    }
}
