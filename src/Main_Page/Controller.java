package Main_Page;

import Db_Connection.Db_Connection;
import Project_Classes.CustomExceptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

import java.sql.ResultSet;

import static Login_Page.Controller.getUsers;

public class Controller {

    public Label addressMain;
    public Label homeDepartment;
    public Label homeFloor;
    public Label homeName;
    public ListView lstHomeAnnouncements;
    public ListView lstMembersPaid;
    public Label infoBalance;
    public TextField infoTC;
    public TextField inforNamelbl;
    public TextField infoManager;
    public TextField infoMemberCount;
    public TextField infoFloorCount;
    public TextField infoAddress;
    public TextField infoPhone;
    public TextField infoEmail;
    public TextField infoSurname;
    public Label infoName;
    public Label infoFloor;
    public Label infoDepartment;
    public Label infoApartment;
    public Label announcementsDepartment;
    public Label announcementsFloor;
    public Label announcementsName;
    public Label paymentsDepartment;
    public Label paymentsFloor;
    public Label paymentsName;
    public Label paymentsBalance;
    public Label homeBalance;
    public Label announcementsBalance;
    public Label infoBalanceInfo;


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

    @FXML
    private WebView webViewId;

    @FXML
    private ListView<String> feedbackType;

    @FXML
    private ListView<String> feedbackMessage;

    @FXML
    private ListView<String> feedbackSender;

    public ListView<String> lstAnnouncements;
    public ListView lstMessages;
    public Button btnShowAnnouncements;
    public Button btnShowMessages;
    public Button btnAddAnnouncement;
    public TextArea txtAnnouncement;
    public ListView lstSenderId;
    public ComboBox cmbUser;


    //ObserveList For Feedback Lister
    ObservableList<String> feedbackTypeList = FXCollections.observableArrayList();
    ObservableList<String> feedbackMessageList = FXCollections.observableArrayList();
    ObservableList<String> feedbackSenderList = FXCollections.observableArrayList();


    public void deposit(ActionEvent event) throws Exception {
        try {

            Db_Connection.connectiondb();

            //Get Balance
            int balance = 0;
            ResultSet rs = Db_Connection.executeQuery("SELECT balance FROM Building where managerCode ='"+ getUsers().getManagerCode()+"'");
            if (rs.next()){
                balance = Integer.parseInt(rs.getString("balance"));
            }

            int value = Integer.parseInt(depositValue.getText());
            if (value < 0) {
                throw new CustomExceptions("Please check your amount");
            }
            String managerNotes = depositNote.getText();
            String asd = "";
            String managerCode = getUsers().getManagerCode();

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
            String s = "UPDATE Building SET balance = '"+newBalance+"' WHERE managerCode = '"+ getUsers().getManagerCode()+"'";
            Db_Connection.ExecuteSql(s);

            withdrawalStatus.setTextFill(Color.GREEN);
            withdrawalStatus.setText("Successful");


        } catch (CustomExceptions exceptions){
            withdrawalStatus.setTextFill(Color.RED);
            withdrawalStatus.setText(exceptions.getMessage());
        } catch (Exception e) {
            withdrawalStatus.setTextFill(Color.RED);
            withdrawalStatus.setText("Unsuccesfull..");
        }


    }

    public void withdrawal(ActionEvent event) {
        try {

            Db_Connection.connectiondb();

            ResultSet rs = Db_Connection.executeQuery("SELECT balance FROM Building where managerCode ='"+ getUsers().getManagerCode()+"'");
            int balance = 0;
            if (rs.next()){
                balance = Integer.parseInt(rs.getString("balance"));
            }

            int value = Integer.parseInt(withdrawalValue.getText());
            if (value < 0) {
                throw new CustomExceptions("Please check your amount");
            }
            String managerNotes = withDrawalNote.getText();
            String asd = "";
            String managerCode = getUsers().getManagerCode();

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
            String s = "UPDATE Building SET balance = '"+newBalance+"' WHERE managerCode = '"+ getUsers().getManagerCode()+"'";
            Db_Connection.ExecuteSql(s);

            withdrawalStatus.setTextFill(Color.GREEN);
            withdrawalStatus.setText("Successful");

        } catch (CustomExceptions exceptions){
            withdrawalStatus.setTextFill(Color.RED);
            withdrawalStatus.setText(exceptions.getMessage());
        } catch (Exception e) {
            withdrawalStatus.setTextFill(Color.RED);
            withdrawalStatus.setText("Unsuccesfull..");
        }
    }

    public void showPersonalInformation() throws Exception {
        userTc.setText(getUsers().getTCNumber());
        userName.setText(getUsers().getName());
        userSurname.setText(getUsers().getSurname());
        userNumber.setText(getUsers().getPhoneNumber());
        userMail.setText(getUsers().getEmail());
        userApartmentCode.setText(getUsers().getManagerCode());
    }


    public void showApartmentInformation() throws Exception {
        try {
            Db_Connection.connectiondb();
            String s = "Select * from building";
            ResultSet rs = Db_Connection.executeQuery(s);
            while(rs.next())
            infoAddress.setText(rs.getString("Adress"));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateApartmentInformation() throws Exception{
        Db_Connection.connectiondb();
        String s = "UPDATE Building SET Adress = '" + apartmentAdress.getText() + "', FloorCount = '" + apartmentFloor.getText() + "', MemberCount = '" + apartmentMember.getText() + "', Manager = '" + apartmentManager.getText() + "'  WHERE managerCode = '" + getUsers().getManagerCode() + "'";
        Db_Connection.ExecuteSql(s);
        Db_Connection.CloseConnection();

    }

    public void updatePersonalInformation() throws Exception {
        Db_Connection.connectiondb();
        String s = "UPDATE Users SET Name = '" + userName.getText() + "', Surname = '" + userSurname.getText() + "', PhoneNumber = '" + userNumber.getText() + "', UserEmail = '" + userMail.getText() + "'  WHERE TCNumber = '" + userTc.getText() + "'";
        Db_Connection.ExecuteSql(s);
        Db_Connection.CloseConnection();

    }

    public void getFeedback(ActionEvent event) throws Exception {

        try {
            Db_Connection.connectiondb();
            String sql = "SELECT feedbacktype,message,userid FROM feedback WHERE managerCode='" + getUsers().getManagerCode() + "'";
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
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void checkBill() {
        webViewId.getEngine().load("https://www.faturaodemeislemleri.com/");
    }


    public void showInfoHome() throws Exception {
        try {
            Db_Connection.connectiondb();
            String sql = "SELECT * FROM users WHERE UserId ='" + getUsers().userId + "'";
            ResultSet resultSet = Db_Connection.executeQuery(sql);
            assert resultSet != null;
            if (resultSet.next()) {
                homeName.setText(resultSet.getString("Name") + " " + resultSet.getString("Surname"));
                homeDepartment.setText(resultSet.getString("ApartmentNumber"));
                homeFloor.setText(resultSet.getString("floorNumber"));
            }
            ResultSet rs2 = Db_Connection.executeQuery("SELECT * FROM Building");
            assert rs2 != null;
            if (rs2.next())
                homeBalance.setText(rs2.getString("Balance"));
            String sql1 = "SELECT * FROM dues WHERE isPaid = 1";
            ResultSet rs = Db_Connection.executeQuery(sql1);
            ObservableList<String> list = FXCollections.observableArrayList();
            while (rs.next())
                list.add(rs.getString("UserId"));

            lstMembersPaid.setItems(list);

            String sql3 = "SELECT * FROM Announcements";
            Db_Connection.connectiondb();
            ResultSet rs3 = Db_Connection.executeQuery(sql3);
            ObservableList<String> items = FXCollections.observableArrayList();
            assert rs3 != null;
            while (rs3.next()) {
                items.add(rs3.getString("AnnouncementDescription"));
            }
            lstHomeAnnouncements.setItems(items);

            Db_Connection.CloseConnection();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }


    public void onPageLoad() {
        final ToggleGroup group = new ToggleGroup();
        depositAidat.setToggleGroup(group);
        depositDiger.setToggleGroup(group);
        depositDükkan.setToggleGroup(group);

    }

    public void addAnnouncement() throws Exception {
        if(txtAnnouncement.getText() != null) {
            String query = ("INSERT INTO Announcements (AnnouncementDescription) Values" + "( '" + txtAnnouncement.getText() + "')");
            Db_Connection.ExecuteSql(query);
            Db_Connection.CloseConnection();
            loadAnnouncements();
        }
        else {
            System.out.println("Announcement cant be empty!");
        }
    }

    public void loadAnnouncements() throws Exception {
        String sql = "SELECT * FROM Announcements";
        Db_Connection.connectiondb();
        ResultSet rs = Db_Connection.executeQuery(sql);
        ObservableList<String> items = FXCollections.observableArrayList();
        assert rs != null;
        while (rs.next()) {
            items.add(rs.getString("AnnouncementDescription"));
        }
        lstAnnouncements.setItems(items);
    }

    public void loadMessages() throws Exception {
        String sql = "SELECT SenderId FROM Messages";
        Db_Connection.connectiondb();
        ResultSet rs = Db_Connection.executeQuery(sql);
        ObservableList<Integer> senderList = FXCollections.observableArrayList();
        assert rs != null;
        while (rs.next()) {
            senderList.add(rs.getInt("SenderId"));
        }
        lstSenderId.setItems(senderList);
        String sql1 = "SELECT Message FROM Messages";
        Db_Connection.connectiondb();
        ResultSet rs1 = Db_Connection.executeQuery(sql1);
        ObservableList<String> messageList = FXCollections.observableArrayList();
        assert rs1 != null;
        while (rs1.next()) {
            messageList.add(rs1.getString("Message"));
        }
        lstMessages.setItems(messageList);
    }


    public void MessageLoaded() throws Exception {
        String sql = "SELECT Name,Surname FROM Users";
        Db_Connection.connectiondb();
        ResultSet rs = Db_Connection.executeQuery(sql);
        ObservableList<String> userList = FXCollections.observableArrayList();
        assert rs != null;
        while (rs.next()) {
            userList.add(rs.getString("Name") + " " + rs.getString("Surname"));
        }
        cmbUser.setItems(userList);
    }

    public void showAlert (ActionEvent event) throws Exception{
        Db_Connection.connectiondb();
        String s = "SELECT userID FROM dues WHERE isPaid = 0 ";
        ResultSet rs = Db_Connection.executeQuery(s);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Members paid in person");
        for (int i = 0; i <rs.getFetchSize() ; i++) {
            alert.setContentText(rs.getString(i));
        }
    }

}
