package uk.ac.cam.cl.id.group18.task3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;

import java.io.IOException;

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
    private void initialize() throws IOException {
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

        OpenStreetMap m = new OpenStreetMap(54.5, -3.5, 6);
        ImageView i = m.getImageView();
        mapImagePane.getChildren().add(i);
    }
}
