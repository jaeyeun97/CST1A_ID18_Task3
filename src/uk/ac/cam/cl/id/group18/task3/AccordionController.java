package uk.ac.cam.cl.id.group18.task3;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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
import javafx.stage.Stage;

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


    @FXML
    private void initialize(){

    	TitledPane day1 = accord(Mon, sunnyCloud);
    	day1.setContent(content());
    	
    	TitledPane day2 = accord(Tue, sunnyCloud);
    	day2.setContent(content());
    	
    	TitledPane day3 = accord(Wed, sunnyCloud);
    	day3.setContent(content());

    	TitledPane day4 = accord(Thu, sunnyCloud);
    	day4.setContent(content());

    	TitledPane day5 = accord(Fri, sunnyCloud);
    	day5.setContent(content());

    	TitledPane day6 = accord(Sat, sunnyCloud);
    	day6.setContent(content());

    	TitledPane day7 = accord(Sun, sunnyCloud);
    	day7.setContent(content());


        accordion.getPanes().add(day1);
        accordion.getPanes().add(day2);
        accordion.getPanes().add(day3);
        accordion.getPanes().add(day4);
        accordion.getPanes().add(day5);
        accordion.getPanes().add(day6);
        accordion.getPanes().add(day7);
    }


    private ScrollPane content(){
		ScrollPane base = new ScrollPane();
		base.setPannable(true);

		List<VBox> hours = new ArrayList<>();
		for(int i=0; i<8; i++){
			VBox hour = new VBox();
			hour.getStyleClass().add("hour");
			List<Node> content = hour.getChildren();

			HBox wind = new HBox();
			ImageView windimg = new ImageView();
			windimg.setImage(new Image("file:images/wind.png"));
			windimg.getStyleClass().add("windicon");
			wind.getChildren().add(windimg);
			Label wind_val = new Label("7-9");
			wind_val.getStyleClass().add("wind_val");
			wind.getChildren().add(wind_val);
;			Label wind_msmt = new Label("mph");
			wind_msmt.getStyleClass().add("wind_msmt");
			wind.getChildren().add(wind_msmt);
			Label wind_dir = new Label("NE");
			wind_dir.getStyleClass().add("wind_dir");
			wind.getChildren().add(wind_dir);
			content.add(wind);


			HBox temp = new HBox();
			ImageView tempimg = new ImageView();
			tempimg.setImage(new Image("file:images/temp.png"));
			tempimg.getStyleClass().add("tempicon");
			temp.getChildren().add(tempimg);
			Label temp_val = new Label("11");
			temp_val.getStyleClass().add("temp_val");
			temp.getChildren().add(temp_val);
			Label temp_msmt = new Label("¨¬C");
			temp_msmt.getStyleClass().add("temp_msmt");
			temp.getChildren().add(temp_msmt);
			content.add(temp);


			HBox prec = new HBox();
			ImageView precimg = new ImageView();
			precimg.setImage(new Image("file:images/prec.png"));
			precimg.getStyleClass().add("precicon");
			prec.getChildren().add(precimg);
			Label prec_val = new Label("0%");
			prec_val.getStyleClass().add("small_val");
			prec.getChildren().add(prec_val);
			content.add(prec);


			HBox hum = new HBox();
			ImageView humimg = new ImageView();
			humimg.setImage(new Image("file:images/hum.png"));
			humimg.getStyleClass().add("humicon");
			hum.getChildren().add(humimg);
			Label hum_val = new Label("0%");
			hum_val.getStyleClass().add("small_val");
			hum.getChildren().add(hum_val);
			content.add(hum);


			HBox uv = new HBox();
			ImageView uvimg = new ImageView();
			uvimg.setImage(new Image("file:images/uv.png"));
			uvimg.getStyleClass().add("uvicon");
			uv.getChildren().add(uvimg);
			Label uv_val = new Label("6");
			uv_val.getStyleClass().add("small_val");
			uv.getChildren().add(uv_val);
			content.add(uv);


			HBox pres = new HBox();
			ImageView presimg = new ImageView();
			presimg.setImage(new Image("file:images/pres.png"));
			presimg.getStyleClass().add("presicon");
			pres.getChildren().add(presimg);
			Label pres_val = new Label("1022 hPa");
			pres_val.getStyleClass().add("small_val");
			pres.getChildren().add(pres_val);
			content.add(pres);



			hour.setMinWidth(320);
			hours.add(hour);
		}

		HBox scrollContent = new HBox();
		scrollContent.setSpacing(4);
		scrollContent.getChildren().addAll(hours);

		base.setContent(scrollContent);
		ScrollBar scroll = new ScrollBar();
		base.setVbarPolicy(ScrollBarPolicy.NEVER);
		base.setHbarPolicy(ScrollBarPolicy.NEVER);

		return base;
	}


/**
	private ScrollPane hourly(){
		ScrollPane base = new ScrollPane();
		base.setPannable(true);
		base.setId("accord");

		List<VBox> hours = new ArrayList<>();
		for(int i=0; i<8; i++){
			VBox hour = new VBox();
			hour.setId("accord");
			hour.setSpacing(5);

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
	private VBox accord_content (){
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

    	VBox final_content = new VBox();
    	final_content.getChildren().add(content);
    	final_content.getChildren().add(hourly());

    	return final_content;
	}
**/

}
