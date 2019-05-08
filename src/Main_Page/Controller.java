package Main_Page;

import Db_Connection.Db_Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
<<<<<<< HEAD
import javafx.scene.web.WebView;
=======
import javafx.stage.FileChooser;
>>>>>>> e089fef0f90cc80bb5d61d77ebedacbf7b3d41d9
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

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
    private RadioButton withdrawalElectric;

    @FXML
    private RadioButton withdrawalWater;

    @FXML
    private RadioButton withdrawalLift;

    @FXML
    private RadioButton withdrawalCleaner;

    @FXML
    private RadioButton withdrawalService;

    @FXML
    private RadioButton withdrawalOther;

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
    private ImageView depositStatus;

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

    @FXML
    private WebView webViewId;

    @FXML
    private ListView<String> feedbackType;

    @FXML
    private ListView<String> feedbackMessage;

    @FXML
    private ListView<String> feedbackSender;


    //ObserveList For Feedback Lister
    ObservableList<String> feedbackTypeList = FXCollections.observableArrayList();
    ObservableList<String> feedbackMessageList = FXCollections.observableArrayList();
    ObservableList<String> feedbackSenderList = FXCollections.observableArrayList();


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
            int balance = 0;
            Db_Connection.connectiondb();

            //Get Balance
            ResultSet rs = Db_Connection.executeQuery("SELECT balance FROM Building where managerCode ='"+getKullanici().getManagerCode()+"'");
            if (rs.next()){
                balance = Integer.parseInt(rs.getString("balance"));
            }

            int value = Integer.parseInt(depositValue.getText());
            String managerNotes = depositNote.getText();
            String asd = "";
            String managerCode = getKullanici().getManagerCode();

            //Explain Deposit
            if (depositAidat.isSelected()) {
                asd = "aidat";
            } else if (depositDükkan.isSelected()) {
                asd = "dükkan";
            } else if (depositDiger.isSelected()) {
                asd = "diger";
            }
            int newBalance = balance + value;

            //Write Transactions on DB
            String query = ("INSERT INTO transactions (Description,Value,IsExpense,TransactionType,ManagerCode) Values" +
                    " ('" + managerNotes + "','" + value + "','" + 0 + "','" + asd + "','" + managerCode + "')");
            Db_Connection.ExecuteSql(query);

            //Update Balance
            String s = "UPDATE Building SET balance = '"+newBalance+"' WHERE managerCode = '"+getKullanici().getManagerCode()+"'";
            Db_Connection.ExecuteSql(s);

            Image image = new Image(getClass().getResourceAsStream("../Project_IMG/successfull.png"));
            depositStatus.setImage(image);

            Db_Connection.CloseConnection();


        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public void withdrawal(ActionEvent event) {
        try {
            int balance = 0;
            Db_Connection.connectiondb();

            ResultSet rs = Db_Connection.executeQuery("SELECT balance FROM Building where managerCode ='"+getKullanici().getManagerCode()+"'");
            if (rs.next()){
                balance = Integer.parseInt(rs.getString("balance"));
            }

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

            int newBalance = balance - value;

            //Database Query
            String query = ("INSERT INTO transactions (Description,Value,IsExpense,TransactionType,ManagerCode) Values" +
                    " ('" + managerNotes + "','" + value + "','" + 1 + "','" + asd + "','" + managerCode + "')");
            Db_Connection.ExecuteSql(query);

            //Update Balance
            String s = "UPDATE Building SET balance = '"+newBalance+"' WHERE managerCode = '"+getKullanici().getManagerCode()+"'";
            Db_Connection.ExecuteSql(s);

            withdrawalStatus.setTextFill(Color.GREEN);
            withdrawalStatus.setText("Succesful");

            Db_Connection.CloseConnection();

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
        String s = "UPDATE Users SET Name = '" + userName.getText() + "', Surname = '" + userSurname.getText() + "', PhoneNumber = '" + userNumber.getText() + "', UserEmail = '" + userMail.getText() + "'  WHERE TCNumber = '" + userTc.getText() + "'";
        Db_Connection.ExecuteSql(s);
        System.out.println("Işlem tamamlandı");
        Db_Connection.CloseConnection();
        System.out.println("DB kapandı");

    }

    public void getFeedback(ActionEvent event) throws Exception {

        Db_Connection.connectiondb();
        String sql = "SELECT feedbacktype,message,userid FROM feedback WHERE managerCode='" + getKullanici().getManagerCode() + "'";
        ResultSet rs = Db_Connection.executeQuery(sql);
        while (rs.next()) {
            feedbackTypeList.add(rs.getString("feedbacktype"));
            feedbackMessageList.add(rs.getString("message"));
            ResultSet user = Db_Connection.executeQuery("Select name,surname FROM users WHERE userId = '" + rs.getString("userId") + "'");
            while (user.next()) {
                feedbackSenderList.add(user.getString("name") + " " + user.getString("surname"));
            }
        }
        feedbackType.setItems(feedbackTypeList);
        feedbackMessage.setItems(feedbackMessageList);
        feedbackSender.setItems(feedbackSenderList);
    }

    public void checkBill() {
        webViewId.getEngine().load("https://www.faturaodemeislemleri.com/");
    }

}
