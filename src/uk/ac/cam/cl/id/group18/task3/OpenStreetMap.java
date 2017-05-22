package uk.ac.cam.cl.id.group18.task3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jaeyeun on 17. 5. 22.
 */
public class OpenStreetMap {
    private static final String baseURL = "http://staticmap.openstreetmap.de/staticmap.php";
    private Image i;

    public OpenStreetMap(double lat, double lon, int zoom) throws IOException {
        URLConnection conn = new URL(getFullURL(lat, lon, zoom)).openConnection();
        InputStream stream = conn.getInputStream();
        i = new Image(stream);
    }

    private String getFullURL(double lat, double lon, int zoom){
        return baseURL + String.format("?center=%.7f,%.7f&zoom=%d&size=960x800&maptype=mapnik", lat, lon, zoom);
    }

    public ImageView getImageView(){
        ImageView im = new ImageView(this.i);
        im.setFitWidth(960);
        im.setFitHeight(800);
        return im;
    }
}
