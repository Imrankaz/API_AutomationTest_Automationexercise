//package tests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import utils.Credentials;

public class EmailOnFailure implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("Test Failed: " + context.getDisplayName());
        sendEmail("Test failed: " + context.getDisplayName() + "\nReason: " + cause.getMessage());
    }

    private void sendEmail(String body) {
        try {
            System.out.println("Trying to send email for Test Failed");

            //final String username = "yourEmail@gmail.com"; // Email sender
            final String username = Credentials.senderEmail; // Email sender
            //final String password = "xxxxx"; // App password if Gmail  // Email sender's mail app password
            final String password = Credentials.appPass; // App password if Gmail  // Email sender's mail app password

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.host", Credentials.mail_smtp_host);
            props.put("mail.smtp.port", Credentials.mail_smtp_port);

            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(
                        Message.RecipientType.TO,
                        //InternetAddress.parse("receiverEmail@example.com") // Email Receiver
                        InternetAddress.parse(Credentials.receiverEmail) // Email Receiver

                );
                message.setSubject("API Test Failed ⚠️");
                message.setText(body);

                Transport.send(message);

                System.out.println("Failure email has been sent!");

            } catch (MessagingException e) {
                System.out.println("1: set valid email");
                throw new RuntimeException(e);
            }
        }catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
            System.out.println("2: set valid email");
        }

    }
}
