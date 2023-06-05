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
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.*;

import javafx.scene.image.ImageView;
import java.io.*; 

public class OrderController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_cart;

    @FXML
    private ImageView img1;
    
    @FXML
    private ImageView img2;
    
    @FXML
    private ImageView img3;
    
    @FXML
    private ImageView img4;
    
    @FXML
    private ImageView img5;
    
    @FXML
    private ImageView img6;
    
    @FXML
    private ImageView img7;
    
    @FXML
    private ImageView img8;

    @FXML
    private ImageView img_exit;
    
    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    @FXML
    private Label lbl3;

    @FXML
    private Label lbl4;

    @FXML
    private Label lbl5;

    @FXML
    private Label lbl6;

    @FXML
    private Label lbl7;

    @FXML
    private Label lbl8;

    @FXML
    private TextField txt_search;
    
    @FXML
    private AnchorPane orderPane;
    Stage stage;
    
    public static String restaurantName;
    
    public int i=0;     
    
    ImageView[] img=new ImageView[8];
    
    Label[] label=new Label[8];
    
    Connection connection=null;
    PreparedStatement query=null;
    ResultSet result=null;
    String sql;

    @FXML
    void btn_cart_Click(ActionEvent event) {

    }

    @FXML
    void img1_Click(MouseEvent event) {
    	goRest(0);
    }

    @FXML
    void img1_Dragged(MouseEvent event) {

    }

    @FXML
    void img2_Click(MouseEvent event) {
    	goRest(1);
    }

    @FXML
    void img2_Dragged(MouseEvent event) {
    	
    }

    @FXML
    void img3_Click(MouseEvent event) {
    	goRest(2);
    }

    @FXML
    void img3_Dragged(MouseEvent event) {

    }

    @FXML
    void img4_Click(MouseEvent event) {
    	goRest(3);
    }

    @FXML
    void img4_Dragged(MouseEvent event) {

    }

    @FXML
    void img5_Click(MouseEvent event) {
    	goRest(4);
    }

    @FXML
    void img5_Dragged(MouseEvent event) {

    }

    @FXML
    void img6_Click(MouseEvent event) {
    	goRest(5);
    }

    @FXML
    void img6_Dragged(MouseEvent event) {

    }

    @FXML
    void img7_Click(MouseEvent event) {
    	goRest(6);
    }

    @FXML
    void img7_Dragged(MouseEvent event) {

    }

    @FXML
    void img8_Click(MouseEvent event) {
    	goRest(7);
    }

    @FXML
    void img8_Dragged(MouseEvent event) {

    }
    
    @FXML
    void img_exit_Dragged(MouseEvent event) {

    }

    @FXML
    void img_exit_Click(MouseEvent event) {
    	System.exit(0);
    }
    
    public void goRest(int i)
    {
    	try {
    		if(label[i].getText()!=null) 
        	{
    			connection=DatabaseUtil.Connect();
    	    	sql="select * from food where foodName='"+label[i].getText().trim()+"'";
    	  	
    	    	try {
    	    		query=connection.prepareStatement(sql);
    	    		ResultSet result=query.executeQuery();
    	    		
    	    		if(result.next())
    	    		{
    	    			restaurantName=result.getString("restaurantName");
    	    			try {
		        			Stage stage1 = new Stage();
		        			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("Restaurant.fxml"));
		        			Scene scene = new Scene(pane1);
		        			stage1.setScene(scene);
		        			//stage1.initStyle(StageStyle.UNDECORATED);
		        			stage1.show();
		        			//stage=(Stage) orderPane.getScene().getWindow();
		        			//stage.hide();
		        		} catch(Exception e) {
		        			e.printStackTrace();
		        		}
    	    		}
    	    	} catch (Exception e) {
    	    		System.out.println(e.getMessage().toString());
    	    	}
        	}
    	}catch(Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    }
    
    
    public void setImageView() {
    	img[0]=img1;
    	img[1]=img2;
    	img[2]=img3;
    	img[3]=img4;
    	img[4]=img5;
    	img[5]=img6;
    	img[6]=img7;
    	img[7]=img8;
    }
    
    public void setLabel() {
    	label[0]=lbl1;
    	label[1]=lbl2;
    	label[2]=lbl3;
    	label[3]=lbl4;
    	label[4]=lbl5;
    	label[5]=lbl6;
    	label[6]=lbl7;
    	label[7]=lbl8;
    }
    		
    @FXML
    void txt_search_KeyPressed(KeyEvent event) {
    	connection=DatabaseUtil.Connect();
    	sql="select * from food where foodName like '%"+txt_search.getText().trim()+"%'";
  	
    	try {
    		query=connection.prepareStatement(sql);
    		ResultSet result=query.executeQuery();
    		
    		if(result.next()) {
    			for(ImageView i:img) {i.setImage(null);}
    			for(Label i:label) {i.setText(null);}
    		}
    		while(result.next() && i!=8)
    		{
    			img[i].setImage(new Image("file:"+result.getString("imageURL")));
    			label[i].setText(result.getString("foodName"));
    			i++;
    		}
    		i=0;
    	} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    }
    
    public void Fill()
    {
    	connection=DatabaseUtil.Connect();
    	sql="select * from food";
    	//ObservableList<Food> FoodList=FXCollections.observableArrayList();
    	try {
    		query=connection.prepareStatement(sql);
    		ResultSet result=query.executeQuery();
    			
    		while(result.next() && i!=8)
    		{
    			//FoodList.add(new Food(result.getInt("foodID"),result.getString("foodName"),result.getString("restaurantName"),result.getString("imageURL"),result.getDouble("price")));	
    			img[i].setImage(new Image("file:"+result.getString("imageURL")));
    			label[i].setText(result.getString("foodName"));
    			i++;
    		}
    	} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    	i=0;
    }

    @FXML
    void initialize() {
    	
    	setImageView();
    	setLabel();
    	Fill();
    	if(txt_search.getText()==null)
    		Fill();
    }

}
