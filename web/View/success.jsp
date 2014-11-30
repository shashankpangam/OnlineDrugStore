<%-- 
    Document   : Logout
    Created on : 14-Nov-2014, 10:51:12 AM
    Author     : Paramjyot
--%>


<!DOCTYPE html>
<html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
        
        Successfully Logged In! Valid User!
    <center>
        <%
    if ((session.getAttribute("sessioncustid") == null) || (session.getAttribute("sessionemail") == "")) {
%>
You are not logged in<br/>
<a href="login.jsp">Please Login</a>
<%} 
    else {
%>
Welcome <%=session.getAttribute("sessionfname")%>
<a href='logout.jsp'>Log out</a>
<%
    }
%>
        <p>
           
    </center>
</body>
</html>


