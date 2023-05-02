package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView btn_exit;

    @FXML
    private Button btn_giris;

    @FXML
    private TextField txt_userName;

    @FXML
    void btn_exit_Click(MouseEvent event) {
    	System.exit(0);
    }

    @FXML
    void btn_giris_Click(ActionEvent event) {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.showAndWait();
    }

    @FXML
    void txt_userName_Click(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btn_exit != null : "fx:id=\"btn_exit\" was not injected: check your FXML file 'Login.fxml'.";
        assert btn_giris != null : "fx:id=\"btn_giris\" was not injected: check your FXML file 'Login.fxml'.";
        assert txt_userName != null : "fx:id=\"txt_userName\" was not injected: check your FXML file 'Login.fxml'.";

    }

}
