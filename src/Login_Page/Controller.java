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



    public void login(ActionEvent event) throws Exception{
        int result = 0;
        Users user = new Users();
        //Database
        String sql = "SELECT username,password,UserId FROM users";
        Db_Connection.connectiondb();
        ResultSet rs = Db_Connection.executeQuery(sql);

        //Checking Values
        while(rs.next()){
            if (rs.getString("username").equals(mail.getText())){
                if (rs.getString("password").equals(passwordField.getText())){
                    System.out.println("Girişiniz başarılı hoş geldiniz");
                    String userData = "SELECT * FROM Users WHERE username = '"+mail.getText()+"'";
                    //Bilgiler null olarak gidiyor ona bi bakalım
                    ResultSet rs2 = Db_Connection.executeQuery(userData);
                    while (rs2.next()) {
                        user.setName(rs2.getString("username"));
                        user.setPassword(rs2.getString("password"));
                        setKullanici(user);
                    }
                    rs2.close();

                    status.setTextFill(Color.GREEN);
                    status.setText("Login Succesfull");

                    result =1;


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
