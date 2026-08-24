package com.servicecop.java_email;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    // Inject JavaMailSender via constructor
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // Cron Expression: "0 0 8 ? * MON" = Runs automatically every Monday at 8:00 AM
    @Scheduled(cron = "0/10 * * * * ?")
    public void sendMailWithAttachmentScheduled() {
        System.out.println("Starting scheduled email transmission task...");
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("tabithamugide31@gmail.com");
            helper.setTo("tabithamugide31@gmail.com");
            helper.setSubject("Java email with attachment from tabitha!");
            helper.setText("Please receive the attached documents below");

            // Attaching the absolute local system files
            helper.addAttachment("Weekly report1.odt", new File("/home/mugide-tabitha/Documents/Weekly reports/Weekly report1.odt"));
            helper.addAttachment("Mugide National ID.pdf", new File("/home/mugide-tabitha/Documents/Mugide National ID.pdf"));

            javaMailSender.send(message);
            System.out.println("Scheduled email sent successfully!");

        } catch (Exception e) {
            // Logs the error directly to the console since there is no web browser interface
            System.err.println("Failed to execute scheduled email job: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
