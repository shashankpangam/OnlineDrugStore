<%-- 
    Document   : index
    Created on : 30-Nov-2014, 2:55:55 AM
    Author     : Paramjyot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Header.jsp" %>
<div id="content">
    <%@include file="sideBarNav.jsp" %>
    <div id="main">
        <div class="container">
            <center>
                <form method='POST' action='LoginServlet.jsp?next=Login&returnURL=/View/index.jsp'>

                    <h2> SIGN IN </h2>
                    <table>
                        <tr><td>Username</td><td><input type=text name='email'></td></tr>
                        <tr><td>Password</td><td><input type=password name='passwd'></td></tr>
                    </table>

                    <input type="submit" name="next" value='Login'/>

                    <p> Don't have an account? </p>
                    <input type="submit" name="next" value='Register'/>
                    <%                String email = request.getParameter("email");
                        String password = request.getParameter("passwd");
                        String submit = request.getParameter("submit");
                        if ((email != null) && (password != null) && (submit != null)) {
                    %>
                    You are logged in with Name: <%=email%> and Password: <%=password%>
                    <% }%>
                </form>
            </center>
        </div>
        <br />

    </div>
</div>
<%@include file="footer.jsp"%>