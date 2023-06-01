package application;

import java.sql.Date;
import java.time.LocalDate;

public class Process {
	private int processID,piece,price;
	private String userName,restaurantName,buyerName,foodName;
	private Date date;
	
	public Process(int processID,int piece,int price,String userName,String restaurantName,String buyerName,String foodName,Date date)
	{
		this.processID=processID;
		this.piece=piece;
		this.price=price;
		this.userName=userName;
		this.restaurantName=restaurantName;
		this.buyerName=buyerName;
		this.foodName=foodName;
		this.date=date;
	}
	
	public Process(String userName)
	{
		this.userName=userName;
	}
	
	public int getProcessID() {
		return processID;
	}
	public void setProcessID(int processID) {
		this.processID = processID;
	}
	public int getPiece() {
		return piece;
	}
	public void setPiece(int piece) {
		this.piece = piece;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
