<%-- 
    Document   : Admin Add Product
    Created on : 21-Nov-2014, 1:43:11 PM
    Author     : meenakshimehta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="Header.jsp" %>
<div id="content">

    <%@include file="sidebar.jsp" %>
  <div class="contactform">  
        <p class="txtcontus" style="width: 600px;"><font color="white"><strong>Update Product Form</strong></font></p><br />

        <form method="post" action="Admin_UpdateProduct.jsp"> 
            <fieldset class="contusfs" style="width: 600px; height: 500px">
              <p style="padding-left: 30px; padding-top: 9px;">  <font color="red">* Indicates a required field </font> </p>
               
                <div class="slide">
                    <table style="width:80%" cellspacing="15">
                        
                        <tr>
                            <td>Product ID<font color="red">*</font>:</td>&nbsp;
                            <td><span class="error"><input type="text" name="productid" value=""/></span></td>		
                        </tr>
                        <tr>
                            <td><label>Product Name<font color="red">*</font>:</td>
                            <td><span class="error"><input type="text" name="productname" value=""/></span></td>		
                        </tr>
                        <tr>
                            <td>Description<font color="red">*</font>:</td>
                            <td><span class="error"><textarea name="description" style="width:170px"/></textarea></span></td>		
                        </tr>
                        <tr>
                            <td>Symptoms<font color="red">*</font>:</td>
                            <td><span class="error"><textarea name="symptoms" value="" style="width:170px"/></textarea></span></td>		
                        </tr>
                        <tr>
                            <td>Category<font color="red">*</font></td>
                            <td><span class="error"><select name="category" style="width:170px">
                                        <option value="Select" seleceted="selected">Select a Category</option>
                                        <option value="Medicine">Medicine</option>
                                        <option value="Vitamins" >Vitamins</option>
                                        <option value="Diet & Fitness" >Diet & Fitness</option>
                                        <option value="Personal" >Personal</option>
                                        <option value="Featured" >Featured</option>
                                    </select></span></td>		
                        </tr>
                        <tr>
                            <td>Price<font color="red">*</font>:</td>
                            <td><input type="text" name="price" value=""/></td>		
                        </tr>
                     
                        <tr>
                            <td>Quantity<font color="red">*</font>:</td>
                            <td><span class="error"><input type="text" name="quantity" value=""/></span></td>		
                        </tr>
                        <tr>
                            <td>Image<font color="red">*</font>:</td>
                            <td><span class="error"><input type="file" name="productimage" value=""/></span></td>		
                        </tr>
                          <tr>
                              <td>On Discount<font color="red">*</font>:</td>
                            <td><span class="error"><input type="radio" name="discount" value="1"/></span></td>	
                            <td>Not On Discount<font color="red">*</font>:</td>
                            <td><span class="error"><input type="radio" name="discount" value="0"/></span></td>		
                        </tr>
                    </table>
                    <div class="bonesubmit">
                        <input class="btnsubmit" type="submit" name="edit" value="Update" >

                        <span class="btwosubmit">
                            <input class="btnsubmit" type="submit" name="clear" value="Clear" style="background-color:#CC3300;">
                        </span>
                    </div>
                </div>
            </fieldset>
        </form>       
    </div> 
</div>
    <%@include file="footer.jsp" %>

