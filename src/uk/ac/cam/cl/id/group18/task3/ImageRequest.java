package uk.ac.cam.cl.id.group18.task3;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.*;
import java.util.*;

public class ImageRequest{
	public static Image getImage(String layer, String time, String timeStep) throws IOException{
		URL url = DataTools.request("layer/wxfcs/" + layer + "/png?RUN=" + time + "Z&FORECAST=" + timeStep + "&");

		Image image = ImageIO.read(url);

		return image;
	}

	public static Map<Integer, Map<MapType, Image>> getLayers() throws IOException, MalformedURLException{
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

		return null;
	}

	private static String cleanup(String inputData){
		String partCleanedData = inputData.replaceAll("@", "");

		int start = partCleanedData.indexOf('[');
		int end = partCleanedData.lastIndexOf(']');

		String cleanedData = partCleanedData.substring(start, end + 1);
		return cleanedData;
	}

	public static void main(String[] args) throws MalformedURLException, IOException{
		ImageRequest.getLayers();
	}
}
