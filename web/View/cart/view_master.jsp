<%-- 
    Document   : viewcart
    Created on : 24-Nov-2014, 6:38:03 PM
    Author     : brian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.ods.util.AppEnvConst" %>
<%@page import="com.ods.cart.*" %>

<html>
    <head>
        <title>Online Drug Store</title>
        <link rel="stylesheet" type="text/css" href="Styles/style.css" />
        <link rel="shortcut icon" href="images/favicon.ico" >
        <script type="text/javascript" src="Scripts/JScript.js"></script>
        <script type="text/javascript" src="Scripts/javascriptFile.js"></script>
        <script type="text/javascript" src="Scripts/jquery.slides.min.js"></script>
        <style>
            .error {color: red;}
        </style>
    </head>
    <body>
        <div id="header">
            <a href=""><img src="images/logo.png" width="237" height="123" class="float" alt="setalpm" /></a>																																																			
            <div class="topnav">
                <span><strong>Welcome</strong> &nbsp;<a href="View/login.jsp">Log in</a> &nbsp; | &nbsp; <a href="View/register.jsp">Register</a></span>
            </div>
            <ul id="menu">
                <li><a href="View/index.jsp"><img src="images/but1.gif" alt="Home Page" width="110" height="32" /></a></li>
                <li><a href="View/login.jsp"><img src="images/but2.gif" alt="Log in" width="110" height="32" /></a></li>
                <li><a href="View/register.jsp"><img src="images/but3.gif" alt="Registration" width="110" height="32" /></a></li>
                <li><a href="View/login.jsp"><img src="images/but4.gif" alt="My account" width="110" height="32" /></a></li>
                <li><a href="View/index.jsp"><img src="images/but5.gif" alt="Shopping Cart" width="110" height="32" /></a></li>
                <li><a href="View/index.jsp"><img src="images/but6.gif" alt="Checkout" width="110" height="32" /></a></li>
            </ul>            
        </div>

<%!
    ShoppingCart cart; 
    //Customer curUser;
    String cur_userid;
%>

<div id="content">
<%@include file="../sideBarNav.jsp" %>
    <div id="main">
        <%!
            String reqPage;
            String toUrl;
        %>
        <% 
            reqPage = request.getParameter(AppEnvConst.VIEWMASTER_COMMAND);
            toUrl = "pagenot_found.jsp";
            if( reqPage.equalsIgnoreCase(AppEnvConst.VIEWMASTER_CMD_ERROR) ) {
                toUrl = "checkout_error.jsp";
            }
            else if( reqPage.equalsIgnoreCase(AppEnvConst.VIEWMASTER_CMD_VIEWCART)) {
                toUrl = "viewcart_slave.jsp";
            }
            else if( reqPage.equalsIgnoreCase(AppEnvConst.VIEWMASTER_CMD_DONE_CHECKOUT)) {
                toUrl = "thankyou_slave.jsp";
            }
        %>
        <jsp:include page="<%= toUrl %>" />
        <!-- <%-- <%= myVariable not working%>" flush="true"  --%>  -->
    </div>
</div>    
        
<div id="footer">

    <img src="images/footer.jpg" alt="" width="919" height="76" />
    <ul>
        <li><a href="">Home page</a> |</li>
        <li><a href="">Sale Products</a> |</li>
        <li><a href="contactus">Contact Us</a></li>
    </ul>
    <p>Copyright &copy; 2014.</p>																																																																				
</div>
<map name="Map">
    <area shape="rect" coords="16,306,159,326" href="#">
</map>
    
</body>
</html>