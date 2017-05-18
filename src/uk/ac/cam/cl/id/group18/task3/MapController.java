package uk.ac.cam.cl.id.group18.task3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;

/**
 * Created by jaeyeun on 17. 5. 16.
 */
public class MapController {
    @FXML
    private TextField searchBar;
    @FXML
    private ListView searchResult;
    @FXML
    private ListView<CheckBoxListCell> tickBox;
    @FXML
    private Button returnCurrent;

    @FXML
    private void initialize(){
        searchResult.setVisible(false);
        //Create Checkbox cells
        CheckBoxListCell cloudBox = new CheckBoxListCell();
    }
}
