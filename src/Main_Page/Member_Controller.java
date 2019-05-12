package Main_Page;

import Db_Connection.Db_Connection;
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
import java.sql.ResultSet;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static Login_Page.Controller.getUsers;

public class Member_Controller implements Initializable {
    public ListView listAnnouncements;
    public ListView listMessages;
    public ComboBox cmbReciever;
    public TextArea txtMessage;
    public TextField homeAddress;
    public Label homeName;
    public Label homeFloor;
    public Label homeDepartment;
    public Label feedbackDepartment;
    public Label feedbackFloor;
    public Label feedbackName;
    public TextArea txtNoteToManager;
    public Button uploadFile;
    public Label paymentsDepartment;
    public Label paymentsFloor;
    public Label paymentsName;
    public Label announcementsDepartment;
    public Label announcementsFloor;
    public Label announcementsName;
    public Label infoAddress;
    public Label infoFloorCount;
    public Label infoMemberCount;
    public Label infoManager;
    public Label infoBalance;
    public Label infoApartment;
    public Label infoDepartment;
    public Label infoFloor;
    public Label infoName;
    public TextField infoTC;
    public TextField infoNameLabel;
    public Label infoEmail;
    public TextField infoPhone;
    public TextField infoSurname;
    public Label infoBalanceInfo;
    public Label announcementsBalance;
    public Label feedbackBalance;
    public Label homeBalance;
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
    ObservableList<String> announcementList = FXCollections.observableArrayList();
    ObservableList<String> messageList = FXCollections.observableArrayList();
    ObservableList<String> nameList = FXCollections.observableArrayList();

    boolean status = false;
    int type;
    boolean confrimPayment = false;
    boolean paidinPerson = false;
    String date;


    public void onPageLoad() {
        final ToggleGroup group = new ToggleGroup();
        complaint.setToggleGroup(group);
        suggestion.setToggleGroup(group);
        breakdown.setToggleGroup(group);
    }

    public void showPersonalInformation (ActionEvent event) {
        userTC.setText(getUsers().getTCNumber());
        userName.setText(getUsers().getName());
        userSurname.setText(getUsers().getSurname());
        userPNumber.setText(getUsers().getPhoneNumber());
        userEmail.setText(getUsers().getEmail());
    }

    public void updatePersonalInformation (ActionEvent event) throws Exception {
        Db_Connection.connectiondb();
        String s = "UPDATE Users SET Name = '" + userName.getText() + "', Surname = '" + userSurname.getText() + "', PhoneNumber = '" + userPNumber.getText() + "', Email = '" + userEmail.getText() + "' WHERE TCnumber = '" + userTC.getText() + "'";
        Db_Connection.ExecuteSql(s);
        System.out.println("Islem tamamlandi.");
        Db_Connection.CloseConnection();
        System.out.println("DB kapandi.");
    }

    public void onSocialTabOpened() throws Exception {
        loadAnnouncements();
        getMessages();
        setRecieverList();
    }

    public void loadAnnouncements() throws Exception {
        Db_Connection.connectiondb();
        ResultSet rs = Db_Connection.executeQuery("SELECT * FROM Announcements");
        while (rs.next())
            announcementList.add(rs.getString("AnnouncementDescription"));
        listAnnouncements.setItems(announcementList);
    }

    public void getMessages() throws Exception {
        Db_Connection.connectiondb();
        ResultSet messages = Db_Connection.executeQuery("SELECT * FROM Messages where RecieverId ='" + getUsers().userId+"'");
        while (messages.next())
            messageList.add(messages.getString("Message"));
        listMessages.setItems(messageList);
    }

    public void sendMessage() throws Exception {
        try {
            Db_Connection.connectiondb();
            String Query = "INSERT INTO Messages (SenderId,RecieverId,RecieverName,Message) VALUES" + "('" + getUsers().userId + "','"+"1"+"','"+ cmbReciever.getValue() + "','" + txtMessage.getText() + "')";
            Db_Connection.ExecuteUpdate(Query);
            Db_Connection.CloseConnection();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setRecieverList() throws Exception {
        Db_Connection.connectiondb();
        ResultSet rs = Db_Connection.executeQuery("SELECT Name,Surname FROM USERS");
        while (rs.next())
            nameList.add(rs.getString("Name") + " " + rs.getString("Surname"));
        cmbReciever.setItems(nameList);
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
                        " ('" + type + "','" + feedbackText.getText() + "','" + getUsers().getManagerCode() + "')");

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

    public void fileProceccing(ActionEvent event) throws Exception{
        Db_Connection.connectiondb();
        String query;
        String userID;
        ResultSet rs;
        boolean confrimPayment = false;
        boolean paidinPerson = false;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files" , "*.pdf"));
        File file = fileChooser.showOpenDialog(null);


        if(file!=null) {
            switch (bankBox.getValue()) {
                case "Isbank":
                    Isbank isbank = new Isbank("", "");
                    if (isbank.checkTax(file.getAbsolutePath()))
                        if (isbank.correctIBAN(file.getAbsolutePath())) {
                            confrimPayment = true;
                            date = isbank.getDate(file.getAbsolutePath());
                        } else
                            confrimPayment = false;
                    break;
                case "YapiKredi":
                    YapiKredi yapiKredi = new YapiKredi("2", "4");
                    if (yapiKredi.checkTax(file.getAbsolutePath()))
                        if (yapiKredi.correctIBAN(file.getAbsolutePath())) {
                            confrimPayment = true;
                            date = yapiKredi.getDate(file.getAbsolutePath());
                        } else
                            confrimPayment = false;
                    break;
                case "Vakifbank":
                    Vakifbank vakifbank = new Vakifbank("", "");
                    if (vakifbank.checkTax(file.getAbsolutePath()))
                        if (vakifbank.correctIBAN(file.getAbsolutePath())) {
                            confrimPayment = true;
                            date = vakifbank.getDate(file.getAbsolutePath());
                        } else
                            confrimPayment = false;
                    break;

                case "Ziraatbank":
                    Ziraatbank ziraatbank = new Ziraatbank("8", "TR070006400000162370062524");
                    if (ziraatbank.checkTax(file.getAbsolutePath()))
                        if (ziraatbank.correctIBAN(file.getAbsolutePath())) {
                            confrimPayment = true;
                            date = ziraatbank.getDate(file.getAbsolutePath());
                        } else
                            confrimPayment = false;
                    break;
                case "Other/Paid in Person":
                    paidinPerson = true;
                    this.paidinPerson = paidinPerson;
                    break;
                default:
                    paymentStatus.setText("ERROR: Please be sure that you choose a bank.");
                    break;
            }

            if (paidinPerson) {
                System.out.println("test");
                isPaid.setText("Sent a messeage to manager that you paid tax in person/via Other bank");
                isPaid.setTextFill(Color.web("#0FE808"));
            }

            if (confrimPayment) {
                isPaid.setText("Tax is paid on :" + date);
                isPaid.setTextFill(Color.web("#0FE808"));
                userID = "SELECT UserId FROM users WHERE userName" + userName;
                query = "UPDATE dues WHERE UserID " + userID + "SET isPaid 1";
            } else {
                isPaid.setText("ERROR: IBAN/TAX not valid");
                isPaid.setTextFill(Color.web("#FF0000"));
            }
        }


    }

    public void confrimPayment (ActionEvent event) throws Exception{
        Db_Connection.connectiondb();
        if(confrimPayment) {
            String query = ("INSERT INTO dues (userID,IsPaid) Values" +
                    " ('" + getUsers().getUserId() + "',1)");
            Db_Connection.ExecuteSql(query);
        }
        if(paidinPerson){
            String query = ("INSERT INTO dues (userID,IsPaid) Values" +
                    " ('" + getUsers().getUserId() + "',2)");
            Db_Connection.ExecuteSql(query);
            System.out.println("Başarılı");
        }
    }

    public void labelStatus () throws Exception {
        Db_Connection.connectiondb();
        String query = "SELECT isPaid FROM dues WHERE UserId = '" + getUsers().getUserId() + "'";
        ResultSet rs = Db_Connection.executeQuery(query);

        if(rs.equals("1") || rs.equals("0"))
        {
            paymentStatus.setTextFill(Color.web("#00FF1E"));
            paymentStatus.setText("PAID");
        }
        else
        {
            paymentStatus.setTextFill(Color.web("#FF0000"));
            paymentStatus.setText("NOT PAID");
        }


    }
}

