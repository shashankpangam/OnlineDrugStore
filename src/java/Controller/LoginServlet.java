/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import java.io.File;
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
import javax.servlet.ServletConfig;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Product;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 *
 * @author Paramjyot
 */
public class LoginServlet extends HttpServlet {

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

//    if(request.getParameter("next").equals("Logout")){
//        session.setAttribute("userid", null);
//        session.invalidate();
//        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
//    }
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
        String action = request.getParameter("action");
        String returnURL = request.getParameter("returnURL");
        ServletConfig cfg = getServletConfig();
        if (action.equals("Login")) {
            ServletContext sc = cfg.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher(returnURL);
            rd.forward(request, response);
        } else if (action.equals("Register")) {
            ServletContext sc = cfg.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher(returnURL);
            rd.forward(request, response);
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
        HttpSession session = request.getSession(true);
        ServletConfig cfg = getServletConfig();
        try (PrintWriter out = response.getWriter()) {

            String nextAct = request.getParameter("next");
            if (nextAct.equalsIgnoreCase("Register")) {
                ServletContext sc = cfg.getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/View/register.jsp");
                rd.forward(request, response);
            } else if (nextAct.equalsIgnoreCase("Logout")) {

                session.setAttribute("sessionfname", null);
                session.invalidate();
                ServletContext sc = cfg.getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/View/index.jsp");
                rd.forward(request, response);
            } else if (nextAct.equalsIgnoreCase("Login")) {
                String email = request.getParameter("email");
                String passwd = request.getParameter("passwd");

                if (email.equals("admin") && passwd.equals("admin")) {
                    out.println("admin");
                    ServletContext sc = cfg.getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/View/Admin_AddProduct.jsp");
                    rd.forward(request, response);
                } else {

                    Customer customer = Customer.loginAction(email, passwd);
                    if (customer != null) {
                        out.println("User Found....");
                        out.println("Authenticating........");

                        session.setAttribute("sessioncustid", customer.getCustomerid());
                        session.setAttribute("sessionfname", customer.getFname());
                        session.setAttribute("sessionlname", customer.getLname());
                        session.setAttribute("sessiondob", customer.getDob());
                        session.setAttribute("sessionaddress1", customer.getAddress1());
                        session.setAttribute("sessionaddress2", customer.getAddress2());
                        session.setAttribute("sessioncity", customer.getCity());
                        session.setAttribute("sessionzip", customer.getZip());
                        session.setAttribute("sessionprovince", customer.getProvince());
                        session.setAttribute("sessionemail", customer.getEmail());
                        session.setAttribute("sessionuname", customer.getEmail());
                        session.setAttribute("sessionpasswd", customer.getPasswd());

                        ServletContext sc = cfg.getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/View/index.jsp");
                        rd.forward(request, response);
                    } else {
                        ServletContext sc = cfg.getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/View/login.jsp");
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sessioninvalid(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("sessionfname", null);
        session.invalidate();
        ServletConfig cfg = getServletConfig();
        ServletContext sc = cfg.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/View/index.jsp");
//        }
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
