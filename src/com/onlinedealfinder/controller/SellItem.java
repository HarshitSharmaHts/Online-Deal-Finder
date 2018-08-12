package com.onlinedealfinder.controller;

import com.onlinedealfinder.model.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/sellitem")
@MultipartConfig
public class SellItem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = "";
        String category = "";
        String price = "";
        String description = "";
        String email = "";
        String longitude = "";
        String latitude = "";
        String photoUrl="";

        if(ServletFileUpload.isMultipartContent(request)) {
            try {
                String fname = null;
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()) {
                        photoUrl = AmazonClient.getInstance().uploadFile(item);
                    } else {
                        if(item.getFieldName().equals("title"))
                            title = new String(item.get());
                        if(item.getFieldName().equals("category"))
                            category = new String(item.get());
                        if(item.getFieldName().equals("price"))
                            price = new String(item.get());
                        if(item.getFieldName().equals("description"))
                            description = new String(item.get());
                        if(item.getFieldName().equals("email"))
                            email = new String(item.get());
                        if(item.getFieldName().equals("longitude"))
                            longitude = new String(item.get());
                        if(item.getFieldName().equals("latitude"))
                            latitude = new String(item.get());
                    }
                }
                System.out.println("TRY");
            } catch (Exception ex) {
                System.out.println("CATCH");
            }
        }else{
            System.out.println("ELSE");
        }

        Document document = new Document(C.FIELD.IMGURL,photoUrl)
                .append(C.FIELD.EMAIL,email)
                .append(C.FIELD.TITLE,title)
                .append(C.FIELD.PRICE,price)
                .append(C.FIELD.CATEGORY,category)
                .append(C.FIELD.DESCRIPTION,description)
                .append(C.FIELD.LONGITUDE,longitude)
                .append(C.FIELD.LATITUDE,latitude)
                .append(C.FIELD.SOLDFLAG,"false");

        MMongo.insert(DB.getInstance().getProductsCollection(),document);

        response.sendRedirect("index.jsp");

    }
}
