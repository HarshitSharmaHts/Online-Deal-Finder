package com.onlinedealfinder.controller;

import java.io.*;
import java.util.Date;
import java.util.Random;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.onlinedealfinder.model.C;
import org.apache.commons.fileupload.FileItem;


public class AmazonClient {

    private static AmazonClient instance ;

    private AmazonS3 s3client;

    private AmazonClient() {
        BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAJVYAHDVJUVYG5FYA", "E/TFtQ/9TDBU4ZUiKJNlFOQ4ngF8MVk2eCP7OF48");
        s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    }


    private File convertMultiPartToFile(FileItem file) throws IOException {
        File convFile = new File(file.getName());
        try {
            file.write(convFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convFile;


    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(C.AWS.BUCKET, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));


    }

    private static String generateFileName(FileItem multiPart) {
        return  new Date().getTime() + "-" +new Random().nextLong()+ multiPart.getName().replace(" ", "_");


    }


    public static AmazonClient getInstance(){
        if (instance == null) {
            instance = new AmazonClient();
        }
        return instance;
    }

    public String uploadFile(FileItem multipartFile) {
        String fileName = generateFileName(multipartFile);
        try {
            File file = convertMultiPartToFile(multipartFile);
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
           e.printStackTrace();
        }

        return C.AWS.END_POINT_URL+"/"+C.AWS.BUCKET+"/"+fileName;
    }
/*
    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
        return "Successfully deleted";

    }
*/
}