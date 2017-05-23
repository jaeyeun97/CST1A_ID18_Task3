package uk.ac.cam.cl.id.group18.task3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import np.com.ngopal.control.AutoFillTextBox;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import uk.ac.cam.cl.id.group18.task3.Location;

/**
 * Created by Charles Yoon on 17. 5. 16.
 * Written by Charles Yoon, Seohyun Woo, Theodora Zamfirache.
 *
 * Seo:
 * the autocomplete box is not working completely, but cannot fix it -
 * even looked at the source code but seems like I cannot change the behaviour
 * for more information, look at https://github.com/privatejava/javafx-autocomplete-field for its code
 * and http://blog.ngopal.com.np/2011/07/04/autofill-textbox-with-filtermode-in-javafx-2-0-custom-control/comment-page-3/#comments
 * for what it's supposed to be
 */

public class MapController {
    @FXML
    private Pane mainPane;
    @FXML
    private HBox searchBar;
    @FXML
    private Slider mapSlider;
    @FXML
    private ListView<MapSelector> tickBox;
    @FXML
    private StackPane mapImagePane;
    @FXML
    private Slider zoomSlider;

    // private Button returnCurrent;

    private ComboBox<Location> box;
    
    private void setSearchBar(){
        //DATA 
        ObservableList data = FXCollections.observableArrayList();
        Locations loc = Locations.getInstance();
		if(loc == null){
			System.out.println("location load failed for autoFillSearchBar");
		}
        for(int j=0; j<loc.locations.length; j++){
            data.add(loc.locations[j]);
        } 
        Collections.sort(data, new Comparator<Location>(){
        	@Override
        	public int compare(Location l1, Location l2){
        		return l1.toString().compareTo(l2.toString());
        	}
        });
        
        //Layout 
        searchBar.setSpacing(10); 
        
        //AutoFillBox
        box = new ComboBox(data);
        box.setValue(Locations.getInstance().getLocation(310042));
        //AutoFillTextBox box = new AutoFillTextBox(data);
        box.setPrefWidth(450);
        //box.setFilterMode(true);
        
        //Label 
        Image search = new Image("file:images/search.png", 0, 40, true, true);
        
        //Button
        //returnCurrent = new Button("Search");
        /*
        Image buttonimg = new Image("file:images/search3.png");
        ImageView buttonimgv = new ImageView(buttonimg);
        returnCurrent = new Button("", new ImageView(buttonimg));
        returnCurrent.setMaxHeight(Double.MAX_VALUE);
        returnCurrent.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println(box.getValue()); //this gives you the corresponding Location object, not just string
                //System.out.println(box.getText());  -> for autofillbox, might need to use a map to return Location object
            }
        });
        */

        
        //put them into HBox
        searchBar.getChildren().add(box);
        searchBar.setMaxHeight(5);
    }
        
    @FXML
    private void initialize() throws IOException {
        // Search Bar
        setSearchBar();

        Location defaultLocation = box.getValue();
        MapImages.setup((int) mapSlider.getValue(),(int) zoomSlider.getValue(), defaultLocation);
        // Pane Set
        Rectangle clip = new Rectangle(960, 800);
        clip.setLayoutX(0);
        clip.setLayoutY(0);
        mapImagePane.setClip(clip);
        mapImagePane.getChildren().setAll(MapImages.getObservableList());

        // Link MapSelectors to MapImages
        for(MapType type : MapType.values()){
            MapSelector.getInstance(type).getSelectedProperty().bindBidirectional(
                    MapImages.getImagePane(type).visibleProperty()
            );
        }
        // Tick Box
        tickBox.setItems(MapSelector.getObservableList());
        tickBox.setCellFactory(CheckBoxListCell.forListView(MapSelector::getSelectedProperty,
                new StringConverter<MapSelector>() {
            @Override
            public String toString(MapSelector ms) {
                return ms.getName();
            }

            @Override
            public MapSelector fromString(String string) {
                return MapSelector.getFromName(string);
            }
        }));

        // Map Slider
        mapSlider.valueProperty().addListener(
                (observable, oldVal, newVal) -> mapSlider.setValue(((int) Math.round(newVal.doubleValue()/3))*3)
        );
        mapSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double d) {
                return d.intValue() + "hours";
            }

            @Override
            public Double fromString(String string) {
                return null;
            }
        });
        mapSlider.valueProperty().addListener((observable, oldVal, newVal) -> {
            try {
                MapImages.updateTimestep(newVal.intValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // Zoom Slider
        zoomSlider.valueProperty().addListener(
                (observable, oldVal, newVal) -> zoomSlider.setValue(((int) Math.round(newVal.doubleValue())))
        );
        zoomSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                MapImages.updateZoom(newValue.intValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        box.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                MapImages.updateLocation(newValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
