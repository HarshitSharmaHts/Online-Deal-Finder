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

@WebServlet(name = "SendDealRequest", urlPatterns = "/senddealrequest")
public class SendDealRequest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customer = request.getParameter("customer");
        String id = request.getParameter("id");
        Document document = new Document("$push",new Document(C.FIELD.CUSTOMERS,customer));

        MMongo.update(new DB().getProductsCollection(), Filters.eq(C.FIELD.IMGURL,id),document);
        response.sendRedirect("index.jsp?msg=");
    }
}
