/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ods.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author brian
 */
public class Order {
    private String orderID; //pk
    private String customerID;
    private String couponID;
    private float sumPrice;
    private Date orderDate;
    //one-to-many
    private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);    
    
    public Order() {
    
    }
    
    public Order(String cusid, float sum, Date date) {
        this.customerID = cusid;
        this.sumPrice = sum;
        this.orderDate = date;
    }
    
    public Order(String cusid, String cpid, float sum, Date date) {
        this.customerID = cusid;
        this.couponID = cpid;
        this.sumPrice = sum;
        this.orderDate = date;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the customerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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
     * @return the sumPrice
     */
    public float getSumPrice() {
        return sumPrice;
    }

    /**
     * @param sumPrice the sumPrice to set
     */
    public void setSumPrice(float sumPrice) {
        this.sumPrice = sumPrice;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the orderDetails
     */
    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    /**
     * @param orderDetails the orderDetails to set
     */
    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
