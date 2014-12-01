<%-- 
    Document   : productList
    Created on : 30-Nov-2014, 12:02:45 AM
    Author     : ShashankPangam
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="Model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Header.jsp"%>
<div id="content">
    <%@include file="sideBarNav.jsp" %>
    <div id="main">
        <div id="inside">
            <div class="saleprodheader">
                <h4><%out.print(request.getParameter("category"));%></h4>
            </div>
            <div id="items">
                <%
                    ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
                    String category = request.getParameter("category");
                    
                    for (int i = 0; i < products.size(); i++) {
                        out.print("<div class='item center' style='height:260px;'>");
                        out.print("<a href='ProductServlet.jsp?action=showDesc&category="+category+"&ID=" + products.get(i).getProductID() + "&returnURL=/View/productDesc.jsp'><img src='" + products.get(i).getProductImage() + "' width='190' height='192'/></a><br />");
                        out.print("<p style='float:left;'><a href='productDesc.php?action=showDesc&category="+category+"&ID=" + products.get(i).getProductID() + "&returnURL=/View/productDesc.jsp'>" + products.get(i).getProductName() + "</a></p><span class='price'>" + products.get(i).getProductPrice() + "</span><br /></div>");
                    }
                %>
            </div>
            
        </div>
    </div>
</div>