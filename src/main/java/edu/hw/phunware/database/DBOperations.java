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
	
	public String getDataforZipcode(String zipcode) throws Exception {
		
		MongoClient mongoClient = null;
		
		try {
			mongoClient = DBConnection.getInstance().getConnection();
			MongoDatabase database = mongoClient.getDatabase("db_weather");
			MongoCollection<Document> collection = database.getCollection("weatherCollection");
			Document doc = collection.find(eq("Zipcode",zipcode)).first();
			
			if(doc == null) {
				JSONObject obj = new JSONObject();
				obj.put("status", false);
				obj.put("error", "Please check the Zipcode you've entered");
				
				return obj.toString();
			}
			
			doc.remove("_id");
			JSONObject obj = new JSONObject(doc.toJson());
			obj.put("status", true);
			return obj.toString();
			
		}
		catch(Exception me) {
			me.printStackTrace();
			throw new Exception("issue while reading data from database");
		}
		finally {
			
		}
	}
	
	public void writeData(WeatherData wd) throws Exception {
		
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
			throw new Exception("issue while writing data to the database");
		}
		finally {
			
		}	
	}
}
