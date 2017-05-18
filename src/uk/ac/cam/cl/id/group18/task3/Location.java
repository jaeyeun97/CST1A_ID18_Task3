package uk.ac.cam.cl.id.group18.task3;

import java.util.*;

public class Location{
	private String name;
	private String id;
	private String[] data;
	public Location(String jsonid, String jsonname, String[] jsondata){
		name = jsonname;
		id = jsonid;
		data = jsondata;
	}

	public String getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}

	public String[] getData(){
		return data;
	}
}
