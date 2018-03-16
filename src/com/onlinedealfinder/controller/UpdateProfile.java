package com.onlinedealfinder.controller;

import com.onlinedealfinder.model.C;
import com.onlinedealfinder.model.FileHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateprofile")
public class UpdateProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String UPLOAD_DIRECTORY = "/home/unnamed/images";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String resp = FileHandler.upload(UPLOAD_DIRECTORY,request);
        String name = request.getParameter(C.FIELD.NAME);
        String email = request.getParameter(C.FIELD.EMAIL);
        String phnno = request.getParameter(C.FIELD.PHONE);


    }
}
