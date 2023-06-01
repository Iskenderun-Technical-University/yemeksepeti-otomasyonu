package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.YemekSepetiMySQL.Util.DatabaseUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;

public class AdminController {
	
	public AdminController()
	{
		connection=DatabaseUtil.Connect();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_refresh;

    @FXML
    private Button btn_search;

    @FXML
    private Button btn_update;

    @FXML
    private TableColumn<Users, Integer> col_ID;

    @FXML
    private TableColumn<Users, String> col_password;

    @FXML
    private TableColumn<Users, String> col_process;

    @FXML
    private TableColumn<Users, Integer> col_title;

    @FXML
    private TableColumn<Users, String> col_userName;

    @FXML
    private Label lbl_message;

    @FXML
    private TableView<Users> tableview_Admin;

    @FXML
    private TextField txt_ID;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_title;

    @FXML
    private TextField txt_userName;
    
	Connection connection=null;
    PreparedStatement query=null;
    ResultSet result=null;
    String sql;
    
    Button[] buttons=new Button[100];
    int buttonNo=0;
    
    public void TableFill(TableView table)
    {
    	sql="select *from login";
    	ObservableList<Users> UsersList=FXCollections.observableArrayList();
    	
    	try {
    		query=connection.prepareStatement(sql);
    		ResultSet result=query.executeQuery();
    		while(result.next()) {
    			UsersList.add(new Users(result.getInt("userID"),result.getInt("title"),result.getString("userName"),result.getString("password"),buttons[buttonNo]));
    			buttonNo++;
    		}
    		col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	    col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
    	    col_userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
    	    col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
    	    col_process.setCellValueFactory(new PropertyValueFactory<>("button"));
    	    table.setItems(UsersList);
    	} catch(Exception e) {
    		lbl_message.setText(e.getMessage().toString());
    	}
    }

    @FXML
    void btn_add_Click(ActionEvent event) {
    	DatabaseUtil.Add(txt_userName.getText().trim(), DatabaseUtil.MD5(txt_password.getText().trim()));
    }

    @FXML
    void btn_delete_Click(ActionEvent event) {
    	DatabaseUtil.Delete(Integer.valueOf(txt_ID.getText().trim()));
    }

    @FXML
    void btn_refresh_Click(ActionEvent event) {
    	TableFill(tableview_Admin);
    }

    @FXML
    void btn_search_Click(ActionEvent event) {

    }

    @FXML
    void btn_update_Click(ActionEvent event) {
    	DatabaseUtil.Update(txt_userName.getText().trim(), DatabaseUtil.MD5(txt_password.getText().trim()));
    }

    @FXML
    void tableview_Admin_Click(MouseEvent event) {
    	Users usr=new Users();
    	usr=(Users) tableview_Admin.getItems().get(tableview_Admin.getSelectionModel().getSelectedIndex());
    	txt_ID.setText(String.valueOf(usr.getId()));
    	txt_userName.setText(usr.getUserName());
    	txt_password.setText(usr.getPassword());
    	if(usr.getTitle()==0) {
    		txt_title.setText("Kullanıcı");
    	}else if(usr.getTitle()==1) {
    		txt_title.setText("Çalışan");
    	}else if(usr.getTitle()==2) {
    		txt_title.setText("Yönetici");
    	}
    }

    @FXML
    void initialize() {
    	
    	for(int i=0; i<buttons.length; i++) {
    	buttons[i]=new Button();
      	buttons[i].setId("btn"+i);
      	buttons[i].setOnAction(this::Button);
    	}
    	
    	TableFill(tableview_Admin);
    }
    
    @FXML
    void Button(ActionEvent event) {
    	for(int i=0; i<buttons.length; i++) {
    		if(event.getSource()==buttons[i]) {
    			System.out.println(i+". butona tıklandı.");
    		}
        }
    }

}
