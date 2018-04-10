package com.onlinedealfinder.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class DB {

    private MongoClient client;

    public DB() {
        client = new MongoClient(C.MONGO.HOST_NAME,C.MONGO.PORT_NUMBER);
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
