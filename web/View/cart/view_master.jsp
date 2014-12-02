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
    <%@include file="../Header.jsp" %>
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
        
<%@include file="../footer.jsp" %>