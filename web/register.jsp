<%-- 
    Document   : register
    Created on : 22-Nov-2014, 3:20:53 AM
    Author     : Paramjyot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
<body>
   <center>
        <form method='POST' action='UserServlet'>
            
            <h2> Create your Account </h2> 
            <table>
                <tr><td>Customer ID :</td><td><input type=text  name='customerid'></td></tr>
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
</body>
</html>
