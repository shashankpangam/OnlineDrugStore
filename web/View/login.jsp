<%--
    Document   : index
    Created on : 13-Nov-2014, 8:18:24 AM
    Author     : Paramjyot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>

    <form method='GET' action='LoginServlet'>
        
        <h2> SIGN IN </h2>
    <table>
                <tr><td>Username</td><td><input type=text name='email'></td></tr>
                <tr><td>Password</td><td><input type=password name='passwd'></td></tr>
            </table>
               
         <input type=submit name=next value='Login'/>
         
         <p> Don't have an account? </p>
         <input type=submit name=next value='Register'/>
     <%
       String email = request.getParameter("email");
       String password = request.getParameter("passwd");
       String submit = request.getParameter("submit"); 
        if ((email != null)&&(password!=null)&&(submit != null))
        {
      %>
         You are logged in with Name: <%=email%> and Password: <%=password%>
         <% } %>
</form>
    </body></html>

