package controler;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Dto.Customer;
import dao.MyDao;
@WebServlet("/signup")
@MultipartConfig
public class CustomerSignup extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String fullname=req.getParameter("name");
	long number=Long.parseLong(req.getParameter("number"));
	String email=req.getParameter("email");
	String password=req.getParameter("password");
    String country=req.getParameter("country");
    String gender=req.getParameter("gender");
	LocalDate dob=LocalDate.parse(req.getParameter("dob"));
	int age=Period.between(dob, LocalDate.now()).getYears();
	
	//logic to receive image  and convert to byte
	Part pic=req.getPart("pic");
	byte []picture=null;
	picture=new byte[pic.getInputStream().available()];
	pic.getInputStream().read(picture);
	
	System.out.println(fullname);
	System.out.println(number);
	System.out.println( email);
	System.out.println(password);
	
	System.out.println(age);
	MyDao dao=new MyDao();
	if(dao.fetchByEmail(email)==null&& dao.fetchByMobile(number)==null) {
	Customer customer=new Customer();
	customer.setFullname(fullname);
	customer.setEmail(email);
	customer.setGender(gender);
	customer.setNumber(number);
	customer.setPassword(password);
	customer.setDob(dob);
	customer.setAge(age);
	customer.setCountry(country);
	customer.setPicture(picture);
	
	
	dao.save(customer);
	
	
	resp.getWriter().print("<h1 style=' color :green'> Account created Succesfully</h1>");
	req.getRequestDispatcher("login.html").include(req, resp);
	}else {
		resp.getWriter().print("<h1 style='color:red'>Email and number Should be Unique  </h1>");
		req.getRequestDispatcher("Sig.html").include(req, resp);
	}
}
}
