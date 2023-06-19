package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.YemekSepetiMySQL.Util.DatabaseUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private Button btn_clear;

    @FXML
    private TextArea txtarea_adress;

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
    private TableColumn<Users, String> col_adress;

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
    
    public void TableFill(String sql)
    {
    	ObservableList<Users> UsersList=FXCollections.observableArrayList();
    	
    	try {
    		query=connection.prepareStatement(sql);
    		result=query.executeQuery();
    		while(result.next()) {
    			UsersList.add(new Users(result.getInt("userID"),result.getInt("title"),result.getString("userName"),result.getString("password"),buttons[buttonNo],result.getString("adress")));
    			buttonNo++;
    		}
    		col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	    col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
    	    col_userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
    	    col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
    	    col_process.setCellValueFactory(new PropertyValueFactory<>("button"));
    	    col_adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
    	    tableview_Admin.setItems(UsersList);
    	} catch(Exception e) {
    		lbl_message.setText(e.getMessage().toString());
    	}
    }

    @FXML
    void btn_add_Click(ActionEvent event) {
    	DatabaseUtil.Add(txt_userName.getText().trim(), DatabaseUtil.MD5(txt_password.getText().trim()),txtarea_adress.getText(),txt_title.getText());
    }

    @FXML
    void btn_delete_Click(ActionEvent event) {
    	DatabaseUtil.Delete(Integer.valueOf(txt_ID.getText().trim()),"delete from login where userID=?");
    }

    @FXML
    void btn_refresh_Click(ActionEvent event) {
    	sql="select * from login";
    	TableFill(sql);
    }

    @FXML
    void btn_search_Click(ActionEvent event) {
    	sql="select * from login where userName like'%"+txt_userName.getText()+"%'";
    	TableFill(sql);
    }
    
    @FXML
    void btn_clear_Click(ActionEvent event) {
    	txt_ID.clear();
    	txt_title.clear();
    	txt_userName.clear();
    	txt_password.clear();
    	txtarea_adress.clear();
    }

    @FXML
    void btn_update_Click(ActionEvent event) {
    	connection=DatabaseUtil.Connect();
    	sql="select * from login where userID=?";
    	try {
    		query=connection.prepareStatement(sql);;
    		query.setInt(1, Integer.valueOf(txt_ID.getText()));
    		result=query.executeQuery();
    		
    		if(result.next()) 
    		{
    			if(result.getString("userName")!=txt_userName.getText())
    				DatabaseUtil.Update("update login set userName='"+txt_userName.getText()+"' where userID='"+Integer.valueOf(txt_ID.getText())+"'");
    			if(result.getString("password")!=DatabaseUtil.MD5(txt_password.getText()))
    				DatabaseUtil.Update("update login set password='"+DatabaseUtil.MD5(txt_password.getText())+"' where userID='"+Integer.valueOf(txt_ID.getText())+"'");
    			if(result.getString("adress")!=txtarea_adress.getText())
    				DatabaseUtil.Update("update login set adress='"+txtarea_adress.getText()+"' where userID='"+Integer.valueOf(txt_ID.getText())+"'");
    		}
    			
    		
    		} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    }
    
    public void GoChart(String userName)
    {
    	ChartController.userName=userName;
    	try {
			Stage stage1 = new Stage();
			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("Chart.fxml"));
			Scene scene = new Scene(pane1);
			stage1.setScene(scene);
			//ChartController.userName=userName;
			stage1.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void tableview_Admin_Click(MouseEvent event) {
    	Users usr=new Users();
    	usr=(Users) tableview_Admin.getItems().get(tableview_Admin.getSelectionModel().getSelectedIndex());
    	txt_ID.setText(String.valueOf(usr.getId()));
    	txt_userName.setText(usr.getUserName());
    	txt_password.setText(usr.getPassword());
    	txtarea_adress.setText(usr.getAdress());
    	if(usr.getTitle()==0) {
    		txt_title.setText("Kullanıcı");
    	}else if(usr.getTitle()==1)
    		txt_title.setText("Admin");
    }

    @FXML
    void initialize() {
    	btn_search.setTooltip(new Tooltip("Kullanıcı adı ile arama yapabilirsiniz."));
    	for(int i=0; i<buttons.length; i++) {
    	buttons[i]=new Button();
      	buttons[i].setId("btn"+i);
      	buttons[i].setOnAction(this::Button);
    	}
    	
    	sql="select * from login";
    	TableFill(sql);
    }
    
    @FXML
    void Button(ActionEvent event) {
    	for(int i=0; i<buttons.length; i++) {
    		if(event.getSource()==buttons[i]) {
    			GoChart(tableview_Admin.getItems().get(i).getUserName());
    		}
        }
    }

}
