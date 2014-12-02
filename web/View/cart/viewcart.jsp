<%-- 
    Document   : viewcart
    Created on : 24-Nov-2014, 6:38:03 PM
    Author     : brian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/View/Header.jsp" %>
<%@page import="com.ods.util.AppEnvConst" %>
<%@page import="com.ods.cart.*" %>

<%!
    ShoppingCart cart; 
    //Customer curUser;
    String cur_userid;
%>

<div id="content">
    <%@ include file="/View/sideBarNav.jsp" %>
    <div id="main">
        <div id="inside">
    <%
        
        cart = (ShoppingCart)request.getAttribute(AppEnvConst.SHOPPING_CART);
        //For test purpose
        if( cart.size() ==0 ) {
        cart.add(new PurchasedItem(new ProductItem("2000000001","VITD",5.6f),10,"coupn001"));
        cart.add(new PurchasedItem(new ProductItem("2000000002","VITC",3.6f),20,"coupn002"));
        cart.add(new PurchasedItem(new ProductItem("2000000003","VITE",6.6f),5,"coupn003"));
        }
        //fortest end
    %>
    <p>  

    
    </p>
    <form method="GET" action="cart">
    <table>
        <thead>
        <tr>
            <th></th>
            <th></th>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Qty</th>
            <th>Coupon</th>            
            <th>Sub-total</th>
        </tr>
        </thead>
        
        <tbody>

        <% 
            String itemid, itemname;
            String imageSrc;
            float itemprice, sum=0;
            int itemqty;
            for(PurchasedItem item : cart ) {
                itemid = item.getItemID();
                itemprice = item.getItemPrice();
                itemqty = item.getQty();
                
                sum += itemprice * itemqty;

                //get img from database
                imageSrc = "images/Almased.JPG";
        %>
        <tr>
            <td><a href="cart?action=remove&itemid=<%= itemid%>&returnurl=cart%3faction%3dshow"> <span class="remove-itm"> Remove </span></a> </td>
            <td><img alt="product picture" src="<%= imageSrc %>" height="50" width="50"/></td>
            <td>                
                <%= itemid %>                
            </td>
            <td><%= item.getItemName()%></td>
            <td> <%= itemprice%> </td>            
            <td>
                <div>
                <input size="4" id="qty<%=itemid%>" type="text" name="<%= AppEnvConst.CART_PARAM_QTY %>" value="<%= item.getQty()%>" 
                       onfocus="this.oldQty=this.value"
                       onchange="subttl('<%=itemid%>', <%=itemprice%>, this.oldQty, this.value)"/>
                    <input type="button" value="-" onclick="dec('<%=itemid%>',<%= itemprice%> )" />
                    <input type="button" value="+" onclick="inc('<%=itemid%>',<%= itemprice%> )" />
                </div>
            </td>
            <td> <input type="text" name="<%= AppEnvConst.CART_PARAM_COUPON_1 %>" /> </td>
            
            <td id="subtotal<%=itemid%>" align="right"> <%= itemqty*itemprice %></td>
        </tr>
        <%
            } 
        %>  
        </tbody>
        <tfoot ><td colspan="6" align="right">Sum</td> <td id="sum" colspan="1" align="right"><%= sum %></td> </tfoot>
    </table>
        <a onclick="javascript:return confirm('Empty the cart, are you sure?')" 
           href="cart?action=empty&returnurl=cart%3faction%3dshow"> 
            <span class="btnsubmit" style="color:red;" >Empty cart</span>
        </a>
            <input type="hidden" name='returnurl' value='cart/checkedout.jsp' />
            <input class="btnsubmit" type="submit" name="action" value="<%= AppEnvConst.CART_PARAM_ACTION_CHECKOUT %>" />
        </form>        
        </div>
    </div>
</div>    
        
<script>     

    function inc(itemid, myPrice) {
        var myQtyInput = 'qty' + itemid;
        var mySubTotal = 'subtotal' + itemid;
        var oldQty = document.getElementById(myQtyInput).value ;
        var newQty = (+oldQty +1) || 1;
        
        document.getElementById(myQtyInput).value = newQty;
        document.getElementById(mySubTotal).innerHTML = (newQty*myPrice).toFixed(2) ;

        sumChange(oldQty,newQty,myPrice);
        
    }
    
    function dec(itemid, myPrice) {
        var myQtyInput = 'qty' + itemid;
        var mySubTotal = 'subtotal' + itemid;        
        var oldQty = document.getElementById(myQtyInput).value ;        
        var newQty = (oldQty -1) || 1;
        
        document.getElementById(myQtyInput).value = newQty;
        document.getElementById(mySubTotal).innerHTML = (newQty*myPrice).toFixed(2);
        
        sumChange(oldQty,newQty,myPrice);
    }   

    function sumChange(oldQty, newQty, myPrice) {
        var sum = document.getElementById("sum").innerHTML;
        var newSum = sum - (oldQty * myPrice) + (newQty * myPrice);
        document.getElementById("sum").innerHTML = newSum.toFixed(2);                
    }
    function subttl(itemid,itemPrice, oldQty, newQty) {
        var mySubTotal = 'subtotal' + itemid;
        document.getElementById(mySubTotal).innerHTML = (itemPrice*newQty).toFixed(2);        
        
        sumChange(oldQty,newQty,itemPrice);    
    }
    
</script>
<%@ include file="/View/footer.jsp" %>