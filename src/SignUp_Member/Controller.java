package SignUp_Member;

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
    private TextField nameSurname;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField code;

    @FXML
    private Label signUpStatus;





    private static String username = "root";
    private static String password = "12345678";

    private static String connectionString = "jdbc:mysql://localhost:3306/cse_102_project_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static Connection connection;
    private static Statement command;
    private static ResultSet data;

    public void createUsers(ActionEvent event){


        try {
            PreparedStatement preparedStatement = null;
            connection = DriverManager.getConnection(connectionString,username,password);
            command = connection.createStatement();
            //command.execute("INSERT INTO users (Username,Password,Name,Surname,PhoneNumber,TCNumber,SerialNumber,ApartmentNumber,IsAdmin) Values"+
            //   " ('Admin','123','Admin','Admin','5555555555','11111111111','abc123','14',1)");

            String mail = email.getText();
            String name = nameSurname.getText();
            String password = passwordField.getText();




            double random = (Math.random() * 10000);
            int rand = (int) random;
            String pin = Integer.toString(rand);
            //codeForMember.setText(pin);
            //String serial = codeForMember.getText();
            preparedStatement.setString(1,mail);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,password);

            command.execute("INSERT INTO users (Username,Password,Name,Surname,PhoneNumber,TCNumber,SerialNumber,ApartmentNumber,IsAdmin)" +
                    " Values(?,?,?,'','','','','',1)");



            signUpStatus.setTextFill(Color.GREEN);
            signUpStatus.setText("Sign Up Succesfull");

            //Todo: execute problemi halledilecek..

            String sql = "SELECT Username, Surname, PhoneNumber, TCNumber FROM users";
            ResultSet rs = command.executeQuery(sql);
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

            }
        }
        catch (SQLException e) {

            e.printStackTrace();
        }
        finally {
            System.out.println("Connection Established! QUERY WORKED RIGHT! INSERT ops. Successuful!");
        }
    }
}

