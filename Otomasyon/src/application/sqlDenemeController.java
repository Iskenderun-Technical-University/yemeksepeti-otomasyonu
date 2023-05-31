package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.YemekSepetiMySQL.Util.DatabaseUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class sqlDenemeController {
	
	public sqlDenemeController()
	{
		connection=DatabaseUtil.Connect();
	}

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
    
    Connection connection=null;
    PreparedStatement query=null;
    ResultSet result=null;
    String sql;

    @FXML
    void btn_Ekle_Click(ActionEvent event) {
    	
    }

    @FXML
    void btn_Guncelle_Click(ActionEvent event) {

    }

    @FXML
    void btn_Login_Click(ActionEvent event) {
    	sql="select * from login where userName=? and password=?";
    	
    	try {
    		query=connection.prepareStatement(sql);
    		query.setString(1, txt_userName.getText().trim());
    		query.setString(2, txt_password.getText().trim());
    		
    		ResultSet result=query.executeQuery();
    		
    		if(!result.next())
    		{
    			txt_userName.setText("Kullanıcı adı veya şifre hatalı");
    		}
    		else {
    			result.getString(1);
    			System.out.println("User ID: "+result.getString("userID"));
    			System.out.println("Title: "+result.getString("title"));
    			System.out.println("Username: "+result.getString("userName"));
    			System.out.println("Password: "+result.getString("password"));
    		}
    	} catch (Exception e) {
    		txt_userName.setText(e.getMessage().toString());
    	}
    }

    @FXML
    void btn_Sil_Click(ActionEvent event) {

    }

    @FXML
    void initialize() {
   
    }

}
