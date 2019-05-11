package Login_Page;

import Db_Connection.Db_Connection;
import Project_Classes.Load_Pages;
import Project_Classes.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
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

    public void login(ActionEvent event) throws Exception {
        int result = 0;
        Users user = new Users();

        //Database
        String sql = "SELECT * FROM users WHERE username='" + mail.getText() + "' and password='" + passwordField.getText() + "'";

        Db_Connection.connectiondb();
        ResultSet rs = Db_Connection.executeQuery(sql);

        //Checking Values
        while (rs.next()) {

            String username = rs.getString("username");
            String password = rs.getString("password");

            if (rs.getString("username").equals(mail.getText())) {

                if (rs.getString("password").equals(passwordField.getText())) {
                    System.out.println("Girişiniz başarılı hoş geldiniz");
                    status.setTextFill(Color.GREEN);
                    status.setText("Login Succesfull");
                    user.setEmail(username);
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    user.setTCNumber(rs.getString("tcnumber"));
                    user.setUserId(rs.getString("UserId"));
                    user.setPhoneNumber(rs.getString("phonenumber"));
                    user.setApartmentNumber(rs.getString("apartmentnumber"));
                    user.setManagerCode(rs.getString("serialnumber"));

                    if (rs.getString("isadmin").equals("1")) {
                        user.setAdmin(true);
                    } else {
                        user.setAdmin(false);
                    }


                    result = 1;
                    setKullanici(user);


                    if (user.isAdmin() == true) {
                        Load_Pages.loadMainManager();
                        ((Node) (event.getSource())).getScene().getWindow().hide();
                    } else {
                        Load_Pages.loadMainMember();
                        ((Node) (event.getSource())).getScene().getWindow().hide();
                    }

                    break;

                }
                result = 2;
                status.setTextFill(Color.ORANGE);
                status.setText("Wrong Password");

            }

        }

        if (result == 0) {
            status.setTextFill(Color.RED);
            status.setText("Invalid Password or Email");
        }

        //Database
        rs.close();
        System.out.println("ResultSet close");

    }

    public void rememberPassword(ActionEvent event) throws Exception {
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
