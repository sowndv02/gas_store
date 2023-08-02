/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.util;

import com.se1715.group4.gasstore.dto.Order;
import com.se1715.group4.gasstore.dto.Product;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;


/**
 *
 * @author ADMIN
 */
public class SendMail {
    
     public void sendWarrantyWarning(Order o, Product p) {
        final String username = "sendotp1234@gmail.com";
        final String password = "ifeahpwziexwuuqo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendotp1234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(o.getCustomer().getEmail()));
            message.setSubject("Your "+ p.getName() + "required warranty");
            message.setText("Dear customer, your " + p.getName()+ " is in need of a check up. The item was brought in Order #"+o.getOrderId()+" and shipped on "+o.getShippedDate()+" .Click here<http://localhost:3979/gasstore/client/index> to go to our website");

            Transport.send(message);

            System.out.println("mail sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendHiddenReview(String to, String contendRep) {
        final String username = "sendotp1234@gmail.com";
        final String password = "ifeahpwziexwuuqo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        System.setProperty("mail.mime.charset", "false");
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendotp1234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Reply Feedback");
            message.setText("Hello! Upon receiving your feedback, we have the following solution: " +contendRep);
            Transport.send(message);

            System.out.println("Review sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    // The above code is sending an email to the user's email address.
    public void sendRegister(String to) {
        final String username = "sendotp1234@gmail.com";
        final String password = "ifeahpwziexwuuqo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendotp1234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Feedback");
            message.setText("Hello! Thank you for registering an account! This is the notification email and you will use it to retrieve your password!");
            Transport.send(message);
            System.out.println("Verified register successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * It sends an email to the user's email address
     * 
     * @param to the email address of the recipient
     */
    public void sendUnLockAccount(String to) {
        final String username = "sendotp1234@gmail.com";
        final String password = "ifeahpwziexwuuqo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        System.setProperty("mail.mime.charset", "false");
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendotp1234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("UnLock Account");
            message.setText("Your account has been unlocked! Claim compliance with our standards! Thank you very much!");
            Transport.send(message);

            System.out.println("Feedback sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    /**
     * It sends an email to the user's email address
     * 
     * @param to The email address of the recipient.
     */
    public void sendLockAccount(String to) {
        final String username = "sendotp1234@gmail.com";
        final String password = "ifeahpwziexwuuqo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        System.setProperty("mail.mime.charset", "false");
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendotp1234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Lock Account");
            message.setText("Your account has violated our standards please respond to this email to resolve them!");
            Transport.send(message);

            System.out.println("Feedback sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    

    /**
     * It sends an email to the user who sent the feedback
     * 
     * @param to The email address of the recipient.
     * @param contendRep the content of the reply
     */
    public void sendFeedBack(String to, String contendRep) {
        final String username = "sendotp1234@gmail.com";
        final String password = "ifeahpwziexwuuqo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        System.setProperty("mail.mime.charset", "false");
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendotp1234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Reply Feedback");
            message.setText("Hello! Upon receiving your feedback, we have the following solution: " +contendRep);
            Transport.send(message);

            System.out.println("Feedback sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * It sends a confirmation email to the user who sent the feedback
     * 
     * @param to the email address of the recipient
     * @param text The text of the message you want to send.
     */
    public void sendVerifiedFeedBack(String to, String text) {
        final String username = "sendotp1234@gmail.com";
        final String password = "ifeahpwziexwuuqo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendotp1234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Feedback");
            message.setText("Hello! Thank you for your feedback. We are sending this letter to confirm that we have received a response from you!"
                    + " We will reply soon! Thank you very much for your contribution! Have a nice day!!!!");
            Transport.send(message);
            System.out.println("Verified feedback sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * It sends an email to the user with the OTP
     * 
     * @param to The email address of the recipient.
     * @param otp The OTP you want to send.
     */
    public void sendOTP(String to, String otp) {
        final String username = "sendotp1234@gmail.com";
        final String password = "ifeahpwziexwuuqo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendotp1234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("OTP for your account");
            message.setText("Your OTP is: " + otp);

            Transport.send(message);

            System.out.println("OTP sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * It generates a random 6-digit number
     * 
     * @return A random 6-digit number.
     */
    public String generateOTP() {
        // generate an OTP
        // for demonstration purposes, this example generates a random 6-digit number
        return String.format("%06d", (int) (Math.random() * 1000000));
    }
    
    public static void main(String[] args) {
        String txt ="Xin chào! Cảm ơn bạn đã gửi phản hồi cho chúng tôi."
                + " Chúng tôi gửi thư này để xác nhận rằng chúng tôi đã nhận được phản hồi từ bạn!"
                + " Chúng tôi sẽ sớm hồi âm! Xin chân thành cảm ơn về đóng góp của bạn! Chúc bạn có một ngày mới tốt lành!!";
    }
}
