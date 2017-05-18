package uk.ac.cam.cl.id.group18.task3;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.*;
import java.io.*;

public class Locations {
	public Locations() throws MalformedURLException, IOException{
		URL data = DataTools.request("val/wxfcs/all/json/sitelist");
		InputStream is = data.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String locationString = "";

		String line;
		while((line = br.readLine()) != null)
			locationString += line;

		br.close();
		is.close();

		System.out.println(locationString);
	}

	private Location[] parseString(String locationString){
		
	}

	public static void main(String[] args) throws IOException{
		try{
			Locations l = new Locations();
		} catch (MalformedURLException e){
			System.out.println("URL Malformed (uh-oh)");
		}
	}
}
