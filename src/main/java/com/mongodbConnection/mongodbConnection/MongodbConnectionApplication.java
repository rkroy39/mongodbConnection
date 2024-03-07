package com.mongodbConnection.mongodbConnection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
@SpringBootApplication
public class MongodbConnectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbConnectionApplication.class, args);
		
		//String connectionString = "mongodb+srv://myAtlasDBUser:admin@myatlasclusteredu.6pgpvt9.mongodb.net/?retryWrites=true&w=majority&connectTimeoutMS=300000&socketTimeoutMS=300000";
		String connectionString="jdbc:mongodb://atlas-sql-6570585776a19a7e51df96c3-qxmmb.a.query.mongodb.net/sample_airbnb?ssl=true&authSource=admin";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("sample_airbnb");
               MongoCollection<Document> collection =database.getCollection("listingsAndReviews");
               // database.runCommand(new Document("ping", 1));
              // collection.find().forEach(s->System.out.println(s.toJson()));
              // Document query=new Document("_id",10006546);
               //Document result=collection.find(query).iterator().next();
               
               
             
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
	}

}
