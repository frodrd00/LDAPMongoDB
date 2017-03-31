package mongo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {

	public static void main(String[] args) {
		
		
		ConnectionMongo conn = new ConnectionMongo();
    	
    	conn.ConnectMongo();
    	
    	MongoClient mongoC = conn.getMongoClient();
    	
    	MongoDatabase db = mongoC.getDatabase("$external");
    	
    	
    	//String role = "{ role: 'read', db: 'cre'}";
    	//String[] roles = new String[] { role };
	   // Map<String, Object> commandArguments = new HashMap<String, Object>();
	    //commandArguments.put("user", "TestUser3");
	   // commandArguments.put("pwd", "password123");
	   // commandArguments.put("roles", roles);
	   // BasicDBObject command = new BasicDBObject(commandArguments);
    	
    	
    	MongoCollection<Document> collection = conn.getDatabase().getCollection("system.users");

    	Document result = db.runCommand(new BasicDBObject("createUser","test3").append("roles", Collections.singletonList(new BasicDBObject("role", "read").append("db", "cre"))));

    	collection.insertOne(result);
    	
    	
    	
	}

}
