package uk.ac.cam.cl.id.group18.task3;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.*;
import java.io.*;
import com.google.gson.*;

//Initialising the object fetches the data.
//
//Methods:
//  
//  locationNames():
//    returns a String[] containing names of the locations
//    made available by the api
//
//  search(String name):
//    returns the id of a site with that name (undefined
//    behaviour with multiple sites with same name)

public class Locations {

	public Location[] locations;
	private Map<Integer, Location> locationMap = new HashMap<>();
	private int pointer;
	private HashMap<String, Integer> map;
	private static Locations instance = null;

	public static Locations getInstance() {
		if(instance == null){
            try {
                instance = new Locations();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return instance;
	}

	private Locations() throws IOException{
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

		map = new HashMap<>();
		pointer = 0;
		
		for(int i = 0; i < locations.length; i++){
		    locationMap.put(locations[i].getID(), locations[i]);
		    map.put(locations[i].toString(), locations[i].getID());
        }


		
	}

	public Location getLocation(int id){
	    return locationMap.get(id);
    }
	
	//changed this bit and the iteration in the contructor for now, you can change it later leo
	public int search(String name){
		for(Map.Entry<String, Integer> e : map.entrySet()){
			if(e.getKey().equals(name)){
				return e.getValue();
			}
		}
		throw new NullPointerException();
		/*
		while(pointer < locations.length){
			if(locations[pointer].getName() == name){
				map.put(locations[pointer].getName(), locations[pointer].getID());
				pointer++;
				return locations[pointer - 1].getID();
			} else {
				map.put(locations[pointer].getName(), locations[pointer].getID());
				pointer++;
			}
		}
		throw new NullPointerException();
		*/
	}

	private Location[] parseString(String locationString){
		Location[] locationArray = new Gson().fromJson(locationString, Location[].class);
		return locationArray;
	}

	public String[] locationNames(){
		//I changed it a bit for now cause the original code gave me java.lang.ArrayStoreException
		String[] s = new String[locations.length];
		for(int i=0; i<locations.length; i++){
			s[i] = locations[i].toString();
		}
		return s;
		//return Arrays.copyOf(locations, locations.length, String[].class);
	}
}
