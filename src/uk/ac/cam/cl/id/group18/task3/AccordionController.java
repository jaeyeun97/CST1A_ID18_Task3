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
    
    private TitledPane Accord(Image weather){
		TitledPane oneTab = new TitledPane();
		Label day = new Label("Monday");
		
		VBox row = new VBox();
		row.setStyle("-fx-padding: 10");
		row.setSpacing(10);
		row.getChildren().add(new Label("weatherInfo 1"));
	
	    HBox col = new HBox();
	    col.setSpacing(3);
	    col.setAlignment(Pos.CENTER);
	    col.getChildren().add(new ImageView(weather));
	    col.getChildren().add(day);
	    
	    row.getChildren().add(col);
	    
		//oneTab.setGraphic(header);
	    oneTab.setGraphic(row);
		return oneTab;
    }
    @FXML
    private void initialize(){
        accordion.getPanes().add(Accord(sunnyCloud));
    }
}
