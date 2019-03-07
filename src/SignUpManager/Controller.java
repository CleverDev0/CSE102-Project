package SignUpManager;

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


    public void initialize() throws Exception{
    }


    public void createUsers(ActionEvent event) throws Exception{


        ResultSet rs=null;

        String s = "INSERT INTO users (Username,Password,Name,Surname,PhoneNumber,TCNumber,SerialNumber,ApartmentNumber,IsAdmin) " +
                "Values(?,?,?,'Admin','5555555555','11111111111','abc123','14',1)";


        String mail = email.getText();
        String name = nameSurname.getText();
        String pass = passwordField.getText();

        double random = (Math.random() * 10000);
        int rand = (int) random;
        String pin = Integer.toString(rand);
        codeForMember.setText(pin);

        //String serial = codeForMember.getText();


        //preparedStatement.setString(4,serial);



         Db_Connection.connectiondb();
         PreparedStatement pr = Db_Connection.getConnection().prepareStatement(s);
        pr.setString(1,mail);
        pr.setString(2,name);
        pr.setString(3,pass);
        Db_Connection.ExecuteSql(s);
        Db_Connection.CloseConnection();











            signUpStatus.setTextFill(Color.GREEN);
            signUpStatus.setText("Sign Up Succesfull");

            //Todo: execute problemi halledilecek..

            // Veriler ayıklanır.
        /*String sql = "SELECT Username, Surname, PhoneNumber, TCNumber FROM users";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        // Veriler ayıklanır.
        while(rs.next()){
            // Sutunlara göre degerlerı alıyoruz

            String adi = rs.getString("Username");
            String soyadi = rs.getString("Surname");
            String tc = rs.getString("PhoneNumber");
            String phıne = rs.getString("TCNumber");

// Verileri görüntüle - yaz
            System.out.print(", Adı: " + adi);
            System.out.println(", Soyadı: " + soyadi);
            System.out.println(tc);
            System.out.println(phıne);

        }*/
        }

    }
