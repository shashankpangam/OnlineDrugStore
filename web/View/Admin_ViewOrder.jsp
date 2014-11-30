<%-- 
    Document   : Admin View Order
    Created on : 21-Nov-2014, 1:43:11 PM
    Author     : meenakshimehta
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>

<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>

<%@include file="Header.jsp" %>

<div id="content">

    <%@include file="sidebar.jsp" %>
     <style>
               th,td{
                   border:1px solid darkslategrey;
               }
               td{
                   text-align: justify;
               }
           </style>
  <div class="contactform">  
        <p class="txtcontus" style="width: 600px;"><font color="white"><strong>View Orders </strong></font></p><br />

        <form method="GET" action=""> 
            <fieldset class="contusfs" style="width: 600px; height: 500px">
              <p style="padding-left: 30px; padding-top: 9px;">  <font color="red"></font> </p>
              <label style="margin-left: 310px">Order ID:</label>
            <INPUT style="margin-left: 5px" TYPE="TEXT" NAME="searchby" >
            <INPUT style="float: right;margin-right: 5px" TYPE="SUBMIT" name="search" value="Search">
          <br/><br/><br/>
        
        <%
              Connection conn=null;
              Context ctx=null;
              DataSource ds = null;
            try {
             ctx = new InitialContext();
             ds = (DataSource)ctx.lookup("jdbc/myDatasource");
             conn = ds.getConnection();
        } catch (NamingException ex) {
           
        } catch (SQLException ex) {
            
        }
        String searchby=request.getParameter("searchby");
     
       Statement stmt = conn.createStatement();
       ResultSet rs=stmt.executeQuery("select * from  TBL_ORDER");
     %>
         <table style="border-collapse: collapse;width:90%;border:1px solid black">       
           <tr>
          <th>ORDER ID</th>
            <th>Customer ID</th> 
            <th>Price</th> 
            <th>Date Ordered</th>          
          </tr>
  
     <%
       while(rs.next()){
         
            %>
           
          <tr>
              <td><%= rs.getInt(1) %></td>
              <td><%= rs.getInt(2) %></td>      
              <td><%= rs.getFloat(4) %></td>
              <td><%= rs.getDate(5) %></td>
          </tr>
               <% 
          
       } if(rs==null){
           out.println("No Order Found");
       }
            stmt.close();
       conn.close();
       
       %>

       
      </table>
            </fieldset>
        </form>       
    </div> 
</div>
    <%@include file="footer.jsp" %>

