package au.com.vb.springboot.controller;

import au.com.vb.springboot.model.Email;
import au.com.vb.springboot.model.EmailResponse;
import au.com.vb.springboot.service.impl.MailGunEmailServiceImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

  @Autowired
  private MailGunEmailServiceImpl emailService;

  @PostMapping("/send-email")
  @ResponseStatus(HttpStatus.CREATED)
  public EmailResponse sendEmail(@RequestBody Email email, HttpServletRequest request) {
    EmailResponse response = emailService.sendEmail(email);
    return response;
  }
}
