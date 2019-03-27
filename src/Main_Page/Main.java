package Main_Page;

import Login_Page.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

import static Login_Page.Controller.getKullanici;

//import static Login_Page.Controller.email;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main_Manager.fxml"));
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

        //System.out.println(Controller.getEmail()+"  "+Controller.getPassword());

        //kod bozmadan satır ekleme duası enter

    }
}
