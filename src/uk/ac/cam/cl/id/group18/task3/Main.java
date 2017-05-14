package uk.ac.cam.cl.id.group18.task3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("task3.fxml"));

        primaryStage.setTitle("Group 18 Weather App");
        primaryStage.setScene(new Scene(root, 1280, 800));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
