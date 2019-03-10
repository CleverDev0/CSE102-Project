package Password_Remember;

import Db_Connection.Db_Connection;
import Project_Classes.Load_Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


import java.sql.ResultSet;
import java.util.UUID;

public class Controller {
    @FXML
    private TextField mail;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField managerCode;

    @FXML
    private Label status;

    String uuid = UUID.randomUUID().toString();
    String newPassword = uuid.substring(0,6);

    public void remember(ActionEvent event) throws Exception{
        int result = 0;

        String sql = "SELECT username,phonenumber,serialnumber FROM users";
        Db_Connection.connectiondb();
        ResultSet rs = Db_Connection.executeQuery(sql);

        while(rs.next()){
            if (rs.getString("username").equals(mail.getText())){
                if (rs.getString("phonenumber").equals(phoneNumber.getText())){
                    if (rs.getString("serialnumber").equals(managerCode.getText())){
                        status.setTextFill(javafx.scene.paint.Color.GREEN);
                        status.setText("New password:"+newPassword);
                        result =1;
                        //String s = "UPDATE Users SET password="+newPassword+" where Username="+mail.getText();
                        //Db_Connection.ExecuteSql(s);

                        //Todo:Database'de password güncellemesi yapılacak
                        break;
                    }

                    result = 2;
                    status.setTextFill(Color.ORANGE);
                    status.setText("Wrong Manager Code!");
                    break;


                }

                status.setTextFill(Color.MEDIUMVIOLETRED);
                status.setText("Wrong Phone Number!");
                result = 3;
                break;

            }


        }
        if (result == 0){
            status.setTextFill(Color.RED);
            status.setText("Wrong Email!");
        }

        rs.close();
        System.out.println("ResultSet close");
        Db_Connection.CloseConnection();
        System.out.println("DB Connection close");
    }

}
