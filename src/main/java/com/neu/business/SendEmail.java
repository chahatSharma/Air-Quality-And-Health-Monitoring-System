package com.neu.business;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 *
 * @author chahatSharma
 */
public class SendEmail {
    public static void main(String [] args)
   { 
    	  ApplicationContext context = 
    	             new ClassPathXmlApplicationContext("bean.xml");
    	  
    	  
    	  MailMail m = (MailMail) context.getBean("mailMail");
    	  
    	  String sender="admin@mayorOffice.com";//write here sender gmail id  
    	  String receiver=args[0];//write here receiver id  
    	  m.sendMail(args[3],receiver,args[2],args[1]);  
    	        
    	  System.out.println("success");  
//     final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//   
//  // Get a Properties object
//      // Recipient's email ID needs to be mentioned.
//      String to = args[0];
//
//      // Sender's email ID needs to be mentioned
//      String from = "admin@mayorOffice.com";
//
//      // Assuming you are sending email from localhost
//      String host = "localhost";
//
//      // Get system properties
//      Properties properties = System.getProperties();
//
//      // Setup mail server
//      properties.setProperty("mail.smtp.host", "smtp.gmail.com");
//        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
//     properties.setProperty("mail.smtp.socketFactory.fallback", "false");
//     properties.setProperty("mail.smtp.port", "465");
//     properties.setProperty("mail.smtp.socketFactory.port", "465");
//     properties.put("mail.smtp.auth", "true");
//     properties.put("mail.debug", "true");
//     properties.put("mail.store.protocol", "pop3");
//     properties.put("mail.transport.protocol", "smtp");
//      // Get the default Session object.
//      //Session session = Session.getDefaultInstance(properties);
//    Session session = Session.getInstance(properties, new GMailAuthenticator("chahat.sharma87@gmail.com", "G0ldl3af*"));
//      try{
//         // Create a default MimeMessage object.
//         MimeMessage message = new MimeMessage(session);
//
//         // Set From: header field of the header.
//         message.setFrom(new InternetAddress(from));
//
//         // Set To: header field of the header.
//         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//         // Set Subject: header field
//         message.setSubject("");
//
//         // Now set the actual message
//         message.setText(args[1]);
//
//         // Send message
//         Transport.send(message);
//         System.out.println("Sent message successfully....");
//      }catch (Exception mex) {
//         mex.printStackTrace();
//      }
   }
}
