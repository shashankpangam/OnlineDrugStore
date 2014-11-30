<%-- 
    Document   : Admin Update Product
    Created on : 21-Nov-2014, 1:43:11 PM
    Author     : meenakshimehta
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Header.jsp" %>
<div id="content">

    <%@include file="sidebar.jsp" %>
  <div class="contactform">  
        <p class="txtcontus" style="width: 600px;"><font color="white"><strong>Update Product Form</strong></font></p><br />
       
        
        <form method="get" action="AdminServlet"> 
            <fieldset class="contusfs" style="width: 600px; height: 500px">
              <p style="padding-left: 30px; padding-top: 9px;">  <font color="red">* Indicates a required field </font> </p>
            
                <div class="slide">
                    <table style="width:80%" cellspacing="15">
                        <tr>
                            <td>Product ID<font color="red">*</font>:</td>&nbsp;
                            <td><span class="error"><input type="text" name="productid" value="<%=request.getParameter("productid")%>"/><input type="submit" name="submit" value="search" style="border: none;display:none;background: none"/></span></td>		
                        </tr>
                            <%
              Connection conn=null;
              Context ctx=null;
              DataSource ds = null;
            try {
             ctx = new InitialContext();
             ds = (DataSource)ctx.lookup("jdbc/myDatasource");
             conn = ds.getConnection();
        } catch (NamingException ex) {
          } catch (SQLException ex) { }
       String productid=request.getParameter("productid");
       Statement stmt = conn.createStatement();
       ResultSet rs=stmt.executeQuery("select * from  TBL_PRODUCT where (PRODUCTID="+productid+")"); 
              while (rs.next()) {     
            %>
                        <tr>
                            <td><label>Product Name<font color="red">*</font>:</td>
                            <td><span class="error"><input type="text" name="productname" value="<%=rs.getString(2)%>"/></span></td>		
                        </tr>
                        <tr>
                            <td>Description<font color="red">*</font>:</td>
                            <td><span class="error"><textarea name="description" style="width:170px"/><%=rs.getString(3)%></textarea></span></td>		
                        </tr>
                        <tr>
                            <td>Symptoms<font color="red">*</font>:</td>
                            <td><span class="error"><textarea name="symptoms" style="width:170px"/><%=rs.getString(4)%></textarea></span></td>		
                        </tr>
                        <tr>
                            <td>Category<font color="red">*</font></td>
                            <td><span class="error"><select name="category" style="width:170px">
                                        <option value="<%=rs.getString(5)%>" selected="selected"><%=rs.getString(5)%></option>
                        
                                        <option value="Medicine">Medicine</option>
                                        <option value="Vitamins" >Vitamins</option>
                                        <option value="Diet & Fitness" >Diet & Fitness</option>
                                        <option value="Personal" >Personal</option>
                                        <option value="Featured" >Featured</option>
                                    </select></span></td>		
                        </tr>
                        <tr>
                            <td>Price<font color="red">*</font>:</td>
                            <td><input type="text" name="price" value="<%=rs.getInt(6)%>"/></td>		
                        </tr>
                     
                        <tr>
                            <td>Quantity<font color="red">*</font>:</td>
                            <td><span class="error"><input type="text" name="quantity" value="<%=rs.getInt(8)%>"/></span></td>		
                        </tr>
                        <tr>
                            <td>Image<font color="red">*</font>:</td>
                            <td><span class="error"><input type="file" name="productimage" value="<%=rs.getString(10)%>"/></span></td>		
                        </tr>
                          <tr>
                       
                             <td>On Discount<font color="red">*</font>:</td>
                             <td><span class="error"><input type="radio" id="one" name="diss" value="1" <% if(rs.getInt(9)==1) {%>checked="checked"<% } %>/></span></td>	
                            <td>Not On Discount<font color="red">*</font>:</td>
                            <td><span class="error"><input type="radio" id="two" name="diss" value="0" <% if(rs.getInt(9)==0){%>checked="checked"<% } %>/></span></td>		
                        </tr>
                    </table>
                        <% } %>
                    <div class="bonesubmit">
                        <input class="btnsubmit" type="submit" name="update" value="Update" >

                        <span class="btwosubmit">
                            <input class="btnsubmit" type="submit" name="clear" value="Clear" style="background-color:#CC3300;">
                        </span>
                    </div>
                </div>
            </fieldset>
        </form>   
        <%
 
        %>
    </div> 
</div>
    <%@include file="footer.jsp" %>

