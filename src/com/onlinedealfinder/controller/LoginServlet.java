package com.onlinedealfinder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlinedealfinder.model.AuthenticationUtil;
import com.onlinedealfinder.model.C;
import org.bson.Document;

/**
 * class used to authenticate user's login.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String email = request.getParameter("form-email");
        String password = request.getParameter("form-password");
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");

        String jAuthResp = AuthenticationUtil.login(email,password);

		if(!jAuthResp.equals(C.ERROR.CODE_1))
		{

			/**
			 * adding cookie for the user.
			 */

			Document jDoc = Document.parse(jAuthResp);
			jDoc.append(C.COOKIE.LONGITUDE_FIELD,longitude).append(C.COOKIE.LATITUDE_FIELD,latitude);
			jAuthResp = jDoc.toJson();

			Cookie cookie = new Cookie(C.COOKIE.LOGIN_COOKIE, jAuthResp);
			cookie.setMaxAge(60);

			response.addCookie(cookie);
			response.sendRedirect("index.jsp?login=true");
		}
		else
		{
			response.sendRedirect("index.jsp?login=false");
		}
	}
}
