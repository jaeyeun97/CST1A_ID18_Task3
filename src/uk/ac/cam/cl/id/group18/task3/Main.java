package uk.ac.cam.cl.id.group18.task3;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
        
        ((GridPane) root).add(accord(), 2, 0);

        primaryStage.setTitle("Group 18 Weather App");
        primaryStage.setScene(new Scene(root, 1280, 800));

        primaryStage.show();
    }

    public TitledPane accord(){
    	TitledPane oneTab = new TitledPane();		
    	Label day = new Label("Monday");
    	Image weather = new Image( "https://cdn.pixabay.com/photo/2012/04/18/13/21/clouds-37009_960_720.png", 0, 24, true, true);
    	
    	VBox errorPane = new VBox();
    	errorPane.setStyle("-fx-padding: 10");
    	errorPane.setSpacing(10);
    	errorPane.getChildren().add(new Label("weatherInfo 1"));

    	Label nErrors = new Label();
        nErrors.getStyleClass().add("nerrors");
        nErrors.textProperty().bind(Bindings.size(errorPane.getChildren()).asString());

        HBox header = new HBox();
        header.setSpacing(2);
        header.setAlignment(Pos.CENTER);
        header.setStyle("header");
        header.getChildren().add(nErrors);
        header.getChildren().add(new ImageView(weather));
        header.getChildren().add(day);
		oneTab.setGraphic(header);
		return oneTab;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
