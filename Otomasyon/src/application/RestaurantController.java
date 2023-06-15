package application;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

import com.YemekSepetiMySQL.Util.DatabaseUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
    
    Button[] buttons=new Button[10];
    int buttonNo=0;
    
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
    	Add(0);
    }

    @FXML
    void cartbtn2_Click(MouseEvent event) {
    	Add(1);
    }

    @FXML
    void cartbtn3_Click(MouseEvent event) {
    	Add(2);
    }

    @FXML
    void cartbtn4_Click(MouseEvent event) {
    	Add(3);
    }
    
    public void Add(int i)
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
    	    			if(CartController.CartList.size()==0 || CartController.CartList.get(0).getRestaurantName().equals(result.getString("restaurantName")))
    	    			{
    	    				Alert alert1=new Alert(AlertType.INFORMATION);
    	    				alert1.setTitle("Sepete Ekleme");
    	    				alert1.setHeaderText("Ürünü sepete eklensin mi?");
    	    				ButtonType btn1=new ButtonType("Tamam",ButtonData.OK_DONE);
    	    				ButtonType btn2=new ButtonType("İptal",ButtonData.CANCEL_CLOSE);
    	    				alert1.getButtonTypes().setAll(btn1,btn2);
    	    				Optional<ButtonType> isOK=alert1.showAndWait();
    	    				if(isOK.get()==alert1.getButtonTypes().get(0)) {
    	    					OrderController.Transfer.add(new CartClass(result.getString("restaurantName"),result.getString("foodName"),result.getDouble("price")));
    	    					CartController.CartList.add(new CartClass(result.getString("restaurantName"),result.getString("foodName"),result.getDouble("price"),result.getDouble("price"),buttons[buttonNo],1));
    	    					buttonNo++;
    	    					CartController.size.set(CartController.size.get()+1);;
    	    				}}	
    	    			else
    	    			{	    					
    	    				Alert alert2=new Alert(AlertType.ERROR);
    	    				alert2.setTitle("Sepete Eklenemez");
    	    				alert2.setHeaderText("Sepette başka restoranta ait ürün var.!");
    	    				alert2.showAndWait();
    	    			}
    	    		}
    	    	} catch (Exception e) {
    	    		System.out.println(e.getMessage().toString());
    	    		
    	    		
    	    	}
        	}
    }
    
    public void buttonRef() 
    {
    	for(int i=0; i<buttons.length; i++) {
			buttons[i]=new Button();
			buttons[i].setId("btn"+i);
			buttons[i].setOnAction(this::Button);
    		}
    }

    @FXML
    void initialize() {
    	
    	buttonRef();
    	setImageView();
    	setCartImageView();
    	setLabelName();
    	setLabelExplanation();
    	setLabelPrice();
    	Fill();
    }
    
    @FXML
    void Button(ActionEvent event) {
    	for(int i=0; i<buttons.length; i++) {
    		if(event.getSource()==buttons[i]) {
    			OrderController.Transfer.remove(i);
    			CartController.CartList.clear();
                for (int j = 0; j < OrderController.Transfer.size(); j++)
              	  CartController.CartList.add(new CartClass(OrderController.Transfer.get(j).getRestaurantName(),OrderController.Transfer.get(j).getFoodName(),OrderController.Transfer.get(j).getPrice(),OrderController.Transfer.get(j).getPrice(),buttons[j],1));
                CartController.size.set(CartController.size.get()-1);
    		}
        }
    }

    
}
