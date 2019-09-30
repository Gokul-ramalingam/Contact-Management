<%@page contentType="text/html" import="java.sql.*"%>
<%@ page import="java.io.*,java.util.*,java.util.regex.*"%>
<html>
<body>
     <h1>Login JSP Page</h1>
    <%
        String uname=request.getParameter("txtName");
        String pass = request.getParameter("txtPass");
       

        try{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jfs","root","12345678");
    PreparedStatement stmt = con.prepareStatement("select password from user where username=?");
    stmt.setString(1, uname);  
    ResultSet rs = stmt.executeQuery();
    if(rs.next()){
        if(pass.equals(rs.getString(1)))
        {
            out.println("<h1>~~~ LOGIN SUCCESSFULLL ~~~ </h1>");
        }
        }
    else{
    out.println("<h1 style='color:red'>User with this username does not exist !!!!!</h1>");
    %>
    <jsp:include page="Register.html" ></jsp:include>
        <%
    }
        }catch(Exception e){out.println(e);}
   %>
    </body>
</html>