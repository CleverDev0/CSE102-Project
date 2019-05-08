package Main_Page;

import Db_Connection.Db_Connection;
import Login_Page.Controller;
import Project_Classes.Isbank;
import Project_Classes.Vakifbank;
import Project_Classes.YapiKredi;
import Project_Classes.Ziraatbank;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static Login_Page.Controller.getKullanici;

public class Member_Controller implements Initializable {
    @FXML
    private RadioButton complaint;

    @FXML
    private RadioButton suggestion;

    @FXML
    private RadioButton breakdown;

    @FXML
    private TextArea feedbackText;

    @FXML
    private Label feedbackStatus;

    @FXML
    private TextField userTC;

    @FXML
    private TextField userName;

    @FXML
    private TextField userSurname;

    @FXML
    private TextField userPNumber;

    @FXML
    private TextField userEmail;

    @FXML
    private ComboBox<String> bankBox;

    @FXML
    private Label paymentStatus;

    @FXML
    private Label isPaid;

    ObservableList<String> bankList = FXCollections.observableArrayList("Isbank" , "YapiKredi" , "Vakifbank" , "Ziraatbank" , "Other/Paid in Person");

    boolean status = false;
    int type;
    boolean confrimPayment=false;
    boolean paidinPerson;
    String date;

    public void showPersonalInformation (ActionEvent event) {
        userTC.setText(getKullanici().getTCNumber());
        userName.setText(getKullanici().getName());
        userSurname.setText(getKullanici().getSurname());
        userPNumber.setText(getKullanici().getPhoneNumber());
        userEmail.setText(getKullanici().getEmail());
    }

    public void updatePersonalInformation (ActionEvent event) throws Exception {
        Db_Connection.connectiondb();
        String s = "UPDATE Users SET Name = '" + userName.getText() + "', Surname = '" + userSurname.getText() + "', PhoneNumber = '" + userPNumber.getText() + "', Email = '" + userEmail.getText() + "' WHERE TCnumber = '" + userTC.getText() + "'";
        Db_Connection.ExecuteSql(s);
        System.out.println("Islem tamamlandi.");
        Db_Connection.CloseConnection();
        System.out.println("DB kapandi.");
    }

    public void sendFeedback(ActionEvent event) {
        try {
            if (complaint.isSelected() && !suggestion.isSelected() && !breakdown.isSelected()) {
                type = 1;
                status = true;
            } else if (suggestion.isSelected() && !complaint.isSelected() && !breakdown.isSelected()) {
                type = 2;
                status = true;
            } else if (breakdown.isSelected() && !suggestion.isSelected() && !complaint.isSelected()) {
                type = 3;
                status = true;
            } else {
                feedbackStatus.setTextFill(Color.RED);
                feedbackStatus.setText("Unsuccesful. Please check feedback types.");
            }

            if (status) {
                String query = ("INSERT INTO feedback (feedbackType,message,managerCode) Values" +
                        " ('" + type + "','" + feedbackText.getText() + "','" + getKullanici().getManagerCode() + "')");

                Db_Connection.ExecuteSql(query);
                feedbackStatus.setTextFill(Color.GREEN);
                feedbackStatus.setText("Succesful. Feedback sended.");


            }
        } catch (Exception exception) {
            feedbackStatus.setTextFill(Color.RED);
            feedbackStatus.setText("Unsuccesful. Please check your connection.");
        }

    }

    @Override
    public void initialize (URL location , ResourceBundle resourceBundle){
        bankBox.setItems(bankList);
    }



    }
}

