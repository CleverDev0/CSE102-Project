package SignUpManager;

import Project_Classes.Load_Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import Db_Connection.Db_Connection;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;
import java.util.UUID;

public class Controller {
    @FXML
    private TextField email;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label signUpStatus;

    @FXML
    private Label codeForMember;

    @FXML
    private TextField surname;

    public boolean memberCode = false;
    boolean isSıgnUp = false;


    //Todo: Eğer aynı isim-soyisim veya email giriş vasa kontrol ettir ve alert bastır. Sqlden veri çekilip kontrol edilecek!
    public void createUsers(ActionEvent event) throws Exception {
        if (!isSıgnUp){
            try{
                String mail = email.getText();
                String name = nameField.getText();
                String surnamee = surname.getText();
                String pass = passwordField.getText();

                //Check Member Code Given
                if (!memberCode){
                    String uuid = UUID.randomUUID().toString();
                    codeForMember.setText(uuid.substring(0,6));
                    memberCode = true;
                }
                String serial = codeForMember.getText();

                //Database Query
                String query = ("INSERT INTO users (Username,Password,Name,Surname,PhoneNumber,TCNumber,SerialNumber,ApartmentNumber,IsAdmin) Values"+
                        " ('"+mail+"','"+pass+"','"+name+"','"+surnamee+"','','','"+serial+"','',1)");

                //Database Connection
                Db_Connection.connectiondb();
                Db_Connection.ExecuteSql(query);
                Db_Connection.CloseConnection();

                signUpStatus.setTextFill(Color.GREEN);
                signUpStatus.setText("Sign Up Succesful.");
                isSıgnUp = true;


                //Yeni sayfa açıldığında eski sayfanın kalmaması için
                //((Node)(event.getSource())).getScene().getWindow().hide();


            }
            catch (Exception e){
                signUpStatus.setTextFill(Color.RED);
                signUpStatus.setText("Sign Up Unsuccesful");
            }
        }

    }

}
