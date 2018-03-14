package com.onlinedealfinder.model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Iterator;

public class MMongo {

    public static Iterator<Document> find(MongoCollection<Document> collection, Document document) {
        FindIterable<Document> findIterable = collection.find(document);
        return findIterable.iterator();
    }

    public static void insert(MongoCollection<Document> collection, Document document) {
        collection.insertOne(document);
    }
}
