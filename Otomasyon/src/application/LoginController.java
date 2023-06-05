package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.YemekSepetiMySQL.Util.DatabaseUtil;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.*;

public class LoginController {
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label lbl_message;

    @FXML
    private ImageView btn_exit;

    @FXML
    private Button btn_giris;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_userName;
    
    @FXML
    private AnchorPane loginPane;
    Stage stage;
    Connection connection=null;
    PreparedStatement query=null;
    ResultSet result=null;
    String sql;
    public static String userSession;    
    
    @FXML
    void btn_exit_Click(MouseEvent event) 
    {
    	System.exit(0);
    }

    @FXML
    void btn_giris_Click(ActionEvent event) 
    {
    	connection=DatabaseUtil.Connect();
		String sql="select * from login where password=? and userName=?";
		
		if(txt_userName.getText()=="" || txt_password.getText()=="") {
			lbl_message.setText("Kullanıcı adı yada şifre boş bırakılamaz.");
		}else {
			try {
	    		query=connection.prepareStatement(sql);
	    		query.setString(2, txt_userName.getText());
	    		query.setString(1, DatabaseUtil.MD5(txt_password.getText()));
	    		result=query.executeQuery();
	    		
	    		if(!result.next())
	    			lbl_message.setText("Kullanıcı adı veya şifre hatalı.");
	    		else {
	    			if(result.getInt("title")==0) {
	    				try {
		        			Stage stage1 = new Stage();
		        			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("Order.fxml"));
		        			Scene scene = new Scene(pane1);
		        			stage1.setScene(scene);
		        			stage1.initStyle(StageStyle.UNDECORATED);
		        			stage1.show();
		        			stage=(Stage) loginPane.getScene().getWindow();
		        			stage.close();
		        			
		        		} catch(Exception e) {
		        			e.printStackTrace();
		        		}
		    		}
	    			else {
	    				try {
		        			Stage stage1 = new Stage();
		        			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("Homepage.fxml"));
		        			Scene scene = new Scene(pane1);
		        			stage1.setScene(scene);
		        			stage1.initStyle(StageStyle.UNDECORATED);
		        			stage1.show();
		        			stage=(Stage) loginPane.getScene().getWindow();
		        			stage.close();
		        			
		        		} catch(Exception e) {
		        			e.printStackTrace();
		        		}
		    		}
	    			userSession=result.getString("userName");
	    			}
	    		} catch (Exception e) {
	    		System.out.println(e.getMessage().toString());
	    		}
		}		
    }
    
    @FXML
    void initialize() {
    }
    
}
