package com.onlinedealfinder.controller;

import com.mongodb.client.model.Filters;
import com.onlinedealfinder.model.*;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/imageupload")
public class ImageUpload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imgurl = FileHandler.upload(C.IMAGE.STORE_PATH,request);

        String str = "";

            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    String namecookie = cookies[i].getName();
                    if (namecookie.equals(C.COOKIE.LOGIN_COOKIE)) {
                        str = cookies[i].getValue();
                        break;
                    }
                }
            }

        System.out.println("COOKIE : " + str);
        Document document = Document.parse(str);

        MMongo.update(new DB().getCollection(), Filters.eq(C.FIELD.EMAIL,document.getString(C.FIELD.EMAIL)),new Document("$set",new Document(C.FIELD.IMGURL,imgurl)));
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                String namecookie = cookies[i].getName();
                if (namecookie.equals(C.COOKIE.LOGIN_COOKIE)) {
                    cookies[i].setValue(AuthenticationUtil.getDetailsByEmail(document.getString(C.FIELD.EMAIL)));
                    response.addCookie(cookies[i]);
                    System.out.println(cookies[i].getValue().toString());
                    break;
                }
            }
        }
        response.sendRedirect("index.jsp?editProfile=true");
    }
}
