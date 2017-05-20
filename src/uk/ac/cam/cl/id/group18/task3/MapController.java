package uk.ac.cam.cl.id.group18.task3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;

/**
 * Created by jaeyeun on 17. 5. 16.
 */
public class MapController {
    @FXML
    private Pane mainPane;
    @FXML
    private TextField searchBar;
    @FXML
    private ListView searchResult;
    @FXML
    private ListView<MapSelector> tickBox;
    @FXML
    private Button returnCurrent;
    @FXML
    private StackPane mapImagePane;

    @FXML
    private void initialize(){
        searchResult.setVisible(false);
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

    }
}
