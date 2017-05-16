package uk.ac.cam.cl.id.group18.task3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("task3.fxml"));
        Parent accordion = FXMLLoader.load(getClass().getResource("accordion.fxml"));
        Parent map = FXMLLoader.load(getClass().getResource("map.fxml"));
        ((GridPane) root).add(map, 0, 0);
        ((GridPane) root).add(accordion, 1, 0);

        primaryStage.setTitle("Group 18 Weather App");
        primaryStage.setScene(new Scene(root, 1280, 800));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
