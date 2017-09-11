package edu.hw.phunware.service;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.hw.phunware.database.DBOperations;

public class WeatherService {
	
	public String getWeatherData(String zipcode) {
		
		String[] arr = zipcode.split(",");
		return getWeatherData(arr);
	}
	
	public String getWeatherData(String[] zipcodes){
		
		JSONArray result = new JSONArray();
		for(String zip: zipcodes) {
			zip = zip.trim();
			String res = getWeatherDataforZipcode(zip);
			JSONObject data = new JSONObject(res);
			result.put(data);
		}
		
		return result.toString();
	}
	
	private String getWeatherDataforZipcode(String zip) {
		
		try {
			String result  = DBOperations.getInstance().getDataforZipcode(zip);
			return result;
		}
		catch(Exception e) {
			return "error with database connection";
		}
	}

}
