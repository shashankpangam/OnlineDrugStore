/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ods.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author bowen
 */
public class ODSDatabase {
    //private static final String DB_URL = "jdbc:oracle:thin:@dilbert.humber.ca:1521:grok";
    private static final String DB_URL = "jdbc:derby:@localhost:1527:sample";
    private static Connection connection; 

    private static Context ctx;
    private static DataSource ds;
    
    
    public ODSDatabase() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a Connection to the datasource
            connection = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/sample ",  
                    "app", "app");

    }
    
    
//    static {
//        con = null;
//        try {
//            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
//            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
//            con = DriverManager.getConnection(DB_URL, "app","app");//n01050183", "oracle");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ODSDatabase.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(ODSDatabase.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(ODSDatabase.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(ODSDatabase.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
       
        public static Connection getDB() {
            if( connection == null ) {
                try {
                    ctx = new InitialContext();
                    ds = (DataSource)ctx.lookup("jdbc/myDatasource");
                    connection = ds.getConnection();
                } catch (NamingException ex) {
                    Logger.getLogger(ODSDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
                 catch (SQLException ex) {
                    Logger.getLogger(ODSDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            return connection;
        }
    
    
    public static Connection getLocalDB() {        
        if( connection == null ) {
//        Connection con = ODSDatabase.getDB();       
            try {

                Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                //Get a Connection to the datasource
                connection = DriverManager.getConnection(
                        "jdbc:derby://localhost:1527/sample ",  
                        "app", "app");

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(OrderHelper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(OrderHelper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(OrderHelper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(OrderHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return connection;
    }
}
