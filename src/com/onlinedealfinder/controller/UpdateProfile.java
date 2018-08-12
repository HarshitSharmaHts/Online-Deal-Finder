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

@WebServlet("/updateprofile")
public class UpdateProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phnno = request.getParameter("phn");
        String pass = request.getParameter("password");

        Document document = new Document();

        if (name!=null && !name.equals("")) {
            document.append(C.FIELD.NAME,name);
        }
        if (email!=null && !email.equals("")) {
            document.append(C.FIELD.EMAIL,email);
        }
        if (phnno!=null && !phnno.equals("")) {
            document.append(C.FIELD.PHONE,phnno);
        }
        if (pass!=null && !pass.equals("")) {
            pass = AuthenticationUtil.generateHash(pass);
            document.append(C.FIELD.PASSWORD,pass);
        }

        MMongo.update(DB.getInstance().getCollection(), Filters.eq(C.FIELD.EMAIL,email),new Document("$set",document));


        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                String namecookie = cookies[i].getName();
                if (namecookie.equals(C.COOKIE.LOGIN_COOKIE)) {
                    cookies[i].setValue(AuthenticationUtil.getDetailsByEmail(email));
                    response.addCookie(cookies[i]);
                    break;
                }
            }
        }

        response.sendRedirect("index.jsp?action=edit_profile");
    }
}
