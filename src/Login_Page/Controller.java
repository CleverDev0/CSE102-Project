package Login_Page;

import Db_Connection.Db_Connection;
import Project_Classes.Load_Pages;
import Project_Classes.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Controller {

    @FXML
    private TextField mail;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label status;

    public static Users kullanici;

//TODO: ÖNCE KULLANIDAN BİGİLERİ AL SONRA SORGUYA ATIP KONTROL ET NULL DÖNERSE BÖYLE BİR KULLANICI YOK DE

    public void login(ActionEvent event) throws Exception{
        int result = 0;
        Users user = new Users();

        //Database
        String sql = "SELECT username,password FROM users WHERE username='"+mail.getText()+"' and password='"+passwordField.getText()+"'";
        Db_Connection.connectiondb();
        ResultSet rs = Db_Connection.executeQuery(sql);

        //Checking Values
        while(rs.next()){

            String username= rs.getString("username");
            String password= rs.getString("password");
            if (rs.getString("username").equals(mail.getText())){

                if (rs.getString("password").equals(passwordField.getText())){
                    System.out.println("Girişiniz başarılı hoş geldiniz");
                    status.setTextFill(Color.GREEN);
                    status.setText("Login Succesfull");
                    user.setName(username);
                    user.setPassword(password);
                    result =1;
                    user.setUserId("2");
                    setKullanici(user);

                    Thread.sleep(2000);

                    Load_Pages load = new Load_Pages();
                    load.loadMain();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    break;



                }
                result = 2;
                status.setTextFill(Color.ORANGE);
                status.setText("Wrong Password");

            }



        }
        if (result == 0){
            status.setTextFill(Color.RED);
            status.setText("Kullanıcı adı veya şifre geçersiz");
        }

        //Database
        rs.close();
        System.out.println("ResultSet close");
        Db_Connection.CloseConnection();
        System.out.println("DB Connection close");

    }

    public void rememberPassword(ActionEvent event) throws Exception{
        Load_Pages load = new Load_Pages();
        load.loadPasswordRemember();
    }

    public static Users getKullanici() {
        return kullanici;
    }

    public void setKullanici(Users kullanici) {
        this.kullanici = kullanici;
    }
}
