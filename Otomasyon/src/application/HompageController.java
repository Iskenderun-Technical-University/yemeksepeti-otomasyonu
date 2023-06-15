package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HompageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView img_delivery;

    @FXML
    private ImageView img_exit;

    @FXML
    private ImageView img_foodLists;

    @FXML
    private ImageView img_order;

    @FXML
    private ImageView img_orderHistory;

    @FXML
    private ImageView img_restaurants;

    @FXML
    private ImageView img_userInformations;
    

    @FXML
    void img_delivery_Click(MouseEvent event) {

    }

    @FXML
    void img_exit_Click(MouseEvent event) {
    	System.exit(0);
    }

    @FXML
    void img_foodLists_Click(MouseEvent event) {
    	
    }

    @FXML
    void img_orderHistory_Click(MouseEvent event) {
    	try {
			Stage stage1 = new Stage();
			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("processPage.fxml"));
			Scene scene = new Scene(pane1);
			stage1.setScene(scene);
			stage1.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

    @FXML
    void img_order_Click(MouseEvent event) {

    }

    @FXML
    void img_restaurants_Click(MouseEvent event) {

    }

    @FXML
    void img_userInformations_Click(MouseEvent event) {
    	try {
			Stage stage1 = new Stage();
			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("Admin.fxml"));
			Scene scene = new Scene(pane1);
			stage1.setScene(scene);
			//stage1.initStyle(StageStyle.UNDECORATED);
			stage1.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }

}
