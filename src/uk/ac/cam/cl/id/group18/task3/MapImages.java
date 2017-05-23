package uk.ac.cam.cl.id.group18.task3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaeyeun on 17. 5. 22.
 */
public class MapImages {
    private static Map<Integer, MapImage> images = new HashMap<>();
    private static Map<Location, Map<Integer, OpenStreetMap>> streetMaps = new HashMap<>();
    private static Map<MapType, AnchorPane> imagePanes = new HashMap<>();
    private static Map<MapType, ImageView> imageViews = new HashMap<>();

    private static int currentTimestep;
    private static int currentZoom ;
    private static Location currentLocation;
    private static ImageView streetMapView = new ImageView();

    public static void setup(int timestep, int zoom, Location location){
        currentLocation = location;
        currentTimestep = timestep;
        currentZoom = zoom;
    }

    public static MapImage getOverlayImage(int timestep) throws IOException {
        if(images.containsKey(timestep)){
            return images.get(timestep);
        } else {
            MapImage newImage = new MapImage(timestep);
            images.put(timestep, newImage);
            return newImage;
        }
    }

    public static ImageView getImageView(MapType type) throws IOException {
        if(imageViews.containsKey(type)){
            return imageViews.get(type);
        } else {
            ImageView im = new ImageView(getOverlayImage(currentTimestep).getOverlayImage(type));
            imageViews.put(type, im);
            if(type == MapType.TEMP){
                im.setOpacity(0.2);
            } else {
                im.setOpacity(0.7);
            }
            calculateImageView(type);
            return im;
        }
    }

    public static void calculateImageView(MapType type) throws IOException {
        ImageView im = getImageView(type);
        double width = 256.0 * (17.0 / 360) * Math.pow(2.0, (double) currentZoom);
        double height = 256.0 * (13.0 / 360) * Math.pow(2.0, (double) currentZoom);
        im.setFitHeight(height);
        im.setFitWidth(width);
    }

    public static AnchorPane getImagePane(MapType type) throws IOException {
        if(imagePanes.containsKey(type)){
            return imagePanes.get(type);
        } else {
            AnchorPane pane = new AnchorPane();
            pane.setPrefHeight(800);
            pane.setPrefWidth(960);
            pane.setVisible(false);
            imagePanes.put(type, pane);
            calculateImagePane(type);
            return pane;
        }
    }

    public static void calculateImagePane(MapType type) throws IOException {
        AnchorPane pane = getImagePane(type);
        ImageView im = getImageView(type);

        double width = im.getFitWidth();
        double height = im.getFitHeight();
        double lat = currentLocation.getLatitude();
        double lon = currentLocation.getLongitude();

        double leftOffset = -1.0 * 256.0 * ((lon + 3.5) / 360) * Math.pow(2.0, (double) currentZoom) - ((width-960) / 2.0);
        double topOffset = -1.0 * 256.0 * ((54.5 - lat) / 360) * Math.pow(2.0, (double) currentZoom) - ((height-800) / 2.0);

        pane.setTopAnchor(im, topOffset);
        pane.setLeftAnchor(im, leftOffset);

        if(!pane.getChildren().contains(im)){
            pane.getChildren().add(im);
        }
    }

    public static OpenStreetMap getOpenStreetMap() throws IOException {
        if (!streetMaps.containsKey(currentLocation)){
            streetMaps.put(currentLocation, new HashMap<>());
        }
        if (!streetMaps.get(currentLocation).containsKey(currentZoom)){
            double lat = currentLocation.getLatitude();
            double lon = currentLocation.getLongitude();
            streetMaps.get(currentLocation).put(currentZoom, new OpenStreetMap(lat, lon, currentZoom));
        }
        return streetMaps.get(currentLocation).get(currentZoom);
    }

    public static ImageView getOpenStreetMapView() throws IOException {
        updateOpenStreetMapView();
        return streetMapView;
    }

    public static void updateOpenStreetMapView() throws IOException {
        streetMapView.setImage(getOpenStreetMap().getImage());
    }

    public static void updateZoom(int zoom) throws IOException {
        currentZoom = zoom;
        updateOpenStreetMapView();
        for(MapType type : MapType.values()){
            calculateImageView(type);
            calculateImagePane(type);
        }
    }

    public static void updateLocation(Location location) throws IOException {
        currentLocation = location;
        updateOpenStreetMapView();
        for(MapType type : MapType.values()){
            calculateImagePane(type);
        }
    }

    public static void updateTimestep(int timestep) throws IOException {
        currentTimestep = timestep;
        for(MapType type: MapType.values()){
            getImageView(type).setImage(getOverlayImage(timestep).getOverlayImage(type));
        }
    }

    public static ObservableList<Node> getObservableList() throws IOException {
        ObservableList<Node> list = FXCollections.observableArrayList();
        list.add(getOpenStreetMapView());
        for(MapType type : MapType.values()){
            list.add(getImagePane(type));
        }
        return list;
    }
}
