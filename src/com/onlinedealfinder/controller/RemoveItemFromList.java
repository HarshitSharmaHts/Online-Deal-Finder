package com.onlinedealfinder.controller;

import com.mongodb.client.model.Filters;
import com.onlinedealfinder.model.C;
import com.onlinedealfinder.model.DB;
import com.onlinedealfinder.model.MMongo;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveItemFromList",urlPatterns = "/removeitemfromlist")
public class RemoveItemFromList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        MMongo.update(new DB().getProductsCollection(), Filters.eq(C.FIELD.IMGURL,id), new Document("$set",new Document(C.FIELD.SOLDFLAG,"true")));

        response.sendRedirect("index.jsp?history=true");
    }
}
