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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;

/**
 *
 * @author Paramjyot
 */
public class UserServlet extends HttpServlet {

//    Context ctx = null;
//    DataSource ds = null;
//    Connection conn = null;
//    private Statement stmt;
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
        //HttpSession session = request.getSession();

    }

    boolean digits(String id) {
        return id.matches("[0-9]+");
    }

    boolean alpha(String name) {
        return name.matches("[a-zA-Z]+");
    }

    boolean emailregex(String email) {
        return email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

    boolean datecheck(String date) {
        return date.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$");
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

//    public void init(ServletConfig config) throws ServletException {
//
//        try {
//            ctx = new InitialContext();
//            ds = (DataSource) ctx.lookup("jdbc/myDatasource");
//            conn = ds.getConnection();
//        } catch (NamingException ex) {
//            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
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
        ServletConfig cfg = getServletConfig();
        
        //String customerid = request.getParameter("customerid");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
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

      //  boolean custidisvalid = false;
        boolean fnameisvalid = false;
        boolean lnameisvalid = false;
        boolean dobisvalid = false;
        boolean address1isvalid = false;
        boolean address2isvalid = false;
        boolean cityisvalid = false;
        boolean zipisvalid = false;
        boolean provinceisvalid = false;
        boolean emailisvalid = false;
        boolean genderisvalid = false;
        boolean passwdisvalid = false;
        boolean repwdisvalid = false;

        try (PrintWriter out = response.getWriter()) {
            //Validation               
            if (fname.equals("") || lname.equals("") || dob.equals("") || address1.equals("")
                    || address2.equals("") || city.equals("") || zip.equals("") || province.equals("") || email.equals("") || gender.equals("") || passwd.equals("")
                    || repwd.equals("")) {
               

                if (fname == null || fname.equals("")) {
                    out.println("<p style='color:red'>Please enter your First Name <p/>");
                }
                if (lname == null || lname.equals("")) {
                    out.println("<p style='color:red'>Please enter your Last Name <p/>");
                }
                if (dob == null || dob.equals("")) {
                    out.println("<p style='color:red'>Please enter your Date Of Birth <p/>");
                }
                if (address1 == null || address1.equals("") || address2 == null || address2.equals("")) {
                    out.println("<p style='color:red'>Please enter your address <p/>");
                }

                if (city == null || city.equals("")) {
                    out.println("<p style='color:red'>Please enter your City<p/>");
                }
                if (zip == null || zip.equals("")) {
                    out.println("<p style='color:red'>Please enter your ZipCode<p/>");
                }
                if (province == null || province.equals("")) {
                    out.println("<p style='color:red'>Please enter your Province<p/>");
                }
                if (gender == null || gender.equals("")) {
                    out.println("<p style='color:red'>Please enter your Gender<p/>");
                }
                if (passwd == null || passwd.equals("")) {
                    out.println("<p style='color:red'>Please enter your Password<p/>");
                }
                if (repwd == null || repwd.equals("")) {
                    out.println("<p style='color:red'>Please enter your Retyped Password<p/>");
                }
            } else if (!alpha(fname) || (!alpha(lname))) {
                out.println("<p style='color:red'>Enter only alphabets for Name <p />");
            } else if ((zip.length() != 6)) {
                out.println("<p style='color:red'>Zip should be 6 in length<p/>");
            } else if ((passwd.length() != 6) || (repwd.length() != 6)) {
                out.println("<p style='color:red'>Password should be 6 in length<p/>");
            } else if (!emailregex(email)) {
                out.println("<p style='color:red'>Invalid Email Address<p/>");
            } else if (!datecheck(dob)) {
                out.println("<p style='color:red'>Invalid DOB<p/>");
            } else {
                
                fnameisvalid = true;
                lnameisvalid = true;
                dobisvalid = true;
                address1isvalid = true;
                address2isvalid = true;
                cityisvalid = true;
                zipisvalid = true;
                provinceisvalid = true;
                emailisvalid = true;
                genderisvalid = true;
                passwdisvalid = true;
                repwdisvalid = true;
            }

            //when valid
            if ((fnameisvalid == true) && (lnameisvalid == true) && (dobisvalid == true) && (address1isvalid == true)
                    && (address2isvalid == true) && (cityisvalid == true) && (zipisvalid == true) && (provinceisvalid == true) && (emailisvalid == true)
                    && (genderisvalid == true) && (passwdisvalid == true) && (repwdisvalid == true)) {
                Customer customer = new Customer(0, fname, lname, dob, address1, address2, city, zip, province, gender, email, passwd, null);

               // int custid = customer.getCustomerid();
                String custfname = customer.getFname();
                String custlname = customer.getLname();
                String custdob = customer.getDob();
                String custaddress1 = customer.getAddress1();
                String custaddress2 = customer.getAddress2();
                String custcity = customer.getCity();
                String custzip = customer.getZip();
                String custprovince = customer.getProvince();
                String custemail = customer.getEmail();
                String custgender = customer.getGender();
                String custpasswd = customer.getPasswd();
                //String custrepwd = customer.getRetypepwd();

                try {
                    boolean flag = Customer.insertNewCustomer(customer);
                    if (flag = true) {
                        out.println("Row Inserted Successfully");
                        ServletContext sc = cfg.getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("View/login.jsp");
                        rd.forward(request, response);
                    } else {
                        out.println("User already registered!");
                        ServletContext sc = cfg.getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/View/login.jsp");
                        rd.forward(request, response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                out.println("Invalid data!");
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
