/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import database.DbConnect;
import db.HexEncodeDecode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Nijas
 */
@WebService(serviceName = "AntiTheftServer")
public class AntiTheftServer {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        String result = DbConnect.Login(username, password);
        if(!result.equals("-1"))
        {
            return "true:" + result;
        }
        return "false";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "uploadSimDetails")
    public String uploadSimDetails(@WebParam(name = "username") String username, @WebParam(name = "deviceId") String deviceId, @WebParam(name = "simop") String simop, @WebParam(name = "simserial") String simserial) {
        boolean b=DbConnect.InsertSimDetail(username, deviceId, simop, simserial);
        if(b)
        {
            return "true";
        }
        return "false";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getNumbers")
    public String getNumbers(@WebParam(name = "username")
    String username) {
        String result=DbConnect.getNumber(username);
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sendNumber")
    public String sendNumber(@WebParam(name = "num1")
    String num1, @WebParam(name = "num2")
    String num2, @WebParam(name = "username")
    String username, @WebParam(name = "email")
    String email) {
        //TODO write your implementation code here:\
     
        int i=DbConnect.sendNumber(num1, num2,email, username);
        if(i>0){
            return "true";
        }
        return "false";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkStatus")
    public String checkStatus(@WebParam(name = "sim") String sim, @WebParam(name = "username") String username) {
        String status=DbConnect.checkStatus(sim,username);
        System.out.println("Status : " + status);
        return status;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateLocation")
    public String updateLocation(@WebParam(name = "username") String username, @WebParam(name = "latitude") String latitude, @WebParam(name = "longitude") String longitude) {
        DbConnect.updateLocation(username,latitude,longitude);
        return "true";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "saveimage")
    public String saveimage(@WebParam(name = "userid") String userid, @WebParam(name = "file") String file) throws IOException {
        
        try {
            
             String user_id = DbConnect.getUserId(userid);
            
            FileOutputStream fileout=null;
            String path="D:\\web\\cyber\\build\\web\\images\\";
            byte[] image=HexEncodeDecode.decode(file);
            fileout=new FileOutputStream(new File(path+"img.jpg"));
            fileout.write(image);
            fileout.flush();
            fileout.close();
            DbConnect db=new DbConnect();
            boolean b=db.insertimage(user_id, "img.jpg");
            if(b)
            {
                return "true";
            }
            
            else
            {
                return "false";
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AntiTheftServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "false";
                
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateSms")
    public String updateSms(@WebParam(name = "phone") String phone, @WebParam(name = "message") String message, @WebParam(name = "datetime") String datetime, @WebParam(name = "type") String type, @WebParam(name = "loginid") String loginid) {
        DbConnect.updateSms(phone, message, datetime, type, loginid);
        return "Success";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateCall")
    public String updateCall(@WebParam(name = "number") String number, @WebParam(name = "type") String type, @WebParam(name = "duration") String duration, @WebParam(name = "dateinfo") String dateinfo, @WebParam(name = "loginid") String loginid) {
        DbConnect.updateCall(number, type, duration, dateinfo, loginid);
        return "Success";
    }
}
