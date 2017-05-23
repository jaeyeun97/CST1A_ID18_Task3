package uk.ac.cam.cl.id.group18.task3;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("task3.fxml"));
        Parent accordion = FXMLLoader.load(getClass().getResource("accordion.fxml"));
        Parent map = FXMLLoader.load(getClass().getResource("map.fxml"));
        ((GridPane) root).add(map, 0, 0);
        ((GridPane) root).add(accordion, 1, 0);
        
        Scene scene = new Scene(root, 1280, 800);
        scene.getStylesheets().add("file:css/weatherApp.css");
        scene.getStylesheets().add("file:css/searchBar.css");
        
        primaryStage.setTitle("Group 18 Weather App");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:images/parachute.png"));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}