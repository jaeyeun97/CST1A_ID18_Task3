package uk.ac.cam.cl.id.group18.task3;

import javafx.scene.image.Image;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaeyeun on 17. 5. 20.
 */
public class MapImage {
    // private static Map<Location,Map<Date, MapImage>> maps = new HashMap<>();
    private Date date;
    private Location location;

    private Image cloudOverlayImage;
    private Image rainFallOverlayImage;
    private Image cloudAndRainOverlayImage;
    private Image temperatureOverlayImage;
    private Image pressureOverlayImage;

    public MapImage(Date date, Location location){
        this.date = date;
        this.location = location;
        // getImages??
        // if( !maps.containsKey(location) || !maps.get(location).containsKey(date) ){
        //     maps
        // }
    }
}
