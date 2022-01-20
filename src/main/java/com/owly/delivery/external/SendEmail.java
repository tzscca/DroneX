package com.owly.delivery.external;

import com.sendgrid.Method;
import com.sendgrid.Response;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SendEmail {
    public static void sendEmail(String toEmailAddress, String subject, String emailContent) throws IOException {
        /*
        Usage example: SendEmail.sendEmail("someone@gmail.com", "Tracking# xxxxxxxxx", "Your package has shipped")
        Note: this api is to send notification emails to package sender/receiver

        0. config.properties file contains KEY, thus gitignored. DM WM for help
        1. input per function signature
        2. pass in API_KEY from config.properties
        3. current email sending service is provided by SendGrid (free tier), there is limited number of emails can be
        sent daily/monthly. Be cautious
         */
        Properties prop = new Properties();
        String propFileName = "config.properties";
        InputStream inputStream = SendEmail.class.getClassLoader().getResourceAsStream(propFileName);
        prop.load(inputStream);
        String API_KEY = prop.getProperty("API_KEY");

        Email from = new Email("owly.service@outlook.com");
        Email to = new Email(toEmailAddress);
        Content content = new Content("text/plain", emailContent);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
