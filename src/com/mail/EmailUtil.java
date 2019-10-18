
package com.mail;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailUtil {
    
    public static void sendAttachmentEmail(Session session,String toEmail,String subject,String body)
    {
        try {
            MimeMessage msg=new MimeMessage(session);
            
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            
            msg.setFrom(new InternetAddress("titanxfitnesspune@gmail.com", "NoReply-TitanXFitness"));
            
            msg.setReplyTo(InternetAddress.parse("titanxfitnesspune@gmail.com", false));
            
            msg.setSubject(subject);
            
            msg.setSentDate(new Date());
            
            msg.setRecipients(Message.RecipientType.TO, toEmail);
            
            BodyPart messageBodyPart=new MimeBodyPart();
            
            messageBodyPart.setText(body);
            
            Multipart multipart=new MimeMultipart();
            
            multipart.addBodyPart(messageBodyPart);
            
            messageBodyPart=new MimeBodyPart();
            
            String filename="C:/Invoice/invoice.pdf";
            DataSource source=new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("invoice.pdf");
            
            multipart.addBodyPart(messageBodyPart);
            
            msg.setContent(multipart);
            
            Transport.send(msg);
            
            System.out.println("Sent succesfully!");
            
        } catch (Exception ex) {
            Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }
    
    public static void sendEmail(Session session,String toEmail,String subject,String body)
    {
        try {
            MimeMessage msg=new MimeMessage(session);
            
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            
            msg.setFrom(new InternetAddress("titanxfitnesspune@gmail.com", "NoReply-TitanXFitness"));
            
            msg.setReplyTo(InternetAddress.parse("titanxfitnesspune@gmail.com", false));
            
            msg.setSubject(subject);
            
            msg.setSentDate(new Date());
            
            msg.setRecipients(Message.RecipientType.TO, toEmail);
            
            BodyPart messageBodyPart=new MimeBodyPart();
            
            messageBodyPart.setText(body);
            
            Multipart multipart=new MimeMultipart();
            
            multipart.addBodyPart(messageBodyPart);
            
            messageBodyPart=new MimeBodyPart();
            
            multipart.addBodyPart(messageBodyPart);
            
            msg.setContent(multipart);
            
            Transport.send(msg);
            
            System.out.println("Sent succesfully!");
            
        } catch (Exception ex) {
            Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }
}
