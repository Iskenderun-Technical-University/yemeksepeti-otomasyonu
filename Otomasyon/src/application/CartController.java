package application;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
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
    private ImageView btn_Exit;

    @FXML
    private TableView<CartClass> tableView_Cart;	
    
    static ObservableList<CartClass> CartList=FXCollections.observableArrayList();
    ObservableList<Integer> combo=FXCollections.observableArrayList();
    static IntegerProperty size = new SimpleIntegerProperty(0);
    double total=0;
    
    @FXML
    void btn_Exit_Clicked(MouseEvent event) {

    }

    @FXML
    void btn_Exit_Moved(MouseEvent event) {
    	btn_Exit.setCursor(Cursor.HAND);
    }

    public void Show(ObservableList<CartClass> list)
    {
    	if(list.size()==0) 
    	{
    		System.out.println("Girdi");
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
