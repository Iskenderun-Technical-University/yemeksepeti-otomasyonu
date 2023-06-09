package application;

import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;

public class CartClass {
	private String foodName;
	//private Integer[] piece;
	private Double price,total;
	private Button button;
	
	CartClass() {}
	CartClass(String foodName,Double price,Double total,Button button) 
	{
		this.foodName=foodName;
		
		//this.piece=new ComboBox<Integer>();
		
		this.price=price;
		this.total=total;
		
		this.button=button;
		this.button.setText("Çıkar");
	}
	
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	/*public ComboBox<Integer> getPiece() {
		return piece;
	}
	public void setPiece(ComboBox<Integer> piece) {
		this.piece = piece;
	}*/
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		this.button = button;
	}
	
}
