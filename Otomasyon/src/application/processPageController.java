package application;

import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import com.YemekSepetiMySQL.Util.DatabaseUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;

public class processPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_search;
    
    @FXML
    private Button btn_clear;
    
    @FXML
    private Button btn_refresh;

    @FXML
    private TableColumn<Process, Integer> col_ID;

    @FXML
    private TableColumn<Process, LocalDate> col_date;

    @FXML
    private TableColumn<Process, String> col_food;

    @FXML
    private TableColumn<Process, Integer> col_piece;

    @FXML
    private TableColumn<Process, Integer> col_price;

    @FXML
    private TableColumn<Process, String> col_restaurant;

    @FXML
    private TableColumn<Process, String> col_userName;

    @FXML
    private ComboBox<String> combo_user;

    @FXML
    private DatePicker date_finish;

    @FXML
    private DatePicker date_start;

    @FXML
    private TableView<Process> tableview_Process;

    @FXML
    private TextField txt_search;
    
    Connection connection=null;
    PreparedStatement query=null;
    ResultSet result=null;
    String sql;

    @FXML
    void btn_search_Click(ActionEvent event) {
    	if(date_start.getValue() == null && date_finish.getValue() == null)  
    		sql="select * from process where userName='"+combo_user.getValue()+"'";
    	if(date_start.getValue() != null && date_finish.getValue() == null){
    		if(combo_user.getSelectionModel().isEmpty())
    			sql="select *from process where processDate > '"+date_start.getValue()+"'";
    		else
    			sql="select *from process where processDate > '"+date_start.getValue()+"' and userName='"+combo_user.getValue()+"'";
    	}    		
    	if(date_start.getValue() == null && date_finish.getValue() != null){
    		if(combo_user.getSelectionModel().isEmpty())
    			sql="select *from process where processDate < '"+date_finish.getValue()+"'";
    		else
    			sql="select *from process where processDate < '"+date_finish.getValue()+"' and userName='"+combo_user.getValue()+"'";
    	}
    	if(date_start.getValue() != null && date_finish.getValue() != null){
    		if(combo_user.getSelectionModel().isEmpty())
    			sql="select *from process where processDate > '"+date_start.getValue()+"' and processDate < '"+date_finish.getValue()+"'";
    		else
    			sql="select *from process where processDate > '"+date_start.getValue()+"' and processDate < '"+date_finish.getValue()+"' and userName='"+combo_user.getValue()+"'";
    	}	
    	Select(sql);
    }
    
    @FXML
    void btn_clear_Click(ActionEvent event) {
    	txt_search.clear();
    	combo_user.valueProperty().setValue(null);
    	date_start.valueProperty().setValue(null);
    	date_finish.valueProperty().setValue(null);
    }

    @FXML
    void combo_user_Click(ActionEvent event) {

    }
    
    @FXML
    void txt_serach_Action(ActionEvent event) {
    	
    }
    
    @FXML
    void btn_refresh_Click(ActionEvent event) {
    	sql="select * from process";
    	Select(sql);
    }
    
    @FXML
    void txt_search_Pressed(KeyEvent event) {
    	if(txt_search.getText().equals("")) {
    		sql="select * from process";
    	}else {
    		sql="select * from process where foodName like '%"+txt_search.getText()+"%' or restaurantName like '%"+txt_search.getText()+"%'";
    	}
    	Select(sql);
    }
    
    public void ComboFill()
    {
    	connection=DatabaseUtil.Connect();
    	sql="select userName from process";
    	ObservableList<String> UsersList=FXCollections.observableArrayList();
    	try {
    		query=connection.prepareStatement(sql);
    		ResultSet result=query.executeQuery();
    		
    		while(result.next())
    		{
    			UsersList.add(result.getString("userName"));
    		}
    		
    		combo_user.setItems(UsersList);
    		
    	}catch(Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    }
    
    public void Select(String sql)
    {
		connection=DatabaseUtil.Connect();
    	ObservableList<Process> ProcessList=FXCollections.observableArrayList();
    	
    	try {
    		query=connection.prepareStatement(sql);
    		ResultSet result=query.executeQuery();
    		
    		while(result.next())
    		{
    			ProcessList.add(new Process(result.getInt("processID"),result.getInt("piece"),result.getInt("price"),result.getString("userName"),result.getString("restaurantName"),result.getString("foodName"),result.getDate("processDate")));
    		}
    		
    		col_ID.setCellValueFactory(new PropertyValueFactory<>("processID"));
    		col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    		col_food.setCellValueFactory(new PropertyValueFactory<>("foodName"));
    		col_piece.setCellValueFactory(new PropertyValueFactory<>("piece"));
    		col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
    		col_restaurant.setCellValueFactory(new PropertyValueFactory<>("restaurantName"));
    		col_userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
    		
    		tableview_Process.setItems(ProcessList);   			
    	} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    }

    @FXML
    void initialize() {
    	sql="select * from process";
    	Select(sql);
    	ComboFill();
    }

}
