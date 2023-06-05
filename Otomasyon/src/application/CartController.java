package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class CartController {

	Integer[] arr= {0,1,2,3,4,5};
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> cmb1;

    @FXML
    private ComboBox<Integer> cmb2;

    @FXML
    private ComboBox<Integer> cmb3;

    @FXML
    private ComboBox<Integer> cmb4;

    @FXML
    private ComboBox<Integer> cmb5;

    @FXML
    private ComboBox<Integer> cmb6;

    @FXML
    private Label lbl_Name1;

    @FXML
    private Label lbl_Name2;

    @FXML
    private Label lbl_Name3;

    @FXML
    private Label lbl_Name4;

    @FXML
    private Label lbl_Name5;

    @FXML
    private Label lbl_Name6;

    @FXML
    private Label lbl_lprice1;

    @FXML
    private Label lbl_lprice2;

    @FXML
    private Label lbl_lprice3;

    @FXML
    private Label lbl_lprice4;

    @FXML
    private Label lbl_lprice5;

    @FXML
    private Label lbl_lprice6;

    @FXML
    private Label lbl_price1;

    @FXML
    private Label lbl_price2;

    @FXML
    private Label lbl_price3;

    @FXML
    private Label lbl_price4;

    @FXML
    private Label lbl_price5;

    @FXML
    private Label lbl_price6;

    @FXML
    private Label lbl_total;

    @FXML
    void cmb1_Action(ActionEvent event) {
    	int x=cmb1.getSelectionModel().getSelectedItem();
    	lbl_lprice1.setText(String.valueOf(Integer.valueOf(lbl_price1.getText())*x));
    }

    @FXML
    void cmb2_Action(ActionEvent event) {

    }

    @FXML
    void cmb3_Action(ActionEvent event) {

    }

    @FXML
    void cmb4_Action(ActionEvent event) {

    }

    @FXML
    void cmb5_Action(ActionEvent event) {

    }

    @FXML
    void cmb6_Action(ActionEvent event) {

    }
    
    public void Fill() 
    {
    	lbl_Name1.setText(RestaurantController.foodName);
    	lbl_price1.setText(String.valueOf(RestaurantController.price));
    	
    	
    }
    
    public void Combo()
    {
    	cmb1.setVisible(true);
    	cmb1.getItems().addAll(arr);
    }

    @FXML
    void initialize() {
    	Combo();
    	Fill();
    }

}
