package au.com.vb.springboot.controller;

import au.com.vb.springboot.model.Email;
import au.com.vb.springboot.model.EmailResponse;
import au.com.vb.springboot.service.impl.MailGunEmailServiceImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

  @Autowired
  private MailGunEmailServiceImpl emailService;

  @PostMapping(value = "/send-email",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public EmailResponse sendEmail(@RequestBody String emailRequest) {

    EmailResponse response;
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      Email email = objectMapper.readValue(emailRequest, Email.class);

      response = emailService.sendEmail(email);
    } catch (IOException e) {
      response = null;
    }
    return response;

  }
}
