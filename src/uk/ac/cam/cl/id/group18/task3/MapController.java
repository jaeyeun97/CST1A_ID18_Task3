//the autocomplete box is not working completely, but cannot fix it - 
//even looked at the source code but seems like I cannot change the behaviour
//for more information, look at https://github.com/privatejava/javafx-autocomplete-field for its code
//and http://blog.ngopal.com.np/2011/07/04/autofill-textbox-with-filtermode-in-javafx-2-0-custom-control/comment-page-3/#comments
//for what it's supposed to be

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
import javafx.util.StringConverter;
import np.com.ngopal.control.AutoFillTextBox;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaeyeun on 17. 5. 16.
 */
public class MapController {
    @FXML
    private Pane mainPane;
    @FXML
    private HBox searchBar;
    @FXML
    private Slider mapSlider;
    @FXML
    private ListView searchResult;
    @FXML
    private ListView<MapSelector> tickBox;
    @FXML
    private StackPane mapImagePane;
    
    private Button returnCurrent;
    
    private void setSearchBar(){
        //DATA 
    	Map<String, Location> name2Loc = new HashMap<>();
        ObservableList data = FXCollections.observableArrayList();
        Locations loc = Locations.getInstance();
		if(loc == null){
			System.out.println("location load failed for autoFillSearchBar");
		}
        for(int j=0; j<loc.locations.length; j++){
            data.add(loc.locations[j]);
        } 
        
        //Layout 
        searchBar.setSpacing(10); 
        
        //AutoFillBox
        ComboBox box = new ComboBox(data);
        //AutoFillTextBox box = new AutoFillTextBox(data);
        box.setPrefWidth(450);
        //box.setFilterMode(true);
        
        //Label 
        Image search = new Image("file:images/search.png", 0, 20, true, true);
        
        //Button
        returnCurrent = new Button("Search");
        returnCurrent.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println(box.getValue()); //this gives you the corresponding Location object, not just string
                //System.out.println(box.getText());  -> for autofillbox, might need to use a map to return Location object
            }
        });

        
        //put them into HBox
        searchBar.getChildren().addAll(new ImageView(search), box, returnCurrent); 
    }
        
    @FXML
    private void initialize() throws IOException {
        searchResult.setVisible(false);
        // Search Bar
        setSearchBar();
        
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

        OpenStreetMap m = MapImages.getOpenStreetMap();
        ImageView i = m.getImageView();
        mapImagePane.getChildren().add(i);
        AnchorPane p = MapImages.getImagePane(MapType.CLOUD);
        mapImagePane.getChildren().add(p);

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
        });
    }
}
