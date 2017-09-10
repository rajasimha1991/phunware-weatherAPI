package edu.hw.phunware.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.hw.phunware.service.WeatherService;

@Path("weather")
public class WeatherResource {
	
	@QueryParam("zipcode") private String zipCode;
	
	WeatherService weatherService =  new WeatherService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWeatherData() {
		Response response = weatherService.getWeatherData(zipCode);
		return response;
	}
}
