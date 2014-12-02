/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ods.model;

import java.util.Date;

/**
 *
 * @author brian
 */
public class OrderDetail {
    
    private Order order;
    private String itemID;
    private String name;
    private float price;
    private int qty;
    private String couponID;

    public OrderDetail() {}
    
    public OrderDetail(Order order,String itemID, String name, float price, int qty) {
        this.order = order;
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.qty = qty;       
    }    

    public OrderDetail(Order order,String itemID, String name, float price, int qty, String cID ) {
        this.order = order;
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.couponID = cID;
        
    }    
    
    /**
     * @return the orderID
     */
    public Order getOrder() {
        return order;
    }    
    /**
     * @param orderID the orderID to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @return the itemID
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

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
    
}
