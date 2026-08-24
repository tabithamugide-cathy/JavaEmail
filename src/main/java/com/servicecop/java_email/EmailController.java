package com.servicecop.java_email;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RestController
public class EmailController {

    private final JavaMailSender javaMailSender;

   public EmailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RequestMapping("/send-email")
    public String sendMail(){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("tabithamugide31@gmail.com");
            message.setTo("tabithamugide31@gmail.com");
            message.setSubject("simple text email from tabitha!");
            message.setText("Hello, i hope all is well");

            javaMailSender.send(message);
            return "success!";
        } catch (Exception e){
            return e.getMessage();
        }

    }

    @RequestMapping("/send-email-with-attachment")
    public String sendMailWithAttachment(){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("tabithamugide31@gmail.com");
            helper.setTo("tabithamugide31@gmail.com");
            helper.setSubject("Java email with attachment from tabitha!");
            helper.setText("Please receive the attached documents below");

            helper.addAttachment("Weekly report1.odt", new File("/home/mugide-tabitha/Documents/Weekly reports/Weekly report1.odt"));
            helper.addAttachment("Mugide National ID.pdf", new File("/home/mugide-tabitha/Documents/Mugide National ID.pdf"));

            javaMailSender.send(message);
            return "success!";
        } catch (Exception e){
            return e.getMessage();
        }

    }

    @RequestMapping("/send-html-email")
    public String sendHtmlEmail(){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("tabithamugide31@gmail.com");
            helper.setTo("tabithamugide31@gmail.com");
            helper.setSubject("Java email with attachment from tabitha!");

            try(var inputStream = Objects.requireNonNull(EmailController.class.getResourceAsStream("/templates/email-profile.html"))) {
                helper.setText(
                        new String(inputStream.readAllBytes(), StandardCharsets.UTF_8),
                        true
                );
            }
            helper.addInline("passport.jpg", new File("/home/mugide-tabitha/Downloads/passport.jpg"));

           // helper.addAttachment("Weekly report1.odt", new File("/home/mugide-tabitha/Documents/Weekly reports/Weekly report1.odt"));
           // helper.addAttachment("Mugide National ID.pdf", new File("/home/mugide-tabitha/Documents/Mugide National ID.pdf"));

            javaMailSender.send(message);
            return "success!";
        } catch (Exception e){
            return e.getMessage();
        }

    }
}
