package au.com.vb.springboot.controller;

import au.com.vb.springboot.config.EmailSender;
import au.com.vb.springboot.exception.EmailException;
import au.com.vb.springboot.model.Email;
import au.com.vb.springboot.model.EmailResponse;
import au.com.vb.springboot.service.EmailService;
import au.com.vb.springboot.service.MailGunEmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

  private static final Logger log = LoggerFactory.getLogger(EmailController.class);

  @Autowired
  @Qualifier("primary")
  private EmailService primary;

  @Autowired
  @Qualifier("secondary")
  private EmailService secondary;

  @PostMapping(value = "/send-email",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public EmailResponse sendEmail(@RequestBody Email email) {

    EmailResponse response = null;
    try {
       response = primary.send(email);
    } catch (EmailException e1) {
      log.error("Unable to send first email attempt " + e1.getMessage());
      try {
        response = secondary.send(email);
      } catch (EmailException e2) {
        log.error("Unable to send second email attempt " + e2.getMessage());
      }
    }
    return response;
  }
}
