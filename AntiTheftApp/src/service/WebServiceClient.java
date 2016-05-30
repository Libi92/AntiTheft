package service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

public class WebServiceClient {

	public static String connect(String methodName, String[] args,
			String[] values) {

		try {
			SoapObject request = new SoapObject(Globals.NAMESPACE, methodName);
			int i = 0;

			for (String s : args) {
				PropertyInfo propInfo = new PropertyInfo();
				propInfo.name = s;
				propInfo.type = PropertyInfo.STRING_CLASS;
				request.addProperty(propInfo, values[i]);
				i++;
			}

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(
					Globals.serviceUrl);
			androidHttpTransport.call(Globals.NAMESPACE + methodName, envelope);
			SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope
					.getResponse(); // Receiving return string

			String result = resultsRequestSOAP.toString();
			if (result != null) {
				return result;
			}
			return "";
		} catch (Exception ex) {
			Log.d("Url", Globals.serviceUrl);
			Log.d("Web Service Exception", ex.toString());
			return "";
		}
	}

	public static String Login(String username,String password) {
		String[] args = new String[2];
		String[] values = new String[2];

		args[0] = "username";
		args[1] = "password";
		
		values[0] = username;
		values[1] = password;
		
		String result = connect("login", args, values);
		Log.d("login Result", result);
		return result;
	}
	
	
	public static String sendimage(String userid,String file) {
		String[] args = new String[2];
		String[] values = new String[2];

		args[0] = "userid";
		args[1] = "file";
		
		values[0] = userid;
		values[1] = file;
		
		String result = connect("saveimage", args, values);
		Log.d("login Result", result);
		return result;
	}
	
	public static String sendSimDetails(String username,String deviceId, String simop, String simserial) {
		String[] args = new String[4];
		String[] values = new String[4];

		args[0] = "username";
		args[1] = "deviceId";
		args[2] = "simop";
		args[3] = "simserial";
		
		values[0] = username;
		values[1] = deviceId;
		values[2] = simop;
		values[3] = simserial;
		
		String result = connect("uploadSimDetails", args, values);
		Log.d("sim Result", result);
		return result;
	}

	public static String getNumbers(String username) {
		String[] args = new String[1];
		String[] values = new String[1];

		args[0] = "username";
		
		values[0] = username;
		
		String result = connect("getNumbers", args, values);
		Log.d("getNumbers Result", result);
		return result;
	}

	public static String sendNumber(String num1, String num2, String email,String username) {
		String[] args = new String[4];
		String[] values = new String[4];

		args[0] = "username";
		args[1]="num1";
		args[2]="num2";
		args[3]="email";
		
		values[0] = username;
		values[1]=num1;
		values[2]=num2;
		values[3]=email;
		
		String result = connect("sendNumber", args, values);
		Log.d("getNumbers Result", result);
		return result;
	}

	public static String checkStatus(String sim, String username) {
		String[] args = new String[2];
		String[] values = new String[2];

		args[0] = "sim";
		args[1] = "username";
		
		values[0] = sim;
		values[1] = username;
		
		String result = connect("checkStatus", args, values);
		
		return result;
	}

	public static String updateLocation(String username, String latitude,
			String longitude) {
		String[] args = new String[3];
		String[] values = new String[3];

		args[0] = "username";
		args[1] = "latitude";
		args[2] = "longitude";
		
		values[0] = username;
		values[1] = latitude;
		values[2] = longitude;
		
		String result = connect("updateLocation", args, values);
		
		return result;

	}
	
	public static String updateSMS(String phone, String message, String type, String loginid) {
		String[] args = new String[5];
		String[] values = new String[5];

		args[0] = "phone";
		args[1] = "message";
		args[2] = "datetime";
		args[3] = "type";
		args[4] = "loginid";
		
		values[0] = phone;
		values[1] = message;
		values[2] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		values[3] = type;
		values[4] = loginid;
		
		String result = connect("updateSms", args, values);
		
		return result;

	}
	
	public static String updateCALL(String phone, String type, String duration, String loginid) {
		String[] args = new String[5];
		String[] values = new String[5];

		args[0] = "number";
		args[1] = "type";
		args[2] = "duration";
		args[3] = "dateinfo";
		args[4] = "loginid";
		
		values[0] = phone;
		values[1] = type;
		values[2] = duration;
		values[3] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		values[4] = loginid;
		
		
		String result = connect("updateCall", args, values);
		
		return result;

	}
	
//	new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

}
