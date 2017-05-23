//Datatools generates DataPoint urls based on a resource string.

package uk.ac.cam.cl.id.group18.task3;

import java.net.URL;
import java.net.MalformedURLException;
/**
 * Created by Leo Williams.
 * Written by Leo Williams.
 */
public class DataTools {
	private static String apiKey = "6f7a9f7a-964f-4f69-8da2-6cad5bc55adc";
	private static String requestURL = "http://datapoint.metoffice.gov.uk/public/data/";
	public static URL request(String resource) throws MalformedURLException{
		String fullURL = requestURL + resource + "?key=" + apiKey;
		URL requestedURL = new URL(fullURL);
		return requestedURL;
	}
}
