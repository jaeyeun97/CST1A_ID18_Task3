package uk.ac.cam.cl.id.group18.task3;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by jaeyeun on 17. 5. 16.
 */
public class AccordionController {
    @FXML
    private Accordion accordion;
    private Image sunnyCloud = new Image("https://cdn.pixabay.com/photo/2012/04/18/13/21/clouds-37009_960_720.png", 0, 24, true, true);
    private Label Monday = new Label("Monday");
    private Label Tuesday = new Label("Tuesday");
    		
    private TitledPane Accord(Label day, Image weather){
		TitledPane oneTab = new TitledPane();
		
		VBox mainBox = new VBox();
		mainBox.setStyle("-fx-padding: 10");
		mainBox.setSpacing(10);
		mainBox.getChildren().add(day);
	
	    HBox subBox = new HBox();
	    subBox.setSpacing(10);
	    subBox.setAlignment(Pos.CENTER);
	    subBox.getChildren().add(new ImageView(weather));
	    subBox.getChildren().add(new Label("33¡ÆC"));
	    subBox.getChildren().add(new ImageView(weather));
	    subBox.getChildren().add(new Label("100mph"));
	    subBox.getChildren().add(new ImageView(weather));
	    subBox.getChildren().add(new Label("NW"));
	   
	    
	    mainBox.getChildren().add(subBox);
	    oneTab.setGraphic(mainBox);
		return oneTab;
    }
    @FXML
    private void initialize(){
    	TitledPane day1 = Accord(Monday, sunnyCloud);
    	day1.setContent(new Label("test pane, would be replaced with anchorpane"));
    	
    	TitledPane day2 = Accord(Tuesday, sunnyCloud);
    	day2.setContent(new Label("test pane, would be replaced with anchorpane"));
    	
        accordion.getPanes().add(day1);
        accordion.getPanes().add(day2);
        
    }
}
