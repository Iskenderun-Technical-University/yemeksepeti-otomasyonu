package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.print.attribute.standard.DateTimeAtCompleted;

import com.YemekSepetiMySQL.Util.DatabaseUtil;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import java.time.LocalDateTime;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.*;

public class CartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<CartClass, Button> col_button;

    @FXML
    private TableColumn<CartClass, String> col_foodName;

    @FXML
    private TableColumn<CartClass, Integer> col_piece;

    @FXML
    private TableColumn<CartClass, Double> col_price;

    @FXML
    private TableColumn<CartClass, Double> col_total;

    @FXML
    private Label lbl_total;
    
    @FXML
    private Button btn_Order;

    @FXML
    private TableView<CartClass> tableView_Cart;	
    
    static ObservableList<CartClass> CartList=FXCollections.observableArrayList();
    ObservableList<Integer> combo=FXCollections.observableArrayList();
    static IntegerProperty size = new SimpleIntegerProperty(0);
    double total=0;
    
    Connection connection=null;
    PreparedStatement query=null;
    ResultSet result=null;
    String sql;
    
    @FXML
    void btn_Order_Moved(MouseEvent event) {
    	btn_Order.setCursor(Cursor.HAND);
    }
    
    @FXML
    void btn_Order_Click(ActionEvent event) {
    	Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("Sipariş");
		alert.setHeaderText("Sipariş verilsin mi?");
		ButtonType btn1=new ButtonType("Tamam",ButtonData.OK_DONE);
		ButtonType btn2=new ButtonType("İptal",ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(btn1,btn2);
		Optional<ButtonType> isOK=alert.showAndWait();
		if(isOK.get()==alert.getButtonTypes().get(0)) {
			for(int i=0; i<CartList.size(); i++)
				Order(i);
			CartList.clear();
			OrderController.Transfer.clear();
			lbl_total.setText(null);
		}
    }
    
    public void Order(int i)
    {
    	sql="insert into process(userName, restaurantName, foodName, piece, price, processDate) values(?,?,?,?,?,?)";
    	try {
    		query=connection.prepareStatement(sql);
    		query.setString(1, LoginController.userSession);
    		query.setString(2, CartList.get(i).getRestaurantName());
    		query.setString(3, CartList.get(i).getFoodName());
    		query.setInt(4, tableView_Cart.getItems().get(i).getPiece());
    		query.setDouble(5, CartList.get(i).getPrice());
    		query.setDate(6,Date.valueOf(LocalDate.now()));
    		query.executeUpdate();
    		
    		} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    }

    public void Show(ObservableList<CartClass> list)
    {
    	if(list.size()==0) 
    	{
    		
    	}
    	else
    	{
    	combo.add(1);
    	combo.add(2);
    	combo.add(3);
    	col_foodName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
    	col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
    	col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
    	col_button.setCellValueFactory(new PropertyValueFactory<>("button"));
    	col_piece.setCellValueFactory(new PropertyValueFactory<>("piece"));
    	col_piece.setCellFactory(ComboBoxTableCell.forTableColumn(combo));
    	col_piece.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CartClass,Integer>>(){
    		@Override
    		public void handle(TableColumn.CellEditEvent<CartClass, Integer> event) {
    			int indis=tableView_Cart.getSelectionModel().getSelectedIndex();
    			list.get(indis).setPiece(event.getNewValue());
    			list.get(indis).setTotal(list.get(indis).getPrice()*list.get(indis).getPiece()); 
    			total=0;
    			for(int i=0; i<size.get(); i++)
        			total+=col_total.getCellData(i).doubleValue();
    			lbl_total.setText(String.valueOf(total+" TL"));
    			tableView_Cart.refresh();
    		}
    	});
    	tableView_Cart.setEditable(true);
    	tableView_Cart.setItems(list);
    	}
    }
    
    public void Total()
    {
    	total=0;
    		for(int i=0; i<size.get(); i++)
    			total+=col_total.getCellData(i).doubleValue();
    	lbl_total.setText(String.valueOf(total+" TL"));
    }
    

    @FXML
    void initialize() 
    {
    	connection=DatabaseUtil.Connect();
    	Show(CartList);
    	
    	if(!col_foodName.getCellData(0).isEmpty())
    		for(int i=0; i<size.get(); i++)
    			total+=col_total.getCellData(i).doubleValue();
    	lbl_total.setText(String.valueOf(total+" TL")); 
    	
    	
        size.addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                Total();
            } else if (newValue.intValue() < oldValue.intValue()) {
                Total();
            }
        });
    	
    }
    		
      
}
