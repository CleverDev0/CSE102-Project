package Project_Classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Load_Pages {
    public static void loadMainManager() throws Exception{
        Parent parent= FXMLLoader.load(Load_Pages.class.getResource("../Main_Page/Main_Manager.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Main Page");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void loadMainMember() throws Exception{
        Parent parent= FXMLLoader.load(Load_Pages.class.getResource("../Main_Page/Main_Member.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Main Page");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void loadStart() throws Exception{
        Parent parent= FXMLLoader.load(Load_Pages.class.getResource("../Start_Page/Start_Page.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Title");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    public static void loadSignManager() throws Exception{
        Parent parent= FXMLLoader.load(Load_Pages.class.getResource("../SignUpManager/SignUpManager.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Title");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    public static void loadSignMember() throws Exception{
        Parent parent= FXMLLoader.load(Load_Pages.class.getResource("../SignUpMember/SignUpMember.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Title");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void loadLogin() throws Exception{
        Parent parent= FXMLLoader.load(Load_Pages.class.getResource("../Login_Page/login.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Title");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void loadPasswordRemember() throws Exception{
        Parent parent= FXMLLoader.load(Load_Pages.class.getResource("../Password_Remember/PasswordRemember.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Title");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    }
