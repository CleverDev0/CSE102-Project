package Login_Page;

import Project_Classes.Users;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static Login_Page.Controller.getKullanici;
import static Login_Page.Controller.kullanici;

public class main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        //primaryStage.fullScreenProperty();
        primaryStage.show();


    }
    public static void main(String[] args) {

        launch(args);

        System.out.println(getKullanici().getUserId());
        System.out.println(getKullanici().getPassword());
        System.out.println(getKullanici().getName());


    }

}
