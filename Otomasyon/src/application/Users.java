package application;
import javafx.scene.control.Button;

public class Users {
	private int id;
	private int title;
	private String userName;
	private String password;
	private String adress;
	
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	private Button button;
	
	public int getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title = title;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	Users(){
		
	}
	
	Users(int id,int title,String userName,String password,Button button,String adress){
		this.id=id;
		this.title=title;
		this.userName=userName;
		this.password=password;
		this.adress=adress;
		
		this.button=button;
		this.button.setText("İşlemler");
	}
}
