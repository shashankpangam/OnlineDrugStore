/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Paramjyot
 */
public class Customer {
    private int customerid = 0;
    private String fname = null;
    private String lname = null;
    private String dob = null;
    private String address1 = null;
    private String address2 = null;
    private String city = null;
    private String zip = null;
    private String province = null;
    private String gender=null;
    private String email = null;
    private String uname = null;
    private String passwd = null;
    private String repwd = null;
    

    public Customer() {
    }
    
    public Customer(int customerid,String fname,String lname,String dob, String address1,String address2, String city, String zip, String province,String gender, String email, String passwd, String repwd){
    this.customerid=customerid;
    this.fname=fname;
    this.lname=lname;
    this.dob=dob;
    this.address1=address1;
    this.address2=address2;
    this.city=city;
    this.zip=zip;
    this.province=province;
    this.gender=gender;
    this.email=email;
    this.uname=email;
    this.passwd=passwd;
    this.repwd=repwd;
           
        
    }
    
     public int getID() {
        return customerid;
    }

    public void setID(int id) {
        this.customerid = id;
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

    
     public String getDOB() {
        return dob;
    }

    public void setDOB(String dob) {
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
        this.uname= uname;
    }
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRetypepwd() {
        return repwd;
    }

    public void setRetypepwd(String repswd) {
        this.repwd = repwd;
    }

    public boolean isValid() {
        return (fname != null && fname.length() > 0)
                && (passwd != null && passwd.length() > 0);
    }
}
