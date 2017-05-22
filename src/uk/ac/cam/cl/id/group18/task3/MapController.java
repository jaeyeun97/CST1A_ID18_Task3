//the autocomplete box is not working completely, but cannot fix it - 
//even looked at the source code but seems like I cannot change the behaviour
//for more information, look at https://github.com/privatejava/javafx-autocomplete-field for its code
//and http://blog.ngopal.com.np/2011/07/04/autofill-textbox-with-filtermode-in-javafx-2-0-custom-control/comment-page-3/#comments
//for what it's supposed to be

package uk.ac.cam.cl.id.group18.task3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import np.com.ngopal.control.AutoFillTextBox;
import uk.ac.cam.cl.id.group18.task3.Locations;
import uk.ac.cam.cl.id.group18.task3.AutoComplete;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by jaeyeun on 17. 5. 16.
 */
public class MapController {
    @FXML
    private Pane mainPane;
    @FXML
    private HBox searchBar;
    @FXML
    private ListView searchResult;
    @FXML
    private ListView<MapSelector> tickBox;
    @FXML
    private StackPane mapImagePane;
    
    private Button returnCurrent;
    
    private void setSearchBar(){
        //DATA 
        ObservableList data = FXCollections.observableArrayList();
        Locations loc = null;
        try {
			loc = new Locations();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(loc == null){
			System.out.println("location load failed for autoFillSearchBar");
		}
        String[] s = loc.locationNames();
        for(int j=0; j<s.length; j++){ 
            data.add(s[j]); 
        } 
        
        //Layout 
        searchBar.setSpacing(10); 
        
        //AutoFillBox
        AutoFillTextBox box = new AutoFillTextBox(data);
        box.setPrefWidth(450);
        box.setFilterMode(true);
        
        //Label 
        Image search = new Image("file:images/search.png", 0, 20, true, true);
        //Button
        returnCurrent = new Button("Search");
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

        OpenStreetMap m = new OpenStreetMap(54.5, -3.5, 6);
        ImageView i = m.getImageView();
        mapImagePane.getChildren().add(i);
    }
}
