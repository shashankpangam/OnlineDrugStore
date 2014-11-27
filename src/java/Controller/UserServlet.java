/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import Model.Customer;
import java.sql.ResultSet;

/**
 *
 * @author Paramjyot
 */
public class UserServlet extends HttpServlet {
 
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
       // processRequest(request, response);
        
        String customerid=request.getParameter("customerid");
        String fname=request.getParameter("fname");
        String lname=request.getParameter("lname");
        String dob = request.getParameter("dob");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2"); 
        String city = request.getParameter("city");
        String zip = request.getParameter("zip");
        String province = request.getParameter("province"); 
        String email = request.getParameter("email"); 
        String gender = request.getParameter("gender"); 
        String passwd = request.getParameter("passwd");
        String repwd = request.getParameter("repwd");
        
      try (PrintWriter out = response.getWriter()) {
        
        Customer customer=new Customer(Integer.parseInt(customerid),fname,lname,dob,address1,address2,city,zip,province, gender,email,passwd,repwd);
        
        int custid=customer.getID();
        String custfname=customer.getFname();
        String custlname=customer.getLname();
        String custdob=customer.getDOB();
        String custaddress1=customer.getAddress1();
        String custaddress2=customer.getAddress2();
        String custcity=customer.getCity();
        String custzip=customer.getZip();
        String custprovince=customer.getProvince();
        String custemail=customer.getEmail();
        String custgender=customer.getGender();
        String custpasswd=customer.getPasswd();
        String custrepwd=customer.getRetypepwd();
  
          try {     
       stmt = conn.createStatement();
       if (conn!=null){
            ResultSet rs=stmt.executeQuery("SELECT * FROM  TBL_CUSTOMER WHERE email='"+email+"'");   
            if(!rs.next()){         
            stmt.executeUpdate("INSERT INTO TBL_CUSTOMER(CUSTOMERID,FIRSTNAME,LASTNAME,DOB,ADDRESS1,ADDRESS2,CITY,ZIP,PROVINCE,GENDER,EMAIL,USERNAME,PASSWORD) VALUES ("+custid+",'"+custfname+"','"+custlname+"', TO_DATE('"+custdob+"','yyyy-mm-dd') ,'"+custaddress1+"','"+custaddress2+"','"+custcity+"','"+custzip+"','"+custprovince+"','"+custgender+"','"+custemail+"', '"+custemail+"', '"+custpasswd+"' )");       
            stmt.close();
            
            out.println("Row Inserted Successfully");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	    rd.include(request, response);
            }
            else{
            out.println("User already registered!");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");   
            }
       }            
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
