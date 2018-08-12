package com.onlinedealfinder.model;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class DB {

    private static DB instance;
    private static MongoClient client;

    private DB() {

        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://harshitsharmahts:zZ1sOiT8Ay3JkDhO@cluster0-shard-00-00-cvn8z.mongodb.net:27017,cluster0-shard-00-01-cvn8z.mongodb.net:27017,cluster0-shard-00-02-cvn8z.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true\n");
        client = new MongoClient(mongoClientURI);
    }

    public static DB getInstance() {
        if(instance==null)
            instance = new DB();
        return instance;
    }

    public MongoClient getClient() {
        return client;
    }

    public MongoCollection<Document> getCollection() {
        return client.getDatabase(C.MONGO.DATABASE_NAME).getCollection(C.MONGO.USER_COLLECTION);
    }

    public MongoCollection<Document> getProductsCollection() {
        return client.getDatabase(C.MONGO.DATABASE_NAME).getCollection(C.MONGO.PRODUCTS_COLLECTION);
    }
}
