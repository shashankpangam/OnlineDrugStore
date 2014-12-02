<%-- 
    Document   : productDesc
    Created on : 1-Dec-2014, 11:51:52 AM
    Author     : ShashankPangam
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.Product"%>
<%@page import="com.ods.util.AppEnvConst"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Header.jsp"%>
<div id="content">
    <%@include file="sideBarNav.jsp" %>
    <div id="main">
        <div id="inside" style="padding-left: 30px;">
            <div class="productname" style="float: left; width: 325px;">
                <%
                    ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("product");
                    Product product = products.get(0);
                    String category = (String)request.getAttribute("category");                    
                %>
                <h2><%out.print(product.getProductName());%></h2>
                
                <img src="<% out.print(product.getProductImage()); %>" height="192" width="190" alt="<% out.print(product.getProductName());%>" />
            </div>
            <div class="bigprice" style="float:right;width:325px;">
                <div  style=" margin-top: 50px">
                    <label class="realPrice" style="font-size: 15px;font-weight: bold;">$<% out.print(product.getProductPrice());%></label><br/><br/><div>
                    <label class="<%
                        int instock = product.getProductQuantity();
                        if (instock > 0) {
                            out.print("instock");
                        } else {
                            out.print("outofstock");
                        }
                           %>" style="font-size: 20px;"><%
                               instock = product.getProductQuantity();
                               if (instock > 0) {
                                   out.print("In Stock");
                               } else {
                                   out.print("Out of Stock");
                               }
                        %>
                    </label>                      
                </div><br/>
                    <label style="color: red;padding-top: 5px;padding-bottom: 5px;">RECOMMEND FOR:</label><br />
                    <ul>
                        <%
                            String symptoms = product.getSymptoms();
                            String[] arrSymptoms = symptoms.split(",");
                            for (int i = 0; i < arrSymptoms.length; i++) {
                        %>
                        <li><% out.print(arrSymptoms[i]);%></li>
                            <%
                                }
                            %>
                    </ul>
                </div>
            </div>
            <div style="padding-top: 15px;height:250px;">

                <form method="post" action="cart?action=add" > 
                    <label>Quantity</label>
                    <select name="<%= AppEnvConst.CART_PARAM_QTY %>">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                    <button class="btnsubmit" style="width:90px;height: 30px;">Add To Cart</button>
                    <input type="hidden" name="<%= AppEnvConst.CART_PARAM_ID %>" value="<%out.print(product.getProductID());%>">
                    <input type="hidden" name="<%= AppEnvConst.CART_PARAM_NAME %>" value="<%out.print(product.getProductName());%>">
                    <input type="hidden" name="<%= AppEnvConst.CART_PARAM_PRICE %>" value="<%out.print(Float.toString( product.getProductPrice() ) );%>">                    
                    <input type="hidden" name="<%= AppEnvConst.CART_PARAM_RTNURL %>" value="ProductServlet.jsp?action=showDesc&returnURL=/View/productDesc.jsp&category=<%= category %>&ID=<%= product.getProductID() %>">
                
                    <!--<input type="hidden" name="return_url" value="returnURL" >-->
                </form>
            </div>

            <br/><br/>
            <div style="padding: 40px 0px 20px 30px; float:left;">           
                <label><font color="red">PRODUCT DESCRIPTION</font></label>
                <p><%
                    out.print(product.getProductDescription());
                    %></p>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
