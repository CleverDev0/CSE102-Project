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

public class Controller {
    @FXML
    private TextField email;

    @FXML
    private TextField nameSurname;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label signUpStatus;

    @FXML
    private Label codeForMember;

    //Todo: Member kodunu bir kere vermesini sağla!
    public void createUsers(ActionEvent event) throws Exception {
        try{
            String mail = email.getText();
            String name = nameSurname.getText();
            String pass = passwordField.getText();

            double random = (Math.random() * 10000);
            int rand = (int) random;
            String pin = Integer.toString(rand);
            codeForMember.setText(pin);
            String serial = codeForMember.getText();

            String query = ("INSERT INTO users (Username,Password,Name,Surname,PhoneNumber,TCNumber,SerialNumber,ApartmentNumber,IsAdmin) Values"+
                    " ('"+mail+"','"+name+"','"+pass+"','','','','"+serial+"','',1)");

            //Çalışmayan yer
            /*PreparedStatement pr = Db_Connection.getConnection().prepareStatement(s);

            pr.setString(1, mail);
            pr.setString(2, name);
            pr.setString(3, pass);
            preparedStatement.setString(4,serial);*/

            //Database
            Db_Connection.connectiondb();
            Db_Connection.ExecuteSql(query);
            Db_Connection.CloseConnection();


            signUpStatus.setTextFill(Color.GREEN);
            signUpStatus.setText("Sign Up Succesful");


            //Yeni sayfa açıldığında eski sayfanın kalmaması için
            //((Node)(event.getSource())).getScene().getWindow().hide();
            //Todo: Butona tıkladıktan iki sn sonra giriş sayfasına yönlendirecek
        }
        catch (Exception e){
            signUpStatus.setTextFill(Color.RED);
            signUpStatus.setText("Sign Up Unsuccesful");
        }

    }

}
