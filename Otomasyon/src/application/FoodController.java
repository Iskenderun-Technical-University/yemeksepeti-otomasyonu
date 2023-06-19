package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.YemekSepetiMySQL.Util.DatabaseUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;

public class FoodController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_file;

    @FXML
    private Button btn_refresh;

    @FXML
    private Button btn_update;
    
    @FXML
    private Button btn_search;
    
    @FXML
    private TableView<Food> tableview_Food;
    
    @FXML
    private TableColumn<Food, String> col_extra;

    @FXML
    private TableColumn<Food, String> col_foodName;

    @FXML
    private TableColumn<Food, Integer> col_id;

    @FXML
    private TableColumn<Food, String> col_imageURL;

    @FXML
    private TableColumn<Food, Double> col_price;

    @FXML
    private TableColumn<Food, String> col_restaurant;
    
    @FXML
    private ImageView img;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private Label label;

    @FXML
    private Label lbl_file;

    @FXML
    private TextField txt_extra;

    @FXML
    private TextField txt_foodName;

    @FXML
    private TextField txt_price;
    
    Connection connection=null;
    PreparedStatement query=null;
    ResultSet result=null;
    String sql;
    
    
    String fileName;
    File file; 

    @FXML
    void btn_add_Click(ActionEvent event) {
    	if(combo.getSelectionModel().getSelectedItem()==null)
    		label.setText("Restorant seçimi yapılmalı.");
    	else if(txt_foodName.getText().equals(""))
    		label.setText("Yemek adı boş geçilemez.");
    	else
    	{
    		sql="select * from food where restaurantName=? and foodName=?";
    		
    		try {
        		query=connection.prepareStatement(sql);
        		query.setString(1, combo.getSelectionModel().getSelectedItem());
        		query.setString(2, txt_foodName.getText());
        		result=query.executeQuery();
        		
        		if(!result.next())
        		{
        			if(txt_price.getText().equals(""))
        				label.setText("Fiyat bilgisi boş geçilemez.");
        			else
        				{
        				try {
        					Double price = Double.parseDouble(txt_price.getText());
        					label.setText(null);
        					File directory=new File("image\\Food\\"+combo.getSelectionModel().getSelectedItem());
        					SaveImage(file,directory);
        					Add();
        				} catch (NumberFormatException e) {
        					label.setText("Fiyat kısmına sayı girmeniz gerekmektedir.");
        				}
        			}
        		}
        		else
        			label.setText("Restorant'a ait bu isimde farklı bir ürün var.");
        		
        		} catch (Exception e) {
        		System.out.println(e.getMessage().toString());
        	}
    		FillTable("select * from food");
    	}
    }

    @FXML
    void btn_clear_Click(ActionEvent event) {
    	combo.valueProperty().setValue(null);
    	txt_foodName.setText(null);
    	txt_price.setText(null);
    	txt_extra.setText(null);
    	file=null;
    	img.setImage(null);
    }

    @FXML
    void btn_delete_Click(ActionEvent event) {
    	sql="delete from food where foodID=?";
    	int id=tableview_Food.getSelectionModel().getSelectedItem().getFoodID();
    	DatabaseUtil.Delete(id, sql);
    	FillTable("select * from food");
    }
    
    @FXML
    void btn_search_Click(ActionEvent event) {
    	sql="select * from food where foodName= '"+txt_foodName.getText()+"'";
    	try {
    		query=connection.prepareStatement(sql);
    		result=query.executeQuery();
    		if(result.next())
    		{
    			FillTable(sql);
    		}	
			} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    }

    @FXML
    void btn_file_Click(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Resim Seç");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Resim Dosyaları", "*.jpg", "*.png", "*.jpeg")
            );

            Stage stage = (Stage) btn_file.getScene().getWindow();
            file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                // Seçilen resmi işle
            	lbl_file.setText("Eklendi");
            	fileName = file.getName();
            	Image image = new Image(file.toURI().toString());
                img.setImage(image);
            }
    }

    @FXML
    void btn_refresh_Click(ActionEvent event) {
    	FillTable("select * from food");
    }

    @FXML
    void btn_update_Click(ActionEvent event) {
    	int id=tableview_Food.getSelectionModel().getSelectedItem().getFoodID();
    	if(tableview_Food.getSelectionModel().getSelectedItem().getFoodName()!=txt_foodName.getText())
    	{
    		sql="update food set foodName= '"+txt_foodName.getText()+"' where foodID='"+id+"'";
    		DatabaseUtil.Update(sql);
    	}
    	if(tableview_Food.getSelectionModel().getSelectedItem().getPrice()!=Double.valueOf(txt_price.getText()))
    	{
    		sql="update food set price= '"+Double.valueOf(txt_price.getText())+"' where foodID='"+id+"'";
    		DatabaseUtil.Update(sql);
    	}
    	if(tableview_Food.getSelectionModel().getSelectedItem().getExtra()!=txt_extra.getText())
    	{
    		sql="update food set extra= '"+txt_extra.getText()+"' where foodID='"+id+"'";
    		DatabaseUtil.Update(sql);
    	}
    	FillTable("select * from food");
    }
    
    @FXML
    void tableview_Food_Click(MouseEvent event) {
    	combo.getSelectionModel().select(tableview_Food.getSelectionModel().getSelectedItem().getRestaurantName());
    	txt_foodName.setText(tableview_Food.getSelectionModel().getSelectedItem().getFoodName());
    	txt_price.setText(String.valueOf(tableview_Food.getSelectionModel().getSelectedItem().getPrice()));
    	txt_extra.setText(tableview_Food.getSelectionModel().getSelectedItem().getExtra());
    	img.setImage(new Image("file:"+tableview_Food.getSelectionModel().getSelectedItem().getImageURL()));
    }
    
    public void FillCombo()
    {
    	sql="select * from restaurant";
    	try {
    		query=connection.prepareStatement(sql);;
    		result=query.executeQuery();
    		
    		while(result.next()) 
    		{
    			combo.getItems().add(result.getString("restaurantName"));
    		}	    		
    		} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    }
    
    public static void SaveImage(File selectedFile, File saveDirectory) {
        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            File destination = new File(saveDirectory, fileName);

            try {
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void Add()
    {
    	sql="insert into food(foodName, restaurantName, price, imageURL, extra) values(?,?,?,?,?)";
		
		try {
    		query=connection.prepareStatement(sql);
    		query.setString(1, txt_foodName.getText());
    		query.setString(2, combo.getSelectionModel().getSelectedItem());
    		query.setDouble(3, Double.valueOf(txt_price.getText()));
    		query.setString(4, "image\\Food\\"+combo.getSelectionModel().getSelectedItem()+"\\"+fileName);
    		query.setString(5, txt_extra.getText());
    		query.executeUpdate();
    		
    		} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    }
    
    public void FillTable(String sql)
    {
    	ObservableList<Food> FoodList=FXCollections.observableArrayList();
    	try {
    		query=connection.prepareStatement(sql);;
    		result=query.executeQuery();
    		
    		while(result.next()) 
    		{
    			FoodList.add(new Food(result.getInt("foodID"),result.getString("foodName"),result.getString("restaurantName"),result.getDouble("price"),result.getString("extra"),result.getString("imageURL")));
    		}
    		
    		col_id.setCellValueFactory(new PropertyValueFactory<>("foodID"));
    		col_foodName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
    		col_restaurant.setCellValueFactory(new PropertyValueFactory<>("restaurantName"));
    		col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
    		col_extra.setCellValueFactory(new PropertyValueFactory<>("extra"));
    		col_imageURL.setCellValueFactory(new PropertyValueFactory<>("imageURL"));
    		tableview_Food.setItems(FoodList);
    		
    		} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    }

    @FXML
    void initialize() {
    	connection=DatabaseUtil.Connect();
    	FillCombo();
    	FillTable("select * from food");
    }

}
