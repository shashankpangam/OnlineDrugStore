/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ods.filter;

import Model.Customer;

import com.ods.util.AppEnvConst;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User-auth checked here. if no active user present in session, 
 * redirect to login page.
 * @author brian
 */
@WebFilter(
        urlPatterns = "/cart",
        filterName = "AuthFilter",
        description = "Authorized access only"       
)
public final class AuthFilter implements Filter {
    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) 
	throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException 
    {
//        if( true) 
//            return;
        
        
        HttpServletRequest servReq = null;
        if (response instanceof HttpServletResponse) {
            servReq = (HttpServletRequest) request;
        }
                
        if (filterConfig == null) {
            return;
        }
        
//        try {
//            String curUserID = Integer.toString((int)servReq.getSession().getAttribute(AppEnvConst.SESSION_USERID));
//            if( curUserID==null || curUserID.isEmpty() ) { //not logined in
//                HttpServletResponse resp = (HttpServletResponse) response;
//                resp.sendRedirect("login.jsp");
//            } 
//            else {
//                chain.doFilter(request, response);                
//            }
//        } catch (java.lang.NullPointerException e) { //casting null active user
//            HttpServletResponse resp = (HttpServletResponse) response;
//            resp.sendRedirect("login.jsp");
//        }
        //For test
        servReq.getSession().setAttribute(AppEnvConst.SESSION_USERID, "HB001");
        
        chain.doFilter(request, response);
        //Fortest end
        return;
    }
}
