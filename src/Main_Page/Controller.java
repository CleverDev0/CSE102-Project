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
    private Label status;

    @FXML
    private Label field;

    public void deneme(ActionEvent event){
        field.setText(getKullanici().getName()+"  "
                +getKullanici().getSurname()+"  "
                +getKullanici().getUserId()+"  "
                +getKullanici().getApartmentNumber()+"  "
                +getKullanici().getEmail()+"  "
                +getKullanici().getManagerCode()+"  "
                +getKullanici().getPhoneNumber()+"  "
                +getKullanici().getTCNumber());
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
                    " ('" + managerNotes + "','" + value + "','" + 1 + "','" + asd +"','"+managerCode+"')");

            Db_Connection.ExecuteSql(query);
            Db_Connection.CloseConnection();

            status.setTextFill(Color.GREEN);
            status.setText("Succesful");


        }catch (Exception e){
            status.setTextFill(Color.RED);
            status.setText("UnSuccesful");
        }


    }

    public void withdrawal(ActionEvent event){}





}
