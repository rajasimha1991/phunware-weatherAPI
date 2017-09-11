package edu.hw.phunware.service;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.hw.phunware.database.DBOperations;

public class WeatherService {
	
	public JSONArray getWeatherData(String zipcode) {
		
		String[] arr = zipcode.split(",");
		return getWeatherData(arr);
	}
	
	public JSONArray getWeatherData(String[] zipcodes){
		
		JSONArray result = new JSONArray();
		for(String zip: zipcodes) {
			String res = getWeatherDataforZipcode(zip);
			JSONObject data = new JSONObject(res);
			result.put(data);
		}
		
		return result;
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
