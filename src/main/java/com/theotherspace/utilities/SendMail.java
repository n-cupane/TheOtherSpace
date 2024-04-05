package com.theotherspace.utilities;

import java.util.Properties;
import java.util.Random;

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
		msg.append("A presto su http://fraxthrough.duckdns.org:57435/TheOtherSpace/");
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        
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
	
	public static void sendConfirmation(User activeUser) {
		
		StringBuilder msg = new StringBuilder();
		msg.append("Ti confermiamo l'emissione dei tuoi biglietti");
		msg.append("\npuoi trovarli in area personale sotto la voce \"I MIEI BIGLIETTI\"\n");
		msg.append("per qualsiasi info o dubbio contattaci a: cinema.theotherspace@gmail.com\n");
		msg.append("nel caso avessi aderito alla promozione TheOtherCard\n");
		msg.append("troverai il tuo saldo aggiornato\n");
		msg.append("A presto su http://fraxthrough.duckdns.org:57435/TheOtherSpace/");
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        	protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(user, appPass);
        	}
        });
        
        try {
        	MimeMessage message = new MimeMessage(session);
        	message.setFrom(new InternetAddress(user));
        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(activeUser.getEmail()));
        	message.setSubject("Conferma Emissione biglietti");
        	message.setText(msg.toString());
        	Transport.send(message);
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendPassword(User userP) {
		String newPassword = generateNewPassword();
		StringBuilder msg = new StringBuilder();
		msg.append(userP.getFirstName() + ", ecco la tua nuova password:\n");
		msg.append(newPassword + "\n\n");
		msg.append("Ti consigliamo di cambiarla al più presto.\n\n");
		msg.append("Se non sei stato tu a richidere il reset della password ti invitiamo a contattarci all'indirizzo:\n");
		msg.append("cinema.theotherspace@gmail.com\n");
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        	protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(user, appPass);
        	}
        });
        
        try {
        	MimeMessage message = new MimeMessage(session);
        	message.setFrom(new InternetAddress(user));
        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(userP.getEmail()));
        	message.setSubject("Recupero password");
        	message.setText(msg.toString());
        	Transport.send(message);
        	userP.setPassword(newPassword);
        	BusinessLogic.updateUser(userP);
        	System.out.println("Nuova password inviata a " + userP.getEmail());
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String generateNewPassword() {
		int leftLimit = 48; // codepoint per '0'
		int rightLimit = 122; // codepoint per 'z'
		
		Random r = new Random();
		String newPassword = r.ints(leftLimit, rightLimit + 1)
							.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
							.limit(10)
							.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
							.toString();
		
		return newPassword;
	}
	
}
