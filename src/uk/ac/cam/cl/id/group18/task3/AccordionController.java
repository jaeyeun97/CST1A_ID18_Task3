package uk.ac.cam.cl.id.group18.task3;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Calendar;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uk.ac.cam.cl.id.group18.task3.Locations;

/**
 * Written by Seohyun Woo, Theodora Zamfirache.
 */
public class AccordionController {
    @FXML
    private Accordion accordion;
    private Label[] days = new Label[7];
    public ThreeHourWeather[][] data = null;
    private Image windTitle = new Image("file:images/titleWind.png", 0, 25, true, true);
    private Image tempTitle = new Image("file:images/titleTemp.png", 0, 25, true, true);
    private Image precTitle = new Image("file:images/titlePrec.png", 0, 25, true, true);
    public List<StringProperty> windAvr = new ArrayList<>();
    public List<StringProperty> tempAvr = new ArrayList<>();
    public List<StringProperty> precAvr = new ArrayList<>();
    
    public List<List<StringProperty>> windSpeedScroll = new ArrayList<>();
	public List<List<StringProperty>> windDirectionScroll = new ArrayList<>();
    public List<List<StringProperty>> tempScroll = new ArrayList<>();
    public List<List<StringProperty>> precScroll = new ArrayList<>();
    public List<List<StringProperty>> humScroll = new ArrayList<>();
    public List<List<StringProperty>> uvScroll = new ArrayList<>();

    public void update(Location loc){
    	getData(loc.getName());
    	for(int i=0; i<data.length; i++) {
			//calculating average weather
			ThreeHourWeather[] todayData = data[i];
			Double temp = 0.0;
			Double wind = 0.0;
			Double prec = 0.0;
			for (ThreeHourWeather w : todayData) {
				temp += w.temperature();
				wind += w.windSpeed();
				prec += w.precipProb();
			}
			temp = ((double) Math.round(temp * 10/todayData.length)) / 10;
			wind = ((double) Math.round(wind * 10/todayData.length)) / 10;
			prec = ((double) Math.round(prec * 10/todayData.length)) / 10;
			
			if(tempAvr.size() > i){
				tempAvr.get(i).setValue(temp + "°C");
				windAvr.get(i).setValue(wind + "mph");
				precAvr.get(i).setValue(prec + "%");
			}
			
			//update ScrollPane
			for(int j=0; j<data[0].length; j++){
				windSpeedScroll.get(i).get(j).setValue(Integer.toString(todayData[i].windSpeed()));
				windDirectionScroll.get(i).get(j).setValue(todayData[i].direction());
				tempScroll.get(i).get(j).setValue(Integer.toString(todayData[i].temperature()));
				precScroll.get(i).get(j).setValue(Integer.toString(todayData[i].precipProb()) + "%");
				humScroll.get(i).get(j).setValue(Integer.toString(todayData[i].humidity()) + "%");
				uvScroll.get(i).get(j).setValue(Integer.toString(todayData[i].UV()));
			}

    	}    	
    }

    private TitledPane accord(Label day, int dd, int mm, int whichday){
		TitledPane oneTab = new TitledPane();
		oneTab.getStylesheets().add("file:css/accordion.css");
		
		//calculating average weather
		ThreeHourWeather[] todayData = data[whichday];
		Double temp = 0.0;
		Double wind = 0.0;
		Double prec = 0.0;
		for(ThreeHourWeather w : todayData){
			temp += w.temperature();
			wind += w.windSpeed();
			prec += w.precipProb();
		}
		temp = ((double) Math.round(temp * 10/todayData.length)) / 10;
		wind = ((double) Math.round(wind * 10/todayData.length)) / 10;
		prec = ((double) Math.round(prec * 10/todayData.length)) / 10;
		
	

		//Make a VBox
		VBox mainBox = new VBox();
		mainBox.setStyle("-fx-padding: 10");
		mainBox.setSpacing(10);
		
		//day, dd, mm
		HBox date = new HBox();
		Label ddmm = new Label(", " + dd + "/" + mm);
		date.getChildren().addAll(day,ddmm);

		//Put this HBox into the VBox above
	    HBox subBox = new HBox();
	    subBox.setSpacing(10);
	    subBox.setAlignment(Pos.CENTER);

	    subBox.getChildren().add(new ImageView(tempTitle));
	    Label tempp = new Label();
	    StringProperty tempProp = new SimpleStringProperty();
	    tempProp.setValue(temp + "°C");
	    tempp.textProperty().bind(tempProp);
	    subBox.getChildren().add(tempp);
	    tempAvr.add(tempProp);

	    subBox.getChildren().add(new ImageView(windTitle));
		Label windd = new Label();
		StringProperty windProp = new SimpleStringProperty();
		windProp.setValue(wind + "mph");
		windd.textProperty().bind(windProp);
	    subBox.getChildren().add(windd);
	    windAvr.add(windProp);

	    subBox.getChildren().add(new ImageView(precTitle));
	    Label precc = new Label();
	    StringProperty precProp = new SimpleStringProperty();
	    precProp.setValue(prec + "%");
	    precc.textProperty().bind(precProp);
	    subBox.getChildren().add(precc);
	    precAvr.add(precProp);
	    
	    
	    //Put Hboxes into VBox, VBox into oneTab
	    mainBox.getChildren().addAll(date, subBox);
	    oneTab.setGraphic(mainBox);
		return oneTab;
    }
    
    private void getData(String currentLocation){
    	TextData raw = null;
    	try {
			raw = new TextData(Integer.toString(Locations.getInstance().search(currentLocation)));
		} catch (MalformedURLException e) {
			System.out.println("weatherData load failed due to wrong URL");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("weatherData load failed due to IOException");
			e.printStackTrace();
		}
    	data = raw.data;
    }
    
    private ScrollPane content(int day){
    	ThreeHourWeather[] todayData = data[day];
    	List<StringProperty> windSpeedTemp = new ArrayList<>();
    	List<StringProperty> windDirTemp = new ArrayList<>();
    	List<StringProperty> tempTemp = new ArrayList<>();
    	List<StringProperty> precTemp = new ArrayList<>();
    	List<StringProperty> humTemp = new ArrayList<>();
    	List<StringProperty> uvTemp = new ArrayList<>();
    	windSpeedScroll.add(windSpeedTemp);
    	windDirectionScroll.add(windDirTemp);
    	tempScroll.add(tempTemp);
    	precScroll.add(precTemp);
    	humScroll.add(humTemp);
    	uvScroll.add(uvTemp);
    	
		ScrollPane base = new ScrollPane();
		base.getStyleClass().add("scroll-pane");
		base.setPannable(true);

		List<VBox> hours = new ArrayList<>();
		for(int i=0; i<todayData.length; i++){
			VBox hour = new VBox();
			hour.setSpacing(10);
			hour.setPrefWidth(120);
			hour.setMaxWidth(120);
			if(i%2 == 0){
				hour.getStyleClass().add("hour");
			}else{
				hour.getStyleClass().add("hour2");
			}
			
			List<Node> content = hour.getChildren();
			
			content.add(new Label(todayData[i].minutesAfterMidnight()/60 + ":00"));


			HBox wind = new HBox();
			wind.getStyleClass().add("transparent");
			ImageView windimg = new ImageView();
			windimg.setImage(new Image("file:images/wind.png", 0, 50, true, true));
			windimg.getStyleClass().add("windicon");
			wind.getChildren().add(windimg);

			Label wind_val = new Label();
			StringProperty windSpeedProp = new SimpleStringProperty();
			windSpeedProp.setValue(Integer.toString(todayData[i].windSpeed()));
			wind_val.textProperty().bind(windSpeedProp);
			windSpeedScroll.get(day).add(windSpeedProp);

			wind_val.getStyleClass().add("wind_val");
			wind.getChildren().add(wind_val);

			VBox windInfo = new VBox();
;			Label wind_msmt = new Label("mph");
			wind_msmt.getStyleClass().add("wind_msmt");
			windInfo.getChildren().add(wind_msmt);

            Label wind_dir = new Label();
            StringProperty windDirectionProp = new SimpleStringProperty();
            windDirectionProp.setValue(todayData[i].direction());
            wind_dir.textProperty().bind(windDirectionProp);
            windDirectionScroll.get(day).add(windDirectionProp);

			wind_dir.getStyleClass().add("wind_dir");
			windInfo.getChildren().add(wind_dir);
			
			wind.getChildren().add(windInfo);
			content.add(wind);



			HBox temp = new HBox();
			ImageView tempimg = new ImageView();
			tempimg.setImage(new Image("file:images/temp.png", 0, 50, true, true));
			tempimg.getStyleClass().add("tempicon");
			temp.getChildren().add(tempimg);

            Label temp_val = new Label();
            StringProperty tempProp = new SimpleStringProperty();
            tempProp.setValue(Integer.toString(todayData[i].temperature()));
            temp_val.textProperty().bind(tempProp);
            tempScroll.get(day).add(tempProp);

			temp_val.getStyleClass().add("temp_val");
			temp.getChildren().add(temp_val);
			Label temp_msmt = new Label("°C");
			temp_msmt.getStyleClass().add("temp_msmt");
			temp.getChildren().add(temp_msmt);
			content.add(temp);


			HBox prec = new HBox();
			prec.getStyleClass().add("transparent");
			ImageView precimg = new ImageView();
			precimg.setImage(new Image("file:images/prec.png", 0, 50, true, true));
			precimg.getStyleClass().add("precicon");
			prec.getChildren().add(precimg);

            Label prec_val = new Label();
            StringProperty precProp = new SimpleStringProperty();
            precProp.setValue(Integer.toString(todayData[i].precipProb()) + "%");
            prec_val.textProperty().bind(precProp);
            precScroll.get(day).add(precProp);

			prec_val.getStyleClass().add("small_val");
			prec.getChildren().add(prec_val);
			content.add(prec);


			HBox hum = new HBox();
			hum.getStyleClass().add("small_content");
			ImageView humimg = new ImageView();
			humimg.setImage(new Image("file:images/hum.png", 0, 50, true, true));
			humimg.getStyleClass().add("humicon");
			hum.getChildren().add(humimg);

            Label hum_val = new Label();
            StringProperty humProp = new SimpleStringProperty();
            humProp.setValue(Integer.toString(todayData[i].humidity()) + "%");
            hum_val.textProperty().bind(humProp);
            humScroll.get(day).add(humProp);

			hum_val.getStyleClass().add("small_val");
			hum.getChildren().add(hum_val);
			content.add(hum);


			HBox uv = new HBox();
			uv.getStyleClass().add("small_content");
			ImageView uvimg = new ImageView();
			uvimg.setImage(new Image("file:images/uv.png", 0, 50, true, true));
			uvimg.getStyleClass().add("uvicon");
			uv.getChildren().add(uvimg);

            Label uv_val = new Label();
            StringProperty uvProp = new SimpleStringProperty();
            uvProp.setValue(Integer.toString(todayData[i].UV()));
            uv_val.textProperty().bind(uvProp);
            uvScroll.get(day).add(uvProp);

			uv_val.getStyleClass().add("small_val");
			uv.getChildren().add(uv_val);
			content.add(uv);

			/*
			HBox pres = new HBox();
			ImageView presimg = new ImageView();
			presimg.setImage(new Image("file:images/pres.png", 0, 50, true, true));
			presimg.getStyleClass().add("presicon");
			pres.getChildren().add(presimg);
			Label pres_val = new Label(Integer.toString(*****pressure data not available*****));
			pres_val.getStyleClass().add("small_val");
			pres.getChildren().add(pres_val);
			content.add(pres);
			*/

			hours.add(hour);
		}

		HBox scrollContent = new HBox();
		scrollContent.getStyleClass().add("transparent");
		scrollContent.setSpacing(4);
		scrollContent.getChildren().addAll(hours);

		base.setContent(scrollContent);
		base.setVbarPolicy(ScrollBarPolicy.NEVER);
		base.setHbarPolicy(ScrollBarPolicy.NEVER);

		return base;
	}

    @FXML
    private void initialize(){
    	//get today
    	Calendar calendar = Calendar.getInstance();
    	
    	//set the days
       	days[0] = new Label("Sunday");
    	days[1] = new Label("Monday");
    	days[2] = new Label("Tuesday");
    	days[3] = new Label("Wednesday");
    	days[4] = new Label("Thursday");
    	days[5] = new Label("Friday");
    	days[6] = new Label("Saturday");
 

    	//set initial location to Cambridge for now
    	getData("Cambridge");
    	
    	for(int i=0; i<data.length; i++){
    		TitledPane oneDay = accord(days[calendar.get(Calendar.DAY_OF_WEEK)-1], calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE),i);
    		calendar.add(Calendar.DATE, 1);
    		
    		VBox fill = new VBox();
    		fill.setSpacing(10);
    		Label hourly = new Label("Hourly Information:");
    		hourly.getStyleClass().add("hourly_text");
    		fill.getChildren().addAll(hourly, content(i));
    		oneDay.setContent(fill);
    		accordion.getPanes().add(oneDay);
    		if(i==0){
    			accordion.setExpandedPane(oneDay);
    		}
    	}
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
    	Label temp_msmt = new Label("��C");
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
