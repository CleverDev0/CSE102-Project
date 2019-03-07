package Project_Classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Load_Pages {
    public void loadMain() throws Exception{
        Parent parent= FXMLLoader.load(getClass().getResource("../Main_Page/main.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Main Page");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void loadStart() throws Exception{
        Parent parent= FXMLLoader.load(getClass().getResource("../Start_Page/Start_Page.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Title");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    public void loadSignManager() throws Exception{
        Parent parent= FXMLLoader.load(getClass().getResource("../SignUpManager/SignUpManager.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Title");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    public void loadSignMember() throws Exception{
        Parent parent= FXMLLoader.load(getClass().getResource("../SignUpMember/SignUpMember.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Title");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    }
