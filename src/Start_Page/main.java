package Start_Page;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Start_Page/Start_Page.fxml"));
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root));
            //primaryStage.fullScreenProperty();
            primaryStage.show();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
    public static void main(String[] args) {
        launch(args);
    }
}
