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
    
    private TitledPane Accord(){
		TitledPane oneTab = new TitledPane();
		Label day = new Label("Monday");
		Image weather = new Image( "https://cdn.pixabay.com/photo/2012/04/18/13/21/clouds-37009_960_720.png", 0, 24, true, true);
		
		VBox vPane = new VBox();
		vPane.setStyle("-fx-padding: 10");
		vPane.setSpacing(10);
		vPane.getChildren().add(new Label("weatherInfo 1"));
	
		Label someLabel = new Label();
	    someLabel.textProperty().bind(Bindings.size(vPane.getChildren()).asString());
	
	    HBox header = new HBox();
	    header.setSpacing(3);
	    header.setAlignment(Pos.CENTER);
	    header.setStyle("header");
	    header.getChildren().add(someLabel);
	    header.getChildren().add(new ImageView(weather));
	    header.getChildren().add(day);
		oneTab.setGraphic(header);
		return oneTab;
    }
    @FXML
    private void initialize(){
        accordion.getPanes().add(Accord());
    }
}
