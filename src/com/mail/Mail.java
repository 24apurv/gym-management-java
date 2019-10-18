
package com.mail;

import com.model.Customer;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class Mail {
    public static void sendMail(Customer customer)
    {
        final String fromEmail="titanxfitnesspune@gmail.com";
        final String password="TitanXPune";
        final String toEmail=customer.getEmailId();
        
        System.out.println("SSL Email Start");
        
        Properties props=new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port", "465");
        
        Authenticator auth=new Authenticator() {
            
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        
        Session session=Session.getDefaultInstance(props, auth);
        System.out.println("Session created");
        
        String subject = "Invoice for TitanX Membership";
        String body = "Hello "+customer.getName()+",\n\n PFA invoice for your membership at TitanX Fitness.\n\n Regards,\n Team TitanX";
        
        EmailUtil.sendAttachmentEmail(session, toEmail, subject, body);
                    
        
    }
    
     public static void sendNotification(Customer customer,String subject,String message)
    {
        final String fromEmail="titanxfitnesspune@gmail.com";
        final String password="TitanXPune";
        final String toEmail=customer.getEmailId();
        
        System.out.println("SSL Email Start");
        
        Properties props=new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port", "465");
        
        Authenticator auth=new Authenticator() {
            
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        
        Session session=Session.getDefaultInstance(props, auth);
        System.out.println("Session created");
        
        String body = message;
        
        EmailUtil.sendAttachmentEmail(session, toEmail, subject, body);
                    
        
    }
}
