package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private PasswordField txt_password;

    @FXML
    private TextField txt_userName;

    @FXML
    void btn_exit_Click(MouseEvent event) 
    {
    	System.exit(0);
    }

    @FXML
    void btn_giris_Click(ActionEvent event) 
    {
    	ObservableList<String> user=FXCollections.observableArrayList("deneme","uned");
    	ObservableList<String> password=FXCollections.observableArrayList("deneme123","123");
    	if(user.contains(txt_userName.getText()) && password.contains(txt_password.getText())) 
    	{
    		try {
    			Stage stage1 = new Stage();
    			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("deneme.fxml"));
    			Scene scene = new Scene(pane1);
    			stage1.setScene(scene);
    			stage1.initStyle(StageStyle.UNDECORATED);
    			stage1.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else
    	{
    		Alert a1=new Alert(AlertType.ERROR);
    		a1.setTitle("HATA");
    		a1.setHeaderText("Kullanıcı adı veya şifre hatalı.!");
    		a1.show();
    	}
    }

    @FXML
    void initialize() {
    }
    
}
