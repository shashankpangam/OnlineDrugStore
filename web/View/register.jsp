<%-- 
    Document   : register
    Created on : 22-Nov-2014, 3:20:53 AM
    Author     : Paramjyot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Header.jsp" %>
<div id="content">
    <%@include file="sideBarNav.jsp" %>
    <div id="main">
        <div class="container">
            <center>
                <form method='POST' action='UserServlet.jsp?action=AddNewUser&returnURL=/View/login.jsp'>

                    <h2> Create your Account </h2> 
                    <table>
                        
                        <tr><td>First Name :</td><td><input type=text name='fname'></td></tr>
                        <tr><td>Last Name :</td><td><input type=text name='lname'></td></tr>
                        <tr><td>DOB :</td><td><input type=text name='dob'></td></tr>
                        <tr><td>Address1 :</td><td><input type=text name='address1'></td></tr>
                        <tr><td>Address2 :</td><td><input type=text name='address2'></td></tr
                        <tr><td>City :</td><td><input type=text name='city'></td></tr>
                        <tr><td>Zip :</td><td><input type=text name='zip'></td></tr>
                        <tr><td>Province :</td><td><input type=text name='province'></td></tr>
                        <td><input type=radio name='gender' value='M'></td><td>Male </td></tr>
                        <td><input type=radio name='gender' value='F'></td><td>Female </td></tr>
                        <tr><td>Email :</td><td><input type=text name='email'></td></tr>
                        <tr><td>Password :</td><td><input type=Password name='passwd'></td></tr> 
                        <tr><td>Retype Password :</td><td><input type=Password name='repwd'></td></tr>         
                        <tr>
                            <td><input type=submit name=next value='Register'></td>
                            <td><input type=submit name=next value='Cancel'></td>
                        </tr>
                    </table>
                </form>
            </center>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
