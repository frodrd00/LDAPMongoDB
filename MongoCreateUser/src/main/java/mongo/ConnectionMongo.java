package mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;


public class ConnectionMongo {
	
	private MongoClient mongoClient;
	private MongoClientURI uri;
	private MongoDatabase database;
	

	public void ConnectMongo(){
		try{
			//conexi�n a mongodb formato URI
			//mongodb://user1@host1/?authSource=$external&authMechanism=PLAIN
			String db = "admin";
			String host = "cre.unileon.es";
			int port = 3306;//default
			String user = "admin";
			String password = "root";

			uri = new MongoClientURI("mongodb://"+user+":"+password+"@"+host+":"+port+"/?authSource=$external&authMechanism=PLAIN");
			
			//conexion con el servidor de mongodb
			mongoClient = new MongoClient(uri);
			
			//se guarda la base de datos para utilizarla despues a la hora de insertar documentos
			database = mongoClient.getDatabase(db);
			
			System.out.println("La conexi�n con " + database.getName() +" fue establecida.");
	    } catch (MongoException e) {
	    	System.out.println("Fallo conexi�n con mongodb "+ database.getName() +"!"+e);
	    }
	}
	
	public void DisConnect(){
		try {
			mongoClient.close();
			System.out.println("Se ha cerrado la conexi�n con mongodb "+ database.getName());
		} catch (MongoException e) {
			System.out.println("Fallo desconexi�n con mongodb "+ database.getName() +"!"+e);
		}
	
	}
	
	public MongoDatabase getDatabase()
	{
		return database;
	}
	
	public MongoClient getMongoClient(){
		
		return mongoClient;
	}
	
}

