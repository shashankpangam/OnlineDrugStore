/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ods.cart;

import com.ods.model.Order;
import com.ods.model.OrderDetail;
import com.ods.model.OrderHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CartManager stays alive as long as the whole application.
 * It is initialized in Context
 * 
 * CartManager holds all shoppingcart objects
 * @author brian
 */
public class CartManager implements java.io.Serializable {
    
    private static final Map<String, ShoppingCart> cartCenter = new HashMap<String, ShoppingCart>();
    private static CartManager cartManager;
    
    private CartManager() {
        
    }
    
    public static CartManager getCartManager() {

        if( cartManager == null) {
            cartManager = new CartManager();
        }
        
        return cartManager;
        
    }
    
    public boolean removeCart(String ownerID) {
        
        ShoppingCart cart = cartCenter.remove(ownerID);

        return (cart!=null);
    }

    /**
     * get the existing cart or create a new one
     * @param ownerID
     * @param createNew
     * @return cart or null
     */
    public ShoppingCart getCart(String ownerID, boolean createNew) {
        if( cartCenter.containsKey(ownerID ) ) {
            return cartCenter.get(ownerID);
        }
        else if( createNew ) {
            ShoppingCart cart = new ShoppingCart();
        
            cartCenter.put(ownerID, cart);
            
            return cart;
        }
        else {
            return null;
        }
        
    }
    
    public ShoppingCart getCart(String ownerID) {
        return this.getCart(ownerID, false);
    }
    

    
    
    public boolean checkOutCart(String ownerID) {
        
        return checkoutByID(ownerID);
    }
    
    /**
     * 
     * @param ownerID
     * @return true if cart is checked out.
     */
    public boolean checkoutByID(String ownerID)        
    {
        ShoppingCart cart = this.getCart(ownerID);
        
        if( null==cart ) {
            return false;
        }
        
        Order order = new Order(ownerID, cart.getTotalPrice(), new Date() );
        
        for(PurchasedItem item : cart ) {
            OrderDetail odtl = new OrderDetail(order, item.getItemID(), item.getItemName(), item.getItemPrice(),item.getQty() );
            order.getOrderDetails().add(odtl);
        }
        
        try {
            OrderHelper.saveOrder(order);
        } catch (SQLException ex) {
            Logger.getLogger(CartManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        
        return true;
    }
    

    
}