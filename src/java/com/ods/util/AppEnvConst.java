/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ods.util;

/**
 * keys used for passing values between session, request, GET/POST
 * @author brian
 */
public final class AppEnvConst {
    public static final String CART_MANAGER = "com.dos.cart.CartManager";
    public static final String ACTIVE_USER = "com.dos.user.Customer";
    public static final String SHOPPING_CART = "com.dos.cart.ShoppingCart";
    
    public static final String CART_PARAM_ACTION = "action";
        public static final String CART_PARAM_ACTION_ADD = "add";
        public static final String CART_PARAM_ACTION_REMOVE = "remove";
        public static final String CART_PARAM_ACTION_SHOW = "show";
        public static final String CART_PARAM_ACTION_EMPTY = "empty";
        public static final String CART_PARAM_ACTION_CHECKOUT = "Checkout";

    public static final String CART_PARAM_USERID = "userid";
    public static final String CART_PARAM_RTNURL = "returnurl";
    public static final String CART_PARAM_ID = "itemid";        
    public static final String CART_PARAM_NAME = "itemname";    
    public static final String CART_PARAM_PRICE = "itemprice";    
    public static final String CART_PARAM_QTY = "itemqty";
    public static final String CART_PARAM_COUPON_1 = "itemcouponcode";
    public static final String CART_PARAM_COUPON_2 = "ordercouponcode";
 
    public static final String VIEWMASTER_COMMAND ="content";
    public static final String VIEWMASTER_CMD_DONE_CHECKOUT ="donecheckout";
    public static final String VIEWMASTER_CMD_VIEWCART ="viewcart";
    public static final String VIEWMASTER_CMD_ERROR ="error";
    
    public static final String SESSION_USERID = "sessioncustid";
//           session.setAttribute("sessionfname", customer.getFname());
//           session.setAttribute("sessionlname", customer.getLname());
//           session.setAttribute("sessiondob", customer.getDOB());
//           session.setAttribute("sessionaddress1", customer.getAddress1());
//           session.setAttribute("sessionaddress2", customer.getAddress2());
//           session.setAttribute("sessioncity", customer.getCity());
//           session.setAttribute("sessionzip", customer.getZip());
//           session.setAttribute("sessionprovince", customer.getProvince());
//           session.setAttribute("sessionemail", customer.getEmail());
}
