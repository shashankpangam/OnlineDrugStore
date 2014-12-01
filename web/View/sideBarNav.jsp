<%-- 
    Document   : sideBarNav
    Created on : 30-Nov-2014, 2:35:55 AM
    Author     : ShashankPangam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="sidebar">
    <div id="navigation">
        <ul>
            <li><a href="ProductServlet.jsp?action=showProducts&category=Medicines&returnURL=/View/productList.jsp" name="category" value="Medicines">Medicines</a></li>
            <li><a href="ProductServlet.jsp?action=showProducts&category=Vitamins&returnURL=/View/productList.jsp" name="category" value="Vitamins">Vitamins</a></li>
            <li><a href="ProductServlet.jsp?action=showProducts&category=DietandFitness&returnURL=/View/productList.jsp" name="category" value="DietandFitness">Diet and Fitness</a></li>
            <li><a href="ProductServlet.jsp?action=showProducts&category=Personal&returnURL=/View/productList.jsp" name="category" value="Personal">Personal</a></li>
            <li><a href="index.php">Checkout</a></li>
        </ul>
    </div>
</div>

