package com.onlinedealfinder.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlinedealfinder.model.AuthenticationUtil;
import com.onlinedealfinder.model.C;
import org.bson.Document;

import java.io.IOException;


/**
 * Servlet implementation class LoginByOAuth
 */
@WebServlet("/loginOAuth")
public class LoginByOAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginByOAuth() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("u_name");
		String email = request.getParameter("u_email");
		String imgurl = request.getParameter("u_imgurl");
		String longitude = request.getParameter("u_longi");
		String latitude = request.getParameter("u_lati");

		String jDocumentString = AuthenticationUtil.loginOAuth(email);


		if(!jDocumentString.equals(C.ERROR.CODE_1)) {
			/**
			 * adding cookie for the user.
			 */

			Document jDoc = Document.parse(jDocumentString);
			jDoc.append(C.COOKIE.LONGITUDE_FIELD,longitude).append(C.COOKIE.LATITUDE_FIELD,latitude);
			jDocumentString = jDoc.toJson();
			Cookie cookie = new Cookie(C.COOKIE.LOGIN_COOKIE, jDocumentString);
			cookie.setMaxAge(60);
			response.addCookie(cookie);
			response.sendRedirect("index.jsp?login=true");

		} else {
		    AuthenticationUtil.signUp(name,email,imgurl,"");
		    doPost(request,response);
		}

        response.setContentType("text/html");
	}

}
