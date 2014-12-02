/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ods.cart;

/**
 *
 * @author brian
 */
public class ProductItem implements java.io.Serializable {
    
    private String itemID;
    private String itemName;
    private float itemPrice;
    private String itemCouponID;
    
    public ProductItem(ProductItem item) {
        this.itemID = item.itemID;
        this.itemName = item.itemName;
        this.itemPrice = item.itemPrice;
    }
    
    public ProductItem(String itemID, String name, float price /*, int categoryID */) {
        this.itemID = itemID;
        this.itemName = name;
        this.itemPrice = price;        
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
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the itemPrice
     */
    public float getItemPrice() {
        return itemPrice;
    }

    /**
     * @param itemPrice the itemPrice to set
     */
    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    
}
