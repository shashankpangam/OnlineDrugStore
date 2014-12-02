/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ods.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brian
 */
public class OrderDetailHelper {
    
    private static final String TABLE_NAME = "ORDER_DETAIL";
    private static final String COLUMN_1 = "order_id";
    private static final String COLUMN_2= "item_id";
    private static final String COLUMN_3 = "name";
    private static final String COLUMN_4 = "price";
    private static final String COLUMN_5 = "qty";
    private static final String COLUMN_6 = "coupon_id";
    
    private static Connection con;
    private static Statement stmt;
//    static {
//        con = ODSDatabase.getDB();
//        try {
//            stmt = con.createStatement();
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderHelper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }            

    public static int saveOrderDetails(Set<OrderDetail> orderDetails ) throws SQLException 
    {
            //This query is for jdbc:oracle:thin
        String sqlOracle = " INSERT INTO " + TABLE_NAME 
                + "(" + COLUMN_1 
                + "," + COLUMN_2
                + "," + COLUMN_3
                + "," + COLUMN_4
                + "," + COLUMN_5
                + "," + COLUMN_6
                + ")" + "VALUES"
                + "(?,?,?,?,?,?)";
        
        Connection con = ODSDatabase.getDB();      
        PreparedStatement pStmt = con.prepareStatement(sqlOracle);
        con.setAutoCommit(false);

        Iterator<OrderDetail> itor = orderDetails.iterator();
        
        while(itor.hasNext()) {
            OrderDetail tmp = itor.next();
            pStmt.setInt(1, Integer.parseInt(tmp.getOrder().getOrderID() ));
            pStmt.setString(2, tmp.getItemID());
            pStmt.setString(3, tmp.getName());
            pStmt.setFloat(4, tmp.getPrice());
            pStmt.setInt(5, tmp.getQty());
            pStmt.setString(6, tmp.getCouponID());
            pStmt.addBatch();

        }
        
        int[] r = pStmt.executeBatch();
        con.commit();
        
        pStmt.close();

        return r.length;
        
    }
    
    public static int DerbySaveOrderDetails(Set<OrderDetail> orderDetails ) 
            throws SQLException 
    {
        //This query is for jdbc:derby
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO " + TABLE_NAME )
                .append("(" + COLUMN_1 + "," + COLUMN_2 +"," +COLUMN_3 + "," + COLUMN_4 + "," + COLUMN_5 + "," + COLUMN_6 + ")")
                .append(" VALUES");

        Iterator<OrderDetail> itor = orderDetails.iterator();
        
        while(itor.hasNext()) {
            OrderDetail tmp = itor.next();
            sql.append("( ")
               .append( Integer.parseInt( tmp.getOrder().getOrderID() ))
               .append( " , '" )
               .append(tmp.getItemID())
                .append( "' , '" )
               .append(tmp.getName())
                .append( "' , " )
               .append( String.format("%.2f", tmp.getPrice() ) )
                .append( " , " )
               .append(tmp.getQty())
                .append( " , '" )
               .append(tmp.getCouponID())
               .append("' )");
            if(itor.hasNext()) {
                sql.append(" , ");
            }
        }
        Connection con = ODSDatabase.getDB();      
        stmt = con.createStatement();
        
        int r = stmt.executeUpdate(sql.toString());
        stmt.close();
        return r;
    }
}
