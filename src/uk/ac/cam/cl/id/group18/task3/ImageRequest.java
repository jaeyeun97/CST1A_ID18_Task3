package uk.ac.cam.cl.id.group18.task3;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.*;
import java.util.*;
import com.google.gson.*;

public class ImageRequest{

	public static String date = "";	

	public static Image getImage(String layer, String time, int timeStep) throws IOException{
		URL url = DataTools.request("layer/wxfcs/" + layer + "/png?RUN=" + time + "Z&FORECAST=" + timeStep + "&");

		Image image = ImageIO.read(url);

		return image;
	}
	

	//timeStep should be the number of hours into the future for the forecast. If it doesn't go that far forward,
	//A NullPointerException will be thrown. This is expected behaviour.
	//On success, it returns a map from MapType to Image.
	public static Map<MapType, Image> getLayers(int timeStep) throws IOException, MalformedURLException{
		URL url = DataTools.request("layer/wxfcs/all/json/capabilities");
		
		InputStream is = url.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String buildString = "";

		String line;
		while((line = br.readLine()) != null)
			buildString += line;

		br.close();
		is.close();

		String data = cleanup(buildString);
		System.out.println(data);
		
		JsonArray jArray = new Gson().fromJson(data, JsonArray.class);
		
		HashMap<MapType, Image> layers = new HashMap<>();

		for(int i = 0; i < jArray.size(); i++){
			JsonObject jObj = jArray.get(i).getAsJsonObject();
			
			String layer = jObj.getAsJsonPrimitive("LayerName").getAsString();
			
			JsonObject timeSteps = jObj.getAsJsonObject("Timesteps");
			String time = timeSteps.getAsJsonPrimitive("defaultTime").getAsString();
			date = time;

			Integer[] timeStepsArray = new Gson().fromJson(timeSteps.get("Timestep"), Integer[].class);

			timeStep = timeStepsArray[timeStep/timeStepsArray[1]]; //Forces timestep onto a suitable value, scaled by the first non-zero val
			
			MapType map;
			
			Image im = getImage(layer, time, timeStep);
			
			switch(layer) {
				case "Precipitation_Rate": map = MapType.RAINFALL;
							   layers.put(map, im);
							   break;
				case "Total_Cloud_Cover": map = MapType.CLOUD;
							  layers.put(map, im);
							  break;
				case "Total_Cloud_Cover_Precip_Rate_Overlaid": map = MapType.CLOUDANDRAIN;
									       layers.put(map, im);
									       break;
				case "Temperature": map = MapType.TEMP;
						    layers.put(map, im);
						    break;

				case "Atlantic": map = MapType.PRESSURE;
						 layers.put(map, im);
						 break;
			}
			

		}

		return layers;
	}

	private static String cleanup(String inputData){
		String partCleanedData = inputData.replaceAll("@", "");

		int start = partCleanedData.indexOf('[');
		int end = partCleanedData.lastIndexOf(']');

		String cleanedData = partCleanedData.substring(start, end + 1);

		return cleanedData;
	}
}
