package application;


public class Food {
	private int foodID;
	private String foodName,restaurantName,imageURL,extra;
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
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
	Food(int foodID,String foodName,String restaurantName,double price,String extra,String imageURL)
	{
		this.foodID=foodID;
		this.foodName=foodName;
		this.restaurantName=restaurantName;
		this.imageURL=imageURL;
		this.price=price;
		this.extra=extra;
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
