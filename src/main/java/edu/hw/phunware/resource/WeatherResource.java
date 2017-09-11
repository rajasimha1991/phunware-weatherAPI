package edu.hw.phunware.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.hw.phunware.service.WeatherService;

@Path("weather")
@Produces(MediaType.APPLICATION_JSON)
public class WeatherResource {
	
	@QueryParam("zipcode") private String zipCode;
	
	WeatherService weatherService =  new WeatherService();
	
	@GET
	public Response getWeatherData() {
		try {
			String results = weatherService.getWeatherData(zipCode);
			JSONArray resp = new JSONArray(results);
			JSONObject response = new JSONObject();
			response.put("result", resp);
			return Response.status(200)
					.entity(response.toString())
					.build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return Response.status(500)
					.entity("error while processing request")
					.build();
		}
	} 
}
