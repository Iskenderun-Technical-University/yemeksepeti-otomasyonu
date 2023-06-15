package application;


public class Food {
	private int foodID;
	private String foodName,restaurantName,imageURL;
	private double price;
	
	Food(){}
	Food(int foodID,String foodName,String restaurantName,String imageURL,double price)
	{
		this.foodID=foodID;
		this.foodName=foodName;
		this.restaurantName=restaurantName;
		this.imageURL=imageURL;
		this.price=price;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public int getFoodID() {
		return foodID;
	}
	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
