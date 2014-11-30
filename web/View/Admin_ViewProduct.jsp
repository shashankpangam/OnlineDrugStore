
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
  <div class="contactform">  
        <p class="txtcontus" style="width: 600px;"><font color="white"><strong>View Products</strong></font></p><br />

        <form method="GET" action=""> 
            <fieldset class="contusfs" style="width: 600px; height: 500px">
              <p style="padding-left: 30px; padding-top: 9px;">  <font color="red"></font> </p>
              <label style="margin-left: 270px">Product Name:</label>
            <INPUT style="margin-left: 5px" TYPE="TEXT" NAME="searchby" >
            <INPUT style="float: right;margin-right: 5px" TYPE="SUBMIT" name="search" value="Search">
          <br/><br/><br/>
         <div style="margin-left: 10px;padding-right:20px; width:100%;overflow-y: scroll">
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
       ResultSet rs=stmt.executeQuery("select * from  TBL_PRODUCT where Lower(NAME) LIKE Lower('%"+searchby+"%')"); 
       
       while(rs.next()){
       if(rs.next()){                
            %>
    
       
                  <img src="<%= rs.getString(10) %>" alt="image" style="width:150px;height:160px;margin-right: 10px" align="right"/>   <br/>
                  <b>PRODUCT ID: <%= rs.getInt(1) %></b><br/><br/>
               Name:  <%= rs.getString(2) %>   <br/><br/>
               Description  : <%= rs.getString(3) %>   <br/><br/>
               Symptoms : <%= rs.getString(4) %>   <br/><br/>
               Category : <%= rs.getString(5) %><br/><br/>
               Price : <%= rs.getInt(6) %><br/><br/>
               Quantity : <%= rs.getInt(8) %><br/><br/>
               Discount ? :<%= rs.getInt(7) %><br/><br/>            
               Sold  : <%= rs.getInt(9) %>  
             
        
               <% 
           }
       
            else{
                out.println("Product does not exists. ");
            }
       }
       %>
            </div>
            </fieldset>
        </form>       
    </div> 
</div>
    <%@include file="footer.jsp" %>

