package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.YemekSepetiMySQL.Util.DatabaseUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class sqlDenemeController {
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_Ekle;

    @FXML
    private Button btn_Guncelle;

    @FXML
    private Button btn_Login;

    @FXML
    private Button btn_Sil;
    
    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_userName;

    @FXML
    void btn_Ekle_Click(ActionEvent event) {
    	DatabaseUtil.Add(txt_userName.getText().trim(), txt_password.getText().trim());
    }

    @FXML
    void btn_Guncelle_Click(ActionEvent event) {
    	DatabaseUtil.Update(txt_userName.getText().trim(), txt_password.getText().trim());
    }

    @FXML
    void btn_Login_Click(ActionEvent event) {
    	DatabaseUtil.Select(txt_userName.getText().trim(), txt_password.getText().trim());
    }

    @FXML
    void btn_Sil_Click(ActionEvent event) {
    	DatabaseUtil.Delete(txt_userName.getText().trim(), txt_password.getText().trim());
    }

    @FXML
    void initialize() {
   
    }

}
