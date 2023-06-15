package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.YemekSepetiMySQL.Util.DatabaseUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class ChartController {
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_Add;

    @FXML
    private Button btn_Show;
    
    @FXML
    private Button btn_Del;
    
    @FXML
    private TableView<Users> listUser;
    
    @FXML
    private TableColumn<Users, String> col_User;

    @FXML
    private LineChart<?, ?> chart;

    @FXML
    private DatePicker date_First;

    @FXML
    private DatePicker date_Last;

    @FXML
    private Label label;
    
    @FXML
    private TextField txt_Piece;

    @FXML
    private TextField txt_User;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    
    ObservableList<Users> users = FXCollections.observableArrayList();
    ObservableList<Integer> user1 = FXCollections.observableArrayList();
    ObservableList<Integer> user2 = FXCollections.observableArrayList();
    ObservableList<Integer> user3 = FXCollections.observableArrayList();
    ObservableList<LocalDate> date = FXCollections.observableArrayList();
    
    Connection connection=null;
    PreparedStatement query=null;
    ResultSet result=null;
    String sql;
    

    @FXML
    void btn_Add_Click(ActionEvent event) {
    	 boolean isIncluded = false;
         for (int i=0; i<users.size(); i++) {
             if (listUser.getItems().get(i).getUserName().equals(txt_User.getText().trim())) {
                 isIncluded = true;
                 break;
             }
         }
    	if(isIncluded)
    		label.setText("Zaten ekli.");
    	else
    	{
    		AddUser(txt_User.getText());
    		label.setText("");
    	}	
    }
    
    @FXML
    void btn_Del_Click(ActionEvent event) {
    	listUser.getItems().remove(listUser.getSelectionModel().getSelectedItem());
    }

    
    @FXML
    void btn_Show_Click(ActionEvent event) {
    	if(listUser.getItems().isEmpty())
    		label.setText("Kullanıcı girmeniz gerekiyor.");
    	else
    	{
    		if(date_First.getValue()==null || date_First.getValue()==null)
    			label.setText("Tarihleri seçmeniz gerekiyor.");
    		else
    		{
    			if(!txt_Piece.getText().matches("-?\\d+"))
    				label.setText("Adet kısmına bir sayı girmeniz gerekiyor.");
    			else
    			{
    				if(Integer.valueOf(txt_Piece.getText())>10 || Integer.valueOf(txt_Piece.getText())<2)
    					label.setText("Adet 2'den büyük 10'dan küçük olmalı");
    				else
    				{
    					DatePref();
    					int i=0;
    					for(Users tmp:users)
    					{
    						DailyAmount(tmp.getUserName());
    						i++;
    					}
    					/*
    					if(users.size()==3)
    						Show(users.get(0).getUserName(),users.get(1).getUserName());
    					else
    						Show(users.get(0).getUserName(),users.get(1).getUserName());
    					*/
    					for(Users a:users)
    						System.out.println(a.getUserName());
    					
    					for(Integer a:user1)
    						System.out.println(a);
    					for(Integer a:user2)
    						System.out.println(a);
    					for(LocalDate a:date)
    						System.out.println(a);
    				}
    			}
    		}	
    	}
    }
    
    @FXML
    void listUser_Click(MouseEvent event) {

    }
    
    public void AddUser(String userName)
    {	
		try {
		    sql = "select * from login where userName='"+userName+"'";
		    query = connection.prepareStatement(sql);
		    ResultSet result = query.executeQuery();
		    while (result.next()) {
		        users.add(new Users(result.getString("userName")));
		    }
		    col_User.setCellValueFactory(new PropertyValueFactory<>("userName"));
		    listUser.setItems(users);
		    txt_User.setText(null);
		} catch (Exception e) {
		    System.out.println(e.getMessage().toString());
		}
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void Show(String usr1,String usr2)
    {
    	XYChart.Series series1 = new XYChart.Series();
        series1.setName(usr1);
        XYChart.Series series2 = new XYChart.Series();
        series2.setName(usr2);
        //XYChart.Series series3 = null;
        
        for (int i = 0; i < Integer.valueOf(txt_Piece.getText()); i++) {
            series1.getData().add(new XYChart.Data<String, Integer>(String.valueOf(date.get(i)), user1.get(i)));
            series2.getData().add(new XYChart.Data<String, Integer>(String.valueOf(date.get(i)), user2.get(i)));
            
           /* if (usr3 != null) {
                if (series3 == null) {
                    series3 = new XYChart.Series();
                    series3.setName(usr3);
                }
                series3.getData().add(new XYChart.Data<String, Integer>(String.valueOf(date[i]), user3[i]));
                if (i + 1 == Integer.valueOf(txt_Piece.getText())) {
                    chart.getData().add(series3);
                }
            }*/
        }
        
        chart.getData().add(series1);
        chart.getData().add(series2);
    }
    
    public void DatePref()
    {
    	try 
    	{
    		LocalDate first=date_First.getValue();
    		LocalDate last=date_Last.getValue();
        	int diff=((last.getYear()-first.getYear())*365)+((last.getMonthValue()-first.getMonthValue())*30)+(last.getDayOfMonth()-first.getDayOfMonth());
        	int piece=Integer.valueOf(txt_Piece.getText());
        	int addedDay=diff/(piece-1);
        	for(int i=0; i<piece; i++)
        	{
        		date.add(first.plusDays(addedDay*i));
        	}
    	}
    	catch(Exception e) 
    	{
    		System.out.println(e.getMessage().toString());
    	}
    }
    
    public void DailyAmount(String userName) {
        try {
            String sql = "SELECT * FROM process WHERE userName = ?";
            query = connection.prepareStatement(sql);
            query.setString(1, userName);
            result = query.executeQuery();
            
            while (result.next()) {
                LocalDate processDate = result.getDate("processDate").toLocalDate();
                int piece = result.getInt("piece");
                
                for (int i = 0; i < date.size(); i++) {
                    LocalDate currentDate = date.get(i);
                    
                    if (processDate.isEqual(currentDate)) {
                        if (userName.equals(users.get(0).getUserName())) {
                            user1.set(i, user1.get(i) + piece);
                        } else if (userName.equals(users.get(1).getUserName())) {
                            user2.set(i, user2.get(i) + piece);
                        } else if (userName.equals(users.get(2).getUserName())) {
                            user3.set(i, user3.get(i) + piece);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }


	@FXML
    void initialize() {
		connection=DatabaseUtil.Connect();
	}

}
