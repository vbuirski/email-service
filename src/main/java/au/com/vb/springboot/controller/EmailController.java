package au.com.vb.springboot.controller;

import au.com.vb.springboot.model.Email;
import au.com.vb.springboot.model.EmailResponse;
import au.com.vb.springboot.service.MailGunEmailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

  @PostMapping(value = "/send-email",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public EmailResponse sendEmail(@RequestBody Email email) {

    MailGunEmailServiceImpl mailGunEmailServiceImpl = new MailGunEmailServiceImpl();

    return mailGunEmailServiceImpl.sendEmail(email);

  }
}
