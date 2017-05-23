package uk.ac.cam.cl.id.group18.task3;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import com.google.gson.Gson;
/**
 * Created by Leo Williams.
 * Written by Leo Williams.
 */
//TextData
//
//This class allows you to obtain text data about a location.
//
//The constructor takes one argument: the id string
//This can be found by searching for the location by name using Locations.search();
//
//Once constructed, a public variable TextData.data is available.
//TextData.data is a two dimensional array of ThreeHourWeather
//
//TextData.data[day][i] is the i'th available ThreeHourWeather data in the day'th
//day from now (day 0 is today)
//
//See the ThreeHourWeather class for what information is available from a 
//ThreeHourWeather.


public class TextData{

	private String id;

	public TextData(String LocationID) throws MalformedURLException, IOException{
		id = LocationID;

		URL jsonSource  = DataTools.request("val/wxfcs/all/json/" + id + "?res=3hourly&");
		
		InputStream is = jsonSource.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String buildString = "";

		String line;
		while((line = br.readLine()) != null)
			buildString += line;

		br.close();
		is.close();

		data = parseString(buildString);
	}
	
	public ThreeHourWeather[][] data;

	private ThreeHourWeather[][] parseString(String jsonData){
		
		Pattern p = Pattern.compile("\\[([^\\[]*?)\\]}"); //Arcane String splitting regex
		Matcher m = p.matcher(jsonData); //Ok, the above works by finding any substring that
		//starts with a [
		//is followed by any number of characters that aren't [
		//and ends with a ]
		
		m.find(); //Throw away that first one that describes the api
		//Like, we know already.
		
		m.find(); //Get the first 'day'

		int i = 1;
		String[] jsonDays = new String[1];
		jsonDays[0] = "[" + m.group(1) + "]"; //the pattern matching removes [] for some reason
		
		while(m.find()){
			i++;
			jsonDays = Arrays.copyOf(jsonDays, i); //extend the array
			jsonDays[(i-1)] = "[" + m.group(1) + "]";
		}
		
		ThreeHourWeather[][] days = new ThreeHourWeather[i][];

		for(int j = 0; j < i; j++){
			ThreeHourWeather[] day = new Gson().fromJson(jsonDays[j], ThreeHourWeather[].class);
			days[j] = day;
		}

		return days;

	}
}
