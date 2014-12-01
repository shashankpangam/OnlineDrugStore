import Model.Product;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

/**
 *
 * @author meenakshimehta
 */
@MultipartConfig
public class AdminServlet extends HttpServlet {
    private static File file;
    String path;
    Context ctx = null;
    DataSource ds = null;
    Connection conn =null;
       private static final String SAVE_DIR="images";
       String imagepath=path+"image";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String prodId = request.getParameter("productid");
        int productid=Integer.parseInt(prodId);       
        String productname=request.getParameter("productname");
        String description=request.getParameter("description");
        String symptoms = request.getParameter("symptoms");
        String category = request.getParameter("category");
        int price = Integer.parseInt(request.getParameter("price")); 
        int quantity = Integer.parseInt(request.getParameter("quantity")); 
 
         
        try (PrintWriter out = response.getWriter()) {
          
      if(productid ==0 || productname==null || !isAlphabet(productname) || description == null || symptoms==null || !isAlphabet(symptoms) || 
         category== "select" || price==0 || quantity ==0 || imagepath==null)
       {
          if(productid==0){
               out.println("<p> <font color=red>* Please enter Product ID <br/></font></p>");
          }
           if(productname == null || productname.equals("")){
	       out.println( "<p> <font color=red>* Please enter Product Name <br/></font></p>");		 
		}
              else if(!isAlphabet(productname)){
                   out.println("<p> <font color=red>* Please enter only alphabets for Productname<br/></font></p>");               
                    }
                 if(description == null){
			out.println( "<p> <font color=red>* Please give a proper description <br/></font></p>");
		      }
                    if(symptoms == null){
			out.println( "<p> <font color=red>* Please mention symptoms <br/></font></p>");
	          	}
                     else if(!isAlphabet(symptoms)){
                         out.println("<p> <font color=red>* Please enter only alphabets for Symptoms<br/></font></p>");               
                        }
                       if(category == "select"){
                           out.println( "<p> <font color=red>* Please select a Category <br/></font></p>");
                         }
                         if(price == 0){
			     out.println( "<p> <font color=red>* Please enter a price <br/></font></p>");
                           }
                           if(quantity == 0){
		         	out.println( "<p> <font color=red>* Please enter a quantity <br/></font></p>");
	                   }
                           
                 
       }
      else if(productid !=0 && productname!=null && isAlphabet(productname) && description != null && symptoms!=null && isAlphabet(symptoms) &&
         category!= "select" && price!=0 && quantity !=0 && imagepath!=null){
            if(request.getParameter("add").equals("Add")){
                doPost(request, response);
                RequestDispatcher rd = request.getRequestDispatcher("Admin_AddProduct.jsp");
       		rd.include(request, response);
            }
            RequestDispatcher rd = request.getRequestDispatcher("Admin_AddProduct.jsp");
       		rd.include(request, response);
            
      }

    }
    
 }
        public boolean isAlphabet(String name) {
                return name.matches("[a-zA-Z]+");
        } 
        public boolean isDigit(String number){
            return number.matches("[0-9]+");
        }
    
   
    public void init(ServletConfig cfg) throws ServletException{
        try {
             super.init(cfg);
             path = cfg.getInitParameter("Path");
             ctx = new InitialContext();
             ds = (DataSource)ctx.lookup("jdbc/myDatasource");
             conn = ds.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

        
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String prodId = request.getParameter("productid");
        int productid=Integer.parseInt(prodId);         
        String productname=request.getParameter("productname");
        String description=request.getParameter("description");
        String symptoms = request.getParameter("symptoms");
        String category = request.getParameter("category");
        float price = Float.parseFloat(request.getParameter("price")); 
        int quantity = Integer.parseInt(request.getParameter("quantity")); 
        int disupd = Integer.parseInt(request.getParameter("diss"));
        Product produpd=new Product(productid,productname,description,symptoms,category,price,disupd,quantity,0,null);
        int discc=produpd.getIfOnDiscount();
        int id=produpd.getProductID();
        String name=produpd.getProductName();
        String desc=produpd.getProductDescription();
        String symptom=produpd.getSymptoms();
        String cate=produpd.getProductCategory();
        float pric=produpd.getProductPrice();
        int quan=produpd.getProductQuantity();
        if(request.getParameter("update").equals("Update")){
             try {
                Statement stmtupdate = conn.createStatement();
                stmtupdate.executeUpdate("UPDATE TBL_PRODUCT SET NAME='"+name+"',DESCRIPTION='"+desc+"',SYMPTOMS='"+symptom+"',CATEGORY='"+cate+"',PRICE="+pric+",ONDISCOUNT="+discc+",QUANTITY="+quan+" WHERE PRODUCTID="+id+"");
                stmtupdate.close();
                conn.close();
                RequestDispatcher rd = request.getRequestDispatcher("Admin_UpdateProduct.jsp");
                rd.include(request, response);
                }
            
            catch( SQLException e ) {
             e.printStackTrace();
             }  
        
             

    }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
       
        processRequest(request, response);
        String prodId = request.getParameter("productid");
        int productid=Integer.parseInt(prodId);       
        String productname=request.getParameter("productname");
        String description=request.getParameter("description");
        String symptoms = request.getParameter("symptoms");
        String category = request.getParameter("category");
        float price = Float.parseFloat(request.getParameter("price")); 
        int quantity = Integer.parseInt(request.getParameter("quantity")); 
        int dis = Integer.parseInt(request.getParameter("discount"));
        int BUF_SIZE = 1024;
     
        
        byte buf[] = new byte[BUF_SIZE];
	int nread = 0, totalBytes = 0;
	PrintWriter out  = response.getWriter();
	response.setContentType("text/html");
	InputStream in = request.getInputStream();
	BufferedInputStream bis = new BufferedInputStream(in);
        FileOutputStream fos = new FileOutputStream(imagepath);
        nread = in.read(buf, 0, BUF_SIZE);
	
        while(nread >= 0){
		fos.write(buf, 0, nread);
		totalBytes += nread;
		nread = in.read(buf, 0, BUF_SIZE);
	}
	
        Product product=new Product(productid,productname,description,symptoms,category,price,dis,quantity,0,imagepath);
        int id=product.getProductID();
        String name=product.getProductName();
        String desc=product.getProductDescription();
        String symptom=product.getSymptoms();
        String cate=product.getProductCategory();
        float pric=product.getProductPrice();
        int quan=product.getProductQuantity();
        int disc=product.getIfOnDiscount();
      
         if(request.getParameter("add").equals("Add")){
         try {  
            
                 Statement stmt = conn.createStatement();
                 stmt.executeUpdate("INSERT INTO TBL_PRODUCT(PRODUCTID,NAME,DESCRIPTION,SYMPTOMS,CATEGORY,PRICE,ONDISCOUNT,QUANTITY,IMAGES) VALUES("+id+",'"+name+"','"+desc+"','"+symptom+"','"+cate+"',"+pric+","+disc+","+quan+",'"+imagepath+"')");   
                 stmt.close();
                 conn.close();
                 RequestDispatcher rd = request.getRequestDispatcher("Admin_AddProduct.jsp");
                 rd.include(request, response);
            }
       catch( SQLException e ) {
                 e.printStackTrace();
             }       
         }   
     }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
