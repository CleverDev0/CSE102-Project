package SignUpMember;

import Db_Connection.Db_Connection;
import Project_Classes.CustomExceptions;
import Project_Classes.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.*;

public class Controller {
    public TextField floorNumber;
    @FXML
    private TextField email;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label signUpStatus;

    @FXML
    private TextField managerCode;

    @FXML
    private TextField surname;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField tcNumber;

    @FXML
    private TextField apartmentNumber;

    boolean isSıgnUp = false;

    public void createUsers(ActionEvent event) {
        if (!isSıgnUp) {
            try {
                Db_Connection.connectiondb();
                System.out.println(managerCode.getText());
                ResultSet rs = Db_Connection.executeQuery("SELECT serialNumber FROM users WHERE serialNumber='" + managerCode.getText() + "'");
                if (rs.next()) {


                    String mail = email.getText();
                    String name = nameField.getText();
                    String surnamee = surname.getText();

                    ResultSet rs2 = Db_Connection.executeQuery("SELECT username FROM users WHERE username='" + email.getText() + "'");
                    if (rs2.next()) {
                        throw new CustomExceptions("There is a user same username");
                    } else {
                        String pass = passwordField.getText();
                        String phone = phoneNumber.getText();
                        String tc = tcNumber.getText();
                        String apartNumber = apartmentNumber.getText();

                        //Database Query
                        String query = ("INSERT INTO users (Username,Password,Name,Surname,PhoneNumber,TCNumber,SerialNumber,ApartmentNumber,IsAdmin,floorNumber) Values" +
                                " ('" + mail + "','" + pass + "','" + name + "','" + surnamee + "','" + phone + "','" + tc + "','" + managerCode.getText() + "','" + apartNumber + "',0,'"+floorNumber.getText()+"')");

                        //Database Connection

                        Db_Connection.ExecuteSql(query);
                        Db_Connection.CloseConnection();

                        signUpStatus.setTextFill(Color.GREEN);
                        signUpStatus.setText("Sign Up Succesful.");
                        isSıgnUp = true;
                    }
                } else {
                    throw new CustomExceptions("Invalid Manager Code");
                }

            } catch (CustomExceptions exceptions) {
                signUpStatus.setTextFill(Color.RED);
                signUpStatus.setText(exceptions.getMessage());
            } catch (Exception e) {
                signUpStatus.setTextFill(Color.RED);
                signUpStatus.setText("Sign Up Unsuccesful");
            }
        }
    }
}

