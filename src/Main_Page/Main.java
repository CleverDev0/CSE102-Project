package Main_Page;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application {

    //Database related variables
    private static String username = "root";
    private static String password = "12345678";

    private static String connectionString = "jdbc:mysql://localhost:3306/cse_102_project_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static Connection connection;
    private static Statement command;
    private static ResultSet data;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Start_Page/Start_Page.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        //primaryStage.fullScreenProperty();
        primaryStage.show();

        try {
            connection = DriverManager.getConnection(connectionString,username,password);
            command = connection.createStatement();
            //command.execute("INSERT INTO users (Username,Password,Name,Surname,PhoneNumber,TCNumber,SerialNumber,ApartmentNumber,IsAdmin) Values ('Admin','123','Admin','Admin','5555555555','11111111111','abc123','14',1)");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Connection Established! QUERY WORKED RIGHT! INSERT ops. Successuful!");
        }
    }
    public static void main(String[] args) {

        launch(args);

        //kod bozmadan satır ekleme duası enter

    }
}
