/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Customer;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Paramjyot
 */
public class LoginServlet extends HttpServlet {
    private static File file;
    Context ctx = null;
    DataSource ds = null;
    Connection conn =null;
    private Statement stmt;
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          HttpSession session = request.getSession();
       // processRequest(request, response);
         try (PrintWriter out = response.getWriter()) {
             
             if(request.getParameter("next").equals("Register")){
              RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
	      rd.include(request, response);
                 }
     
        if(request.getParameter("next").equals("Login")){

       Customer customer;
       String email = request.getParameter("email");
       String passwd = request.getParameter("passwd");
       Statement stmt = conn.createStatement();
       ResultSet rs=stmt.executeQuery("SELECT * FROM  TBL_CUSTOMER WHERE email='"+email+"'");   
              
           if(rs.next()){   
           out.println("User Found....");
           out.println("Authenticating........");
               if((rs.getString("PASSWORD")).equals(request.getParameter("passwd"))){
   
           customer=new Customer();
           
           customer.setID(rs.getInt("CUSTOMERID"));
           customer.setFname(rs.getString("FIRSTNAME"));
           customer.setLname(rs.getString("LASTNAME"));
           customer.setDOB(rs.getString("DOB"));
           customer.setAddress1(rs.getString("ADDRESS1"));
           customer.setAddress2(rs.getString("ADDRESS2"));
           customer.setCity(rs.getString("CITY"));
           customer.setZip(rs.getString("ZIP"));
           customer.setProvince(rs.getString("PROVINCE"));
           customer.setGender(rs.getString("GENDER"));
           customer.setEmail(rs.getString("EMAIL"));
           customer.setUname(rs.getString("USERNAME"));
           customer.setPasswd(rs.getString("PASSWORD"));
          // customer.setLastSignIn(rs.getTimestamp("LASTSIGNIN"));
                     
           session.setAttribute("sessioncustid", customer.getID());
           session.setAttribute("sessionfname", customer.getFname());
           session.setAttribute("sessionlname", customer.getLname());
           session.setAttribute("sessiondob", customer.getDOB());
           session.setAttribute("sessionaddress1", customer.getAddress1());
           session.setAttribute("sessionaddress2", customer.getAddress2());
           session.setAttribute("sessioncity", customer.getCity());
           session.setAttribute("sessionzip", customer.getZip());
           session.setAttribute("sessionprovince", customer.getProvince());
           session.setAttribute("sessionemail", customer.getEmail());
           session.setAttribute("sessionuname", customer.getEmail());
           session.setAttribute("sessionpasswd", customer.getPasswd());
           
          
           
           RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
	   rd.forward(request, response);
 
           // setting SESSION
      
           //request.setAttribute("customer",customer);
               }
               
           out.println("Wrong Password! Try Again");
                    
               
           RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
           
           }

       
           if(!rs.next()) {
               out.println("No Data found");
               out.println("Invalid Credentials! Try Again! ");
            }  
       stmt.close();
      // conn.close();
       

  
//        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
//        rd.forward(request, response);
//        }
//        
//        
    }
        
         } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }}
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    
     public void init (ServletConfig config) throws ServletException
        {
            
           
        try {
            ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("jdbc/myDatasource");
            conn = ds.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (SQLException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
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
