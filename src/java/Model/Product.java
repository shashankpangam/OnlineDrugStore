/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ShashankPangam
 */
public class Product {

    private int productid;
    private String name;
    private String description;
    private String symptoms;
    private String category;
    private float price;
    private int ondiscount;
    private int quantity;
    private int sold;
    private String image;

    public Product(int id, String name, String desc, String symptoms, String category, float price, int ondis, int qty, int sold, String image) {
        this.productid = id;
        this.name = name;
        this.description = desc;
        this.category = category;
        this.ondiscount = ondis;
        this.price = price;
        this.quantity = qty;
        this.sold = sold;
        this.symptoms = symptoms;
        this.image = image;
    }

    public int getProductID() {
        return this.productid;
    }

    public String getProductName() {
        return this.name;
    }

    public void setProductName(String name) {
        this.name = name;
    }

    public String getProductDescription() {
        return this.description;
    }

    public void setProductDescription(String desc) {
        this.description = desc;
    }

    public String getSymptoms() {
        return this.symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getProductCategory() {
        return this.category;
    }

    public void setProductCategory(String category) {
        this.category = category;
    }

    public float getProductPrice() {
        return this.price;
    }

    public void setProductPrice(float price) {
        this.price = price;
    }

    public int getIfOnDiscount() {
        return this.ondiscount;
    }

    public void setOnDiscount(int ondis) {
        this.ondiscount = ondis;
    }

    public int getProductQuantity() {
        return this.quantity;
    }

    public void setProductQuantity(int qty) {
        this.quantity = qty;
    }

    public int getProductSoldNo() {
        return this.sold;
    }

    public void setProductSoldNo(int sold) {
        this.sold = sold;
    }

    public String getProductImage() {
        return this.image;
    }

    public void setProductImage(String img) {
        this.image = img;
    }

    public static ArrayList<Product> getAllProducts() {
        Database db = new Database();
        Statement stmt = null;
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            stmt = db.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("Select * from tbl_product");
            while (rs.next()) {
                if (rs.next()) {
                    Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
                    products.add(p);
                } else {
                    products = null;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public static ArrayList<Product> getProductByID(int id) {
        Database db = new Database();
        Statement stmt = null;
        ArrayList<Product> products = null;
        String query = "Select * from tbl_product where productid=" + id + ""; 
        try {
            stmt = db.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                products = new ArrayList<Product>();
                do {
                    Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
                    products.add(p);
                } while (rs.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return products;
    }

    public static ArrayList<Product> getProductsByCategory(String category) {
        Database db = new Database();
        Statement stmt = null;
        ArrayList<Product> products = null;
        String query = "Select * from tbl_product where category='" + category + "'";
        try {
            stmt = db.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                products = new ArrayList<Product>();
                do {
                    Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
                    products.add(p);
                } while (rs.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }
}
