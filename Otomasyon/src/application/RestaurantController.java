package application;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.YemekSepetiMySQL.Util.DatabaseUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RestaurantController {
	
	public int i=0;     
    
    ImageView[] img=new ImageView[4];
    ImageView[] cartImg=new ImageView[4];
    Label[] labelName=new Label[4];
    Label[] labelExplanation=new Label[4];
    Label[] labelPrice=new Label[4];
	
	Connection connection=null;
    PreparedStatement query=null;
    ResultSet result=null;
    String sql;    
    
    public static String foodName;
    public static Double price;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;
    
    @FXML
    private ImageView cartbtn1;

    @FXML
    private ImageView cartbtn2;

    @FXML
    private ImageView cartbtn3;

    @FXML
    private ImageView cartbtn4;

    @FXML
    private ImageView img_Restaurant;

    @FXML
    private Label lbl_Explanation1;

    @FXML
    private Label lbl_Explanation2;

    @FXML
    private Label lbl_Explanation3;

    @FXML
    private Label lbl_Explanation4;

    @FXML
    private Label lbl_Name1;

    @FXML
    private Label lbl_Name2;

    @FXML
    private Label lbl_Name3;

    @FXML
    private Label lbl_Name4;

    @FXML
    private Label lbl_Price1;

    @FXML
    private Label lbl_Price2;

    @FXML
    private Label lbl_Price3;

    @FXML
    private Label lbl_Price4;

    @FXML
    private Label lbl_Restaurant;
    
    @FXML
    void cartbtn1_Moved(MouseEvent event) {
    	cartbtn1.setCursor(Cursor.HAND);
    }
    
    @FXML
    void cartbtn2_Moved(MouseEvent event) {
    	cartbtn2.setCursor(Cursor.HAND);
    }
    
    @FXML
    void cartbtn3_Moved(MouseEvent event) {
    	cartbtn3.setCursor(Cursor.HAND);
    }
    
    @FXML
    void cartbtn4_Moved(MouseEvent event) {
    	cartbtn4.setCursor(Cursor.HAND);
    }
    
    public void Fill()
    {
    	connection=DatabaseUtil.Connect();
    	sql="select * from food inner join restaurant on food.restaurantName=restaurant.restaurantName where restaurant.restaurantName='"+OrderController.restaurantName+"'";

    	try {
    		query=connection.prepareStatement(sql);
    		ResultSet result=query.executeQuery();
    		
    		
    		while(result.next() && i!=4)
    		{
    			if(i==0) {
    				lbl_Restaurant.setText(result.getString("restaurantName"));
    				img_Restaurant.setImage(new Image("file:"+result.getString("restImageURL")));
    			}
    			img[i].setImage(new Image("file:"+result.getString("imageURL")));
    			cartImg[i].setImage(new Image("file:"+"image\\plus.png"));
    			labelName[i].setText(result.getString("foodName"));
    			labelExplanation[i].setText(result.getString("extra"));
    			labelPrice[i].setText(result.getString("price")+",00 TL");
    			i++;
    		}
    	} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    	i=0;
    }
    
    public void setImageView() {
    	img[0]=img1;
    	img[1]=img2;
    	img[2]=img3;
    	img[3]=img4;
    }
    
    public void setCartImageView() {
    	cartImg[0]=cartbtn1;
    	cartImg[1]=cartbtn2;
    	cartImg[2]=cartbtn3;
    	cartImg[3]=cartbtn4;
    }
    
    public void setLabelName() {
    	labelName[0]=lbl_Name1;
    	labelName[1]=lbl_Name2;
    	labelName[2]=lbl_Name3;
    	labelName[3]=lbl_Name4;
    }
    
    public void setLabelExplanation() {
    	labelExplanation[0]=lbl_Explanation1;
    	labelExplanation[1]=lbl_Explanation2;
    	labelExplanation[2]=lbl_Explanation3;
    	labelExplanation[3]=lbl_Explanation4;
    }
    
    public void setLabelPrice() {
    	labelPrice[0]=lbl_Price1;
    	labelPrice[1]=lbl_Price2;
    	labelPrice[2]=lbl_Price3;
    	labelPrice[3]=lbl_Price4;
    }
    
    @FXML
    void cartbtn1_Click(MouseEvent event) {
    	goCart(0);
    }

    @FXML
    void cartbtn2_Click(MouseEvent event) {
    	goCart(1);
    }

    @FXML
    void cartbtn3_Click(MouseEvent event) {
    	goCart(2);
    }

    @FXML
    void cartbtn4_Click(MouseEvent event) {
    	goCart(3);
    }
    
    public void goCart(int i)
    {
    		if(labelName[i].getText()!=null) 
        	{
    			connection=DatabaseUtil.Connect();
    	    	sql="select * from food where foodName='"+labelName[i].getText().trim()+"'";
    	  	
    	    	try {
    	    		query=connection.prepareStatement(sql);
    	    		ResultSet result=query.executeQuery();
    	    		
    	    		//if(CartController.i==6) {
    	    		//	System.out.println("Sepet Dolu.!");
    	    		//}
    	    		
    	    		if(result.next())
    	    		{
    	    			foodName=result.getString("foodName");
    	    			price=result.getDouble("price");
    	    			try {
		        			Stage stage1 = new Stage();
		        			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("Cart.fxml"));
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
    }

    @FXML
    void initialize() {
    	setImageView();
    	setCartImageView();
    	setLabelName();
    	setLabelExplanation();
    	setLabelPrice();
    	Fill();
    }

}
