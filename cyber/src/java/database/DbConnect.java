/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author staff
 */
public class DbConnect {

    static Connection con;
    static Statement st;

    public static void Connect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            if (con == null) {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/antitheft", "root", "mysql");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String Login(String username, String password) {
        Connect();
        try {
            ResultSet rs = con.createStatement().executeQuery("select * from login where username='" + username + "' and password='" + password + "' ");
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "-1";
    }

    public static boolean Register(String name, String num1, String num2, String email, String username, String password) {
        Connect();
        try {
            int rs = con.createStatement().executeUpdate("insert into reg(name,pno,sno,email,username,password) values('" + name + "'," + num1 + "," + num2 + ",'" + email + "','" + username + "','" + password + "')");
            int ab = con.createStatement().executeUpdate("insert into login(username,password) values ('" + username + "','" + password + "')");
            if (rs > 0) {
                return true;
            }
            if (ab > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    
    public static boolean insertimage(String userid, String image) {
        Connect();
        try {
            int rs = con.createStatement().executeUpdate("insert into image(userid,image) values('" + userid + "','" + image + "')");
            if (rs > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean isExist(String sql) {
        try {
            Connect();
            ResultSet rs = con.createStatement().executeQuery(sql);
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean InsertSimDetail(String username, String deviceId, String simop, String simserial) {
        Connect();
        try {
            String userid = DbConnect.getUserId(username);
            boolean b = isExist("select * from sim where userid='" + userid + "' and simop='" + simop + "' and simserial='" + simserial + "'");
            if (b) {

            } else {
                int i = con.createStatement().executeUpdate("Insert into sim (userid,deviceId,simop,simserial,status) value('" + userid + "','" + deviceId + "','" + simop + "','" + simserial + "','2' )");
                if (i > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {

//        new DbConnect();
//        DbConnect.InsertUserName("Insert into client_tab(username,userSocket,logStatus) values('nijas','sdd','0')");
    }

    public static String getUserId(String username) {

        try {
            Connect();
            ResultSet rs = con.createStatement().executeQuery("select id from login where username='" + username + "'");
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String getPrimaryNumber(String username) {

        try {
            Connect();
            ResultSet rs = con.createStatement().executeQuery("select pno from reg where username='" + username + "'");
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
public ResultSet select(String sql) 
{
        try {
            Connect();
            System.out.println(sql);
            ResultSet rs=con.createStatement().executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
    public static String getSecondaryNumber(String username) {

        try {
            Connect();
            ResultSet rs = con.createStatement().executeQuery("select sno from reg where username='" + username + "'");
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean UpdatePrimaryNumber(String username, String phone) {

        try {
            Connect();

            String id = DbConnect.getUserId(username);
            int rs = con.createStatement().executeUpdate("update reg set pno='" + phone + "' where id='" + id + "'");
            if (rs > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean UpdateSecondaryNumber(String username, String phone) {

        try {
            Connect();

            String id = DbConnect.getUserId(username);
            int rs = con.createStatement().executeUpdate("update reg set sno='" + phone + "' where id='" + id + "'");
            if (rs > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String getNumber(String username) {
        try {
            Connect();
            ResultSet rs = con.createStatement().executeQuery("SELECT pno,sno,email FROM reg WHERE username='" + username + "' ");
            if (rs.next()) {
                System.out.println(rs.getString(1) + ":" + rs.getString(2) + ":" + rs.getString(3));
                return rs.getString(1) + ":" + rs.getString(2)+ ":" + rs.getString(3);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static int sendNumber(String num1, String num2, String email, String username) {
        try {
            Connect();
            int i = con.createStatement().executeUpdate("UPDATE reg set pno='" + num1 + "', sno='" + num2 + "', email='" + email + "' WHERE username='" + username + "'");
            if (i > 0) {
                return i;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static ResultSet getSimDetails(String username) {
        try {
            Connect();
            String userid = DbConnect.getUserId(username);
            ResultSet i = con.createStatement().executeQuery("select * from sim where userid='" + userid + "'");
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ResultSet getLocation(String username) {
        try {
            Connect();
            String userid = DbConnect.getUserId(username);
            System.out.println("userid:"+userid);
            ResultSet i = con.createStatement().executeQuery("select * from location where userid='" + userid + "'");
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String checkStatus(String sim, String username) {
        try {
            Connect();
            String userid = DbConnect.getUserId(username);
            ResultSet i = con.createStatement().executeQuery("select status from sim where userid='" + userid + "' and simserial='" + sim + "'");
            if (i.next()) {
                return i.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static int updateSim(String id, String status) {
        try {
            Connect();

            int i = con.createStatement().executeUpdate("update sim set status='" + status + "' where id='" + id + "'");
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static int updateLocation(String username, String latitude, String longitude) {
        try {
            Connect();
            String userid = getUserId(username);
            boolean b = isExist("select * from location where userid='" + userid + "'");
            if (b) {
                int i = con.createStatement().executeUpdate("update location set lat='" + latitude + "', lng='" + longitude + "' where userid='" + userid + "'");
                return i;
            }
            else
            {
            int i = con.createStatement().executeUpdate("insert into location(userid,lat,lng) values('"+userid+"','"+latitude+"','"+longitude+"')");
            return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static void updateSms(String phone, String message, String datetime, String type, String loginid){
        try {
            String sql = "INSERT INTO smsinfo (phoneno, message, dateinfo, type, loginid) VALUES ('" + phone + "', '" + message + "', '" + datetime + "', '" + type + "', '" +loginid + "')";
            
            Connect();
            
            con.createStatement().execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void updateCall(String phone, String type, String duration, String dateinfo, String loginid){
        try {
            String sql = "INSERT INTO callinfo (number, type, duration, dateinfo, loginid) VALUES ('" + phone + "', '" + type + "', '" + duration + "', '" + dateinfo + "', '" + loginid + "')";
            
            System.out.println(sql);
            Connect();
            
            con.createStatement().execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
