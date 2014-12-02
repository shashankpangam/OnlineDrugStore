/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ods.servlet;

import com.ods.cart.CartManager;
import com.ods.cart.ProductItem;
import com.ods.cart.PurchasedItem;
import com.ods.cart.ShoppingCart;
import com.ods.util.AppEnvConst;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author brian
 */
@WebServlet(name = "MyCart", urlPatterns = {"/cart"})
public class MyCart extends HttpServlet {

    
    private ShoppingCart cart;
    private CartManager cm;
    //
    private int action;
    private String rtnUrl;
    private String userID;

    private String item_id, item_name, item_price, item_qty, item_couponcode;

    //private Customer curUser;

    private static final int ACTION_ADD = 0, ACTION_REMOVE = 1, ACTION_SHOW = 2, ACTION_EMPTY = 3, ACTION_CHECKOUT = 4, ACTION_DONE_CHECKOUT=5, ACTION_INVALID = -1;

    protected void getForm(HttpServletRequest request) {
        String form_action = request.getParameter(AppEnvConst.CART_PARAM_ACTION);
        rtnUrl = request.getParameter(AppEnvConst.CART_PARAM_RTNURL);
        
        if (form_action == null) {
            action = ACTION_INVALID;
        } else {
            switch (form_action) {
                case AppEnvConst.CART_PARAM_ACTION_ADD:
                    action = ACTION_ADD;
                    break;
                case AppEnvConst.CART_PARAM_ACTION_REMOVE:
                    action = ACTION_REMOVE;
                    break;
                case AppEnvConst.CART_PARAM_ACTION_SHOW:
                    action = ACTION_SHOW;
                    break;
                case AppEnvConst.CART_PARAM_ACTION_EMPTY:
                    action = ACTION_EMPTY;
                    break;
                case AppEnvConst.CART_PARAM_ACTION_CHECKOUT:
                    action = ACTION_CHECKOUT;
                    break;

                default:
                    action = ACTION_INVALID;
            }
        }
    }

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
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        cm = (CartManager) getServletContext().getAttribute(AppEnvConst.CART_MANAGER);
        
        this.getForm(request);
        
        cart = (ShoppingCart) session.getAttribute(AppEnvConst.SHOPPING_CART);
        userID = (String) session.getAttribute(AppEnvConst.SESSION_USERID);
        if (cart == null) {
            cart = cm.getCart(userID, true);
            session.setAttribute(AppEnvConst.SHOPPING_CART, cart);
        }
        RequestDispatcher rd;
        switch (action) {
            case ACTION_ADD:
                actADD(request,response);
                break;
            case ACTION_REMOVE:
                actREMOVE(request,response);
                break;
            case ACTION_SHOW:
                actSHOW(request,response);
                break;
            case ACTION_CHECKOUT:
                actCHECKOUT(request,response);
                break;
            case ACTION_DONE_CHECKOUT:
                actDONECHECKOUT(request,response);
                break;                
            case ACTION_EMPTY:
                actEMPTY(request, response);
                break;
            case ACTION_INVALID:
            default:
                response.sendRedirect(rtnUrl);
                //rd.forward(request, response);
        }
        
        return ;
    }
    
    private void actREMOVE(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        item_id = request.getParameter(AppEnvConst.CART_PARAM_ID);
        
        cart.remove(item_id);
        
        if( rtnUrl == null ) {
            RequestDispatcher rd;

            rd = getServletContext().getRequestDispatcher("/View/cart/view_master.jsp?content=" + AppEnvConst.VIEWMASTER_CMD_VIEWCART);

            rd.forward(request, response);
        }
        else {
            response.sendRedirect(rtnUrl);
        }
       
        return;
    }
    
    private void actADD(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        item_id = request.getParameter(AppEnvConst.CART_PARAM_ID);
        item_name = request.getParameter(AppEnvConst.CART_PARAM_NAME);
        item_price = request.getParameter(AppEnvConst.CART_PARAM_PRICE);

        item_qty = request.getParameter(AppEnvConst.CART_PARAM_QTY);
        item_couponcode = request.getParameter(AppEnvConst.CART_PARAM_COUPON_1);
                
        cart.add(new ProductItem(item_id, item_name, Float.parseFloat(item_price)), 
                Integer.parseInt( item_qty ), item_couponcode );
                
        if( rtnUrl == null ) { //not return url specified, then show the cart
            RequestDispatcher rd;
        
            rd = getServletContext().getRequestDispatcher("/View/cart/view_master.jsp?content=" + AppEnvConst.VIEWMASTER_CMD_VIEWCART);

            rd.forward(request, response);

        }
        else {
            response.sendRedirect(rtnUrl);
        }
        
        return;
    }    
    private void actSHOW(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setAttribute(AppEnvConst.SHOPPING_CART, cart);
        
        RequestDispatcher rd;
        
        rd = getServletContext().getRequestDispatcher("/View/cart/view_master.jsp?content=" + AppEnvConst.VIEWMASTER_CMD_VIEWCART);
        
        rd.forward(request, response);
        
        return;
    }
    
    private void actEMPTY(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        cart.clear();
        if( rtnUrl == null ) {
            RequestDispatcher rd;

            rd = getServletContext().getRequestDispatcher("/View/cart/view_master.jsp?content=" + AppEnvConst.VIEWMASTER_CMD_VIEWCART);

            rd.forward(request, response);
        }
        else {
            response.sendRedirect(rtnUrl);
        }        
        return;
    }

    private void actDONECHECKOUT(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        
    }
    
    private void actCHECKOUT(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {               
        String[] newqtys = (String[]) request.getParameterValues(AppEnvConst.CART_PARAM_QTY);
        String[] coupons = (String[])request.getParameterValues(AppEnvConst.CART_PARAM_COUPON_1);
        
        if( userID == null || userID.isEmpty() ) {
            
            userID = (String) request.getSession().getAttribute(AppEnvConst.SESSION_USERID);
        }
        
        ShoppingCart cart =cm.getCart(userID);
        
        int i=0;
        for(PurchasedItem item : cart) {
            item.setQty( Integer.parseInt( newqtys[i]) );
            item.setCouponID( coupons[i] );
            i++;
        }
        
        if( cm.checkOutCart(userID) ) {

            cart.clear();
            if( rtnUrl == null ) {

                RequestDispatcher rd;

                rd = getServletContext().getRequestDispatcher("/View/cart/view_master.jsp?content=" + AppEnvConst.VIEWMASTER_CMD_DONE_CHECKOUT);

                rd.forward(request, response);                
            }
            else {
                response.sendRedirect(rtnUrl);
            }            
        }
        else {
            RequestDispatcher rd;

            rd = getServletContext().getRequestDispatcher("/View/cart/view_master.jsp?content=" + AppEnvConst.VIEWMASTER_CMD_ERROR);

            rd.forward(request, response);                            
        }
        
        return;
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    protected void testServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowCart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }// </editor-fold>

}
