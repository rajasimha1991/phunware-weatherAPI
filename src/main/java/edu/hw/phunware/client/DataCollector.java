package edu.hw.phunware.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.hw.phunware.database.DBOperations;
import edu.hw.phunware.model.WeatherData;

public class DataCollector {

	public static void main(String[] args) throws UnsupportedEncodingException{
		
		BufferedReader br = null;
		Client client = ClientBuilder.newClient();
		
		final String COMMA_DELIMITER = ",";
		
		try {
			br = new BufferedReader(new FileReader("zipcodes-test.csv"));
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] details = line.split(COMMA_DELIMITER);
				String Zipcode="", city="";
				
				if(details.length > 1) {
					Zipcode = details[0];
					city = details[1];
				}
				System.out.println(Zipcode + " " + city);
				
				String baseurl = "https://query.yahooapis.com/v1/public/yql?";
				String yql_query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\""+city+", tx\")";
				String yql_url = baseurl +"q="+ URLEncoder.encode(yql_query, "UTF-8")+"&format=json";
				System.out.println(yql_url);
				
				Response response = client.target(yql_url).request(MediaType.APPLICATION_JSON).get();
				String body = response.readEntity(String.class);
				
				JSONObject resp = new JSONObject(body);
				JSONArray data = resp.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast");
				
				String highTemp = data.getJSONObject(0).getString("high");
				String lowTemp = data.getJSONObject(0).getString("low");
				String forecast = data.getJSONObject(0).getString("text");
					
				WeatherData wd = new WeatherData(Zipcode, city, lowTemp, highTemp, forecast);
				DBOperations.getInstance().writeData(wd);
			}
		}
		catch (FileNotFoundException fe) 
		{
			fe.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try
            {
                br.close();
                client.close();
            }
            catch(IOException ie)
            {
                System.out.println("Error occured while closing the BufferedReader");
                ie.printStackTrace();
            }
		}
		
	}

}
