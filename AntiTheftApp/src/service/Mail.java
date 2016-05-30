package service;

import java.util.Date; 
import java.util.Properties; 
import javax.activation.CommandMap; 
import javax.activation.DataHandler; 
import javax.activation.DataSource; 
import javax.activation.FileDataSource; 
import javax.activation.MailcapCommandMap; 
import javax.mail.BodyPart; 
import javax.mail.Multipart; 
import javax.mail.PasswordAuthentication; 
import javax.mail.Session; 
import javax.mail.Transport; 
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeBodyPart; 
import javax.mail.internet.MimeMessage; 
import javax.mail.internet.MimeMultipart; 

import android.util.Log;

 
public class Mail extends javax.mail.Authenticator { 
	
	private String _user, recepient_1; 
	private String _pass; 
	private String _from; 
 
	private String _port; 
	private String _sport; 
 	private String _host; 
 
	private String _subject; 
	private String _body; 
 
	private boolean _auth; 
   
	private boolean _debuggable; 
 
	private Multipart _multipart; 
 
 
	public Mail(String email_1,String from, String pass) { 
		recepient_1=email_1;
//		recepient_2=email_2;
	  
		_host = "smtp.gmail.com"; // default smtp server 
		_port = "465"; // default smtp SERVER_PORT 
		_sport = "465"; // default socketfactory SERVER_PORT 
 
		_user = from;
//				"staff.cyberprism@gmail.com";
//				Globals.application_context.getSharedPreferences(Configuration.PREFERENCES_NAME, 0).getString(Configuration.SAPCRON_MAIL_ID, "staff.cyberprism@gmail.com"); // username 
		_pass = pass;
//				
//		Globals.application_context.getSharedPreferences(Configuration.PREFERENCES_NAME, 0).getString(Configuration.SAPCRON_MAIL_PASSWORD, "staffs123"); // password 
		_from = from;
//				"staff.cyberprism@gmail.com";
//		Globals.application_context.getSharedPreferences(Configuration.PREFERENCES_NAME, 0).getString(Configuration.SAPCRON_MAIL_ID, "staff.cyberprism@gmail.com"); // email sent from 
		_subject = "AntiTheft"; // email subject 
		_body = "Please see attachments.."; // email body 
 
		_debuggable = false; // debug mode on or off - default off 
		_auth = true; // smtp authentication - default on 
 
		_multipart = new MimeMultipart(); 
 
		// There is something wrong with MailCap, javamail can not find a handler for the multipart/mixed part, so this bit needs to be added. 
		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap(); 
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html"); 
		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml"); 
		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain"); 
		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed"); 
		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822"); 
		CommandMap.setDefaultCommandMap(mc); 
	} 
 
    public boolean send() throws Exception { 
	 	  
    	String[] _to={recepient_1}; 
    	Properties props = _setProperties(); 
 
    	if(!_user.equals("") && !_pass.equals("") && _to.length > 0 && !_from.equals("") && !_subject.equals("") && !_body.equals("")) { 
      
    		Log.v("Mail.java","Inside send()");	
    	
    		Session session = Session.getInstance(props, this); 
 
    		MimeMessage msg = new MimeMessage(session); 
 
    		msg.setFrom(new InternetAddress(_from)); 
       
    		InternetAddress[] addressTo = new InternetAddress[_to.length]; 
    		
    		for (int i = 0; i < _to.length; i++) { 
    			addressTo[i] = new InternetAddress(_to[i]); 
    		} 
      
    		Log.v("email", recepient_1);
    		msg.setRecipients(MimeMessage.RecipientType.TO, addressTo); 
 
    		msg.setSubject(_subject); 
    		msg.setSentDate(new Date()); 
 
    		// setup message body 
    		BodyPart messageBodyPart = new MimeBodyPart(); 
    		messageBodyPart.setText(_body); 
    		_multipart.addBodyPart(messageBodyPart); 
 
    		// Put parts in message 
    		msg.setContent(_multipart); 
 
    		// send email 
    		Transport.send(msg); 
 
    		return true; 
    	}
    	else { 
    		return false; 
    	} 
    } 
 
    public void addAttachment(String filepath) throws Exception { 
    	BodyPart messageBodyPart = new MimeBodyPart(); 
    	DataSource source = new FileDataSource(filepath); 
    	messageBodyPart.setDataHandler(new DataHandler(source)); 
    	messageBodyPart.setFileName(filepath); 
 
    	_multipart.addBodyPart(messageBodyPart); 
    } 
 
    @Override 
    public PasswordAuthentication getPasswordAuthentication() { 
    	return new PasswordAuthentication(_user, _pass); 
    } 
 
    private Properties _setProperties() { 
    	Properties props = new Properties(); 
 
    	props.put("mail.smtp.host", _host); 
 
    	if(_debuggable) { 
    		props.put("mail.debug", "true"); 
    	} 
 
    	if(_auth) { 
    		props.put("mail.smtp.auth", "true"); 
    	} 
 
    	props.put("mail.smtp.port", _port); 
    	props.put("mail.smtp.socketFactory.port", _sport); 
    	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
    	props.put("mail.smtp.socketFactory.fallback", "false"); 
 
    	return props; 
    } 
 
    // the getters and setters 
    public String getBody() { 
    	return _body; 
    } 
 
    public void setBody(String _body) { 
    	this._body = _body; 
    } 
 
    // more of the getters and setters �.. 
} 
