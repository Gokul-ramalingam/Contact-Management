package com.controllers.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.util.UserDao;
import com.model.dao.LoginModel;

@WebServlet(name = "Register", urlPatterns = { "/Register" }) //set in web.xml
public class RegisterController extends HttpServlet{	
	private static final long serialVersionUID = 1L;	
	private static String ADMINPG= "/admin.jsp";
	//private static String WELCMPG= "/welcome.jsp";	
	private UserDao dao;	

	public RegisterController() {
		super();
		dao = new UserDao(); //create new data object
		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		LoginModel user = new LoginModel();	//create new user object	
		PrintWriter pwOut= response.getWriter();
		
		//get input from jsp and store it in user object
		String un=request.getParameter("username");		
		String pw =request.getParameter("psword");
		String email=request.getParameter("email");
		String userID = request.getParameter("userid");		
		user.setUsername(un);			
		user.setPsword(pw);
		user.setEmail(email);
		 private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
		  private Pattern pattern = Pattern.compile(USERNAME_PATTERN);
		  private Matcher matcher;
		  
		  
		  public boolean validate(final String username){
			  
			  matcher = pattern.matcher(username);
			  return matcher.matches();
		    	    
		  }
     
		  

		   public boolean validateEmail(final String email){
			   String Email_Pattern = "^(.+)@(.+)$";
			   private Pattern pattern = Pattern.compile(Email_Pattern); 
	        	Matcher matcher = pattern.matcher(email);
	            System.out.println(email +" : "+ matcher.matches());
			    	    
			  }
		  
		//if there is no ID field a new user is being created and added to database
		if(validate(un)&&validateEmail(email))
		{
			dao.createUser(user);				
			pwOut.print("Registration Successful! Please Login.");
			response.setContentType("text/html");
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");		
			view.include(request, response); //index page is reloaded with text for new user to login
		}
		//if there is an ID field a user is being edited
		else
		{
			pwOut.print("Please enter valid details!");
			response.setContentType("text/html");		
			view.forward(request, response); //reload admin page with updated table
		}				
			
	}

}
