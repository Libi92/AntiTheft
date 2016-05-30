package service;

import java.util.regex.Pattern;

import android.R.bool;
import android.location.Location;

public class Globals {
    public static Location lastKnownLocation = null;
    public static Thread locationManagerThread = null;
	
    public static final String SERVER_IP="serverip";
    public static final String SERVER_PORT="serverPort";
    public static final String NAMESPACE="http://service/";
	public static final String PREFERENCES_NAME = "MyPref";
	public static String SIM_SERIAL = "sim";
	public static String NEW_SIM_SERIAL = "newSim";
	public static String LOGIN_ID = "login";
	public static String USER_NAME = "USERNAME";
	
	public static boolean flag = true;
	
	public static final String phone1="ph1";
	public static final String phone2="ph2";
	public static final String emailid="email";
	public static final String userEmail="staff.cyberprism@gmail.com";
	public static final String userPass="staffs@cyber";
	
	public static final String cmdPrefix = "commandPrefix";
	
	

    public static String serviceUrl = "http://10.0.2.2:8080/cyber/AntiTheftServer?xsd=1";


    public static void setServer(String ip, String port) {
    	serviceUrl = "http://"+ip+":8080/cyber/AntiTheftServer?xsd=1";
    }

    public static boolean ip_validate(String ip_address) {

    	return Pattern
    			.compile(
    					"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\"
    							+ ".([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")
    			.matcher(ip_address).matches();

    }
}
