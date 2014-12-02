/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ods.model;

import Model.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author brian
 */
public class OrderHelper {

    private static final String TABLE_NAME = "DRUG_ORDER";//TBL_ORDER";
    private static final String COLUMN_1 = "order_id";
    private static final String COLUMN_2 = "customer_id";
    private static final String COLUMN_3 = "coupon_id";
    private static final String COLUMN_4 = "sum_price";
    private static final String COLUMN_5 = "order_date";
    
//    private static final String COL_ORDERID = "ORDERID";
//    private static final String COL_PRODUCTID = "PRODUCTID";
//    private static final String COL_CUSTOMERID = "CUSTOMERID";
//    private static final String COL_COUPONCODE = "COUPONCODE";
//    private static final String COL_PRICE = "PRICE";
//    private static final String COL_ORDERDATE = "ORDERDATE";
    
    private static long last_id=-1;
    
    private static Connection con;
    private static Statement stmt;
    static {
        con = ODSDatabase.getDB();
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(OrderHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public OrderHelper() throws SQLException {

    }
    
    public OrderHelper(int i) {
//        try {
//            ctx = new InitialContext();
//            ds = (DataSource)ctx.lookup("jdbc/myDatasource");
//            conn = ds.getConnection();
//        } catch (NamingException ex) {
//            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         catch (SQLException ex) {
//            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }        
    }
       
    public static Order getOrderById(long id) throws SQLException {
        //String sql = "select * from " + TABLE_NAME + " where = " + id;
        String sql = "select * from " + TABLE_NAME + " where order_id='" + Long.toString(id) + "'";
        
        ResultSet rs =  stmt.executeQuery(sql);
        Order order=null;
        while( rs.next() ) {
            order = new Order();
        }
        
        return order;
    }
    
    private static long getLastID() 
            throws SQLException 
    {
        ResultSet r = stmt.executeQuery("select max(order_id) from DRUG_ORDER");
        
        while(r.next()) {
            return r.getInt(1);
        }
        
        return -1;
    }
    
    public static boolean saveOrder(Order order) 
            throws SQLException 
    {
        Connection con = ODSDatabase.getDB();      
//        
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO " + TABLE_NAME )
                .append(" (" + COLUMN_1 + "," + COLUMN_2 +"," +COLUMN_3 + "," + COLUMN_4 + "," + COLUMN_5 + ") ")
                .append(" VALUES ");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss") ;
        sql.append(" ( ")
            .append("order_seq.NEXTVAL")
            .append( " , '" )
            .append(order.getCustomerID())
            .append( "' , '" )
            .append(order.getCouponID())
            .append( "' , " )
            .append(order.getSumPrice())                
            .append( " , '" )
            .append(sdf.format( order.getOrderDate() ) )
            .append("' )");

        
        PreparedStatement ps = con.prepareStatement(sql.toString(), new String[] { "order_id" });
        
        Long studentId = null;

        // execute the insert statement, if success get the primary key value
        if (ps.executeUpdate() > 0) {

            ResultSet generatedKeys = ps.getGeneratedKeys();

            // if resultset has data, get the primary key value
            // of last inserted record
            if (null != generatedKeys && generatedKeys.next()) {
                // voila! we got order id which was generated from sequence
                last_id = generatedKeys.getLong(1);
            }
        }
        
//        stmt = con.createStatement();
//        //insert entry in order table
//        stmt.executeUpdate(sql.toString(), Statement.RETURN_GENERATED_KEYS );
        //"insert into DRUG_ORDER (customer_id,coupon_id,sum_price,order_date) values('HB001','abcd',23.3,'2014-11-29')"
        order.setOrderID( Long.toString( last_id ) );
        
        //insert entries in order_detail
        OrderDetailHelper.saveOrderDetails(order.getOrderDetails());
        
        stmt.close();
        
        return true;
    }
}    
/*
create table "APP".DRUG_ORDER
(
	ORDER_ID INTEGER not null primary key 
	GENERATED ALWAYS AS IDENTITY
	(START WITH 1, INCREMENT BY 1),
	CUSTOMER_ID VARCHAR(10),
	COUPON_ID VARCHAR(10),
	SUM_PRICE REAL default 0 not null,
	ORDER_DATE DATE
)    
    

        try {        
//            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
//            //Get a Connection to the datasource
//            con = DriverManager.getConnection(
//                    "jdbc:derby://localhost:1527/sample ",  
//                    "app", "app");
//            
//            stmt = con.createStatement();
//            
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(OrderHelper.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderHelper.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(OrderHelper.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(OrderHelper.class.getName()).log(Level.SEVERE, null, ex);
//        }
    
    
*/    
    

