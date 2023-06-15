package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_goster;

    @FXML
    private Button btn_guncelle;

    @FXML
    private Button btn_secilenler;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_yorum_goster;

    @FXML
    private TableColumn<Users, Integer> id;

    @FXML
    private TableColumn<Users, String> islem;

    @FXML
    private TableView<Users> kayitlar_table;

    @FXML
    private TableColumn<Users, String> kul_adi;

    @FXML
    private TableColumn<Users, Double> ortalama;

    @FXML
    private TableColumn<Users, String> secim;

    @FXML
    private TextField txt_kul;

    @FXML
    private TextField txt_sifre;

    @FXML
    private TableColumn<Users, String> yorum;
    
    ObservableList<Users> veriler;
    Button[] butonlar=new Button[10];
    int id_No=0;

    @FXML
    void btn_ekle_Click(ActionEvent event) {
    	/*veriler=FXCollections.observableArrayList();
    	veriler.add(new Kayitlar(id_No,txt_kul.getText(),Double.parseDouble(txt_sifre.getText()),"Varsayilan",butonlar[id_No]));
    	kayitlar_table.getItems().addAll(veriler);*/
    }

    @FXML
    void btn_goster_Click(ActionEvent event) {
    	/*Kayitlar kayit=new Kayitlar();
    	if(kayitlar_table.getSelectionModel().getSelectedIndex()!=-1) {
    		kayit=(Kayitlar) kayitlar_table.getItems().get(kayitlar_table.getSelectionModel().getSelectedIndex());
    		System.out.println("Kullanıcı Adı: "+kayit.getKul_adi()+"\n"+ //
    				"ID: "+kayit.getId()+"\n"+"Ortalama: "+kayit.getOrtalama());
    	}
    	else {
    		System.out.println("Herhangi bir kayıt seçili değil..");
    	}*/
    }

    @FXML
    void btn_guncelle_Click(ActionEvent event) {
    	/*Kayitlar kayit=new Kayitlar();
    	if(kayitlar_table.getSelectionModel().getSelectedIndex()!=-1) {
    		kayit=(Kayitlar) kayitlar_table.getItems().get(kayitlar_table.getSelectionModel().getSelectedIndex());
    		int idm=kayit.getId();
    		
    		Kayitlar kk=new Kayitlar(idm,txt_kul.getText(),Double.parseDouble(txt_sifre.getText()),"Güncellendi",butonlar[idm]);
    		int sira=kayitlar_table.getSelectionModel().getSelectedIndex();
    		kayitlar_table.getItems().set(sira, kk);
    	}
    	else {
    		System.out.println("Herhangi bir kayıt seçili değil..");
    	}*/
    }

    @FXML
    void btn_secilenler_Click(ActionEvent event) {
    	//CheckBox seçilmiş olansatırların değerlerini alma
    	/*for(Kayitlar islem: veriler) {
    		if(islem.getOnay().isSelected()) {
    			System.out.println("Seçilen: "+islem.getKul_adi());
    		}
    	}*/
    }

    @FXML
    void btn_sil_Click(ActionEvent event) {
    	/*ObservableList<Kayitlar> secilenKayitlar, tumKayitlar;
    	tumKayitlar=kayitlar_table.getItems();
    	secilenKayitlar=kayitlar_table.getSelectionModel().getSelectedItems();
    	
    	secilenKayitlar.forEach(tumKayitlar::remove);*/
    }

    @FXML
    void btn_yorum_goster_Click(ActionEvent event) {
    	//Yorum girilmiş kayıtları belirleme
    	/*for(Kayitlar islem: veriler) {
    		if(!islem.getMetin().getText().isEmpty()) {
    			System.out.println("Seçilen: "+islem.getKul_adi());
    		}
    	}*/
    }

    @FXML
    void kayitlar_table_Click(MouseEvent event) {
    	/*Kayitlar kayit=new Kayitlar();
    	kayit=(Kayitlar) kayitlar_table.getItems().get(kayitlar_table.getSelectionModel().getSelectedIndex());
    	txt_kul.setText(kayit.getKul_adi());
    	txt_sifre.setText(String.valueOf(kayit.getOrtalama()));*/
    }

    @FXML
    void initialize() {
     /* for(int i=0; i<butonlar.length; i++) {
    	  butonlar[i]=new Button();
    	  butonlar[i].setId("btn"+i);
    	  butonlar[i].setOnAction(this::ButonOlayi);
      }
      
      veriler=FXCollections.observableArrayList();
      veriler.add(new Kayitlar(1,"admin",0.5,"Mesaj 1",butonlar[id_No]));
      id_No++;
      veriler.add(new Kayitlar(2,"root",0.63,"Mesaj 2",butonlar[id_No]));
      id_No++;
      veriler.add(new Kayitlar(1,"user",0.71,"Mesaj 3",butonlar[id_No]));
      id_No++;
      
      
      id.setCellValueFactory(new PropertyValueFactory<>("id"));
      kul_adi.setCellValueFactory(new PropertyValueFactory<>("kul_adi"));
      ortalama.setCellValueFactory(new PropertyValueFactory<>("ortalama"));
      secim.setCellValueFactory(new PropertyValueFactory<>("onay"));
      islem.setCellValueFactory(new PropertyValueFactory<>("buton"));
      yorum.setCellValueFactory(new PropertyValueFactory<>("metin"));
      
      kayitlar_table.setItems(veriler);*/
    }
    
    //Butona olay atama
    
    @FXML
    void ButonOlayi(ActionEvent event) {
    	/*for(int i=0; i<butonlar.length; i++) {
    		if(event.getSource()==butonlar[i]) {
    			System.out.println(i+". butona tıklandı.");
    		}
        }*/
    }

}
