package com.onlinedealfinder.controller;

import java.io.IOException;

import com.onlinedealfinder.model.AuthenticationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addUser
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String name = request.getParameter("form-first-name");
        String email = request.getParameter("form-email");
        String password = request.getParameter("form-password");
        
        password = AuthenticationUtil.generateHash(password);

        boolean flag = AuthenticationUtil.signUp(name, email,"", password);
        
        if(flag)
        	response.sendRedirect("index.jsp");
        else
        	response.sendError(404);
	}

}