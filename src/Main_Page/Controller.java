package Main_Page;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static Login_Page.Controller.getKullanici;

public class Controller {

    @FXML
    private Label nameSurname;

    @FXML
    private Label userID;

    public void deneme(ActionEvent event){
        nameSurname.setText(getKullanici().getName()+getKullanici().getSurname());
        userID.setText(getKullanici().getUserId());
    }

}
