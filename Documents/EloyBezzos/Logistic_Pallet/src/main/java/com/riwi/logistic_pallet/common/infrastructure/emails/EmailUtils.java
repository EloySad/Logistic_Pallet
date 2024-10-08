package com.riwi.logistic_pallet.common.infrastructure.emails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import jakarta.mail.Address;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

  @Value("${spring.mail.username}")
  private String mailUsername;

  @Autowired
  private JavaMailSender javaMailSender;

  @Async
  public void sendHtmlEmail(String[] recipients, String subject, String htmlTemplate)
      throws MessagingException, RuntimeException {
    MimeMessage message = this.javaMailSender.createMimeMessage();
    Address[] addresses = this.convertToAddresses(recipients);

    message.setFrom(this.mailUsername);
    message.setRecipients(MimeMessage.RecipientType.TO, addresses);
    message.setSubject(subject);
    message.setContent(htmlTemplate, "text/html; charset=UTF-8");

    this.javaMailSender.send(message);
  }

  private Address[] convertToAddresses(String[] recipients) throws RuntimeException {
    return Arrays.stream(recipients).map(recipient -> {
      try {
        return new InternetAddress(recipient);
      } catch (AddressException e) {
        throw new RuntimeException(e);
      }
    }).toArray(Address[]::new);
  }
}