package Start_Page;

import Project_Classes.Load_Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Controller {
    public void loginBtn(ActionEvent event) throws Exception {
        Load_Pages load = new Load_Pages();
        load.loadLogin();
        //Yeni sayfa açıldığında eski sayfanın kalmaması için
        //((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void signManager(ActionEvent event) throws Exception{
        Load_Pages load = new Load_Pages();
        load.loadSignManager();

        //Yeni sayfa açıldığında eski sayfanın kalmaması için
        //((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void signMember(ActionEvent event) throws Exception{
        Load_Pages load = new Load_Pages();
        load.loadSignMember();


        //Yeni sayfa açıldığında eski sayfanın kalmaması için
        //((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
