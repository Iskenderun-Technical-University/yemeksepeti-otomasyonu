package application;


import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Kayitlar {
	private int id;
	private String kul_adi;
	private double ortalama;
	private CheckBox onay;
	private TextField metin;
	private Button buton;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKul_adi() {
		return kul_adi;
	}

	public void setKul_adi(String kul_adi) {
		this.kul_adi = kul_adi;
	}

	public double getOrtalama() {
		return ortalama;
	}

	public void setOrtalama(double ortalama) {
		this.ortalama = ortalama;
	}

	public CheckBox getOnay() {
		return onay;
	}

	public void setOnay(CheckBox onay) {
		this.onay = onay;
	}

	public TextField getMetin() {
		return metin;
	}

	public void setMetin(TextField metin) {
		this.metin = metin;
	}

	public Button getButon() {
		return buton;
	}

	public void setButon(Button buton) {
		this.buton = buton;
	}

	Kayitlar(){
		
	}
	
	Kayitlar(int id,String kul_adi,double ortalama, String metin,Button buton){
		this.id=id;
		this.kul_adi=kul_adi;
		this.ortalama=ortalama;
		this.onay=new CheckBox();
		
		this.metin=new TextField();
		this.metin.setText(metin);
		
		this.buton=buton;
		this.buton.setText("Yolla");
	}
}
