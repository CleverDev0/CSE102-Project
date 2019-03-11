package SignUpMember;

import Db_Connection.Db_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.*;

public class Controller {
    @FXML
    private TextField email;

    @FXML
    private TextField memberName;

    @FXML
    private TextField memberSurname;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField managerCode;

    @FXML
    private Label signUpStatus;

    boolean isSıgnUp = false;

    public void createUsers(ActionEvent event){
        if(!isSıgnUp) {
            try {
                //Get Values
                String mail = email.getText();
                String name = memberName.getText();
                String surnamee = memberSurname.getText();
                String pass = passwordField.getText();
                String serial = managerCode.getText();

                //Database Query
                String query = ("INSERT INTO users (Username,Password,Name,Surname,PhoneNumber,TCNumber,SerialNumber,ApartmentNumber,IsAdmin) Values" +
                        " ('" + mail + "','" + pass + "','" + name + "','" + surnamee + "','','','" + serial + "','',0)");

                //Database Connection
                Db_Connection.connectiondb();
                Db_Connection.ExecuteSql(query);
                Db_Connection.CloseConnection();

                signUpStatus.setTextFill(Color.GREEN);
                signUpStatus.setText("Sign Up Succesful.");
                isSıgnUp = true;


                //Yeni sayfa açıldığında eski sayfanın kalmaması için
                //((Node)(event.getSource())).getScene().getWindow().hide();


            } catch (Exception e) {
                signUpStatus.setTextFill(Color.RED);
                signUpStatus.setText("Sign Up Unsuccesful");
            }
        }
    }
}

