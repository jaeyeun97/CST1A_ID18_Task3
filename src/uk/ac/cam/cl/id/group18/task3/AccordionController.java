package uk.ac.cam.cl.id.group18.task3;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

/**
 * Created by jaeyeun on 17. 5. 16.
 */
public class AccordionController {
    @FXML
    private Accordion accordion;
    private Image sunnyCloud = new Image("https://cdn.pixabay.com/photo/2012/04/18/13/21/clouds-37009_960_720.png", 0, 24, true, true);
    private Label Mon = new Label("Monday");
    private Label Tue = new Label("Tuesday");
    private Label Wed = new Label("Wednesday");
    private Label Thu = new Label("Thursday");
    private Label Fri = new Label("Friday");
    private Label Sat = new Label("Saturday");
    private Label Sun = new Label("Sunday");

    		
    private TitledPane accord(Label day, Image weather){
		TitledPane oneTab = new TitledPane();
		oneTab.getStylesheets().add("file:css/accordion.css");

		//Make a VBox
		VBox mainBox = new VBox();
		mainBox.setStyle("-fx-padding: 10");
		mainBox.setSpacing(10);
		mainBox.getChildren().add(day);

		//Put this HBox into the VBox above
	    HBox subBox = new HBox();
	    subBox.setSpacing(10);
	    subBox.setAlignment(Pos.CENTER);
	    subBox.getChildren().add(new ImageView(weather));
	    subBox.getChildren().add(new Label("33¨¬C"));
	    subBox.getChildren().add(new ImageView(weather));
	    subBox.getChildren().add(new Label("100mph"));
	    subBox.getChildren().add(new ImageView(weather));
	    subBox.getChildren().add(new Label("NW"));
	    
	    //Put the VBox above into oneTab
	    mainBox.getChildren().add(subBox);
	    oneTab.setGraphic(mainBox);
		return oneTab;
    }

    private ScrollPane hourly(){
    	ScrollPane base = new ScrollPane();
    	base.setPannable(true);
    	base.setId("accord");

    	List<VBox> hours = new ArrayList<>();
    	for(int i=0; i<8; i++){
    		VBox hour = new VBox();
    		hour.setId("accord");
    		hour.setSpacing(5);

    		List<Node> contents = hour.getChildren();
    		contents.add(new Label("00:00"));
    		contents.add(new Label("10 ¨¬C"));
    		contents.add(new Label("999 mph"));
    		hours.add(hour);
    	}

    	HBox scrollContent = new HBox();
    	scrollContent.setId("accord");
    	scrollContent.setSpacing(3);
    	scrollContent.getChildren().addAll(hours);

    	base.setContent(scrollContent);
    	base.setHbarPolicy(ScrollBarPolicy.NEVER);
    	base.setVbarPolicy(ScrollBarPolicy.NEVER);

    	return base;
    }

    @FXML
    private void initialize(){

    	TitledPane day1 = accord(Mon, sunnyCloud);
    	GridPane temp = new GridPane();
    	temp.add(hourly(), 0, 0);
    	temp.add(accord_content(), 0, 1);
    	day1.setContent(temp);
    	
    	TitledPane day2 = accord(Tue, sunnyCloud);
    	day2.setContent(accord_content());
    	
    	TitledPane day3 = accord(Wed, sunnyCloud);
    	day3.setContent(accord_content());

    	TitledPane day4 = accord(Thu, sunnyCloud);
    	day4.setContent(accord_content());

    	TitledPane day5 = accord(Fri, sunnyCloud);
    	day5.setContent(accord_content());

    	TitledPane day6 = accord(Sat, sunnyCloud);
    	day6.setContent(accord_content());

    	TitledPane day7 = accord(Sun, sunnyCloud);
    	day7.setContent(accord_content());


        accordion.getPanes().add(day1);
        accordion.getPanes().add(day2);
        accordion.getPanes().add(day3);
        accordion.getPanes().add(day4);
        accordion.getPanes().add(day5);
        accordion.getPanes().add(day6);
        accordion.getPanes().add(day7);
    }


    @FXML
	private GridPane accord_content (){
    	GridPane content = new GridPane();
		content.getStyleClass().add("content_tab");


        Label wind_val = new Label("7-9");
        wind_val.getStyleClass().add("wind_val");
    	content.add(wind_val,0,0);
    	Label wind_msmt = new Label("mph");
		wind_msmt.getStyleClass().add("wind_msmt");
    	content.add(wind_msmt, 1, 0);
    	Label wind_dir = new Label("NE");
		wind_dir.getStyleClass().add("wind_dir");
    	content.add(wind_dir, 2, 0);

        Label temp_val = new Label("11");
		temp_val.getStyleClass().add("temp_val");
    	content.add(temp_val, 0, 1);
    	Label temp_msmt = new Label("¨¬C");
		temp_msmt.getStyleClass().add("temp_msmt");
    	content.add(temp_msmt, 1, 1);

        Label prec_name = new Label("Precipitation:");
		prec_name.getStyleClass().add("small_name");
    	content.add(prec_name, 0, 2);
    	Label prec_val = new Label("0%");
		prec_val.getStyleClass().add("small_val");
    	content.add(prec_val, 1, 2);

        Label hum_name = new Label("Humidity:");
        hum_name.getStyleClass().add("small_name");
    	content.add(hum_name, 0,3);
    	Label hum_val = new Label("0%");
    	hum_val.getStyleClass().add("small_val");
		content.add(hum_val, 1, 3);

        Label uv_name = new Label("UV:");
        uv_name.getStyleClass().add("small_name");
    	content.add(uv_name, 0, 4);
    	Label uv_val = new Label("6");
    	uv_val.getStyleClass().add("small_val");
    	content.add(uv_val, 1, 4);

		Label pres_name = new Label("Pressure:");
		pres_name.getStyleClass().add("small_name");
    	content.add(pres_name, 0, 5);
    	Label pres_val = new Label("1022 hPa");
    	pres_val.getStyleClass().add("small_val");
    	content.add(pres_val, 1, 5);

    	return content;
	}

}
