package edu.hw.phunware.database;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.hw.phunware.model.WeatherData;

public class DBOperations {
	
	private static DBOperations instance = null;
	
	private DBOperations(){
		
	}
	
	static{
		instance = new DBOperations();
	}

	public static DBOperations getInstance() {
		return instance;
	}
	
	public String getDataforZipcode(String zipcode) {
		
		MongoClient mongoClient = null;
		
		try {
			mongoClient = DBConnection.getInstance().getConnection();
			MongoDatabase database = mongoClient.getDatabase("db_weather");
			MongoCollection<Document> collection = database.getCollection("weatherCollection");
			Document doc = collection.find(eq("Zipcode",zipcode)).first();
			
			if(doc == null) {
				
				return new JSONObject().put("result", "No data for the input").toString();
			}
			
			doc.remove("_id");
			
			JSONObject result = new JSONObject();
			result.put("result", doc);
			return result.toString();
			
		}
		catch(Exception me) {
			me.printStackTrace();
			return "error in database connection";
		}
		finally {
			
		}
	}
	
	public void writeData(WeatherData wd) {
		
		MongoClient mongoClient = null;
		
		try {
			mongoClient = DBConnection.getInstance().getConnection();
			MongoDatabase database = mongoClient.getDatabase("db_weather");
			MongoCollection<Document> collection = database.getCollection("weatherCollection");
			
			Document document = new Document("Zipcode", wd.getZipCode())
					.append("CityName", wd.getCityName())
					.append("Low Temp", wd.getLowTemp())
					.append("High Temp", wd.getHighTemp())
					.append("Forecast", wd.getForecast());

			collection.insertOne(document);
		}
		catch(Exception me) {
			me.printStackTrace();
		}
		finally {
			
		}	
	}
}
