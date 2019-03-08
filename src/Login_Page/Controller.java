package Login_Page;

import Db_Connection.Db_Connection;
import Project_Classes.Load_Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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



    public void login(ActionEvent event) throws Exception{
        int result = 0;


        String sql = "SELECT username,password FROM users";
        Db_Connection.connectiondb();
        ResultSet rs = Db_Connection.executeQuery(sql);

        while(rs.next()){
            if (rs.getString("username").equals(mail.getText())){
                if (rs.getString("password").equals(passwordField.getText())){
                    System.out.println("Girişiniz başarılı hoş geldiniz");
                    status.setTextFill(Color.GREEN);
                    status.setText("Login Succesfull");
                    result =1;
                    Load_Pages load = new Load_Pages();
                    load.loadMain();
                    break;

                    //Todo:User nesnesi oluşturulacak ve bilgiler buna atanacak..

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

        rs.close();
        Db_Connection.CloseConnection();
        System.out.println("kapandı");

    }

}
