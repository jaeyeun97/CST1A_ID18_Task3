package uk.ac.cam.cl.id.group18.task3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jaeyeun on 17. 5. 22.
 */
public class OpenStreetMap {
    private static final String baseURL = "http://staticmap.openstreetmap.de/staticmap.php";
    private double lat;
    private double lon;
    private int zoom;
    private Image i;

    public OpenStreetMap(double lat, double lon, int zoom) throws IOException {
        this.lat = lat;
        this.lon = lon;
        this.zoom = zoom;

        URLConnection conn = new URL(getFullURL()).openConnection();
        InputStream stream = conn.getInputStream();
        i = new Image(stream);
    }

    private String getFullURL(){
        return baseURL + String.format("?center=%.7f,%.7f&zoom=%d&size=960x800&maptype=mapnik", lat, lon, zoom);
    }

    public Image getImage(){
        return i;
    }
}
