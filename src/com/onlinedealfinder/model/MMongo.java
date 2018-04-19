package com.onlinedealfinder.model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
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

    public static Iterator<Document> getDistanceIterator(Iterator<Document> input, String number,double lat, double lon) {
        int distance=0;
        try {
            distance = Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            distance=0;
        }
        if(distance!=0){

            ArrayList<Document> arrayList = new ArrayList<>();
            while(input.hasNext()) {
                Document document = input.next();
                double latitude = Double.parseDouble(document.getString(C.FIELD.LATITUDE));
                double longitude = Double.parseDouble(document.getString(C.FIELD.LONGITUDE));
                int dist = C.calculateDistance(lat,lon,latitude,longitude);
                if(dist <= distance) {
                    arrayList.add(document);
                }
            }
            return arrayList.iterator();

        } else {
            return input;
        }
    }
}
