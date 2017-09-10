package edu.hw.phunware.database;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.MongoClient;


public class DBConnection {
	
	private static DBConnection instance = null;
	private static MongoClient mongo = null;
	
	private DBConnection()
	{
		try{
		    Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
			mongoLogger.setLevel(Level.SEVERE); 
			mongo = new MongoClient("localhost",27017);
			
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	static{
		instance = new DBConnection();
	}
	
	public static DBConnection getInstance()
	{
		return instance;
	}
	public MongoClient getConnection(){
		return mongo;
	}

}
