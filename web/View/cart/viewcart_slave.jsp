<%-- 
    Document   : viewcart_slave
    Created on : 1-Dec-2014, 12:52:15 AM
    Author     : brian
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.ods.model.ODSDatabase"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.ods.util.AppEnvConst" %>
<%@page import="com.ods.cart.*" %>


<%!
    ShoppingCart cart; 
    //Customer curUser;
    String cur_userid;
%>

<div id="inside">
    <%
        
        cart = (ShoppingCart)request.getAttribute(AppEnvConst.SHOPPING_CART);
        //For test purpose
        if( cart.size() ==0 ) {           
    %>
    <h1> Your cart is empty! </h1>
    <%
            return;
//        cart.add(new PurchasedItem(new ProductItem("2000000001","VITD",5.6f),10,"coupn001"));
//        cart.add(new PurchasedItem(new ProductItem("2000000003","VITC",3.6f),20,"coupn002"));
//        cart.add(new PurchasedItem(new ProductItem("2000000005","VITE",6.6f),5,"coupn003"));
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

        <%! 
            Connection con = ODSDatabase.getDB();
            Statement stmt;
            String itemid, itemname;
            String imageSrc;
            float itemprice, sum=0;
            int itemqty;
            String itemcpn;
        %>
        <%
            
            //stmt = con.createStatement();
            String sql = "SELECT images from tbl_product where productid=?";
            
            PreparedStatement pSt = con.prepareStatement(sql);
            sum=0;
            for(PurchasedItem item : cart ) {
                itemid = item.getItemID();
                itemprice = item.getItemPrice();
                itemqty = item.getQty();
                itemcpn = item.getCouponID();
                if( itemcpn == null ) {
                    itemcpn = "";
                }
                sum += itemprice * itemqty;

                //get img from database
                pSt.setInt(1, Integer.parseInt(itemid));
                ResultSet result = pSt.executeQuery();
                if(result.next()) {
                    imageSrc = result.getString(1); //.substring(3);
                }
                //imageSrc = "images/Almased.JPG";
        %>
        <tr>
            <td><a href="cart?action=remove&itemid=<%= itemid%>&returnurl=cart%3faction%3dshow"> <span class="remove-itm"> Remove </span></a> </td>
            <td><img alt="product picture" src="<%= imageSrc %>" height="50" width="50"/></td>
            <td><%= itemid %> </td>
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
            <td> <input type="text" size="6" name="<%= AppEnvConst.CART_PARAM_COUPON_1 %>" value="<%= itemcpn %>"/> </td>
            
            <td id="subtotal<%=itemid%>" align="right"> <%= itemqty*itemprice %></td>
        </tr>
        <%
            } 
            pSt.close();
            //con.close();
        %>  
        </tbody>
        <tfoot ><td colspan="6" align="right">Sum</td> <td id="sum" colspan="1" align="right"><%= sum %></td> </tfoot>
    </table>
        <a onclick="javascript:return confirm('Empty the cart, are you sure?')" 
           href="cart?action=empty&returnurl=cart%3faction%3dshow"> 
            <span class="btnsubmit" style="color:red;" >Empty cart</span>
        </a>
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

