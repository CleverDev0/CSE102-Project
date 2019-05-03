package Main_Page;

import Db_Connection.Db_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import static Login_Page.Controller.getKullanici;

public class Member_Controller {
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

    boolean status = false;
    int type;

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


}
