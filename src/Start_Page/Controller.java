package Start_Page;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Controller {
    public void loginBtn(ActionEvent event) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("../Main_Page/main.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Ana Menü");
        stage.setScene(new Scene(parent));
        stage.show();

        //Yeni sayfa açıldığında eski sayfanın kalmaması için
        ((Node)(event.getSource())).getScene().getWindow().hide();


        //Todo: Login sayfası yapılacak..


    }
}
