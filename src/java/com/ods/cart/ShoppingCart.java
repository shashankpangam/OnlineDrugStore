/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ods.cart;

//import com.ods.PurchasedItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * ShoppingCart holds and manages all items purchased by customer.
 * 
 * @author brian
 */
public class ShoppingCart extends ArrayList<PurchasedItem> implements java.io.Serializable {
    
    //private List<PurchasedItem> itemList = new ArrayList<PurchasedItem>();
   
    /**
     * Default constructor
     * @
     * @param none
     */
    protected ShoppingCart() {
        
    }
        

    /**
     * Sum of price for all items in cart
     * @return float sum
     */
    public float getTotalPrice() {
        float sum=0;
    
        for(PurchasedItem item : this ) {
            sum += item.getItemPrice();
        }
        return sum;
    }

    public void add(ProductItem productItem, int item_qty, String item_couponcode) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        for(PurchasedItem item : this) {
            if( item.getItemID().equalsIgnoreCase( productItem.getItemID() ) ) {
                item.setQty( item.getQty() + item_qty);
                return;
            }
        }
        this.add( new PurchasedItem(productItem, item_qty, item_couponcode) );
    }
    
    public boolean remove(String item_id) {
        Iterator<PurchasedItem> itor = this.iterator();
        
        while(itor.hasNext()) {
            PurchasedItem item = itor.next();
            if( item.getItemID().equals(item_id ) ) {
                this.remove(item);
                return true;
            }
        }
        return false;
    }
}
