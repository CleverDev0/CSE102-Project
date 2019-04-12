package SignUpMember;

import Db_Connection.Db_Connection;
import Project_Classes.Users;
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

    @FXML
            private TextField TCNumber;
    @FXML
            private TextField PhoneNumber;
    @FXML
            private TextField ApartmentNumber;

    boolean isSıgnUp = false;

    //Todo: Sistemde geçerli manager Code'u varsa kayıt olabilir. Yoksa geçersiz manager code uyarısı göster

    public void createUsers(ActionEvent event){
        if(!isSıgnUp) {
            try {
                Users user = new Users();
                user.Email = email.getText();
                user.Name = memberName.getText();
                user.Surname = memberSurname.getText();
                user.Password = passwordField.getText();
                user.ManagerCode = managerCode.getText();
                user.TCNumber = TCNumber.getText();
                user.ApartmentNumber = ApartmentNumber.getText();
                user.PhoneNumber = PhoneNumber.getText();
                //Get Values

                //Database Query
                String query = ("INSERT INTO users (Username,Password,Name,Surname,PhoneNumber,TCNumber,SerialNumber,ApartmentNumber,IsAdmin) Values" +
                        " ('" + user.Email + "','" + user.Password + "','" + user.Name + "','" + user.Surname + "'  "+ user.PhoneNumber +",' " + user.TCNumber + "','','" + user.ManagerCode + "',' " + user.ApartmentNumber + "',0)");

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

