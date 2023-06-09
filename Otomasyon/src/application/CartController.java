package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<CartClass, Button> col_button;

    @FXML
    private TableColumn<CartClass, String> col_foodName;

    @FXML
    private TableColumn<CartClass, Integer> col_piece;

    @FXML
    private TableColumn<CartClass, Double> col_price;

    @FXML
    private TableColumn<CartClass, Double> col_total;

    @FXML
    private Label lbl_total;

    @FXML
    private TableView<CartClass> tableView_Cart;
    
    Button[] buttons=new Button[100];
    int buttonNo=0;

    
    public void newOrder()
    {
    	ObservableList<CartClass> OrderList=FXCollections.observableArrayList();
    	OrderList.add(new CartClass(RestaurantController.foodName,RestaurantController.price,RestaurantController.price*2,buttons[buttonNo]));
    	col_foodName.setCellValueFactory(null);
    }

    @FXML
    void initialize() {
    	
    }

}
