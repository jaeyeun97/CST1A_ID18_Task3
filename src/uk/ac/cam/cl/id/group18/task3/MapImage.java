package uk.ac.cam.cl.id.group18.task3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaeyeun on 17. 5. 20.
 *
 * So for this class, I guess we will just have to zoom in to the met office data and present it.
 */
public class MapImage {
    // private static Map<Location,Map<Date, MapImage>> maps = new HashMap<>();
    private Date date;

    private Map<MapType, Image> overlayImages = new HashMap<>();

    public MapImage(Date date){
        this.date = date;
    }

    public Image getOverlayImage(MapType type){
        if(overlayImages.containsKey(type)){
            return overlayImages.get(type);
        } else {
            return null;
        }
    }

    public ImageView getOverlayImageView(int zoom, MapType type){
        double width = 256.0 * (17.0 / 360) * Math.pow(2.0, (double) zoom);
        double height = 256.0 * (13.0 / 360) * Math.pow(2.0, (double) zoom);

        ImageView im = new ImageView(getOverlayImage(type));
        im.setFitHeight(height);
        im.setFitWidth(width);

        return im;
    }

    public AnchorPane getImageAnchorPane(double lat, double lon, int zoom, MapType type){
        ImageView im = getOverlayImageView(zoom, type);
        AnchorPane pane = new AnchorPane();
        pane.setPrefHeight(800);
        pane.setPrefWidth(960);

        double width = im.getFitWidth();
        double height = im.getFitHeight();

        double leftOffset = -1.0 * 256.0 * ((lat + 3.5) / 360) * Math.pow(2.0, (double) zoom) - ((width-960) / 2.0);
        double topOffset = -1.0 * 256.0 * ((54.5 - lon) / 360) * Math.pow(2.0, (double) zoom) - ((height-800) / 2.0);

        pane.setTopAnchor(im, topOffset);
        pane.setLeftAnchor(im, leftOffset);
        pane.getChildren().add(im);

        return pane;
    }
}
