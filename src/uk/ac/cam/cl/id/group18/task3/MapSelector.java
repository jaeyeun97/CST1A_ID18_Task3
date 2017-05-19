package uk.ac.cam.cl.id.group18.task3;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaeyeun on 17. 5. 19.
 */
public class MapSelector {
    private static Map<MapType, MapSelector> instances = new HashMap<>();
    private static Map<MapType, String> names = new HashMap<>();
    static {
        names.put(MapType.CLOUD, "Cloud");
        names.put(MapType.RAINFALL, "Rainfall");
        names.put(MapType.PRESSURE, "Pressure");
        names.put(MapType.TEMP, "Temperature");
    }
    private BooleanProperty selected = new SimpleBooleanProperty(false);
    private ReadOnlyStringProperty name;

    private MapSelector(String name) {
        this.name = new ReadOnlyStringWrapper(name);
    }

    public static Map<MapType, MapSelector> getInstances(){
        for(MapType type: MapType.values()){
            if(type != MapType.CLOUDANDRAIN && !instances.containsKey(type)){
                instances.put(type, new MapSelector(getName(type)));
            }
        }
        return instances;
    }

    public static MapSelector getInstance(MapType type){
        if(type != MapType.CLOUDANDRAIN && !instances.containsKey(type)){
            instances.put(type, new MapSelector(getName(type)));
        }
        return instances.get(type);
    }

    private static String getName(MapType type){
        return names.get(type);
    }

    public static MapSelector getFromName(String s){
        MapSelector instance = null;
        for(MapType type: MapType.values()){
            if(names.containsKey(type) && names.get(type).equals(s)){
                instance = getInstance(type);
            }
        }
        return instance;
    }

    public static ObservableList<MapSelector> getObservableList(){
        return FXCollections.observableArrayList(getInstances().values());
    }

    public BooleanProperty getSelectedProperty(){
        return this.selected;
    }

    public boolean isSelected(){
        return this.selected.get();
    }

    public void setSelected(boolean prop){
        this.selected.setValue(prop);
    }

    public ReadOnlyStringProperty getStringProperty(){
        return this.name;
    }

    public String getName(){
        return this.name.get();
    }
}
