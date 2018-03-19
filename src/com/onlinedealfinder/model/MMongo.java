package com.onlinedealfinder.model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Iterator;

public class MMongo {

    public static Iterator<Document> find(MongoCollection<Document> collection, Document document) {
        FindIterable<Document> findIterable = collection.find(document).projection(new Document(C.FIELD.PASSWORD,0).append("_id",0));
        return findIterable.iterator();
    }

    public static void insert(MongoCollection<Document> collection, Document document) {
        collection.insertOne(document);
    }

    public static void update(MongoCollection<Document> collection, Bson filter, Document document) {
        collection.updateMany(filter,document);
    }
}
