package uk.ac.cam.cl.id.group18.task3;

import java.util.*;
import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Location{
	private String elevation;
	private String id;
	private double latitude;
	private double longitude;
	private String name;
	private String region;
	private String unitaryAuthArea;

	public Location(String jElevation, String jId, String jLatitude, String jLongitude, String jName, String jRegion, String jUnitaryAuthArea){
		elevation = jElevation;
		id = jId;
		latitude = Double.parseDouble(jLatitude);
        longitude = Double.parseDouble(jLongitude);
		name = jName;
		region = jRegion;
		unitaryAuthArea = jUnitaryAuthArea;
	}

	public String getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}

	public StringProperty getStringProperty() { return new SimpleStringProperty(name); }

	public String toString(){
		return name;
	}

	public double getLatitude() { return this.latitude; }

    public double getLongitude() { return this.longitude; }
}
