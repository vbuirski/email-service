package au.com.vb.springboot.config;

import au.com.vb.springboot.service.EmailService;
import au.com.vb.springboot.service.MailGunEmailServiceImpl;
import au.com.vb.springboot.service.SendGridEmailServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

public class EmailSender {

  @Bean
  @ConfigurationProperties(prefix = "first.email")
  public EmailService firstEmailSender() {
    return new MailGunEmailServiceImpl();
  }

  @Bean
  @ConfigurationProperties(prefix = "second.email")
  public EmailService secondEmailSender() {
    return new SendGridEmailServiceImpl();
  }


}
