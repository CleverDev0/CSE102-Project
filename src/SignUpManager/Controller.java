package SignUpManager;

import Project_Classes.CustomExceptions;
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

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField tcNumber;

    @FXML
    private TextField apartmentNumber;


    public boolean memberCode = false;
    boolean isSıgnUp = false;

    public void createUsers(ActionEvent event) throws Exception {
        if (!isSıgnUp) {
            try {
                Db_Connection.connectiondb();

                String mail = email.getText();
                String name = nameField.getText();
                String surnamee = surname.getText();

                //Database
                String sql = "SELECT username FROM users WHERE username='" + email.getText() + "'";
                ResultSet rs = Db_Connection.executeQuery(sql);
                if (rs.next()) {
                    System.out.println("User with same email exists.");
                    throw new CustomExceptions();
                } else {

                    String pass = passwordField.getText();
                    String phone = phoneNumber.getText();
                    String tc = tcNumber.getText();
                    String apartNumber = apartmentNumber.getText();

                    //Check Member Code Given
                    if (!memberCode) {
                        String uuid = UUID.randomUUID().toString();
                        codeForMember.setText(uuid.substring(0, 6));
                        memberCode = true;
                    }

                    String serial = codeForMember.getText();
                    System.out.println(tc);

                    //Database Query
                    String query = ("INSERT INTO users (Username,Password,Name,Surname,PhoneNumber,TCNumber,SerialNumber,ApartmentNumber,IsAdmin) Values" +
                            " ('" + mail + "','" + pass + "','" + name + "','" + surnamee + "','" + phone + "','" + tc + "','" + serial + "','" + apartNumber + "',1)");

                    //Database Connection
                    Db_Connection.ExecuteSql(query);
                    Db_Connection.CloseConnection();

                    signUpStatus.setTextFill(Color.GREEN);
                    signUpStatus.setText("Sign Up Succesful.");
                    isSıgnUp = true;
                }

            } catch (CustomExceptions e) {
                signUpStatus.setTextFill(Color.RED);
                signUpStatus.setText("There is a user with same username");
            } catch (Exception e) {
                signUpStatus.setTextFill(Color.RED);
                signUpStatus.setText("Sign Up Unsuccesful");
            }
        } else {
            signUpStatus.setTextFill(Color.ORANGE);
            signUpStatus.setText("You already signed up.");
        }

    }

}
