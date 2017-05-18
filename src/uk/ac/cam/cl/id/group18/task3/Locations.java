package uk.ac.cam.cl.id.group18.task3;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.*;
import java.io.*;
import com.google.gson.*;

//Initialising the object fetches the data.
//the method locationNames returns an array of strings.

public class Locations {

	public Location[] locations;

	public Locations() throws MalformedURLException, IOException{
		URL data = DataTools.request("val/wxfcs/all/json/sitelist");
		InputStream is = data.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String buildString = "";

		String line;
		while((line = br.readLine()) != null)
			buildString += line;

		br.close();
		is.close();
		
		String locationString = buildString.substring(buildString.indexOf("["), buildString.indexOf("]") + 1);

		locations = parseString(locationString);
	}

	private Location[]  parseString(String locationString){
		Location[] locationArray = new Gson().fromJson(locationString, Location[].class);
		return locationArray;
	}

	public String[] locationNames(){
		return Arrays.copyOf(locations, locations.length, String[].class);
	}
}
