<%@page contentType="text/html" import="java.sql.*"%>
<%@ page import="java.io.*,java.util.*,java.util.regex.*"%>
<html>
<body>
        <h1>Registration JSP Page</h1>
    <%
        String uname=request.getParameter("txtName");
        String pass1 = request.getParameter("txtPass1");
        String pass2 = request.getParameter("txtPass2");
        String email = request.getParameter("txtEmail");
        String ctry = request.getParameter("txtCon");
        String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
	    Pattern userPattern = Pattern.compile(USERNAME_PATTERN);
		Matcher userMatcher = userPattern.matcher(uname);
		String Email_Pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern emailPattern = Pattern.compile(Email_Pattern);
		Matcher emailMatcher = emailPattern.matcher(email);
		String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
		Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher passwordMatcher = emailPattern.matcher(pass1);
  if(userMatcher.matches() && emailMatcher.matches() && passwordMatcher.matches() && pass1.equals(pass2)){
        try{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jfs","root","12345678");
    PreparedStatement stmt = con.prepareStatement("insert into user values (?,?,?,?)");
    stmt.setString(1, uname);    stmt.setString(2, pass1);
    stmt.setString(3, email);    stmt.setString(4, ctry);
    int row = stmt.executeUpdate();
    if(row==1) { out.println("Registration Successful"); }
    else {    
        out.println("Registration Failed!!!!");
        %>
            <jsp:include page="Register.html" ></jsp:include>
        <%
    
        }
    }catch(Exception e){out.println(e);}
  }
  else 
  {
  out.println("<h1 style='color:red'> Please Enter Valid Credentials</h1>");
  %>
  <jsp:include page="Register.html" ></jsp:include>
<%  }
    %>
    </body>
</html>