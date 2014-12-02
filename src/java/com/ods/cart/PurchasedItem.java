/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ods.cart;

import java.util.Date;


/**
 * Products selected by customer, including qty, coupons.
 * 
 * @author brian
 */
public class PurchasedItem extends ProductItem implements java.io.Serializable  {
    private int qty;
    private String couponID;
    private Date date;

    public PurchasedItem(ProductItem item, int qty, String couponID) {
        super(item);
        this.qty = qty;
        this.couponID = couponID;
        this.date = new Date();
    }
    

    
//    public PurchasedItem setCoupon(String couponID) {
//        this.couponID = couponID;
//        return this;
//    }
//    
//    public PurchasedItem getCoupon(String couponID) {
//        return this;
//    }
//    
//    public PurchasedItem setQty(int qty) {
//        this.qty = qty;
//        return this;
//    }
//    
//    public int getQty() {
//
//        return this.qty;
//    }

    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * @return the couponID
     */
    public String getCouponID() {
        return couponID;
    }

    /**
     * @param couponID the couponID to set
     */
    public void setCouponID(String couponID) {
        this.couponID = couponID;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
        
}