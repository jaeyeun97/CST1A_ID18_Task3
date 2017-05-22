package uk.ac.cam.cl.id.group18.task3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaeyeun on 17. 5. 20.
 *
 * So for this class, I guess we will just have to zoom in to the met office data and present it.
 */
public class MapImage {
    private int timestep;
    private Map<MapType, Image> overlayImages = new HashMap<>();

    public MapImage(int timestep) throws IOException {
        this.timestep = timestep;
        this.overlayImages = ImageRequest.getLayers(timestep);
    }

    public Image getOverlayImage(MapType type){
        if(overlayImages.containsKey(type)){
            return overlayImages.get(type);
        } else {
            return null;
        }
    }
}
