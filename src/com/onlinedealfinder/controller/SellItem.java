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
import javax.servlet.http.Part;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/sellitem")
@MultipartConfig
public class SellItem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        Part titlePart = request.getPart("title");
        String title = InputStreamProcessor.getString(titlePart.getInputStream());


        Part categoryPart = request.getPart("category");
        String category = InputStreamProcessor.getString(categoryPart.getInputStream());

        Part pricePart = request.getPart("price");
        String price = InputStreamProcessor.getString(pricePart.getInputStream());

        Part descriptionPart = request.getPart("description");
        String description = InputStreamProcessor.getString(descriptionPart.getInputStream());

        Part emailPart = request.getPart("email");
        String email = InputStreamProcessor.getString(emailPart.getInputStream());

        Part photoPart = request.getPart("photo");
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date())+"_"+email+"."+photoPart.getContentType().split("/")[1];
        FileOutputStream os = new FileOutputStream (C.IMAGE.STORE_PATH +File.separator+timeStamp);
        InputStream is = photoPart.getInputStream();
        int ch = is.read();
        while (ch != -1) {
            os.write(ch);
            ch = is.read();
        }
        os.close();

        Document document = new Document(C.FIELD.IMGURL,"images/"+timeStamp)
                .append(C.FIELD.TITLE,title)
                .append(C.FIELD.PRICE,price)
                .append(C.FIELD.CATEGORY,category)
                .append(C.FIELD.DESCRIPTION,description);

        MMongo.insert(new DB().getProductsCollection(),document);

        response.sendRedirect("index.jsp");

    }
}
