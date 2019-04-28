package Main_Page;

import Db_Connection.Db_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;

import static Login_Page.Controller.getKullanici;

public class Controller {
    //Todo:Değişken isimleri kontrol edilip düzelecenecek!

    @FXML
    private RadioButton depositAidat;

    @FXML
    private RadioButton depositDükkan;

    @FXML
    private RadioButton depositDiger;

    @FXML
    private CheckBox withdrawalElectric;

    @FXML
    private CheckBox withdrawalWater;

    @FXML
    private CheckBox withdrawalLift;

    @FXML
    private CheckBox withdrawalCleaner;

    @FXML
    private CheckBox withdrawalService;

    @FXML
    private CheckBox withdrawalOther;

    @FXML
    private TextField depositValue;

    @FXML
    private TextField withdrawalValue;

    @FXML
    private CheckBox sendReceipt;

    @FXML
    private TextArea withDrawalNote;

    @FXML
    private TextArea depositNote;

    @FXML
    private Label nameSurname;

    @FXML
    private Label depositStatus;

    @FXML
    private Label withdrawalStatus;

    @FXML
    private Label field;

    @FXML
    private TextField userTc;

    @FXML
    private TextField userName;

    @FXML
    private TextField userSurname;

    @FXML
    private TextField userNumber;

    @FXML
    private TextField userMail;

    @FXML
    private Label userApartmentCode;

    @FXML
    private Label apartmentAdress;

    @FXML
    private Label apartmentFloor;

    @FXML
    private Label apartmentMember;

    @FXML
    private Label apartmentManager;

    @FXML
    private Label apartmentBalance;

    @FXML
    private Label date;

    public void baslanictaCalısacakMetodlar(ActionEvent event) {
        showPersonalInformation(event);
    }

    public void deneme(ActionEvent event) {
        field.setText(getKullanici().getName() + "  "
                + getKullanici().getSurname() + "  "
                + getKullanici().getUserId() + "  "
                + getKullanici().getApartmentNumber() + "  "
                + getKullanici().getEmail() + "  "
                + getKullanici().getManagerCode() + "  "
                + getKullanici().getPhoneNumber() + "  "
                + getKullanici().getTCNumber());
    }

    public void deposit(ActionEvent event) throws Exception {
        try {
            Db_Connection.connectiondb();
            int value = Integer.parseInt(depositValue.getText());
            String managerNotes = depositNote.getText();
            String asd = "";
            String managerCode = getKullanici().getManagerCode();

            if (depositAidat.isSelected()) {
                asd = "aidat";
            } else if (depositDükkan.isSelected()) {
                asd = "dükkan";
            } else if (depositDiger.isSelected()) {
                asd = "diger";
            }

            //Database Query
            String query = ("INSERT INTO transactions (Description,Value,IsExpense,TransactionType,ManagerCode) Values" +
                    " ('" + managerNotes + "','" + value + "','" + 1 + "','" + asd + "','" + managerCode + "')");

            Db_Connection.ExecuteSql(query);
            Db_Connection.CloseConnection();

            depositStatus.setTextFill(Color.GREEN);
            depositStatus.setText("Succesful");


        } catch (Exception e) {
            depositStatus.setTextFill(Color.RED);
            depositStatus.setText("UnSuccesful");
        }


    }

    public void withdrawal(ActionEvent event) {
        try {
            Db_Connection.connectiondb();
            int value = Integer.parseInt(withdrawalValue.getText());
            String managerNotes = withDrawalNote.getText();
            String asd = "";
            String managerCode = getKullanici().getManagerCode();

            if (withdrawalCleaner.isSelected()) {
                asd = "temizlik";
            } else if (withdrawalElectric.isSelected()) {
                asd = "elektrik";
            } else if (withdrawalLift.isSelected()) {
                asd = "asansör";
            } else if (withdrawalService.isSelected()) {
                asd = "bakım";
            } else if (withdrawalWater.isSelected()) {
                asd = "su";
            } else if (withdrawalOther.isSelected()) {
                asd = "diger";
            }

            //Database Query
            String query = ("INSERT INTO transactions (Description,Value,IsExpense,TransactionType,ManagerCode) Values" +
                    " ('" + managerNotes + "','" + value + "','" + 1 + "','" + asd + "','" + managerCode + "')");

            Db_Connection.ExecuteSql(query);
            Db_Connection.CloseConnection();

            withdrawalStatus.setTextFill(Color.GREEN);
            withdrawalStatus.setText("Succesful");

        } catch (Exception e) {
            withdrawalStatus.setTextFill(Color.RED);
            withdrawalStatus.setText("UnSuccesful");
        }
    }

    public void showPersonalInformation(ActionEvent event) {
        userTc.setText(getKullanici().getTCNumber());
        userName.setText(getKullanici().getName());
        userSurname.setText(getKullanici().getSurname());
        userNumber.setText(getKullanici().getPhoneNumber());
        userMail.setText(getKullanici().getEmail());
        userApartmentCode.setText(getKullanici().getManagerCode());
    }

    public void showApartmentInformation(ActionEvent event) {
        //Todo: DB'ye Apartments olarak table açılacak ve oradan bilgiler çekilip, işlemler oradan yapılacak
    }

    public void updateApartmentInformation(ActionEvent event) {
        //Todo: DB'ye Apartments olarak table açılacak ve oradan bilgiler çekilip, işlemler oradan yapılacak

    }

    public void updatePersonalInformation(ActionEvent event) throws Exception {
        Db_Connection.connectiondb();
        String s = "UPDATE Users SET Name = '" + userName.getText() + "', Surname = '" + userSurname.getText() + "', PhoneNumber = '" + userNumber.getText() + "', Username = '" + userMail.getText() + "'  WHERE TCNumber = '" + userTc.getText() + "'";
        Db_Connection.ExecuteSql(s);
        System.out.println("Işlem tamamlandı");
        Db_Connection.CloseConnection();
        System.out.println("DB kapandı");

    }


}
