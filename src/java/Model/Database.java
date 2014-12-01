/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ShashankPangam
 */
public class Database {

    private Statement stmt;
    private Connection connect;
    private Context ctx;
    private DataSource ds;

    public Statement getStatement() {
        return this.stmt;
    }

    public void setStatement(Statement stmt) {
        this.stmt = stmt;
    }
    
    public Connection getConnection()
    {
        return this.connect;
    }

    public Database() {
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("jdbc/myDatasource_1");
            connect = ds.getConnection();
            this.stmt = null;
        } catch (NamingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
