package Main_Page;

import Db_Connection.Db_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
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

    public void sendFeedback(ActionEvent event) {
        try {
            Db_Connection.getConnection();
            if (complaint.isSelected()) {
                String type = "complaint";
            } else if (suggestion.isSelected()) {
                String type = "suggestion";
            } else if (breakdown.isSelected()) {
                String type = "breakdown";
            } else {
                feedbackStatus.setTextFill(Color.RED);
                feedbackStatus.setText("Unsuccesful. Please check feedback types.");
            }

            /*String query = ("INSERT INTO transactions (Description,Value,IsExpense,TransactionType,ManagerCode) Values" +
                    " ('" + managerNotes + "','" + value + "','" + 1 + "','" + asd + "','" + managerCode + "')");*//*

            Db_Connection.ExecuteSql(query);
            Db_Connection.CloseConnection();*/
            //Todo:Db'ye işlenecek


        } catch (Exception exception) {
            feedbackStatus.setTextFill(Color.RED);
            feedbackStatus.setText("Unsuccesful. Please check your connection.");
        }

    }


}
