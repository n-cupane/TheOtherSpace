package com.theotherspace.utilities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

import com.theotherspace.model.User;

public class SendMail {
	final static String user = "cinema.theotherspace@gmail.com";
	final static String pass = "6PAJutH%@EzkTb8vGWsy#o$GVS*#qWJ&";
	final static String appPass = "ngjg faoo eivc ehww";

	public static void send(User activeUser) {
		
		StringBuilder msg = new StringBuilder();
		msg.append("Benvenuto in TheOtherSpace " + activeUser.getFirstName() + "!\n");
		msg.append("La tua iscrzione è andata a buon fine!\nEcco un riepilogo dei tuoi dati:\n");
		msg.append("- Username: " + activeUser.getUsername() + "\n");
		msg.append("- Password: " + activeUser.getPassword() + "\n");
		msg.append("Conserva queste informazioni e non condividerle con nessuno!\n");
		msg.append("A presto su http://fraxthrough.duckdns.org/TheOtherSpace/");
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        	protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(user, appPass);
        	}
        });
        
        try {
        	MimeMessage message = new MimeMessage(session);
        	message.setFrom(new InternetAddress(user));
        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(activeUser.getEmail()));
        	message.setSubject("Benvenuto in TheOtherSpace!");
        	message.setText(msg.toString());
        	Transport.send(message);
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
