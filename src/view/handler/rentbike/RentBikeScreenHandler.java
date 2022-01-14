package view.handler.rentbike;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.Config;
import entity.Bike;
import entity.BikeType;
import entity.Dock;

public class RentBikeScreenHandler implements Initializable {
	@FXML
    public Pane mainPane;

    @FXML
    private Button btnWatch;

    @FXML
    public TableView<Bike> tableBikeInfo;
    
    @FXML
    private TableColumn<Bike, Integer> col_bikeID;

    @FXML
    private TableColumn<Bike, String> col_bikeType;
    
    @FXML
    private TableColumn<Bike, String> col_battery;
    
    @FXML
    private TableColumn<Bike, String> col_lisencePlate;

    @FXML
    private TextField textBikeCode;
    
    @FXML
    private Text textDockName;
    
    private ObservableList<Bike> list = FXCollections.observableArrayList(
    		new Bike(1, "dock 1", BikeType.ELECTRIC.getValue(), "DH73JF", "80%", "red"),
    		new Bike(2, "dock 1", BikeType.ELECTRIC.getValue(), "DH73JF", "80%", "red"),
    		new Bike(3, "dock 1", BikeType.ELECTRIC.getValue(), "DH73JF", "80%", "red"),
    		new Bike(4, "dock 1", BikeType.ELECTRIC.getValue(), "DH73JF", "80%", "red"),
    		new Bike(5, "dock 1", BikeType.ELECTRIC.getValue(), "DH73JF", "80%", "red"),
    		new Bike(6, "dock 1", BikeType.ELECTRIC.getValue(), "DH73JF", "80%", "red"),
    		new Bike(7, "dock 1", BikeType.ELECTRIC.getValue(), "DH73JF", "80%", "red"),
    		new Bike(8, "dock 1", BikeType.ELECTRIC.getValue(), "DH73JF", "80%", "red"),
    		new Bike(9, "dock 1", BikeType.ELECTRIC.getValue(), "DH73JF", "80%", "red"),
    		new Bike(10, "dock 1", BikeType.ELECTRIC.getValue(), "DH73JF", "80%", "red")
    		);
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	textDockName.setText("dock 1");
    	
    	col_lisencePlate.setCellValueFactory(new PropertyValueFactory<Bike, String>("licensePlate"));
    	col_battery.setCellValueFactory(new PropertyValueFactory<Bike, String>("battery"));
    	col_bikeID.setCellValueFactory(new PropertyValueFactory<Bike, Integer>("bikeID"));
    	col_bikeType.setCellValueFactory(new PropertyValueFactory<Bike, String>("bikeType"));
    	
    	tableBikeInfo.setItems(list);
    }
    

    @FXML
    void onClickWatch(ActionEvent event) {
    	int searchID = Integer.valueOf(textBikeCode.getText());
    	tableBikeInfo.getItems().stream()
        .filter(item -> item.getBikeID() == searchID)
        .findAny()
        .ifPresent(item -> {
            tableBikeInfo.getSelectionModel().select(item);
            tableBikeInfo.scrollTo(item);
        });
    }
    
    @FXML
    void onClickRentBikeBtn(ActionEvent event) throws IOException {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("Bạn có muốn thuê xe không?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
    		Stage stage = (Stage) mainPane.getScene().getWindow();
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(Config.PAYMENT_SCREEN_PATH));
    		Parent parent = loader.load();
    		
    		Bike bike = tableBikeInfo.getSelectionModel().getSelectedItem();
    		PaymentScreenHandler paymentScreenHandler = (PaymentScreenHandler) loader.getController();
    		paymentScreenHandler.initializeBike(bike);
    		
    		stage.setScene(new Scene(parent));
    		stage.show();
    	}
    	
    }
    
    @FXML
    void handleRow(MouseEvent event) throws IOException {
    	if(event.getClickCount() == 2 && tableBikeInfo.getSelectionModel().getSelectedItem() != null) {
    		Bike bike = tableBikeInfo.getSelectionModel().getSelectedItem();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(Config.BIKE_DETAIL_SCREEN_PATH));
    		Parent parent = loader.load();
    		
    		BikeInfoHandler bikeInfoHandler = (BikeInfoHandler) loader.getController();
    		bikeInfoHandler.initializeTextField(bike);
    		
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
    	}
    }
}
