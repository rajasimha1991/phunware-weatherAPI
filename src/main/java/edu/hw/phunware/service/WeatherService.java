package edu.hw.phunware.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import edu.hw.phunware.database.DBOperations;

public class WeatherService {
	
	public Response getWeatherData(String zipcode) {
		//create a database connection
		//lookup the zipcode in the database and handle exceptions
		//construct the WeatherData from the fields and return it
		
		try {
			String result  = DBOperations.getInstance().getDataforZipcode(zipcode);
			return Response.status(200).entity(result).build();
		}
		catch(Exception e) {
			return Response.status(500).entity("error with database connection").build();
		}
	}
	
	public List<Response> getWeatherData(List<String> zipcodes){
		
		List<Response> result = new ArrayList<>();
		
		for(String zip: zipcodes) {
			Response res = getWeatherData(zip);
			result.add(res);
		}
		
		return result;
	}

}
