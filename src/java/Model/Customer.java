/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Paramjyot
 */
public class Customer {

    private int customerid;
    private String fname;
    private String lname;
    private String dob;
    private String address1;
    private String address2;
    private String city;
    private String zip;
    private String province;
    private String gender;
    private String email;
    private String uname;
    private String passwd;
    private Date lastsignin;

    public Customer() {
    }

    public Customer(int customerid, String fname, String lname, String dob, String address1, String address2, String city, String zip, String province, String gender, String email, String passwd, Date lastsignin) {
        this.customerid = customerid;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.zip = zip;
        this.province = province;
        this.gender = gender;
        this.email = email;
        this.uname = email;
        this.passwd = passwd;
        this.lastsignin = lastsignin;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getLastsignin() {
        return lastsignin;
    }

    public void setLastsignin(Date lastsignin) {
        this.lastsignin = lastsignin;
    }

    
    
    public boolean isValid() {
        return (fname != null && fname.length() > 0)
                && (passwd != null && passwd.length() > 0);
    }

    public static ArrayList<Customer> loginAction(String username, String password) {
        Database db = new Database();
        Statement stmt = null;
        ArrayList<Customer> customers = null;
        String query = "SELECT * FROM  TBL_CUSTOMER WHERE email='" + username + "' and password='" + password + "'";
        try {
            stmt = db.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                customers = new ArrayList<Customer>();
                do {
                    Customer c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11),rs.getString(12),rs.getDate(13));
                    customers.add(c);
                } while (rs.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }
    
    public static boolean insertNewCustomer(Customer customer)
    {
        String custfname = customer.getFname();
        String custlname = customer.getLname();
        String custdob = customer.getDob();
        String custaddress1 = customer.getAddress1();
        String custaddress2 = customer.getAddress2();
        String custcity = customer.getCity();
        String custzip = customer.getZip();
        String custprovince = customer.getProvince();
        String custgender = customer.getGender();
        String custemail = customer.getEmail();
        String custpasswd = customer.getPasswd();
        
        boolean flag = false;
        Database db = new Database();
        Statement stmt = null;
        String query = "INSERT INTO TBL_CUSTOMER(CUSTOMERID,FIRSTNAME,LASTNAME,DOB,ADDRESS1,ADDRESS2,CITY,ZIP,PROVINCE,GENDER,EMAIL,USERNAME,PASSWORD) VALUES (SEQ_CUSTOMER.nextval,'" + custfname + "','" + custlname + "', TO_DATE('" + custdob + "','yyyy-mm-dd') ,'" + custaddress1 + "','" + custaddress2 + "','" + custcity + "','" + custzip + "','" + custprovince + "','" + custgender + "','" + custemail + "', '" + custemail + "', '" + custpasswd + "' )";
        
        try {
            stmt = db.getConnection().createStatement();
            int rs = stmt.executeUpdate(query);
            if (rs==0) {
                flag= false;
            }
            else
                flag= true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }
}
