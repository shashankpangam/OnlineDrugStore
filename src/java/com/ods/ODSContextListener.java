/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author brian
 */
package com.ods;

import com.ods.cart.CartManager;
import com.ods.util.AppEnvConst;
import javax.servlet.ServletContextEvent;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;


/**
 * Servlet context listener
 * 
 * @author Bo Huang
 */
@WebListener()
public class ODSContextListener implements javax.servlet.ServletContextListener {
   
    @Override    
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(AppEnvConst.CART_MANAGER, CartManager.getCartManager());
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        CartManager cm = (CartManager)sce.getServletContext().getAttribute(AppEnvConst.CART_MANAGER);
        //check shopping cart center to see if any cart 
    }
}
