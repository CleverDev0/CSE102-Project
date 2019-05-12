package Main_Page;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static Login_Page.Controller.getUsers;

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

        System.out.println(getUsers().getUserId());
        System.out.println(getUsers().getPassword());
        System.out.println(getUsers().getName());

        //System.out.println(Controller.getEmail()+"  "+Controller.getPassword());

        //kod bozmadan satır ekleme duası enter

    }
}
