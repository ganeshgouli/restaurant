package dao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dto.Customer;
@WebServlet("/login")
public class MyLogin  extends HttpServlet{

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	
	
	//Verify if email exists
	MyDao dao=new MyDao() ;
	if (email.equals("admin@jsp.com")&&password.equals("admin")) {
		resp.getWriter().print("<h1 style='color:green'>login success</h1>");
        //		this is Logic to send to next page
		req.getRequestDispatcher("AdminHome.html").include(req, resp);
	}else {
	Customer customer=dao.fetchByEmail(email);
	if (customer==null) {
		resp.getWriter().print("<h1 style='color:red'>invalid email</h1>");
		req.getRequestDispatcher("login.html").include(req, resp);
	}else {
		if (password.equals(customer.getPassword())) {
			resp.getWriter().print("<h1 style='color:green'>login success</h1>");
			  //		this is Logic to send to next page
			req.getRequestDispatcher("CoustomerHome.html").include(req, resp);
		}else {
			resp.getWriter().print("<h1 style='color:red'>invalid password</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
	}
}
}
}